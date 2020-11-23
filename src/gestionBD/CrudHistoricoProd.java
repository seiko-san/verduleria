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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joako
 */
public class CrudHistoricoProd {

    public boolean ingresarHistorico(int id_producto, double precioProd,
            String fecha) {

        Connection con = null;
        Statement stm;

        int resultUpdate = 0;
        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            resultUpdate = stm.executeUpdate("insert into historico_productos(id_producto,precio_producto_h,fecha_h) "
                    + "values(" + id_producto + "," + precioProd + ",'" + fecha + "')");

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

    public void mostrarProductos(JTable jtHistoricoProd) {
        Connection con = null;
        Statement stm;
        ResultSet rs;

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("ID producto");
        modelo.addColumn("Precio Producto");
        modelo.addColumn("Fecha");

        String[] productos = new String[4];

        jtHistoricoProd.setModel(modelo);

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery("Select codigo_historico_p ,historico_productos.id_producto , precio_producto_h , "
                    + "fecha_h  from historico_productos ");

            while (rs.next()) {
                productos[0] = rs.getString("codigo_historico_p");
                productos[1] = rs.getString("id_producto");
                productos[2] = rs.getString("precio_producto_h");
                productos[3] = rs.getString("fecha_h");

                modelo.addRow(productos);
            }
            jtHistoricoProd.setModel(modelo);
            Conexion.cerrar();
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

    }

    
}
