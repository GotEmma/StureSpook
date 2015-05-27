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
class ActiveEnemies implements DrawableWorldObjects {

    private float x,y;
    private String textureName;
    
    public ActiveEnemies(String str, float x, float y) {
        this.x = x;
        this.y = y;
        this.textureName = str;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return x;
    }

    @Override
    public String getTextureName() {
        return textureName;
    }
    
}
