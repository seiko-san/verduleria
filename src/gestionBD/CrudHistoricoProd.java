/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionBD;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author joako
 */
public class CrudHistoricoProd {
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
}
