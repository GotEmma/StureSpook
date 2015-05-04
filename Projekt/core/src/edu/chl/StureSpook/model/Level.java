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
public class Level {
    private String textureName;
    private float width;
    private float height;
    
    public float getWidth() {
        return this.width;
    }
    
    public float getHeight() {
        return this.height;
    }
    
    public Level(String textureName){
        this.textureName=textureName;  
        this.width = 1000; //Set using constructor later?
        this.height = 600;
    }
    
    public String getMapTextureName(){
        return textureName;
    }
}
