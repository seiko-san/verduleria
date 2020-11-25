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
import vistas.Principal;

/**
 *
 * @author joako
 * @collaborator seiko
 */
public class IngresoLogin {
    public String nombre = "";
    public void llenarCombobox(JComboBox cbxSucursal) {
        try{
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

    }catch(Exception e){
        JOptionPane.showMessageDialog(null,"Debe conectar su base de datos","Error" ,JOptionPane.WARNING_MESSAGE);
    }}

    public int validarIngreso(String usuario, String sucursal) {

        Connection con = null;
        Statement stm;
        ResultSet rs;
        Principal p = new Principal();
        int resultado = 0;
        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT  codigo_vendedor , nombre_vendedor , sucursales.codigo_sucursal , nombre_sucursal FROM sucursales JOIN "
                    + "vendedores ON  vendedores.codigo_sucursal = sucursales.codigo_sucursal WHERE codigo_vendedor = '" + usuario + "' and nombre_sucursal='" + sucursal + "'");

            if (rs.next()) {
                
                resultado = 1;
                nombre= rs.getString(2);
                
            }
          
            

            Conexion.cerrar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e.getMessage());
        }
        return resultado;
    }

}
