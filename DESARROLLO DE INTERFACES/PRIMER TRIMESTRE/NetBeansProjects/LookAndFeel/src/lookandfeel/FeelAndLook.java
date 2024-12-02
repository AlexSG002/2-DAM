/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lookandfeel;

import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;

/**
 *
 * @author Alejandro Sánchez Gil
 */
public class FeelAndLook {
    
    public static void main (String [] args){
        JFrame.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.FindingNemoSkin");
        Ventana v = new Ventana();
        v.setVisible(true);
    }
    
}
