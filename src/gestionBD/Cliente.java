package gestionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import vistas.Principal;


/**
 *
 * @author Law
 */
public class Cliente {
    
    public void ConsultaCliente(String rut_cliente, JTextField nomnbre_cliente){
        
        Connection con = null;
        Statement stm;
        ResultSet rs;
        int resultado = 0;

        
        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * From Clientes where  rut_cliente = '" + rut_cliente + "'");

            if(rut_cliente.isEmpty()){
                JOptionPane.showMessageDialog(null, "Debes rellenar datos!");
            }else{
                

            if (rs.next()) {
                //resultado = 1;
                rs.getString(2);
                nomnbre_cliente.setText(rs.getString(2)); 
                System.out.println(rs);
            }else{
                //JOptionPane.showMessageDialog(null, "No se encontro datos!");
                int opcion = JOptionPane.showConfirmDialog(null,"No se encontraron referencias\n ¿Desea crear el cliente?", "Consulta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                System.out.println(opcion);
                
                if(opcion == 0){
                    Principal p = new Principal();
                  //  p.panel_productos.setSelectedIndex(0);
                    p.setVisible(false);
                    //p.panel_productos.setSelectedIndex(1);
                    p.setVisible(true);
                }

            }
           }
            Conexion.cerrar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e.getMessage());
        }
    }
    
//    public void AgregarCliente(){
//        
//        String rut="";
//        String nombre="";
//        String correo="";
//        String telefono="";
//        String direccion="";
//        
//        String rut_cliente = JOptionPane.showInputDialog(null, "Rut Cliente " + rut);
//        String nombre_cliente = JOptionPane.showInputDialog(null, " Nombre Cliente " + nombre);
//        String correo_cliente = JOptionPane.showInputDialog(null, " Correo Cliente " + correo);
//        String telefono_cliente = JOptionPane.showInputDialog(null, " Telefono Cliente " + telefono);
//        String direccion_cliente = JOptionPane.showInputDialog(null, " Direccion Cliente" + direccion);
//        
//        System.out.println(rut_cliente + " " + nombre_cliente + " " + " " + correo_cliente + " " + telefono_cliente + " " + direccion_cliente);
//        
//    }
    
}
