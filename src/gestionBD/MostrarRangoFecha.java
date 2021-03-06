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
        modelo.addColumn("Nombre Producto");
        modelo.addColumn("Cantidad Venta");
        modelo.addColumn("Fecha Venta");


        String[] MayorVentaFecha = new String[6];

        jtmostrar_productos.setModel(modelo);

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery("select SUM(detalle_venta.cantidad),detalle_venta.codigo_detalle_venta, productos.nombre_producto, \n"
                    + "descuentos.descuento, detalle_venta.cantidad,  detalle_venta.total,\n"
                    + "vendedores.nombre_vendedor,  clientes.nombre_cliente,\n"
                    + "ventas.fecha_venta, ventas.hora_venta, sucursales.nombre_sucursal\n"
                    + "FROM detalle_venta\n"
                    + "JOIN ventas ON detalle_venta.codigo_venta = ventas.codigo_venta\n"
                    + "JOIN Clientes ON ventas.id_cliente = Clientes.id_cliente\n"
                    + "JOIN Vendedores ON ventas.codigo_vendedor = Vendedores.codigo_vendedor\n"
                    + "join sucursales ON  vendedores.codigo_sucursal = sucursales.codigo_sucursal\n"
                    + "JOIN Descuentos ON detalle_venta.codigo_descuento = Descuentos.codigo_descuento\n"
                    + "JOIN Productos ON detalle_venta.id_producto = Productos.id_producto\n"
                    + "where ventas.fecha_venta  BETWEEN  '" + fecha_inicio + "' and '" + fecha_final + "' group by productos.nombre_producto desc");

            while (rs.next()) {

                MayorVentaFecha[0] = rs.getString("nombre_producto");
                MayorVentaFecha[1] = rs.getString("SUM(detalle_venta.cantidad)");
                MayorVentaFecha[2] = rs.getString("fecha_venta");

                modelo.addRow(MayorVentaFecha);
            }
            jtmostrar_productos.setModel(modelo);
            Conexion.cerrar();
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

}
