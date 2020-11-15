package gestionBD;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import gestionBD.Conexion;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import principal.Login;

/**
 *
 * @author seiko
 */
public class CRUD {

     Conexion coneccion = new Conexion();
//
//    public static PreparedStatement ps;
//    public static ResultSet rs;
//    public static String sql;
//    public static int result_num = 0;
//    String busq_vendedor = null;
//    Connection conection = null;
//    Statement sentencia = null;

    public int valida_ingreso(){
        Connection conect = null;
            int resultado = 0;

        try{
        String nick = Login.txtnick.getText();
        String clave = String.valueOf(Login.txtclave.getPassword());
        
        String sql = "select * from vendedor where nick_vendedor= '"+nick+"' and clave_vendedor='"+clave+"'";
        
        
        conect = coneccion.conectar();
                
        Statement st = conect.createStatement();
        ResultSet rs = st.executeQuery(sql);
        
        if(rs.next()){
            resultado =1;
        }
        
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Error de conexion"
                    ,"Mensaje de error",
                    JOptionPane.ERROR_MESSAGE);
        
    }finally{
            try{
            conect.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de desconexion"
                    ,"Mensaje de error",
                    JOptionPane.ERROR_MESSAGE);

            }
        }
    return resultado;
    
    
    
    }
    
} 
    
    
    
    
    
    
//    public String Busq_vendedor(String nick, String clave, JTextField txtnick, JTextField txtclave) {
//
//        try {
//
//            Class.forName(coneccion.driver);
//            coneccion = (Conexion) DriverManager.getConnection(coneccion.url, coneccion.user, coneccion.pass);
//
//            sentencia = conection.createStatement();
//            String sql = ("select nick_vendedor,clave_vendedor from vendedor where = nick_vendedor = '"
//                    + nick + "' && clave_vendedor= '" + clave + "'");
//
//            sentencia.executeUpdate(sql);
//            System.out.print("encontrado");
//            sentencia.close();
//            conection.close();
//
//             
//            
//        } catch (Exception e) {
//            System.err.print("Error: " + e.getMessage());
//        }     
//        return null;
//
//    }
//
//    public String Busq_nom(String nick) {
//        String busqueda = null;
//        Connection conection = null;
//
//        try {
//            conection = coneccion.conectar();
//            String busq = ("select nombre_vendedor,apellido_vendedor from vendedor where = nick_vendedor = '" + nick + "'");
//            ps = conection.prepareStatement(busq);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//
//                String nombre = rs.getString("nombre");
//                String apellido = rs.getString("apellido");
//                busqueda = (nombre + "" + apellido);
//            }
//            conection.close();
//        } catch (Exception e) {
//
//        }
//        return busqueda;
//
//    }

