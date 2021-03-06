/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import java.awt.Point;

/**
 *
 * @author Olof
 */
public class Door implements DrawableWorldObjects{
    private float x,y,width,height;
    private String textureName;
    private String leadsToLevel;
    private Point nextLvlStartPoint;
    
    public Door(float x, float y, String leadsToLevel, Point nextLvlStartPoint){
        this.x=x; this.y=y;
        this.width = 32;
        this.height = 32;
        this.leadsToLevel=leadsToLevel;
        this.textureName="door";
        this.nextLvlStartPoint=nextLvlStartPoint;
    }
    
    
    public boolean contains(float x, float y){
        return x>=this.x && x<=(this.x+width) && y>=this.y && y<=(this.y+height);
    }
    
    public String getConnectedLevelKey(){
        return leadsToLevel;
    }
    
    public Point getNextLvlStartPoint(){
        return nextLvlStartPoint;
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
