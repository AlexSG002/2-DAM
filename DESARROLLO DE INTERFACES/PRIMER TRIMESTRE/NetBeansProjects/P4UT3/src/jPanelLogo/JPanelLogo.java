/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jPanelLogo;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Alejandro Sánchez Gil
 */
public class JPanelLogo extends JPanel implements Serializable{

    private File rutaImagen = new File("C:\\Users\\Tarde\\Documents\\2DAM\\DESARROLLO DE INTERFACES\\UT3\\large.png");
    
    public JPanelLogo(){
        
        
    }
        private void setIconImage(){
            if (rutaImagen != null && rutaImagen.exists()) {
            ImageIcon imageIcon = new ImageIcon(rutaImagen.getAbsolutePath());
            Image image = imageIcon.getImage();
            
            if(getTopLevelAncestor() instanceof JFrame){
                JFrame frame = (JFrame) getTopLevelAncestor();
                frame.setIconImage(image);
            }
            
            }
        }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addNotify() {
        super.addNotify(); //To change body of generated methods, choose Tools | Templates.
        setIconImage();
    }
        
        
        
}

    
