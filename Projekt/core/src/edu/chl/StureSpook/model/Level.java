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
    private DeadlyObsticles spider;
    
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
    
    public DeadlyObsticles getCurrentEnemy(String deadly, float x, float y){
        if (deadly == "spider"){            
            return CreateSpider(x,y);
        }
        else {return CreateSpider(x,y);}
    }
    
    public DeadlyObsticles CreateSpider(float x, float y){
        return spider = new DeadlyObsticles(x,y);
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
