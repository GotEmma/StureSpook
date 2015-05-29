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
public class ActiveEnemies implements DrawableWorldObjects {

    private float x,y,height, width, startX, endX;
    private String textureName;
    private boolean goingRight;
    
    public ActiveEnemies(String str, float x, float y, float height, float width, float endX) {
        this.x = x;
        this.y = y;
        this.textureName = str;
        this.height = height;
        this.width = width;
        this.startX = x;
        this.endX = endX;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
    
    public float getEndX(){
        return endX;
    }

    @Override
    public String getTextureName() {
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
    
    public boolean goingRight(){
        return goingRight;
    }
    
    public void act(){
        if(goingRight){
            x++;
            if (x >= endX) {
                goingRight = false;
            }
        } else {
            x--;
            if (x <= startX) {
                goingRight = true;
            }
        }
    }
}
 