/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

/**
 *
 * @author Linneas
 */
public class MenuItem {
    private String name;
    private String textureName;
    private int key;
    
    public MenuItem(String name, String textureName){
        this.name = name;
        this.textureName = textureName;
    
    }
    public MenuItem(String name, String textureName, int key){
        this(name, textureName);
        this.key = key;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public String getTextureName() {
        return this.textureName;
    }
    
}
