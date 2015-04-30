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
    
    public Level(String textureName){
        this.textureName=textureName;    
    }
    
    public String getMapTextureName(){
        return textureName;
    }
}
