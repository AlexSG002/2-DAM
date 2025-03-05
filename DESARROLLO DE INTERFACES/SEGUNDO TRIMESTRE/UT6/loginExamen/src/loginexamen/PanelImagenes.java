/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginexamen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Tarde
 */
public class PanelImagenes extends JPanel{
    private Image backgroundImage;
    private final int WIDTH = 800, HEIGHT = 600;
    
    public PanelImagenes(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        loadBackgroundImage();
    }
    
    private void loadBackgroundImage(){
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/loginexamen/digimon.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            setBackground(Color.BLACK);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT, this);
        }
    }
}