/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego2D;

import javax.swing.JFrame;

/**
 *
 * @author Tarde
 */
public class Juego extends JFrame{
    
    private JuegoPanel panel;
    
    public Juego(){
        panel = new JuegoPanel();
        add(panel);
        setTitle("Juego 2D");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.setVisible(true);
    }
    
}
