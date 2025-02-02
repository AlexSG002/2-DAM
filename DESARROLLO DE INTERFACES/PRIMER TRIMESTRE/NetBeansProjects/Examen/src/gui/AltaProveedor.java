/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Tarde
 */
public class AltaProveedor extends javax.swing.JFrame {
    Altas parent;
    TablaProveedores tab;
    /**
     * Creates new form AltaCliente
     */
    public AltaProveedor(Altas parent) {
        this.parent = parent;
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        this.setTitle("Alta Proveedor");
        loadComboBoxWithImages();
        this.tab = new TablaProveedores(this);
        this.setIconImage(new ImageIcon(getClass().getResource("/gui/imgs/Logo.png")).getImage());
        jPanelImagenBasico1.setRutaImagen("/gui/imgs/Logo.png");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldNombreEmpresa = new javax.swing.JTextField();
        jTextFieldTlfEmpresa = new javax.swing.JTextField();
        jComboBoxLocalizacion = new javax.swing.JComboBox<>();
        jTextFieldDireccion = new javax.swing.JTextField();
        jTextFieldMailEmpresa = new javax.swing.JTextField();
        jLabelCIF = new javax.swing.JLabel();
        jTextFieldCIF = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldNombreContacto = new javax.swing.JTextField();
        jTextFieldApellidos = new javax.swing.JTextField();
        jComboBoxNacionalidad = new javax.swing.JComboBox<>();
        jTextFieldMailContacto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldTlfContacto = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jButtonAgregar = new javax.swing.JButton();
        jButtonLimpiar = new javax.swing.JButton();
        jButtonMostrar = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jPanelImagenBasico1 = new jpanelimagenbasico.jPanelImagenBasico();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(870, 587));
        setMinimumSize(new java.awt.Dimension(870, 587));

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.setMaximumSize(new java.awt.Dimension(279, 347));
        jPanel1.setMinimumSize(new java.awt.Dimension(279, 347));

        jLabel2.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Datos de la empresa");

        jLabel6.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N
        jLabel6.setText("Nombre de la empresa:");

        jLabel7.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N
        jLabel7.setText("Teléfono de la empresa:");

        jLabel8.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N
        jLabel8.setText("E-mail:");

        jLabel10.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N
        jLabel10.setText("Localización de sucursal:");

        jLabel11.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N
        jLabel11.setText("Direccion:");

        jTextFieldNombreEmpresa.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N

        jTextFieldTlfEmpresa.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N

        jComboBoxLocalizacion.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N
        jComboBoxLocalizacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxLocalizacion.setMaximumSize(new java.awt.Dimension(70, 25));

        jTextFieldDireccion.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N

        jTextFieldMailEmpresa.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N

        jLabelCIF.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N
        jLabelCIF.setText("CIF:");

        jTextFieldCIF.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxLocalizacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldDireccion)
                            .addComponent(jTextFieldMailEmpresa)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelCIF)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNombreEmpresa)
                            .addComponent(jTextFieldTlfEmpresa)
                            .addComponent(jTextFieldCIF))))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCIF)
                    .addComponent(jTextFieldCIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldTlfEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBoxLocalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldMailEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel5.setMaximumSize(new java.awt.Dimension(391, 350));
        jPanel5.setMinimumSize(new java.awt.Dimension(391, 350));

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Datos de contacto");

        jLabelNombre.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N
        jLabelNombre.setText("Nombre: ");

        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N
        jLabel3.setText("Apellidos:");

        jLabel4.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N
        jLabel4.setText("Nacionalidad:");

        jLabel5.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N
        jLabel5.setText("E-mail:");

        jTextFieldNombreContacto.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N

        jTextFieldApellidos.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N

        jComboBoxNacionalidad.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N
        jComboBoxNacionalidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxNacionalidad.setMaximumSize(new java.awt.Dimension(70, 25));

        jTextFieldMailContacto.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N
        jLabel9.setText("Teléfono:");

        jTextFieldTlfContacto.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldMailContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTlfContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelNombre)
                            .addComponent(jLabel3))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNombreContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(jTextFieldNombreContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldTlfContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldMailContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        jPanel6.setMaximumSize(new java.awt.Dimension(164, 162));
        jPanel6.setMinimumSize(new java.awt.Dimension(164, 162));
        jPanel6.setLayout(new java.awt.GridLayout(2, 2));

        jButtonAgregar.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 14)); // NOI18N
        jButtonAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imgs/agregar-usuario.png"))); // NOI18N
        jButtonAgregar.setText("Agregar proveedor");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });
        jPanel6.add(jButtonAgregar);

        jButtonLimpiar.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 14)); // NOI18N
        jButtonLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imgs/limpiar.png"))); // NOI18N
        jButtonLimpiar.setText("Limpiar campos");
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });
        jPanel6.add(jButtonLimpiar);

        jButtonMostrar.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 14)); // NOI18N
        jButtonMostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imgs/oficina.png"))); // NOI18N
        jButtonMostrar.setText("Mostrar proveedores");
        jButtonMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarActionPerformed(evt);
            }
        });
        jPanel6.add(jButtonMostrar);

        jButtonSalir.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 14)); // NOI18N
        jButtonSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imgs/salida (2).png"))); // NOI18N
        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        jPanel6.add(jButtonSalir);

        javax.swing.GroupLayout jPanelImagenBasico1Layout = new javax.swing.GroupLayout(jPanelImagenBasico1);
        jPanelImagenBasico1.setLayout(jPanelImagenBasico1Layout);
        jPanelImagenBasico1Layout.setHorizontalGroup(
            jPanelImagenBasico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
        );
        jPanelImagenBasico1Layout.setVerticalGroup(
            jPanelImagenBasico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 181, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(jPanelImagenBasico1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelImagenBasico1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        parent.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        if(validarEntradas()){
            crearProveedor();
            tab.cambiarBoton();
        }
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jButtonMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarActionPerformed
        tab.setVisible(true);
    }//GEN-LAST:event_jButtonMostrarActionPerformed

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
        jTextFieldApellidos.setText("");
        jTextFieldCIF.setText("");
        jTextFieldDireccion.setText("");
        jTextFieldMailContacto.setText("");
        jTextFieldMailEmpresa.setText("");
        jTextFieldNombreContacto.setText("");
        jTextFieldNombreEmpresa.setText("");
        jTextFieldTlfContacto.setText("");
        jTextFieldTlfEmpresa.setText("");
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

    /**
     * @param args the command line arguments
     */
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JButton jButtonMostrar;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JComboBox<String> jComboBoxLocalizacion;
    private javax.swing.JComboBox<String> jComboBoxNacionalidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCIF;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private jpanelimagenbasico.jPanelImagenBasico jPanelImagenBasico1;
    private javax.swing.JTextField jTextFieldApellidos;
    private javax.swing.JTextField jTextFieldCIF;
    private javax.swing.JTextField jTextFieldDireccion;
    private javax.swing.JTextField jTextFieldMailContacto;
    private javax.swing.JTextField jTextFieldMailEmpresa;
    private javax.swing.JTextField jTextFieldNombreContacto;
    private javax.swing.JTextField jTextFieldNombreEmpresa;
    private javax.swing.JTextField jTextFieldTlfContacto;
    private javax.swing.JTextField jTextFieldTlfEmpresa;
    // End of variables declaration//GEN-END:variables
private void loadComboBoxWithImages(){
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();
        model.addElement("España");
        model.addElement("Bélgica");
        model.addElement("Brasil");
        model.addElement("USA");
        model2.addElement("España");
        model2.addElement("Bélgica");
        model2.addElement("Brasil");
        model2.addElement("USA");
        jComboBoxNacionalidad.setModel(model);
        jComboBoxNacionalidad.setRenderer(new ListCellRenderer<String>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends String> jlist, String value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = new JLabel();
                switch(value){
                    case "España":
                        label.setIcon(new ImageIcon(getClass().getResource("/gui/imgs/spain25.png")));
                        label.setText("España");
                        break;
                    case "Bélgica":
                        label.setIcon(new ImageIcon(getClass().getResource("/gui/imgs/belgium25.png")));
                        label.setText("Bélgica");
                        break;
                    case "Brasil":
                        label.setIcon(new ImageIcon(getClass().getResource("/gui/imgs/brazil25.png")));
                        label.setText("Brasil");
                        break;
                    case "USA":
                        label.setIcon(new ImageIcon(getClass().getResource("/gui/imgs/usa25.png")));
                        label.setText("USA");
                        break;
                }
                if(isSelected){
                    label.setBackground(jlist.getSelectionBackground());
                    label.setOpaque(true);
                }


                return label;
            }
        });
        
        jComboBoxLocalizacion.setModel(model2);
        jComboBoxLocalizacion.setRenderer(new ListCellRenderer<String>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends String> jlist, String value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = new JLabel();
                switch(value){
                    case "España":
                        label.setIcon(new ImageIcon(getClass().getResource("/gui/imgs/spain25.png")));
                        label.setText("España");
                        break;
                    case "Bélgica":
                        label.setIcon(new ImageIcon(getClass().getResource("/gui/imgs/belgium25.png")));
                        label.setText("Bélgica");
                        break;
                    case "Brasil":
                        label.setIcon(new ImageIcon(getClass().getResource("/gui/imgs/brazil25.png")));
                        label.setText("Brasil");
                        break;
                    case "USA":
                        label.setIcon(new ImageIcon(getClass().getResource("/gui/imgs/usa25.png")));
                        label.setText("USA");
                        break;
                }
                if(isSelected){
                    label.setBackground(jlist.getSelectionBackground());
                    label.setOpaque(true);
                }


                return label;
            }
        });
    }

    

public String[] crearProveedor(){
    String[] proveedor = new String[9];
    proveedor[0] = jTextFieldCIF.getText();
    proveedor[1] = jTextFieldNombreEmpresa.getText();
    proveedor[2] = jTextFieldTlfEmpresa.getText();
    proveedor[3] = jTextFieldMailEmpresa.getText();
    proveedor[4] = jComboBoxLocalizacion.getSelectedItem().toString();
    proveedor[5] = jTextFieldDireccion.getText();
    proveedor[6] = jTextFieldNombreContacto.getText();
    proveedor[7] = jTextFieldTlfContacto.getText();
    proveedor[8] = jTextFieldMailContacto.getText();
    return proveedor;
    }

public boolean validarEntradas(){
    if(jTextFieldApellidos.getText().isEmpty() || jTextFieldCIF.getText().isEmpty() || jTextFieldDireccion.getText().isEmpty() || jTextFieldMailContacto.getText().isEmpty()
            || jTextFieldMailEmpresa.getText().isEmpty() || jTextFieldNombreContacto.getText().isEmpty() || jTextFieldNombreEmpresa.getText().isEmpty() || jTextFieldTlfContacto.getText().isEmpty()
            || jTextFieldTlfEmpresa.getText().isEmpty()){
        JOptionPane.showMessageDialog(this, "No puede haber campos en blanco", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(jTextFieldNombreEmpresa.getText().matches(".*\\d.*")){
            JOptionPane.showMessageDialog(this, "El nombre de empresa introducido tiene números", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    
    if(jTextFieldNombreContacto.getText().matches(".*\\d.*")){
            JOptionPane.showMessageDialog(this, "El nombre de contacto introducido tiene números", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    
    if(jTextFieldApellidos.getText().matches(".*\\d.*")){
            JOptionPane.showMessageDialog(this, "Los apellidos del contacto introducido tienen números", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    
    if(!jTextFieldMailContacto.getText().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
            JOptionPane.showMessageDialog(this, "El email de contacto introducido es incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    
    if(!jTextFieldMailEmpresa.getText().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
            JOptionPane.showMessageDialog(this, "El email de contacto introducido es incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    
    if(!jTextFieldTlfContacto.getText().matches(".*\\d.*")){
            JOptionPane.showMessageDialog(this, "El teléfono de contacto introducido contiene caracteres alfabéticos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    
    if(!jTextFieldTlfEmpresa.getText().matches(".*\\d.*")){
            JOptionPane.showMessageDialog(this, "El teléfono de empresa introducido contiene caracteres alfabéticos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    
    if(tab.CIFDuplicado(jTextFieldCIF.getText())){
        JOptionPane.showMessageDialog(this, "La empresa ya existe como proveedor", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    return true;
}


}
