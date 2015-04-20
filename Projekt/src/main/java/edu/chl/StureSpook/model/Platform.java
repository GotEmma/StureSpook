/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Olof
 */
public class Platform {
    
    private float x,y,width,height;
    private Rectangle shape;
    private Image image;
    private Graphics graphics;
    
    public Platform(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        shape = new Rectangle(x,y,width,height);
        
        try{
            image = new Image((int)shape.getWidth(),(int)shape.getHeight());
            graphics = image.getGraphics();
            
        }catch(SlickException e){
            e.printStackTrace();
        }
        graphics.setColor(Color.yellow);
        graphics.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
    }
    
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public float getWidth(){
        return width;
    }
    public float getHeight(){
        return height;
    }
    
    public Image getImage(){
        graphics.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
        return image;
    }
}
