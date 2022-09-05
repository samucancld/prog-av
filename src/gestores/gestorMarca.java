package gestores;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.conexion;
import modelos.marca;
import vistas.vistaMarca;

public class gestorMarca {
    
    public void agregarMarca () {
        vistaMarca objeto = new vistaMarca();
        objeto.viewGuardar();
    }
    
    //Metodo para mostrar los datos de la tabla de la base de datos
    
    public void mostrarMarca (JTable paramTablaMarcas){
    
        conexion objetoConexion = new conexion();
        DefaultTableModel modelo = new DefaultTableModel();

        String sql="";
        modelo.addColumn("ID");
        modelo.addColumn("Marcas");
        modelo.addColumn("Observacion");

        paramTablaMarcas.setModel(modelo);
        
        //query
        
        sql="select * from marca;";

        String [] datos = new String[3];
        Statement st;

            try {
                st=objetoConexion.establecerConexion().createStatement();

                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    datos[0]=rs.getString(1);
                    datos[1]=rs.getString(2);
                    datos[2]=rs.getString(3);

                    modelo.addRow(datos);

                }

                paramTablaMarcas.setModel(modelo);

            }   catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error:" + e.toString());
            }
    }
    
    //Metodo para agregar una marca en la db
    
    public void guardarMarca(JTextField paramTablaMarcas, JTextArea paramDescrip){
        marca objetoMarca = new marca();
        objetoMarca.setNombre(paramTablaMarcas.getText());
        objetoMarca.setObservacion(paramDescrip.getText());
        
        conexion objetoConexion = new conexion();
        
        String consulta = "insert into marca (nombre, observacion) values (?, ?);";
        
        try {
            
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, objetoMarca.getNombre());
            cs.setString(2, objetoMarca.getObservacion());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se agrego correctamente.");
            
        }   catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error:"+ e.toString());
  
        }
        

    }
    
    //Metodo para seleccionar una fila en la tabla de la db
    
    public void seleccionarMarca(JTable paramTablaMarcas, JTextField paramCodigo, JTextField paramNombre, JTextArea paramDescrip){
            
        try {

           int fila =paramTablaMarcas.getSelectedRow();
           
           if (fila >= 0) {
               paramCodigo.setText(paramTablaMarcas.getValueAt(fila, 0).toString());
               paramNombre.setText(paramTablaMarcas.getValueAt(fila, 1).toString());
               paramDescrip.setText(paramTablaMarcas.getValueAt(fila,2).toString());
           }
           else {
               JOptionPane.showMessageDialog(null, "Fila no seleccionada");
           }
            
        }   catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error:"+ e.toString());

        } 
    }
    
    //Metodo para modificar un dato ya cargado en la db
    
    public void modificarMarca(JTextField paramCodigo, JTextField paramNombre, JTextArea paramObs){
        marca objetoMarca = new marca();
        
        objetoMarca.setId(Integer.parseInt(paramCodigo.getText()));
        objetoMarca.setNombre(paramNombre.getText());
        objetoMarca.setObservacion(paramObs.getText());
        
        conexion objetoConexion = new conexion();
        
        String consulta = "UPDATE marca SET nombre=?, observacion=? where marca.id=?;";
        
        try {
            
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, objetoMarca.getNombre());
            cs.setString(2, objetoMarca.getObservacion());
            cs.setInt(3, objetoMarca.getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se modifico correctamente.");
            
        }   catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error:"+ e.toString());
  
        }
    }
    
    //Metodo para eliminar un dato
    
    public void eliminarMarca(JTextField paramCodigo){
        marca objetoMarca = new marca();
        objetoMarca.setId(Integer.parseInt(paramCodigo.getText()));
        
        conexion objetoConexion = new conexion();
        
        String consulta = "DELETE FROM marca WHERE marca.id=?;";
        
        try {
            
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setInt(1, objetoMarca.getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimino correctamente.");
            
        }   catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error:"+ e.toString());
  
        }
    }
    
  
}

