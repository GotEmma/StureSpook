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
public class DeadlyObsticles implements Enemy{
    private float x,y;
    private String textureName = "DeadlyObsticles";
    //private Rectangle shape;
   // private Image image;
   // private Graphics graphics;
    
    public DeadlyObsticles(float x, float y){
        this.x = x;
        this.y = y;
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
    
    public void setX(float x){
        this.x = x;
    }
    
    public void setY(float y){
        this.y = y;
    }
}
