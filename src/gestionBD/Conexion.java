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

    public static String user = "root";
    public static String pass = "";
    public static String nombreBD = "verduleria";
    public static String driver = "com.mysql.jdbc.Driver";
    public static String url = "jdbc:mysql://localhost:81/" + nombreBD;
    public static Connection conexion; 

    public static Connection conectar() {

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

    public static void cerrar() {

        try {
            if (conexion != null) {
                conexion.close();
            }

        } catch (Exception e) {
            System.out.println("Error : no se logro cerrar la base de datos " + e);
        }

    }

}
