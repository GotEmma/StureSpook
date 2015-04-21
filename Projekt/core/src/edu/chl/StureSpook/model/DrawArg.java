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
    private String texture;
    private int x,y;
    
    public DrawArg(String textureName, int posX, int posY) {
        this.texture = texture;
        this.x = posX;
        this.y = posY;
    }
    
    public String getTextureName() { return this.texture; }
    public int getX() { return this.x; }
    public int getY() { return this.y; }
}
