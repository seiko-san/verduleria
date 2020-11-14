package gestionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import gestionBD.Conexion;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextField;

/**
 *
 * @author seiko
 */
public class CRUD {

    public static Conexion coneccion = new Conexion();

    public static PreparedStatement ps;
    public static ResultSet rs;
    public static String sql;
    public static int result_num = 0;
    String busq_vendedor = null;
    Connection conection = null;
    Statement sentencia = null;

    public String Busq_vendedor(String nick, String clave, JTextField txtnick, JTextField txtclave) {

        try {

            Class.forName(coneccion.driver);
            coneccion = (Conexion) DriverManager.getConnection(coneccion.url, coneccion.user, coneccion.pass);

            sentencia = conection.createStatement();
            String sql = ("select nick_vendedor,clave_vendedor from vendedor where = nick_vendedor = '"
                    + nick + "' && clave_vendedor= '" + clave + "'");

            sentencia.executeUpdate(sql);
            System.out.print("encontrado");
            sentencia.close();
            conection.close();

             
            
        } catch (Exception e) {
            System.err.print("Error: " + e.getMessage());
        }     
        return null;

    }

    public String Busq_nom(String nick) {
        String busqueda = null;
        Connection conection = null;

        try {
            conection = coneccion.conectar();
            String busq = ("select nombre_vendedor,apellido_vendedor from vendedor where = nick_vendedor = '" + nick + "'");
            ps = conection.prepareStatement(busq);
            rs = ps.executeQuery();

            if (rs.next()) {

                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                busqueda = (nombre + "" + apellido);
            }
            conection.close();
        } catch (Exception e) {

        }
        return busqueda;

    }
}
