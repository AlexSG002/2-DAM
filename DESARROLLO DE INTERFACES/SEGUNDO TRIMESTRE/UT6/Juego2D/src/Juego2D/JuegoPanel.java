package Juego2D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class JuegoPanel extends JPanel implements ActionListener {
    private Timer timer;
    private int x, y, velX, velY;
    private int x2, y2, velObstaculoX;
    private int x3, y3, velObstaculoX3;
    private int x4, y4, velObstaculoX4;
    private final int limiteX = 800, limiteY = 570;
    private final int anchoCuadrado = 30, altoCuadrado = 30;
    private int xVida1 = 760, xVida2 = 740, xVida3 = 720, contVidas = 3;
    private int meta = 540;
    private boolean haColisionado = false;

    public JuegoPanel() {
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new TAdapter());
        x = 390;
        y = 20;
        velX = 0;
        velY = 0;
        x2 = 220;
        y2 = 70;
        x3 = 0;
        y3 = 400;
        x4 = 400;
        y4 = 220;
        velObstaculoX4 = 6;
        velObstaculoX3 = 8;
        velObstaculoX = 8;
        timer = new Timer(10, this);
        timer.start();
        JOptionPane.showMessageDialog(this, "Eres el cuadrado rojo, tu objetivo es llegar hasta el final de la pantalla evitando los cuadrados verdes, tienes 3 vidas que verás en la parte superior de la pantalla");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujar(g);
    }

    private void dibujar(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 30, 30);

        g.setColor(Color.GREEN);
        g.fillRect(x2, y2, 30, 30);

        g.setColor(Color.GREEN);
        g.fillRect(x3, y3, 30, 30);

        g.setColor(Color.GREEN);
        g.fillRect(x4, y4, 30, 30);

        g.setColor(Color.red);
        g.fillOval(xVida1, 0, 20, 20);

        g.setColor(Color.red);
        g.fillOval(xVida2, 0, 20, 20);

        g.setColor(Color.red);
        g.fillOval(xVida3, 0, 20, 20);
    }

    private boolean ganar(){
        if (y >= meta)
        return true;
        else return false;
    }
    
    private boolean comprobarColision(int x, int y, int x2, int y2, int x3, int y3, int x4, int y4) {
        if (x + anchoCuadrado > x2 &&
            x < x2 + anchoCuadrado &&
            y + altoCuadrado > y2 &&
            y < y2 + altoCuadrado) {
            return true;
        }

        if (x + anchoCuadrado > x3 &&
            x < x3 + anchoCuadrado &&
            y + altoCuadrado > y3 &&
            y < y3 + altoCuadrado) {
            return true;
        }

        if (x + anchoCuadrado > x4 &&
            x < x4 + anchoCuadrado &&
            y + altoCuadrado > y4 &&
            y < y4 + altoCuadrado) {
            return true;
        }

        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mover();
        moverObstaculo();
        if (!haColisionado && comprobarColision(x, y, x2, y2, x3, y3, x4, y4)) {
            haColisionado = true;
            JOptionPane.showMessageDialog(this, "Has golpeado un obstaculo.");
            x = 390;
            y = 20;
            if (contVidas == 3) {
                contVidas--;
                xVida1 = 900;
            } else if (contVidas == 2) {
                contVidas--;
                xVida2 = 900;
            } else if (contVidas == 1) {
                contVidas--;
                xVida3 = 900;
                JOptionPane.showMessageDialog(this, "Has perdido");
                System.exit(0);
            }
        } else {
            haColisionado = false; 
        }

        if(ganar()){
            JOptionPane.showMessageDialog(this, "Has ganado!");
            System.exit(0);
        }else
        repaint();
        
    }

    private void mover() {
        if (x + velX >= 0 && x + velX <= limiteX - anchoCuadrado) {
            x += velX;
        }
        if (y + velY >= 0 && y + velY <= limiteY - altoCuadrado) {
            y += velY;
        }
    }

    private void moverObstaculo() {
        x2 += velObstaculoX;

        if (x2 >= limiteX - anchoCuadrado) {
            velObstaculoX = -8;
        }

        if (x2 <= 0) {
            velObstaculoX = 8;
        }

        x3 += velObstaculoX3;

        if (x3 >= limiteX - anchoCuadrado) {
            velObstaculoX3 = -8;
        }

        if (x3 <= 0) {
            velObstaculoX3 = 8;
        }

        x4 += velObstaculoX4;

        if (x4 >= limiteX - anchoCuadrado) {
            velObstaculoX4 = -6;
        }

        if (x4 <= 0) {
            velObstaculoX4 = 6;
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                velX = -2;
            }

            if (key == KeyEvent.VK_RIGHT) {
                velX = 2;
            }

            if (key == KeyEvent.VK_UP) {
                velY = -2;
            }

            if (key == KeyEvent.VK_DOWN) {
                velY = 2;
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
                velX = 0;
            }

            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
                velY = 0;
            }

        }

    }
}
