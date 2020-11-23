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
import vistas.Principal;

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

        modeloventas.addColumn("folio");
        modeloventas.addColumn("producto");
        modeloventas.addColumn("descuento");
        modeloventas.addColumn("cantidad");
        modeloventas.addColumn("total");
        // modeloventas.addColumn("cod venta");
        modeloventas.addColumn("vendedor");
        modeloventas.addColumn("cliente");
        modeloventas.addColumn("fecha");
        modeloventas.addColumn("hora");
        modeloventas.addColumn("sucursal");

        String[] ventas = new String[10];

        jventas.setModel(modeloventas);

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery("select detalle_venta.codigo_detalle_venta, productos.nombre_producto, \n"
                    + "descuentos.descuento, detalle_venta.cantidad,  detalle_venta.total,  \n"
                    + "vendedores.nombre_vendedor,  clientes.nombre_cliente, \n"
                    + "ventas.fecha_venta, ventas.hora_venta, sucursales.nombre_sucursal\n"
                    + "FROM detalle_venta\n"
                    + "JOIN ventas ON detalle_venta.codigo_venta = ventas.codigo_venta\n"
                    + "JOIN Clientes ON ventas.id_cliente = Clientes.id_cliente\n"
                    + "JOIN Vendedores ON ventas.codigo_vendedor = Vendedores.codigo_vendedor \n"
                    + "join sucursales ON  vendedores.codigo_sucursal = sucursales.codigo_sucursal\n"
                    + "JOIN Descuentos ON detalle_venta.codigo_descuento = Descuentos.codigo_descuento\n"
                    + "JOIN Productos ON detalle_venta.id_producto = Productos.id_producto");

            while (rs.next()) {

                ventas[0] = rs.getString("detalle_venta.codigo_detalle_venta");
                ventas[1] = rs.getString("productos.nombre_producto");
                ventas[2] = rs.getString("descuentos.descuento");
                ventas[3] = rs.getString("detalle_venta.cantidad");
                ventas[4] = rs.getString("detalle_venta.total");
                ventas[5] = rs.getString("vendedores.nombre_vendedor");
                ventas[6] = rs.getString("clientes.nombre_cliente");
                ventas[7] = rs.getString("ventas.fecha_venta");
                ventas[8] = rs.getString("ventas.hora_venta");
                ventas[9] = rs.getString("sucursales.nombre_sucursal");

                modeloventas.addRow(ventas);
            }
            jventas.setModel(modeloventas);
            Conexion.cerrar();
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

    }

    public void mostrarclientes(JTable jclientes) {
        Connection con = null;
        Statement stm;
        ResultSet rs;

        DefaultTableModel modelocliente = new DefaultTableModel();

        modelocliente.addColumn("Rut Cliente");
        modelocliente.addColumn("Nombre");
        modelocliente.addColumn("correo");
        modelocliente.addColumn("Telefono");
        modelocliente.addColumn("Direccion");

        String[] clientes = new String[5];

        jclientes.setModel(modelocliente);

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM clientes");

            while (rs.next()) {

                clientes[0] = rs.getString("rut_cliente");
                clientes[1] = rs.getString("nombre_cliente");
                clientes[2] = rs.getString("correo_cliente");
                clientes[3] = rs.getString("telefono_cliente");
                clientes[4] = rs.getString("direccion_cliente");

                modelocliente.addRow(clientes);
            }
            jclientes.setModel(modelocliente);
            Conexion.cerrar();
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

    }

    public int agregaproductos(String producto, JTable jagrega) {
        Principal ver = new Principal();
        Connection con = null;
        Statement stm;
        ResultSet rs;

        DefaultTableModel modeloagrega = new DefaultTableModel();

        modeloagrega.addColumn("Sku producto");
        modeloagrega.addColumn("codigo de Barra");
        modeloagrega.addColumn("Producto");
        modeloagrega.addColumn("Descripcion");
        modeloagrega.addColumn("precio total");
//        modeloagrega.addColumn("cantidad");
        modeloagrega.addColumn("promocion");
        int resultado = 0;
        String[] productos = new String[6];

        jagrega.setModel(modeloagrega);
        
        
        
        try {
//            String valor  = (String) ver.jcant.getValue();
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT productos.sku_producto,productos.codigobarra_producto,\n"
                    + "productos.nombre_producto,productos.descripcion_producto,\n"
                    + "productos.precio_iva, promociones.nombre_promocion\n"
                    + " From productos join promociones on productos.codigo_promocion = promociones.codigo_promocion \n"
                    + " WHERE productos.codigobarra_producto = '"+producto+"'");

            while (rs.next()) {

                productos[0] = rs.getString("productos.sku_producto");
                productos[1] = rs.getString("productos.codigobarra_producto");
                productos[2] = rs.getString("productos.nombre_producto");
                productos[3] = rs.getString("productos.descripcion_producto");
                productos[4] = rs.getString("productos.precio_iva");
//                productos[4] = valor;
                productos[5] = rs.getString("promociones.nombre_promocion");

                modeloagrega.addRow(productos);
                resultado = 1;
            }
            jagrega.setModel(modeloagrega);
            Conexion.cerrar();
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
        return resultado;
    }

}
