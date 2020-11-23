/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vistas.Principal;

/**
 *
 * @author joako
 * @collaborator seiko
 */
public class BusquedaVentas {

    public void llenarCombobox(JComboBox cbxSucursal) {

        Connection con = null;
        Statement stm;
        ResultSet rs;

        cbxSucursal.addItem("Seleccione una sucursal");
        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery("Select nombre_sucursal from sucursales");

            while (rs.next()) {
                cbxSucursal.addItem(rs.getString("nombre_sucursal"));

            }
            Conexion.cerrar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e.getMessage());
        }

    }

    public void ConsultasVentasTiendas(String Sucursal, JTable jtventastiendas) {

        Connection con = null;
        Statement stm;
        ResultSet rs;

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre Producto");
        modelo.addColumn("Cantidad Venta");
        modelo.addColumn("Precio Unitario");
        modelo.addColumn("Precio Total");

        String[] MayorVentaFecha = new String[6];

        jtventastiendas.setModel(modelo);

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery("select  productos.nombre_producto, \n"
                    + "descuentos.descuento, SUM(detalle_venta.cantidad), detalle_venta.total,  SUM(detalle_venta.total),\n"
                    + "vendedores.nombre_vendedor,  clientes.nombre_cliente,\n"
                    + "ventas.fecha_venta, ventas.hora_venta, sucursales.nombre_sucursal\n"
                    + "FROM detalle_venta\n"
                    + "JOIN ventas ON detalle_venta.codigo_venta = ventas.codigo_venta\n"
                    + "JOIN Clientes ON ventas.id_cliente = Clientes.id_cliente\n"
                    + "JOIN Vendedores ON ventas.codigo_vendedor = Vendedores.codigo_vendedor\n"
                    + "join sucursales ON  vendedores.codigo_sucursal = sucursales.codigo_sucursal\n"
                    + "JOIN Descuentos ON detalle_venta.codigo_descuento = Descuentos.codigo_descuento\n"
                    + "JOIN Productos ON detalle_venta.id_producto = Productos.id_producto\n"
                    + "where sucursales.nombre_sucursal = '"+ Sucursal +"' GROUP BY Productos.nombre_producto;");

            while (rs.next()) {

                MayorVentaFecha[0] = rs.getString("nombre_producto");
                MayorVentaFecha[1] = rs.getString("SUM(detalle_venta.cantidad)");
                MayorVentaFecha[2] = rs.getString("total");
                MayorVentaFecha[3] = rs.getString("SUM(detalle_venta.total)");

                modelo.addRow(MayorVentaFecha);
            }
            jtventastiendas.setModel(modelo);
            Conexion.cerrar();
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

}
