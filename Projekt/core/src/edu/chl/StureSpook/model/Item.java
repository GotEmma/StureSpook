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
public class Item implements DrawableWorldObjects {
    
    private String name;
    private String textureName;
    private float height;
    private float width;
    private float x;
    private float y;
    
    public Item(String name, String textureName, float height, float width, float x, float y){
        this.name = name;
        this.textureName = textureName;
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
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
    
}
