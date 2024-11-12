/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import gui.PantallaInicial;
import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;

/**
 *
 * @author Alejandro Sánchez Gil
 */
public class Main {
    
    public static void main (String [] args){
        JFrame.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.CremeCoffeeSkin");
        PantallaInicial p = new PantallaInicial();
        p.setVisible(true);
    }
    
}
