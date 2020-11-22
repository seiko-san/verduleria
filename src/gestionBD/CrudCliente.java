/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionBD;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joako
 */
public class CrudCliente {

    public boolean ingresarCliente(String rut, String nombre, String correo,
            String telefono, String direccion) {

        Connection con = null;
        Statement stm;

        int resultUpdate = 0;
        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            resultUpdate = stm.executeUpdate("insert into Clientes(rut_cliente , "
                    + "nombre_cliente , correo_cliente , telefono_cliente, "
                    + "direccion_cliente) values('" + rut + "','" + nombre + "','" + correo + "','" + telefono + "','" + direccion +"')");

            if (resultUpdate != 0) {
                Conexion.cerrar();
                JOptionPane.showMessageDialog(null, "El Cliente se a ingresado con exito");
                return true;
            } else {
                return false;
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos ");
            e.printStackTrace();
            return false;
        }

    }

    public void mostrarCliente(JTable jtaddcliente) {
        Connection con = null;
        Statement stm;
        ResultSet rs;

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Cliente");
        modelo.addColumn("Rut Cliente");
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Correo Cliente");
        modelo.addColumn("Telefono Cliente");
        modelo.addColumn("Direccion Cliente");

        String[] clientes = new String[6];

        
        jtaddcliente.setModel(modelo);

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery("Select id_cliente, rut_cliente , nombre_cliente , "
                    + "correo_cliente , telefono_cliente ,direccion_cliente from Clientes");

            while (rs.next()) {
                
                clientes[0] = rs.getString("id_cliente");
                clientes[1] = rs.getString("rut_cliente");
                clientes[2] = rs.getString("nombre_cliente");
                clientes[3] = rs.getString("correo_cliente");
                clientes[4] = rs.getString("telefono_cliente");
                clientes[5] = rs.getString("direccion_cliente");

                modelo.addRow(clientes);
            }
            jtaddcliente.setModel(modelo);
            Conexion.cerrar();
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

    }

    public boolean eliminarCliente(String id) {
        Connection con = null;
        Statement stm;

        int resultUpdate = 0;
        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            resultUpdate = stm.executeUpdate("Delete from Clientes where id_cliente = '" + id + "'");

            if (resultUpdate != 0) {
                Conexion.cerrar();
                JOptionPane.showMessageDialog(null, "El Cliente se a eliminado con exito");
                return true;
            } else {
                return false;
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificarCliente(int id, String rut, String nombre, String correo,
            String telefono, String direccion) {
        
        Connection con = null;
        Statement stm;
        ResultSet rs;
   
        int resultUpdate = 0;

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            
            
                
            JOptionPane.showMessageDialog(null, "Debes rellenar datos!");
            
            
            resultUpdate = stm.executeUpdate("update Clientes set rut_cliente= '"+rut+"', nombre_cliente = '"+nombre+"', correo_cliente ='"+correo+"', telefono_cliente = '"+telefono+"', direccion_cliente= '"+direccion+"' where id_cliente = '"+id+"'");
            if (resultUpdate != 0) {
                Conexion.cerrar();
                JOptionPane.showMessageDialog(null, "El Cliente se modifico correctamente");
                return true;
            } else {

                return false;
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error en la base de datos ");
            e.printStackTrace();
            return false;

        }
        
        

    }
    
     

}
