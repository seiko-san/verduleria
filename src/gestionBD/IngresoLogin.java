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

/**
 *
 * @author joako
 */
public class IngresoLogin {

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

    public int validarIngreso(String usuario, String sucursal) {

        Connection con = null;
        Statement stm;
        ResultSet rs;

        int resultado = 0;
        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT  codigo_vendedor , nombre_vendedor , sucursales.codigo_sucursal , nombre_sucursal FROM sucursales JOIN "
                    + "vendedores ON  vendedores.codigo_sucursal = sucursales.codigo_sucursal WHERE nombre_vendedor = '" + usuario + "' and nombre_sucursal='" + sucursal + "'");

            if (rs.next()) {

                resultado = 1;
            }

           

            Conexion.cerrar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e.getMessage());
        }
        return resultado;
    }

}
