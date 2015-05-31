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
public class WorldItem implements DrawableWorldObjects{
    private float x,y, height, width;
    private String textureName;
    private String itemName;
    private Item item;
    
    public WorldItem(String itemName, String textureName, float x, float y){
        this.height = 32;
        this.width = 32;
        this.x = x;
        this.y = y;
        this.textureName = textureName;
        this.itemName = itemName;
        this.item = new Item(itemName,textureName);
    }
    
    @Override
    public float getX(){
        return x;
    }
    
    @Override
    public float getY(){
        return y;
    }
    
    
    @Override
    public String getTextureName(){
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
    
    public String getItemName() {
        return this.itemName;
    }
    
    public Item getItem() {
        return this.item;
    }
}
