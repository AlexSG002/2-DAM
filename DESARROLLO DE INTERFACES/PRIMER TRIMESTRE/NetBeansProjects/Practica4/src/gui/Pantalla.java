/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
//Importamos la función color para el fondo de la aplicación.
import java.awt.Color;

/**
 *
 * @author Alejandro Sánchez Gil
 */
public class Pantalla extends javax.swing.JFrame {
    
    /**
     * Creates new form Pantalla
     */
    public Pantalla() {
        initComponents();
        //Marca el primer botón como actualmente seleccionado.
        rbtPrimero.setSelected(true);
        //getContentPane() es una función para seleccionar como contenido en este caso la clase Pantalla, de manera que nos permite modificar el fondo.
        getContentPane().setBackground(new java.awt.Color(190, 192, 0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgGrupo = new javax.swing.ButtonGroup();
        txtInfo = new javax.swing.JLabel();
        rbtPrimero = new javax.swing.JRadioButton();
        rbtSegundo = new javax.swing.JRadioButton();
        rbtTercero = new javax.swing.JRadioButton();
        btnGenerar = new javax.swing.JButton();
        txtResultado = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtInfo.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        txtInfo.setText("Generador de números:");

        rbtPrimero.setBackground(new java.awt.Color(102, 102, 0));
        btgGrupo.add(rbtPrimero);
        rbtPrimero.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        rbtPrimero.setText("Entre 0 y 100");
        rbtPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtPrimeroActionPerformed(evt);
            }
        });

        rbtSegundo.setBackground(new java.awt.Color(102, 102, 0));
        btgGrupo.add(rbtSegundo);
        rbtSegundo.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        rbtSegundo.setText("Entre 100 y 200");
        rbtSegundo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtSegundoActionPerformed(evt);
            }
        });

        rbtTercero.setBackground(new java.awt.Color(102, 102, 0));
        btgGrupo.add(rbtTercero);
        rbtTercero.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        rbtTercero.setText("Entre 200 y 500");
        rbtTercero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtTerceroActionPerformed(evt);
            }
        });

        btnGenerar.setBackground(new java.awt.Color(255, 255, 0));
        btnGenerar.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        txtResultado.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtResultado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtResultado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtResultado.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtResultado.setEnabled(false);
        txtResultado.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(btnGenerar)
                                .addGap(116, 116, 116))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtInfo)
                                .addGap(29, 29, 29)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtSegundo)
                            .addComponent(rbtPrimero)
                            .addComponent(rbtTercero)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(txtResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtInfo)
                            .addComponent(rbtPrimero))
                        .addGap(18, 18, 18)
                        .addComponent(rbtSegundo)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnGenerar)
                        .addGap(2, 2, 2)))
                .addComponent(rbtTercero)
                .addGap(18, 18, 18)
                .addComponent(txtResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbtPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtPrimeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtPrimeroActionPerformed

    private void rbtSegundoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtSegundoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtSegundoActionPerformed

    private void rbtTerceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtTerceroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtTerceroActionPerformed

    
    //Método que realizará la función que aparece más abajo, al pulsar el botón de nombre btnGenerar.
    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        //Condición que comprueba si el primer radioButton está seleccionado.
        if(rbtPrimero.isSelected()){
            //Si se cumple la condición, crea las variables enteras max, min, rango y num; Dichas variables se iniacilizan respectivamente a
            // 100, 0, max - min +1 y a (int) (Math.random()*rango)+min.
            int max=100;
            int min=0;
            //A la variable rango, que esta definida como max - min, para establecer el rango de números, le sumamos 1, ya que los números aleatorios
            //generados por la función Math.random solo devuelve números de 0 a 99.
            int rango = max - min +1;
            //La variable num esta inicializada a una función Math.random, que sirve para generar números aleatorios, (int) es un cast, para convertir
            //el número a entero, ya que la función Math.random devuelve números de tipo double, la multiplicamos por la variable rango para que nos
            //devuelva números entre el rango máximo y el mínimo y le sumamos de nuevo el mínimo para que establezca la base, en este caso es 0.
            int num = (int) (Math.random()*rango)+min;
            //Finalmente, establecemos el campo txtResultado que es un textField donde imprimimos los resultados de la generación de números aleatoria
            //Asi que lo que hacemos es establecer el texto a "" para que detecte la entrada como cadena y la variable num.
            txtResultado.setText(""+num);
        }
            //Condición que comprueba si el segundo radioButton está seleccionado.
        if(rbtSegundo.isSelected()){
            //En caso de que se cumpla la condición, opera de la misma manera que si el primer radioButton estuivese seleccionado, la diferencia es que en
            //este caso, el rango es entre 100 y 200, definidos por el máximo y el mínimo.
            int max=200;
            int min=100;
            int rango = max - min +1;
            int num = (int) (Math.random()*rango)+min;
            txtResultado.setText(""+num);
        }
        
        if(rbtTercero.isSelected()){
            //En caso de que se cumpla la condición, opera de la misma manera que si el primer y el segundo radioButton estuivesen seleccionados,
            //la diferencia es que en este caso, el rango es entre 200 y 500, definidos por el máximo y el mínimo.
            int max=500;
            int min=200;
            int rango = max - min +1;
            int num = (int) (Math.random()*rango)+min;
            txtResultado.setText(""+num);
        }
    }//GEN-LAST:event_btnGenerarActionPerformed

    /**
     * @param args the command line arguments
     */
    
    //Método main.
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
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGrupo;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JRadioButton rbtPrimero;
    private javax.swing.JRadioButton rbtSegundo;
    private javax.swing.JRadioButton rbtTercero;
    private javax.swing.JLabel txtInfo;
    private javax.swing.JTextField txtResultado;
    // End of variables declaration//GEN-END:variables
}
