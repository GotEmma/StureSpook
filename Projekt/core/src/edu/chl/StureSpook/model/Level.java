/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 *
 * @author emmafahlen
 */
public class Level {
    private float width;
    private float height;
    private TiledMap map;
    private String mapFileName;
    private String mapTextureName;
    
    public float getWidth() {
        return this.width;
    }
    
    public float getHeight() {
        return this.height;
    }
    
    public Level(String mapFileName, String mapTextureName){
        this.mapTextureName = mapTextureName;
        this.mapFileName = mapFileName;  
        this.width = 1000; //Set using constructor later?
        this.height = 600;
    }
    
    public TiledMap getMap(){
        return map;
    }
    
    public String getMapTextureName(){
        return mapTextureName;
    }
    
    public void init(){
        map = new TmxMapLoader().load(mapFileName);
    }
}
