/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author emmafahlen
 */
public class Enemy {
    private float x,y,dx,dy,ddy;
    private String textureName = "Enemy";
    //private Rectangle shape;
    private Image image;
    private Graphics graphics;
    
    public Enemy(){
        this.x = 0;
        this.y = 0;
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
