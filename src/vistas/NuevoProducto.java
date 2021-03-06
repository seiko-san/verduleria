/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import gestionBD.Conexion;
import gestionBD.CrudHistoricoProd;
import gestionBD.CrudProductos;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.Productos;

/**
 *
 * @author seiko
 */
public class NuevoProducto extends javax.swing.JFrame {

    /**
     * Creates new form NuevoProducto
     */
    Conexion cn = new Conexion();
    CrudProductos crudProd = new CrudProductos();
    Productos productos = new Productos();
    DefaultListModel modelo = new DefaultListModel();
    Principal ver = new Principal();
    CrudHistoricoProd crudHistorico = new CrudHistoricoProd();

    public NuevoProducto() {
        initComponents();
        listpromocion.setModel(modelo);
        crudProd.mostrarProductos(jtProductos);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        llenarlista();
        txtId.setVisible(false);
        setDefaultCloseOperation(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtSku = new javax.swing.JTextField();
        txtCodigoProd = new javax.swing.JTextField();
        txtNombreProd = new javax.swing.JTextField();
        txtDesc = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        listpromocion = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        txtIva = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        btnHistorico = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtProductos = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel8.setBackground(new java.awt.Color(204, 255, 204));

        jPanel9.setBackground(new java.awt.Color(204, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel15.setText("SKU:");

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel16.setText("Codigo:");

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel17.setText("Descripcion:");

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel18.setText("Promociones:");

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel19.setText("Precio:");

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel20.setText("Nombre:");

        txtSku.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        txtSku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSkuActionPerformed(evt);
            }
        });

        txtCodigoProd.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N

        txtNombreProd.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N

        txtDesc.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N

        txtPrecio.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N

        btnAgregar.setBackground(new java.awt.Color(204, 255, 204));
        btnAgregar.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(255, 255, 153));
        btnModificar.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(255, 102, 153));
        btnEliminar.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        listpromocion.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jScrollPane4.setViewportView(listpromocion);

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel1.setText("Precio Con IVA:");

        txtIva.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N

        txtId.setEditable(false);
        txtId.setBackground(new java.awt.Color(204, 255, 255));
        txtId.setEnabled(false);
        txtId.setFocusable(false);
        txtId.setOpaque(false);
        txtId.setRequestFocusEnabled(false);
        txtId.setSelectedTextColor(new java.awt.Color(204, 255, 255));
        txtId.setSelectionColor(new java.awt.Color(204, 255, 255));

        btnHistorico.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        btnHistorico.setText("Historico Productos");
        btnHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoricoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(307, 307, 307)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel17))
                        .addGap(72, 72, 72)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSku, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(txtDesc)
                    .addComponent(txtNombreProd)
                    .addComponent(txtIva)
                    .addComponent(txtId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(198, 198, 198)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                        .addComponent(txtPrecio))
                    .addComponent(txtCodigoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(177, 177, 177))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(366, 366, 366)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(603, 603, 603)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(93, 93, 93)
                .addComponent(btnEliminar)
                .addGap(100, 100, 100)
                .addComponent(btnHistorico)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtSku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtCodigoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtNombreProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(35, 35, 35)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 204));

        jtProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtProductosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtProductos);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jLabel21.setText("Verduras Chile S.A");
        jLabel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 204)));

        jButton1.setBackground(new java.awt.Color(255, 102, 102));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 36)); // NOI18N
        jLabel2.setText("Agregar Nuevo Producto");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(381, 381, 381)
                        .addComponent(jLabel21))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 1344, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 24, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel2))
                .addGap(23, 23, 23)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1380, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 982, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSkuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSkuActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try{
            String skupro =txtSku.getText();
        String codpro=txtCodigoProd.getText();
        String nombpro =txtNombreProd.getText();
        String descpro=txtDesc.getText();
        String precpro=txtPrecio.getText();
        String ivapro=txtIva.getText();
            if(skupro.isEmpty() || codpro.isEmpty() || nombpro.isEmpty() ||descpro.isEmpty() || precpro.isEmpty() || ivapro.isEmpty()){
                JOptionPane.showMessageDialog(null,"Debe completar todos los campos","Advertencia",JOptionPane.WARNING_MESSAGE);
            }else{
        Date fecha = new Date();
        DateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

        int idlist = listpromocion.getSelectedIndex() + 1;
        productos.setSku(txtSku.getText());
        productos.setCodigo(txtCodigoProd.getText());
        productos.setNombre(txtNombreProd.getText());
        productos.setDescripcion(txtDesc.getText());
        productos.setPrecio(Double.parseDouble(txtPrecio.getText()));
        productos.setIva(Double.parseDouble(txtIva.getText()));
        productos.setCodPromo(idlist);
        //        productos.setText((String)lista.getSelectedValue());
        int filasTabla = jtProductos.getRowCount();
        crudProd.ingresarProductos(productos.getSku(), productos.getCodigo(),
                productos.getNombre(), productos.getDescripcion(), productos.getPrecio(), productos.getCodPromo(), productos.getIva());

        crudProd.mostrarProductos(jtProductos);
        if (jtProductos.getRowCount() > filasTabla) {
            int id_producto = Integer.parseInt(String.valueOf(jtProductos.getValueAt(jtProductos.getRowCount() - 1, 0)));
            crudHistorico.ingresarHistorico(id_producto, productos.getPrecio(), String.valueOf(formato.format(fecha)));
            System.out.println(jtProductos.getRowCount() + " " + filasTabla + " " + id_producto + " " + String.valueOf(formato.format(fecha)));

        }

        txtSku.setText("");
        txtCodigoProd.setText("");
        txtNombreProd.setText("");
        txtPrecio.setText("");
        txtDesc.setText("");
        txtId.setText("");
        txtIva.setText("");

            }}catch(Exception e){
                JOptionPane.showMessageDialog(null,"Debe llenar todos datos","Advertencia",JOptionPane.WARNING_MESSAGE);
            }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try{
        Date fecha = new Date();
        DateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

        int seleccion = jtProductos.getSelectedRow();

        int idlist = listpromocion.getSelectedIndex() + 1;
        productos.setSku(txtSku.getText());
        productos.setCodigo(txtCodigoProd.getText());
        productos.setNombre(txtNombreProd.getText());
        productos.setDescripcion(txtDesc.getText());
        productos.setPrecio(Double.parseDouble(txtPrecio.getText()));
        productos.setId(Integer.parseInt(txtId.getText()));
        productos.setIva(Double.parseDouble(txtIva.getText()));
        productos.setCodPromo(idlist);
        //    productos.setCodPromo(Integer.parseInt(txtPromo.getText()));
        String precioAntes = String.valueOf(jtProductos.getValueAt(seleccion, 5));
        crudProd.modificarProd(productos.getSku(), productos.getCodigo(),
                productos.getNombre(), productos.getDescripcion(), productos.getPrecio(), productos.getCodPromo(), productos.getIva(), productos.getId());

        crudProd.mostrarProductos(jtProductos);
        String precioDespues = String.valueOf(jtProductos.getValueAt(seleccion, 5));

        if (!precioAntes.equals(precioDespues)) {

            crudHistorico.ingresarHistorico(productos.getId(), productos.getPrecio(), String.valueOf(formato.format(fecha)));
            System.out.println("Se cambio el precio pa");
        }
        System.out.println(precioAntes + " " + precioDespues);
        txtId.setText("");
        txtSku.setText("");
        txtCodigoProd.setText("");
        txtNombreProd.setText("");
        txtPrecio.setText("");
        txtDesc.setText("");
        txtIva.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fila","Error",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try{
        productos.setId(Integer.parseInt(txtId.getText()));

        crudProd.eliminarProd(productos.getId());
        crudProd.mostrarProductos(jtProductos);

        txtSku.setText("");
        txtCodigoProd.setText("");
        txtNombreProd.setText("");
        txtPrecio.setText("");
        txtDesc.setText("");
        txtId.setText("");
        txtIva.setText("");
        }catch(Exception e ){
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fila","Error",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void jtProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtProductosMouseClicked
        int seleccion = jtProductos.rowAtPoint(evt.getPoint());
        txtId.setText(String.valueOf(jtProductos.getValueAt(seleccion, 0)));
        txtSku.setText(String.valueOf(jtProductos.getValueAt(seleccion, 1)));
        txtCodigoProd.setText(String.valueOf(jtProductos.getValueAt(seleccion, 2)));
        txtNombreProd.setText(String.valueOf(jtProductos.getValueAt(seleccion, 3)));
        txtDesc.setText(String.valueOf(jtProductos.getValueAt(seleccion, 4)));
        txtPrecio.setText(String.valueOf(jtProductos.getValueAt(seleccion, 5)));
        //  txtPromo.setText(String.valueOf(jtProductos.getValueAt(seleccion, 5)));
        txtIva.setText(String.valueOf(jtProductos.getValueAt(seleccion, 6)));
    }//GEN-LAST:event_jtProductosMouseClicked

    private void btnHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoricoActionPerformed

        HistoricoProductos historico = new HistoricoProductos();

        historico.setVisible(true);
    }//GEN-LAST:event_btnHistoricoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
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

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(NuevoProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevoProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnHistorico;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jtProductos;
    public javax.swing.JList<String> listpromocion;
    private javax.swing.JTextField txtCodigoProd;
    private javax.swing.JTextField txtDesc;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtNombreProd;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtSku;
    // End of variables declaration//GEN-END:variables
}
