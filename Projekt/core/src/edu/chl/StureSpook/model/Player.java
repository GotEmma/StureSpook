/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;



/**
 *
 * @author emmafahlen
 */
public class Player {
    private float x,y;
    //private Rectangle shape;
    //private Image image;
    //private Graphics graphics;
    
    public Player(){
        x = 0; y = 0;
        //shape = new Rectangle(x,y,20,20);
        
        
        //image = new Image((int)shape.getWidth(),(int)shape.getHeight());
        //graphics = image.getGraphics();
        
        //graphics.setColor(Color.yellow);
        //graphics.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
    }
    
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }

    //public Image getImage() {
    //    graphics.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
    //    return image;
    //}
}
