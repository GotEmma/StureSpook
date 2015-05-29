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
public class HeartItem implements DrawableWorldObjects{
    private float x, y, width, height;
    private String textureName = "smallHeart";
    
    public HeartItem(float x, float y, float width, float height){
        this.x=x;
        this.y=y;
        this.height=height;
        this.width=width;
        this.textureName = "smallHeart";
    }
    
    
    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public String getTextureName() {
        return textureName;
    }
    
}
