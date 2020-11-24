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
public class CrudVentas {
    public boolean ingresarProductos( int codigoVendedor, int  idCliente,
            String fecha, String hora) {

        Connection con = null;
        Statement stm;

        int resultUpdate = 0;
        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            resultUpdate = stm.executeUpdate("insert into ventas(codigo_vendedor , "
                    + "id_cliente , fecha_venta , hora_venta) values("+codigoVendedor+","+idCliente+",'"+fecha+"','"+hora+"')");

            if (resultUpdate != 0) {
                Conexion.cerrar();
                JOptionPane.showMessageDialog(null, "La compra se realizo exito");
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
