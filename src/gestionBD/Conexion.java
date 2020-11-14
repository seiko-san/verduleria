package gestionBD;

/**
 *
 * @author seiko
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextField;


public class Conexion {

      String user = "root";
      String pass = "";
      String nombreBD = "verduleria";
      String driver = "com.mysql.jdbc.Driver";
      String url = "jdbc:mysql://localhost:3306/" + nombreBD;

    public Connection conectar() {
        Connection conexion = null;
        Statement sentencia = null;

        try {

            Class.forName(driver);
            conexion = DriverManager.getConnection(url, user, pass);
            
            System.out.print("conectados a la BD!");
            conexion.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.print("Error: " + e.getMessage());
        }
        return conexion;
    }

}
