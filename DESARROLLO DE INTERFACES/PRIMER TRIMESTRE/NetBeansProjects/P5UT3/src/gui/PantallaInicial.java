/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Tarde
 */
public class PantallaInicial extends javax.swing.JFrame {
    SeleccionPJ seleccion = new SeleccionPJ();
    /**
     * Creates new form PantallaInicial
     */
    public PantallaInicial() {
        initComponents();
        jPanel1.setOpaque(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelImagen1 = new jpanelimagen.JPanelImagen();
        jPanel1 = new javax.swing.JPanel();
        jButtonLimpiar = new javax.swing.JButton();
        jButtonEntrar = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelNombre = new javax.swing.JLabel();
        jLabelPass = new javax.swing.JLabel();
        jTextFieldNick = new javax.swing.JTextField();
        jLabelNick = new javax.swing.JLabel();
        jTextFieldEdad = new javax.swing.JTextField();
        jLabelEdad = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelImagen1.setImagenfondo(new jpanelimagen.ImagenFondo(new java.io.File("C:/Users/Tarde/Documents/2DAM/DESARROLLO DE INTERFACES/UT3/Imagenes-de-Naruto-revelan-mapa-completo-de-Konoha-dibujado-por-Masashi-Kishimoto.jpg"), 0.36f));

        jPanel1.setLayout(new java.awt.GridLayout());

        jButtonLimpiar.setText("Limpiar");
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonLimpiar);

        jButtonEntrar.setText("Entrar");
        jButtonEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEntrarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonEntrar);

        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSalir);

        jLabelNombre.setText("Nombre: ");

        jLabelPass.setText("Contraseña");

        jLabelNick.setText("Nick: ");

        jLabelEdad.setText("Edad: ");

        javax.swing.GroupLayout jPanelImagen1Layout = new javax.swing.GroupLayout(jPanelImagen1);
        jPanelImagen1.setLayout(jPanelImagen1Layout);
        jPanelImagen1Layout.setHorizontalGroup(
            jPanelImagen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelImagen1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanelImagen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelImagen1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanelImagen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNick)
                            .addComponent(jLabelNombre)
                            .addComponent(jLabelEdad)
                            .addComponent(jLabelPass))
                        .addGap(127, 127, 127)
                        .addGroup(jPanelImagen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNick, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanelImagen1Layout.setVerticalGroup(
            jPanelImagen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelImagen1Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(jPanelImagen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelImagen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNick)
                    .addComponent(jTextFieldNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanelImagen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEdad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addGroup(jPanelImagen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPass))
                .addGap(48, 48, 48)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelImagen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelImagen1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
        jTextFieldEdad.setText("");
        jTextFieldNick.setText("");
        jTextFieldNombre.setText("");
        jPasswordField1.setText("");
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

    private void jButtonEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEntrarActionPerformed
        if(validarEntradas()){
            if(Integer.parseInt(jTextFieldEdad.getText())<18){
            JOptionPane.showMessageDialog(this, "Este juego esta catalogádo como PG-18, juega bajo tu propio juicio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
        
            seleccion.setVisible(true);
        }
    }//GEN-LAST:event_jButtonEntrarActionPerformed

    private boolean validarEntradas(){
    if(jTextFieldEdad.getText().isEmpty() || jTextFieldEdad.getText()==""){
        JOptionPane.showMessageDialog(this, "La edad no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(!jTextFieldEdad.getText().matches("^(100|[1-9]?[0-9])$")){
        JOptionPane.showMessageDialog(this, "La edad debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(jTextFieldNombre.getText().isEmpty() || jTextFieldNombre.getText()==""){
        JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(jTextFieldNick.getText().isEmpty() || jTextFieldNick.getText()==""){
        JOptionPane.showMessageDialog(this, "El nick no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(jPasswordField1.getText().isEmpty() || jPasswordField1.getText()==("")){
            JOptionPane.showMessageDialog(this, "La contraseña no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
        return true;
}
    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(PantallaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEntrar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JLabel jLabelEdad;
    private javax.swing.JLabel jLabelNick;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPass;
    private javax.swing.JPanel jPanel1;
    private jpanelimagen.JPanelImagen jPanelImagen1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextFieldEdad;
    private javax.swing.JTextField jTextFieldNick;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}
