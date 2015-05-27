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
    private float x,y;
    private String textureName;
    //private Rectangle shape;
   // private Image image;
   // private Graphics graphics;
    
    public DeadlyObsticles(String str, float x, float y){
        this.x = x;
        this.y = y;
        this.textureName = str;
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
}
