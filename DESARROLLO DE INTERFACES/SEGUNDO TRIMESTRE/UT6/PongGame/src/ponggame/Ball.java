/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Timer;
/**
 *
 * @author Tarde
 */
public class Ball {
    private int x, y, width, height;
    private int xSpeed = 3, ySpeed = 3;

    public Ball(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void move(){
        x += xSpeed;
        y += ySpeed;
    }
    
    public void checkCollision(Paddle p1, Paddle p2, int screenWidth, int screenHeight){
        if(y <= 0 || y + height >= screenHeight)
            ySpeed *= -1;
        
        if(x <= p1.getX() + p1.getWidth() && y + height >= p1.getY() && y <= p1.getY() + p1.getHeight()){
            xSpeed *= -1;
        }
        
        if(x + width >= p2.getX() && y + height >= p2.getY() && y <= p2.getY() + p2.getHeight()){
            xSpeed *= -1;
        }
            
    }
    
    public void draw(Graphics g){
            g.setColor(Color.WHITE);
            g.fillOval(x, y, width, height);
        }
    
    public void aumentarVelocidad(){
        if(xSpeed  > 0){
            xSpeed += 1;
        }else{
            xSpeed -= 1;
        }
        
        if(ySpeed > 0){
            ySpeed += 1;
        }else{
            ySpeed -= 1;
        }
    }
    
    public int checkOutOfBounds(int screenWidth, int screenHeight){
        if(x > screenWidth){
            return 1;
        }
        
        if(x < 0){
            return 2;
        }
        return 0;
    }
    
    public void resetSpeed(){
        
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
    
}
