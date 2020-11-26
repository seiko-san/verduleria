/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import gestionBD.Cliente;
import gestionBD.Conexion;
import gestionBD.CrudProductos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Productos;
import gestionBD.Conexion;
import gestionBD.CrudVentas;
import gestionBD.IngresoLogin;
import gestionBD.listas;
import java.text.DateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import listas.Clientes;
import listas.Ventas;
import listas.ProductosFecha;
import listas.PromocionesDesc;
import listas.VentasTiendas;

/**
 *
 * @author Seiko
 */
public class Principal extends javax.swing.JFrame implements Runnable {

    String hora, minutos, segundos;
    Thread hilo;
    Conexion cn = new Conexion();

    CrudProductos crudProd = new CrudProductos();
    Productos productos = new Productos();
    DefaultListModel modelo = new DefaultListModel();
    listas lista = new listas();

    int idVenta;
    int codDesc = 1;

    public Principal() {
        initComponents();
        //   this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        lista.llenarCombobox(cbxDesc);
        lblfecha.setText(fecha());
        hilo = new Thread(this);
        hilo.start();
        this.setLocationRelativeTo(null);
        llenarlista();
        this.setResizable(false);
        this.jagrega.setModel(modelo1);
        modelo1.addColumn("Id producto");
        modelo1.addColumn("Sku producto");
        modelo1.addColumn("codigo de Barra");
        modelo1.addColumn("Producto");
        modelo1.addColumn("Descripcion");
        modelo1.addColumn("precio total");
        modelo1.addColumn("cantidad");
        modelo1.addColumn("promocion");
        // setDefaultCloseOperation(0);
        txtIdcliente.setVisible(false);
        txtIdProd.setVisible(false);
        txtNombrePromo.setVisible(false);
        cbxDesc.setVisible(false);
        btnCalcular.setVisible(false);
        jcant.setValue(1);
    }

    DefaultTableModel modelo1 = new DefaultTableModel();

    public void hora() {
        Calendar calendario = new GregorianCalendar();
        Date horaactual = new Date();
        calendario.setTime(horaactual);
        hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }

    public void run() {
        Thread current = Thread.currentThread();

        while (current == hilo) {
            hora();
            lblhora.setText(hora + ":" + minutos + ":" + segundos);
        }
    }

    public void ConsultaId() {
        Connection con = null;
        Statement stm;
        ResultSet rs;
        int resultado = 0;

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT codigo_venta From ventas order by  codigo_venta desc limit 1");

            if (rs.next()) {
                idVenta = rs.getInt("codigo_venta") + 1;
            }
        } catch (Exception e) {
        }

    }

    public void ConsultaDesc(double descuento) {

        Connection con = null;
        Statement stm;
        ResultSet rs;

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT codigo_descuento From descuentos  where descuento=" + descuento + "");

            if (rs.next()) {
                codDesc = rs.getInt("codigo_descuento");
            }
        } catch (Exception e) {
        }

    }

    public void ConsultaCliente(String rut_cliente, JTextField nomnbre_cliente, JTextField id_cliente) {

        Connection con = null;
        Statement stm;
        ResultSet rs;
        int resultado = 0;

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * From Clientes where  rut_cliente = '" + rut_cliente + "'");

            if (rut_cliente.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debes rellenar datos!", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {

                if (rs.next()) {
                    id_cliente.setText(rs.getString(1));
                    nomnbre_cliente.setText(rs.getString(3));
                } else {
                    int opcion = JOptionPane.showConfirmDialog(null, "No se encontraron referencias\n ¿Desea crear el cliente?", "Consulta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(opcion);

                    if (opcion == 0) {

                        NuevoCliente ver = new NuevoCliente();
                        ver.setVisible(true);
                    }

                }
            }
            Conexion.cerrar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e.getMessage());
        }
    }

    public void ConsultaProducto(JTextField codigo_barra, JTextField sku_producto, JTextField nombre_producto, JTextField precio_final, JTextArea descripcion, JTextField nombrePromo, JTextField idprod) {

        Connection con = null;
        Statement stm;
        ResultSet rs;
        int resultado = 0;

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT sku_producto ,codigobarra_producto, "
                    + "nombre_producto , descripcion_producto , precio_iva , "
                    + "nombre_promocion,id_producto "
                    + "From productos join promociones on productos.codigo_promocion = promociones.codigo_promocion "
                    + "where  codigobarra_producto = '" + codigo_barra.getText() + "'");

            if (codigo_barra == null) {
                JOptionPane.showMessageDialog(null, "Debes rellenar datos!");
            } else {

                if (rs.next()) {    
                    sku_producto.setText(rs.getString(1));
                    nombre_producto.setText(rs.getString(3));
                    precio_final.setText(rs.getString(5));
                    descripcion.setText(rs.getString(3));
                    nombrePromo.setText(rs.getString(6));
                    idprod.setText(rs.getString(7));
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontraron referencias", "Advertencia", JOptionPane.WARNING_MESSAGE);

                }
            }
            Conexion.cerrar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblvendedor = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblsucursal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblfecha = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblhora = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblcodigo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtsku = new javax.swing.JTextField();
        btnbuscaproducto = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtnomproducto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtprecio = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnagregap = new javax.swing.JButton();
        jcant = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        txtcodigobarra = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtdescripcion = new javax.swing.JTextArea();
        txtNombrePromo = new javax.swing.JTextField();
        txtIdProd = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtrutcliente = new javax.swing.JTextField();
        btnbuscacliente = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtnombrecliente = new javax.swing.JTextField();
        btnlimpiar = new javax.swing.JButton();
        txtIdcliente = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jagrega = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        btnGenerarVenta = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtPrecFinal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtDesc = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTotalSinDesc = new javax.swing.JTextField();
        cbxDesc = new javax.swing.JComboBox<>();
        btnCalcular = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuAgregar = new javax.swing.JMenu();
        menuCliente = new javax.swing.JMenu();
        menuProducto = new javax.swing.JMenu();
        menuListar = new javax.swing.JMenu();
        menuVentas = new javax.swing.JMenu();
        menuclientes = new javax.swing.JMenu();
        menufecha = new javax.swing.JMenu();
        menusucursal = new javax.swing.JMenu();
        menuPromos = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel1.setText("Bienvenido(a)");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel2.setText("Vendedor(a):");

        lblvendedor.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        lblvendedor.setText("jLabel3");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel4.setText("Sucursal:");

        lblsucursal.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        lblsucursal.setText("jLabel5");

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel6.setText("Fecha:");

        lblfecha.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        lblfecha.setText("jLabel7");

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel8.setText("Hora:");

        lblhora.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        lblhora.setText("jLabel9");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel3.setText("Codigo:");

        lblcodigo.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        lblcodigo.setText("jLabel5");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblvendedor, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblhora, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblcodigo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblfecha, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(74, 74, 74))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lblsucursal)
                        .addGap(20, 20, 20))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblvendedor))
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblfecha))
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblhora))
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblsucursal))
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblcodigo))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel9.setText("SKU Producto:");

        txtsku.setEditable(false);
        txtsku.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N

        btnbuscaproducto.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        btnbuscaproducto.setText("Buscar");
        btnbuscaproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscaproductoActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel10.setText("Nombre Producto:");

        txtnomproducto.setEditable(false);
        txtnomproducto.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        txtnomproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnomproductoActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel11.setText("Precio Final:");

        txtprecio.setEditable(false);
        txtprecio.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel12.setText("Cantidad:");

        btnagregap.setBackground(new java.awt.Color(153, 255, 153));
        btnagregap.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        btnagregap.setText("Agregar");
        btnagregap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregapActionPerformed(evt);
            }
        });

        jcant.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel13.setText("Codigo Producto:");

        txtcodigobarra.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel15.setText("Descripcion Producto:");

        txtdescripcion.setEditable(false);
        txtdescripcion.setColumns(20);
        txtdescripcion.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        txtdescripcion.setRows(5);
        jScrollPane2.setViewportView(txtdescripcion);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel13)
                    .addComponent(jLabel9))
                .addGap(61, 61, 61)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(159, 159, 159)
                        .addComponent(jLabel11))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtcodigobarra, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnbuscaproducto)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(167, 167, 167)
                                .addComponent(jLabel12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)))))
                    .addComponent(txtsku, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcant, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnomproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 194, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(txtNombrePromo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnagregap, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addGap(65, 65, 65))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtnomproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel12)
                                            .addComponent(jcant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(31, 31, 31)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGap(133, 133, 133)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnagregap, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombrePromo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 7, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtcodigobarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnbuscaproducto))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtsku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2)))))
                .addGap(13, 13, 13))
        );

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel5.setText("Rut Cliente:");

        txtrutcliente.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N

        btnbuscacliente.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        btnbuscacliente.setText("Buscar");
        btnbuscacliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscaclienteActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel7.setText("Nombre Cliente:");

        txtnombrecliente.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        txtnombrecliente.setEnabled(false);

        btnlimpiar.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        btnlimpiar.setText("Limpiar");
        btnlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtrutcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnlimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnbuscacliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 22, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdcliente)
                            .addComponent(txtnombrecliente, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtrutcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscacliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(txtIdcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtnombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jagrega.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jagrega);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        jPanel7.setBackground(new java.awt.Color(204, 255, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton4.setBackground(new java.awt.Color(255, 153, 153));
        jButton4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jButton4.setText("Quitar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btnGenerarVenta.setBackground(new java.awt.Color(153, 255, 153));
        btnGenerarVenta.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        btnGenerarVenta.setText("Generar Venta");
        btnGenerarVenta.setActionCommand("");
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVentaActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel14.setText("Total a Pagar:");

        txtPrecFinal.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        txtPrecFinal.setToolTipText("");

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel17.setText("Descuento:");

        txtDesc.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel18.setText("Total:");

        txtTotalSinDesc.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N

        cbxDesc.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        cbxDesc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxDescMouseClicked(evt);
            }
        });

        btnCalcular.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        btnCalcular.setText("Calcular");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(btnCalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDesc, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(txtTotalSinDesc))
                .addGap(134, 134, 134)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(txtPrecFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGenerarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(btnCalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGenerarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtPrecFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(txtTotalSinDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel16.setText("Verduras Chile S.A");
        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 204)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(22, 1530, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 204, 102));
        jMenuBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        menuAgregar.setBackground(new java.awt.Color(255, 204, 255));
        menuAgregar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuAgregar.setText("Agregar");
        menuAgregar.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N

        menuCliente.setText("Cliente");
        menuCliente.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        menuCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuClienteMouseClicked(evt);
            }
        });
        menuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuClienteActionPerformed(evt);
            }
        });
        menuAgregar.add(menuCliente);

        menuProducto.setText("Producto");
        menuProducto.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        menuProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuProductoMouseClicked(evt);
            }
        });
        menuProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProductoActionPerformed(evt);
            }
        });
        menuAgregar.add(menuProducto);

        jMenuBar1.add(menuAgregar);

        menuListar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menuListar.setText("Listar");
        menuListar.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N

        menuVentas.setText("Ventas");
        menuVentas.setToolTipText("");
        menuVentas.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        menuVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuVentasMouseClicked(evt);
            }
        });
        menuVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVentasActionPerformed(evt);
            }
        });
        menuListar.add(menuVentas);

        menuclientes.setText("Clientes");
        menuclientes.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        menuclientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuclientesMouseClicked(evt);
            }
        });
        menuListar.add(menuclientes);

        menufecha.setText("Producto Por Fecha");
        menufecha.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        menufecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menufechaMouseClicked(evt);
            }
        });
        menuListar.add(menufecha);

        menusucursal.setText("Ventas Sucursal");
        menusucursal.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        menusucursal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menusucursalMouseClicked(evt);
            }
        });
        menuListar.add(menusucursal);

        menuPromos.setText("Promociones");
        menuPromos.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        menuPromos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPromosMouseClicked(evt);
            }
        });
        menuListar.add(menuPromos);

        jMenuBar1.add(menuListar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbuscaclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscaclienteActionPerformed

        String rut = txtrutcliente.getText();

        ConsultaCliente(rut, txtnombrecliente, txtIdcliente);

    }//GEN-LAST:event_btnbuscaclienteActionPerformed

    private void btnbuscaproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscaproductoActionPerformed
        try {
            String codigo_barra = txtcodigobarra.getText();
//        int id_producto = Integer.parseInt(prod);
            ConsultaProducto(txtcodigobarra, txtsku, txtnomproducto, txtprecio, txtdescripcion, txtNombrePromo, txtIdProd);
        } catch (Exception e) {

        }
    }//GEN-LAST:event_btnbuscaproductoActionPerformed

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed

        txtrutcliente.setText("");
        txtnombrecliente.setText("");
    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void menuVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVentasActionPerformed

    }//GEN-LAST:event_menuVentasActionPerformed

    private void menuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuClienteActionPerformed

    }//GEN-LAST:event_menuClienteActionPerformed

    private void menuProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProductoActionPerformed

    }//GEN-LAST:event_menuProductoActionPerformed

    private void menuClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuClienteMouseClicked

        NuevoCliente cliente = new NuevoCliente();
        cliente.setVisible(true);

    }//GEN-LAST:event_menuClienteMouseClicked

    private void menuProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuProductoMouseClicked

        NuevoProducto ver = new NuevoProducto();
        ver.setVisible(true);

    }//GEN-LAST:event_menuProductoMouseClicked

    private void menuVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuVentasMouseClicked

        Ventas ver = new Ventas();
        ver.setVisible(true);

    }//GEN-LAST:event_menuVentasMouseClicked

    private void menuclientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuclientesMouseClicked
        Clientes ver = new Clientes();
        ver.setVisible(true);
    }//GEN-LAST:event_menuclientesMouseClicked

    private void txtnomproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnomproductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnomproductoActionPerformed

    private void btnagregapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregapActionPerformed

        try {
            ConsultaId();
            System.out.println(idVenta);

            int precio = Integer.parseInt(txtprecio.getText());
            int cantidad = Integer.parseInt(String.valueOf(jcant.getValue()));
            String promocion = txtNombrePromo.getText();

            if (cantidad == 2 && promocion.equals("Promocion 2x1")) {

                precio = (precio * cantidad) - precio;

            } else if (cantidad == 3 && promocion.equals("Promocion 3x1")) {

                precio = (precio * cantidad) - (precio*2);

            } else {
                precio = precio * cantidad;
            }

            this.modelo1.addRow(new Object[]{txtIdProd.getText() ,txtsku.getText(), txtcodigobarra.getText(), txtdescripcion.getText(), txtnomproducto.getText(), precio, jcant.getValue(), txtNombrePromo.getText()});

            int cantidadFinal = 0;
            int totalPrec = 0;
            for (int i = 0; i < modelo1.getRowCount(); i++) {
                int cantidadDesc = Integer.parseInt(String.valueOf(modelo1.getValueAt(i, 6)));

                cantidadFinal = cantidadDesc + cantidadFinal;

                int precioTotal = Integer.parseInt(String.valueOf(modelo1.getValueAt(i, 5)));

                totalPrec = precioTotal + totalPrec;

            }
            txtTotalSinDesc.setText(String.valueOf(totalPrec));
            cbxDesc.setEnabled(false);

            if (cantidadFinal >= 20) {

                cbxDesc.setEnabled(true);
                cbxDesc.setVisible(true);
                btnCalcular.setVisible(true);
            }
            
            //int pguarda = Integer.parseInt((String) guarda);
            txtcodigobarra.setText("");
            txtsku.setText("");
            txtdescripcion.setText("");
            txtnomproducto.setText("");
            txtprecio.setText("");
            jcant.setValue(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe completar todos datos", "Error", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnagregapActionPerformed

    private void menufechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menufechaMouseClicked

        ProductosFecha ver = new ProductosFecha();
        ver.setVisible(true);

    }//GEN-LAST:event_menufechaMouseClicked

    private void menusucursalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menusucursalMouseClicked

        VentasTiendas ver = new VentasTiendas();
        ver.setVisible(true);

    }//GEN-LAST:event_menusucursalMouseClicked

    private void cbxDescMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxDescMouseClicked


    }//GEN-LAST:event_cbxDescMouseClicked

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        try {
            int totalDesc = Integer.parseInt(txtTotalSinDesc.getText());
            int totalPrecio = Integer.parseInt(txtTotalSinDesc.getText());
            double descuento = Double.parseDouble(String.valueOf(cbxDesc.getSelectedItem()));

            ConsultaDesc(descuento);
            System.out.println(codDesc);
            descuento = descuento / 100;

            totalDesc = (int) (totalDesc * descuento);

            totalPrecio = totalPrecio - totalDesc;
            txtDesc.setText(String.valueOf(totalDesc));
            txtPrecFinal.setText(String.valueOf(totalPrecio));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un Descuento", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed

        try {

            int codVendedor = Integer.parseInt(lblcodigo.getText());
            int idcliente = Integer.parseInt(txtIdcliente.getText());
            String fecha = lblfecha.getText();
            String hora = lblhora.getText();
            int idProd = Integer.parseInt(txtIdProd.getText());
            CrudVentas ventas = new CrudVentas();

            ventas.ingresarProductos(codVendedor, idcliente, fecha, hora);

            for (int i = 0; i < modelo1.getRowCount(); i++) {

                int cantidad = Integer.parseInt(String.valueOf(modelo1.getValueAt(i, 6)));
                int total = Integer.parseInt(String.valueOf(modelo1.getValueAt(i, 5)));
                int id = Integer.parseInt(String.valueOf(modelo1.getValueAt(i, 0)));
                ventas.ingresarProductosDetalle(id, idVenta, codDesc, cantidad, total);

                System.out.println(idProd);
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debes completar todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
        }
        modelo1.setRowCount(0);
        txtDesc.setText("");
        txtTotalSinDesc.setText("");
        txtPrecFinal.setText("");

    }//GEN-LAST:event_btnGenerarVentaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try {
            modelo1.removeRow(jagrega.getSelectedRow());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void menuPromosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPromosMouseClicked
       PromocionesDesc promo = new PromocionesDesc();
       
       promo.setVisible(true);
       
        
        
        
    }//GEN-LAST:event_menuPromosMouseClicked

    /**
     * @param args the command line arguments
     */
    public static String fecha() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY/MM/dd");
        return formatofecha.format(fecha);
    }

    private void llenarlista() {
        String SQL = "select * from promociones";
        String datos[] = new String[1];
        // limpiarT();
        try {
            cn.conectado();

            cn.st = cn.conexion.createStatement();
            cn.rt = cn.st.executeQuery(SQL);
            while (cn.rt.next()) {
                datos[0] = cn.rt.getString(2);
                modelo.addElement(datos[0]);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JButton btnagregap;
    private javax.swing.JButton btnbuscacliente;
    private javax.swing.JButton btnbuscaproducto;
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JComboBox<String> cbxDesc;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jagrega;
    public javax.swing.JSpinner jcant;
    public javax.swing.JLabel lblcodigo;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JLabel lblhora;
    public javax.swing.JLabel lblsucursal;
    public javax.swing.JLabel lblvendedor;
    private javax.swing.JMenu menuAgregar;
    private javax.swing.JMenu menuCliente;
    private javax.swing.JMenu menuListar;
    private javax.swing.JMenu menuProducto;
    private javax.swing.JMenu menuPromos;
    private javax.swing.JMenu menuVentas;
    private javax.swing.JMenu menuclientes;
    private javax.swing.JMenu menufecha;
    private javax.swing.JMenu menusucursal;
    private javax.swing.JTextField txtDesc;
    private javax.swing.JTextField txtIdProd;
    private javax.swing.JTextField txtIdcliente;
    private javax.swing.JTextField txtNombrePromo;
    private javax.swing.JTextField txtPrecFinal;
    private javax.swing.JTextField txtTotalSinDesc;
    private javax.swing.JTextField txtcodigobarra;
    private javax.swing.JTextArea txtdescripcion;
    public javax.swing.JTextField txtnombrecliente;
    private javax.swing.JTextField txtnomproducto;
    private javax.swing.JTextField txtprecio;
    private javax.swing.JTextField txtrutcliente;
    private javax.swing.JTextField txtsku;
    // End of variables declaration//GEN-END:variables

}
