/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 *
 * @author Tarde
 */
public class GamePanel extends JPanel implements Runnable, KeyListener, ActionListener{
    private Image backgroundImage;
    private final int WIDTH = 800, HEIGHT = 600;
    private Thread gameThread;
    private boolean running;
    private Ball ball;
    private Paddle player1, player2;
    private Timer timer;
    private int player1Score = 0;
    private int player2Score = 0;
    
    private boolean player1ReducedAt5 = false;
    private boolean player1ReducedAt10 = false;
    private boolean player2ReducedAt5 = false;
    private boolean player2ReducedAt10 = false;
    
    public GamePanel(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
        setFocusable(true);
        ball = new Ball(WIDTH /2, HEIGHT /2, 20, 20);
        player1 = new Paddle(10, HEIGHT /2 - 60, 10, 120);
        player2 = new Paddle(WIDTH -20, HEIGHT /2 - 60, 10, 120);
        timer = new Timer(10000, this);
        timer.start();
        loadBackgroundImage();
        startGame();
    }

    private void loadBackgroundImage(){
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/ponggame/fondo.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            setBackground(Color.BLACK);
        }
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
        ball.checkCollision(player1, player2, WIDTH, HEIGHT);
        player1.move(HEIGHT);
        player2.move(HEIGHT);
        
        int score = ball.checkOutOfBounds(WIDTH, HEIGHT);
        if(score == 1){
            player1Score++;
            resetGame();
        } else if(score == 2){
            player2Score++;
            resetGame();
        }
        
        int alturaPlayer1 = player1.getHeight();
        int alturaPlayer2 = player2.getHeight();
        
        if(player1Score == 5 && !player1ReducedAt5){
        int nuevaAltura = player1.getHeight() - 20;
        if(nuevaAltura > 0){
            player1.setHeight(nuevaAltura);
            player1ReducedAt5 = true;
        }
    }   
        if(player1Score == 10 && !player1ReducedAt10){
        int nuevaAltura = player1.getHeight() - 20;
        if(nuevaAltura > 0){
            player1.setHeight(nuevaAltura);
            player1ReducedAt10 = true;
        }
    }
        
        if(player2Score == 5 && !player2ReducedAt5){
        int nuevaAltura = player2.getHeight() - 20;
        if(nuevaAltura > 0){
            player2.setHeight(nuevaAltura);
            player2ReducedAt5 = true;
        }
    }
        
        if(player2Score == 10 && !player2ReducedAt10){
        int nuevaAltura = player2.getHeight() - 20;
        if(nuevaAltura > 0){
            player2.setHeight(nuevaAltura);
            player2ReducedAt10 = true;
        }
    }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT, this);
        }
        ball.draw(g);
        player1.draw(g);
        player2.draw(g);
        
        
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString(String.valueOf(player1Score), WIDTH / 2 - 50, 50);
        g.drawString(String.valueOf(player2Score), WIDTH / 2 + 25, 50);
    }
    
    

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W) player1.setDirection(-1);
        if(key == KeyEvent.VK_S) player1.setDirection(1);
        if(key == KeyEvent.VK_UP) player2.setDirection(-1);
        if(key == KeyEvent.VK_DOWN) player2.setDirection(1);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W || key == KeyEvent.VK_S) player1.setDirection(0);
        if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) player2.setDirection(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.aumentarVelocidad();
    }
    
    private void resetGame(){
        ball.setX(WIDTH / 2 - ball.getWidth() / 2);
        ball.setY(HEIGHT / 2 - ball.getHeight() / 2);
        
        ball.resetSpeed();
    }
    
}
