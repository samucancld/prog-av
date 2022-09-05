package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class conexion {
    Connection conectar = null;
    
    String user = "postgres";
    String password = "root";
    String db = "agencia";
    String ip = "localhost";
    String port = "5432";
    
    String cadena = "jdbc:postgresql://"+ip+":"+port+"/"+db;
    
    public Connection establecerConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            conectar = DriverManager.getConnection(cadena, user, password);
            // JOptionPane.showMessageDialog(null, "La conexion con la base de datos ha sido exitosa.");
            
        }   catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos."+e.toString());
        }
        
        return conectar;
      }
}
