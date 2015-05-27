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
public class DeadlyObsticles implements DrawableWorldObjects{
    private float x,y, height, width;
    private String textureName;
    //private Rectangle shape;
   // private Image image;
   // private Graphics graphics;
    
    public DeadlyObsticles(String str, float x, float y, float height, float width){
        this.x = x;
        this.y = y;
        this.textureName = str;
        this.height = height;
        this.width = width;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public String getTextureName(){
        return textureName;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public float getWidth() {
        return width;
    }
}
