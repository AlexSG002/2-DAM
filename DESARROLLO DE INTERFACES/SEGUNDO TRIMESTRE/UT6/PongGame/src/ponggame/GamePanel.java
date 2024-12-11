/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;


/**
 *
 * @author Tarde
 */
public class GamePanel extends JPanel implements Runnable, KeyListener{
    private final int WIDTH = 800, HEIGHT = 600;
    private Thread gameThread;
    private boolean running;
    private Ball ball;
    private Paddle player1, player2;
    
    public GamePanel(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);
        ball = new Ball(WIDTH /2, HEIGHT /2, 20, 20);
        player1 = new Paddle(10, HEIGHT /2 - 60, 10, 120);
        player2 = new Paddle(WIDTH -20, HEIGHT /2 - 60, 10, 120);
        startGame();
    }

    private void startGame(){
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
        while(running){
            update();
            repaint();
            try{
                Thread.sleep(10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    
    private void update(){
        ball.move();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
