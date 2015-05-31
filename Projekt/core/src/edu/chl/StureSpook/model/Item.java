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
public class Item {
    
    private String name;
    private String textureName;
    
    public Item(String name, String textureName){
        this.name = name;
        this.textureName = textureName;
    }
    
    public String getTextureName() {
        return this.textureName;
    }
    
    public String getName() {
        return this.name;
    }
    

    
    
}
