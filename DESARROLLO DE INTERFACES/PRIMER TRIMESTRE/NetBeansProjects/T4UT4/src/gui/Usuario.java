/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.ImageIcon;

/**
 *
 * @author Alejandro Sánhez Gil
 */
public class Usuario extends javax.swing.JFrame {

    //Creamos una instancia de la pantalla inicial que es su parent, ya que de esta sacamos la password y el nombre del usuario además del idioma.
    private PantallaInicial parent;
    private String pass;
    private String nomUsu;
    /**
     * Creates new form Usuario
     */
    //Constructor para usuario.
    public Usuario(PantallaInicial parent) {
        //Establecemos como pantalla inicial la ya existente que abrimos en un inicio.
        this.parent = parent;
        //Cambios estéticos
        initComponents();
        getContentPane().setBackground(new java.awt.Color(255, 105, 97));
        this.setIconImage(new ImageIcon(getClass().getResource("/gui/imgs/usuario (2).png")).getImage());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelDetalles = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelPass = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButtonMostrar = new javax.swing.JButton();
        jButtonVovler = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelDetalles.setFont(new java.awt.Font("Reem Kufi", 0, 14)); // NOI18N
        jLabelDetalles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDetalles.setText("DETALLES DE USUARIO:");

        jLabelNombre.setFont(new java.awt.Font("Reem Kufi", 0, 14)); // NOI18N
        jLabelNombre.setText("Nombre de usuario:");

        jLabelPass.setFont(new java.awt.Font("Reem Kufi", 0, 14)); // NOI18N
        jLabelPass.setText("Contraseña:");

        jTextField1.setEditable(false);
        jTextField1.setText("***********");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setText("************");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imgs/usuario (1).png"))); // NOI18N

        jButtonMostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imgs/ojo.png"))); // NOI18N
        jButtonMostrar.setText("Mostrar");
        jButtonMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarActionPerformed(evt);
            }
        });

        jButtonVovler.setFont(new java.awt.Font("Reem Kufi", 0, 14)); // NOI18N
        jButtonVovler.setText("Volver");
        jButtonVovler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVovlerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombre)
                            .addComponent(jLabelPass))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                        .addContainerGap(121, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonMostrar)
                        .addGap(65, 65, 65)
                        .addComponent(jButtonVovler, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(jLabelDetalles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPass)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonVovler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //Método para cerrar el menú de usuario.
    private void jButtonVovlerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVovlerActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonVovlerActionPerformed
    //Método para mostrar el nombre de usuario y la pass.
    private void jButtonMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarActionPerformed
        jTextField1.setText(nomUsu);
        jTextField2.setText(pass);
    }//GEN-LAST:event_jButtonMostrarActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonMostrar;
    private javax.swing.JButton jButtonVovler;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelDetalles;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPass;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    //Método para actualizar el idioma a español si la variable booleana es true.
    public void actualizarIdioma(boolean spanish){
        if(spanish){
            jButtonMostrar.setText("Mostrar");
            jButtonVovler.setText("Volver");
            jLabelDetalles.setText("DETALLES DE USUARIO");
            jLabelNombre.setText("Nombre de usuario:");
            jLabelPass.setText("Contraseña:");
            //En caso contrario lo establece al inglés.
        }else{
            jButtonMostrar.setText("Show");
            jButtonVovler.setText("Return");
            jLabelDetalles.setText("USER DETAILS");
            jLabelNombre.setText("Username:");
            jLabelPass.setText("Password:");
        }
    }
//Getters y setters de la pass y el nombre de usuario.
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNomUsu() {
        return nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
    }
    
    
    
}
