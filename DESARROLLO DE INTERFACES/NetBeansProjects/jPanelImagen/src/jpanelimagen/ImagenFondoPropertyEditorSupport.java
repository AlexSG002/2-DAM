/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanelimagen;

import java.awt.Component;
import java.beans.PropertyEditorSupport;
import java.io.File;

/**
 *
 * @author Alejandro Sánchez Gil
 */
public class ImagenFondoPropertyEditorSupport extends PropertyEditorSupport{

    private ImagenFondoPanel imagenFondoPanel = new ImagenFondoPanel();
    
    @Override
    public boolean supportsCustomEditor() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Component getCustomEditor() {
        return imagenFondoPanel; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getJavaInitializationString() {
        ImagenFondo imagenFondo = imagenFondoPanel.getSelectedValue();
        File rutaImagen = (File) imagenFondo.getRutaImagen();
        String ruta = rutaImagen.getAbsolutePath();
        ruta = ruta.replace('\\', '/');
        return "new jpanelimagen.ImagenFondo("+"new java.io.File(\"" + ruta + "\"), "+imagenFondo.getOpacidad()+"f)"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValue() {
        return imagenFondoPanel.getSelectedValue(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
