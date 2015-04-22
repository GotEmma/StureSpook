/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;



/**
 *
 * @author Olof
 */
public class Platform implements Drawable{
    
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
        
        //image = new Image(new Skin(),);
        //image = new Image((int)shape.getWidth(),(int)shape.getHeight());
        
        //graphics = image.getGraphics();
        
        //graphics.setColor(Color.yellow);
        //graphics.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
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
    
    //public Image getImage(){
    //    graphics.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
    //    return image;
    //}

    @Override
    public DrawArg getDrawArg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
