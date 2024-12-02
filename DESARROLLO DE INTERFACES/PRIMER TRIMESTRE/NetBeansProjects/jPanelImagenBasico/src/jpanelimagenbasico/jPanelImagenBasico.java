/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanelimagenbasico;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Tarde
 */
public class jPanelImagenBasico extends JPanel implements Serializable{
    private Image imagen;
    
    public jPanelImagenBasico(){
        
    }
    
    public Image getImagen(){
        return imagen;
    }
    
    public void setRutaImagen(String ruta) {
        try {
            URL resource = getClass().getResource(ruta);
            if (resource != null) {
                this.imagen = new ImageIcon(resource).getImage();
                repaint();
            } else {
                System.err.println("La ruta de la imagen no es válida: " + ruta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    
    
}
