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
public class MostrarRangoFecha {

    public void ConsultaProductoFecha(String fecha_inicio, String fecha_final, JTable jtmostrar_productos) {

        Connection con = null;
        Statement stm;
        ResultSet rs;

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Cliente");
        modelo.addColumn("Rut Cliente");
//        modelo.addColumn("Nombre Cliente");
//        modelo.addColumn("Correo Cliente");
//        modelo.addColumn("Telefono Cliente");
//        modelo.addColumn("Direccion Cliente");

        String[] MayorVentaFecha = new String[6];

        
        jtmostrar_productos.setModel(modelo);

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery("select detalle_venta.codigo_detalle_venta, productos.nombre_producto, \n" +
"descuentos.descuento, detalle_venta.cantidad,  detalle_venta.total,\n" +
"vendedores.nombre_vendedor,  clientes.nombre_cliente,\n" +
"ventas.fecha_venta, ventas.hora_venta, sucursales.nombre_sucursal\n" +
"FROM detalle_venta\n" +
"JOIN ventas ON detalle_venta.codigo_venta = ventas.codigo_venta\n" +
"JOIN Clientes ON ventas.id_cliente = Clientes.id_cliente\n" +
"JOIN Vendedores ON ventas.codigo_vendedor = Vendedores.codigo_vendedor\n" +
"join sucursales ON  vendedores.codigo_sucursal = sucursales.codigo_sucursal\n" +
"JOIN Descuentos ON detalle_venta.codigo_descuento = Descuentos.codigo_descuento\n" +
"JOIN Productos ON detalle_venta.id_producto = Productos.id_producto\n" +
"where ventas.fecha_venta  BETWEEN  '"+ fecha_inicio +"' and '"+ fecha_final +"'");

            while (rs.next()) {
                
                MayorVentaFecha[0] = rs.getString("codigo_detalle_venta");
                MayorVentaFecha[1] = rs.getString("nombre_producto");
//                MayorVentaFecha[2] = rs.getString("nombre_cliente");
//                MayorVentaFecha[3] = rs.getString("correo_cliente");
//                MayorVentaFecha[4] = rs.getString("telefono_cliente");
//                MayorVentaFecha[5] = rs.getString("direccion_cliente");

                modelo.addRow(MayorVentaFecha);
            }
            jtmostrar_productos.setModel(modelo);
            Conexion.cerrar();
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

//    public void mostrarCliente(JTable jtaddcliente) {
//        Connection con = null;
//        Statement stm;
//        ResultSet rs;
//
//        DefaultTableModel modelo = new DefaultTableModel();
//        modelo.addColumn("ID Cliente");
//        modelo.addColumn("Rut Cliente");
//        modelo.addColumn("Nombre Cliente");
//        modelo.addColumn("Correo Cliente");
//        modelo.addColumn("Telefono Cliente");
//        modelo.addColumn("Direccion Cliente");
//
//        String[] clientes = new String[6];
//
//        
//        jtaddcliente.setModel(modelo);
//
//        try {
//            con = Conexion.conectar();
//            stm = con.createStatement();
//            rs = stm.executeQuery("Select id_cliente, rut_cliente , nombre_cliente , "
//                    + "correo_cliente , telefono_cliente ,direccion_cliente from Clientes");
//
//            while (rs.next()) {
//                
//                clientes[0] = rs.getString("id_cliente");
//                clientes[1] = rs.getString("rut_cliente");
//                clientes[2] = rs.getString("nombre_cliente");
//                clientes[3] = rs.getString("correo_cliente");
//                clientes[4] = rs.getString("telefono_cliente");
//                clientes[5] = rs.getString("direccion_cliente");
//
//                modelo.addRow(clientes);
//            }
//            jtaddcliente.setModel(modelo);
//            Conexion.cerrar();
//        } catch (SQLException e) {
//            System.out.println("Error" + e.getMessage());
//        }
//
//    }
//
//    public boolean eliminarCliente(String id) {
//        Connection con = null;
//        Statement stm;
//
//        int resultUpdate = 0;
//        try {
//            con = Conexion.conectar();
//            stm = con.createStatement();
//            resultUpdate = stm.executeUpdate("Delete from Clientes where id_cliente = '" + id + "'");
//
//            if (resultUpdate != 0) {
//                Conexion.cerrar();
//                JOptionPane.showMessageDialog(null, "El Cliente se a eliminado con exito");
//                return true;
//            } else {
//                return false;
//            }
//        } catch (HeadlessException | SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error en la base de datos ");
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean modificarCliente(int id, String rut, String nombre, String correo,
//            String telefono, String direccion) {
//        
//        Connection con = null;
//        Statement stm;
//        ResultSet rs;
//   
//        int resultUpdate = 0;
//
//        try {
//            con = Conexion.conectar();
//            stm = con.createStatement();
//            
//            
//                
//            JOptionPane.showMessageDialog(null, "Debes rellenar datos!");
//            
//            
//            resultUpdate = stm.executeUpdate("update Clientes set rut_cliente= '"+rut+"', nombre_cliente = '"+nombre+"', correo_cliente ='"+correo+"', telefono_cliente = '"+telefono+"', direccion_cliente= '"+direccion+"' where id_cliente = '"+id+"'");
//            if (resultUpdate != 0) {
//                Conexion.cerrar();
//                JOptionPane.showMessageDialog(null, "El Cliente se modifico correctamente");
//                return true;
//            } else {
//
//                return false;
//            }
//        } catch (Exception e) {
//
//            JOptionPane.showMessageDialog(null, "Error en la base de datos ");
//            e.printStackTrace();
//            return false;
//
//        }
//        
//        
//
//    }
    
     

}
