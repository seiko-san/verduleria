package gestionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


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
                    AgregarCliente();
                }

            }
           }
            Conexion.cerrar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e.getMessage());
        }
    }
    
    public void AgregarCliente(){
        
        String rut="";
        String nombre="";
        String correo="";
        int telefono=0;
        String direccion="";
        
        JOptionPane.showInputDialog(null, "Rut Cliente " + rut + " Nombre Cliente "
                + nombre + " Correo Cliente " + correo + " Telefono Cliente " + telefono + " Direccion Cliente" + direccion);
    }
    
}
