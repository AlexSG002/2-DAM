/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Tarde
 */
public class Paddle {
    private int x, y, width, height, speed = 5, direction = 0;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void move(int screenHeight){
        y += direction * speed;
        if(y < 0) y = 0;
        if(y + height > screenHeight) y = screenHeight - height;
    }
    
    public void setDirection(int direction){
        this.direction = direction;
    }
    
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
    
}
