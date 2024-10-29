/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanelimagen;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Alejandro Sánchez Gil
 */
public class JPanelImagen extends JPanel implements Serializable{

    private ImagenFondo imagenFondo;
    
   public JPanelImagen(){
       
   }

    public ImagenFondo getImagenfondo() {
        return imagenFondo;
    }

    public void setImagenfondo(ImagenFondo imagenfondo) {
        this.imagenFondo = imagenfondo;
    }

    

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
        if(imagenFondo!=null){
            if(imagenFondo.getRutaImagen()!=null && imagenFondo.getRutaImagen().exists()){
            ImageIcon imageIcon = new ImageIcon(imagenFondo.getRutaImagen().getAbsolutePath());
            Graphics2D g2d = (Graphics2D) grphcs;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, imagenFondo.getOpacidad()));
            g2d.drawImage(imageIcon.getImage(), 0, 0, null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
        }
    }

   
    
   
   
}
