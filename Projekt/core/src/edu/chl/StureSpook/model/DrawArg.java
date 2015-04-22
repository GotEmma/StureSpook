/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

/**
 *
 * @author Née
 */
public class DrawArg {
    private final String texture;
    private float x,y;
    
    public DrawArg(String textureName, float posX, float posY) {
        this.texture = textureName;
        this.x = posX;
        this.y = posY;
    }
    
    public String getTextureName() { return this.texture; }
    public float getX() { return this.x; }
    public float getY() { return this.y; }
}
