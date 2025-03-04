/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejandro Sánchez Gil
 */
public class PantallaInicial extends javax.swing.JFrame {

    boolean clave = false;
    Formulario f = new Formulario(this);
    
    /**
     * Creates new form PantallaInicial
     */
    public PantallaInicial() {
        initComponents();
        jTextFieldMostrarClave.setVisible(false);
        setLocationRelativeTo(null);
        setResizable(false);
        this.setIconImage(new ImageIcon(getClass().getResource("/gui/imgs/main_icon.png")).getImage());
        this.setTitle("Biblioteca");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelLibro = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelClave = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButtonEntrar = new javax.swing.JButton();
        jButtonLimpiar = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jTextFieldNombre = new javax.swing.JTextField();
        jButtonMostrarClave = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPasswordFieldClave = new javax.swing.JPasswordField();
        jTextFieldMostrarClave = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(652, 512));
        setMinimumSize(new java.awt.Dimension(652, 512));
        setPreferredSize(new java.awt.Dimension(652, 512));

        jLabelLibro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imgs/libro (1).png"))); // NOI18N

        jLabelNombre.setFont(new java.awt.Font("MV Boli", 0, 12)); // NOI18N
        jLabelNombre.setText("Nombre de usuario:");

        jLabelClave.setFont(new java.awt.Font("MV Boli", 0, 12)); // NOI18N
        jLabelClave.setText("Clave:");

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jButtonEntrar.setFont(new java.awt.Font("MV Boli", 0, 12)); // NOI18N
        jButtonEntrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imgs/puerta-abierta.png"))); // NOI18N
        jButtonEntrar.setText("Entrar");
        jButtonEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEntrarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonEntrar);

        jButtonLimpiar.setFont(new java.awt.Font("MV Boli", 0, 12)); // NOI18N
        jButtonLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imgs/barriendo.png"))); // NOI18N
        jButtonLimpiar.setText("Limpiar");
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonLimpiar);

        jButtonSalir.setFont(new java.awt.Font("MV Boli", 0, 12)); // NOI18N
        jButtonSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imgs/salida.png"))); // NOI18N
        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSalir);

        jTextFieldNombre.setFont(new java.awt.Font("MV Boli", 0, 12)); // NOI18N

        jButtonMostrarClave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imgs/ojo-abierto.png"))); // NOI18N
        jButtonMostrarClave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonMostrarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarClaveActionPerformed(evt);
            }
        });

        jPanel2.setLayout(new java.awt.CardLayout());

        jPasswordFieldClave.setFont(new java.awt.Font("MV Boli", 0, 12)); // NOI18N
        jPanel2.add(jPasswordFieldClave, "card2");
        jPanel2.add(jTextFieldMostrarClave, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelClave, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldNombre)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
                .addGap(19, 19, 19)
                .addComponent(jButtonMostrarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addComponent(jLabelLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabelLibro)
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelClave)
                        .addComponent(jButtonMostrarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
        jTextFieldNombre.setText("");
        jPasswordFieldClave.setText("");
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

    private void jButtonMostrarClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarClaveActionPerformed
        if(!clave){
            jTextFieldMostrarClave.setText(new String(jPasswordFieldClave.getPassword()));
            jPasswordFieldClave.setVisible(false);
            jTextFieldMostrarClave.setVisible(true);
            jButtonMostrarClave.setIcon(new ImageIcon(getClass().getResource("/gui/imgs/ojo.png")));
            clave = true;
        }
        else{
            jPasswordFieldClave.setText(jTextFieldMostrarClave.getText());
            jTextFieldMostrarClave.setVisible(false);
            jPasswordFieldClave.setVisible(true);
            jButtonMostrarClave.setIcon(new ImageIcon(getClass().getResource("/gui/imgs/ojo-abierto.png")));
            clave = false;
        
        }
    }//GEN-LAST:event_jButtonMostrarClaveActionPerformed

    private void jButtonEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEntrarActionPerformed
        if(validarEntradas()){
            this.setVisible(false);
            f.setVisible(true);
        }
    }//GEN-LAST:event_jButtonEntrarActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEntrar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JButton jButtonMostrarClave;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JLabel jLabelClave;
    private javax.swing.JLabel jLabelLibro;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordFieldClave;
    private javax.swing.JTextField jTextFieldMostrarClave;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables

    private boolean validarEntradas(){
        if(jPasswordFieldClave.getPassword().equals("") || jTextFieldNombre.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "No puede haber campos en blanco", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        
        return true;
    }
    
    
}
