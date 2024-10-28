/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author Alejandro Sánchez Gil
 */
public class Icono {
    
    private File imagen = new File("C:\\Users\\Tarde\\Documents\\2DAM\\DESARROLLO DE INTERFACES\\UT3\\large.png");
    
    public Icono(){
        
    }

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }
    
     public Image setIconImage(){
         Image image = null;
            if (imagen != null && imagen.exists()) {
            ImageIcon imageIcon = new ImageIcon(imagen.getAbsolutePath());
            image = imageIcon.getImage();
            }
            return image;
     }
    
}
