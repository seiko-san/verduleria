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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author seiko
 */
public class listas {

    public void mostrarventas(JTable jventas) {
        Connection con = null;
        Statement stm;
        ResultSet rs;

        DefaultTableModel modeloventas = new DefaultTableModel();

        modeloventas.addColumn("cod de venta");
        modeloventas.addColumn("id producto");
        modeloventas.addColumn("codigo descuento");
        modeloventas.addColumn("fecha");
        modeloventas.addColumn("cantidad");
        modeloventas.addColumn("total");
        modeloventas.addColumn("cantidad");
        modeloventas.addColumn("cod venta");
        modeloventas.addColumn("cod vendedor");
        modeloventas.addColumn("rut cliente");
        modeloventas.addColumn("fecha");
        modeloventas.addColumn("hora");

        String[] ventas = new String[12];

        jventas.setModel(modeloventas);

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT detalle_venta.codigo_detalle_venta,"
                    + " detalle_venta.id_producto, detalle_venta.codigo_descuento, "
                    + "detalle_venta.cantidad,  detalle_venta.total,  "
                    + "ventas.codigo_venta,  ventas.codigo_vendedor,  "
                    + "ventas.rut_cliente, ventas.fecha_venta,   ventas.hora_venta "
                    + "FROM detalle_venta JOIN    "
                    + "ventas ON detalle_venta.codigo_venta = ventas.codigo_venta");

            while (rs.next()) {

                ventas[0] = rs.getString("sku_producto");
                ventas[1] = rs.getString("codigobarra_producto");
                ventas[2] = rs.getString("nombre_producto");
                ventas[3] = rs.getString("descripcion_producto");
                ventas[4] = rs.getString("precio_neto");
                ventas[5] = rs.getString("precio_iva");
                ventas[6] = rs.getString("codigo_promocion");

                modeloventas.addRow(ventas);
            }
            jventas.setModel(modeloventas);
            Conexion.cerrar();
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

    }

}
