/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

/**
 *
 * @author Olof
 */
public class Door{
    float x,y,width,height;
    String textureName;
    int connectedLevel;
    
    public Door(float x, float y, int connectedLevel, String textureName){
        this.x=x; this.y=y;
        this.connectedLevel=connectedLevel;
        this.textureName=textureName;
    }
    
    
    public boolean contains(float x, float y){
        return x>=this.x && x<=(this.x+width) && y>=this.y && y<=(this.y+height);
    }
    
    public int getConnectedLevelNumber(){
        return connectedLevel;
    }
}
