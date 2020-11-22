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
public class CrudProductos {

    public boolean ingresarProductos(String sku, String codigo, String nombre,
            String descripcion, double precio, int codpromo , double iva) {

        Connection con = null;
        Statement stm;

        int resultUpdate = 0;
        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            resultUpdate = stm.executeUpdate("insert into productos(sku_producto , "
                    + "codigobarra_producto , nombre_producto , descripcion_producto, "
                    + "precio_neto ,precio_iva,codigo_promocion) values('" + sku + "','" + codigo + "',"
                    + "'" + nombre + "','" + descripcion + "'," + precio + ","+iva+"," + codpromo + ")");

            if (resultUpdate != 0) {
                Conexion.cerrar();
                JOptionPane.showMessageDialog(null, "El producto se a ingresado con exito");
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

    public void mostrarProductos(JTable jtProductos) {
        Connection con = null;
        Statement stm;
        ResultSet rs;

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("SKU");
        modelo.addColumn("Codigo de Barra ");
        modelo.addColumn("Nombre Producto");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Precio");
        modelo.addColumn("IVA");
        modelo.addColumn("Codigo Promocion");

        String[] productos = new String[7];

        jtProductos.setModel(modelo);

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery("Select sku_producto , codigobarra_producto , "
                    + "nombre_producto , descripcion_producto ,precio_neto ,precio_iva, codigo_promocion from productos");

            while (rs.next()) {

                productos[0] = rs.getString("sku_producto");
                productos[1] = rs.getString("codigobarra_producto");
                productos[2] = rs.getString("nombre_producto");
                productos[3] = rs.getString("descripcion_producto");
                productos[4] = rs.getString("precio_neto");
                productos[5] = rs.getString("precio_iva");
                productos[6] = rs.getString("codigo_promocion");

                modelo.addRow(productos);
            }
            jtProductos.setModel(modelo);
            Conexion.cerrar();
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

    }

    public boolean eliminarProd(String sku) {
        Connection con = null;
        Statement stm;

        int resultUpdate = 0;
        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            resultUpdate = stm.executeUpdate("Delete from productos where sku_producto= " + sku + "");

            if (resultUpdate != 0) {
                Conexion.cerrar();
                JOptionPane.showMessageDialog(null, "El producto se a eliinado con exito");
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

    public boolean modificarProd(String sku , String codigo , String nombre , String descripcion , double precio , int codpromo, double iva) {
        Connection con = null;
        Statement stm;
        ResultSet rs;

        int resultUpdate = 0;

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            resultUpdate = stm.executeUpdate("update productos set sku_producto= "+sku+", codigobarra_producto = '"+codigo+"', nombre_producto ='"+nombre+"', descripcion_producto = '"+descripcion+"', precio_neto= "+precio+",precio_iva="+iva+" ,codigo_promocion ="+codpromo+" where sku_producto = "+sku+"");
            if (resultUpdate != 0) {
                Conexion.cerrar();
                JOptionPane.showMessageDialog(null, "El producto se modifico correctamente");
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
