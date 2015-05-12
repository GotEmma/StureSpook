/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * @author emmafahlen
 */
public class Level {

    private float width;
    private float height;
    private String mapFileName;
    private String mapTextureName;
<<<<<<< HEAD
    private int[][] tileMap;
    private final String backgroundImageName;

=======
    private DeadlyObsticles spider;
    private ActiveEnemies spikes;
    
>>>>>>> againamy
    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public Level(String mapFileName,String backgroundImageName) {
        this.backgroundImageName = backgroundImageName;
        this.mapFileName = mapFileName;
        this.width = 1000; //Set using constructor later?
        this.height = 600;
    }
<<<<<<< HEAD

    public String getBackgroundImageName(){
        return backgroundImageName;
=======
    
    public Enemy createEnemy(String deadly, float x, float y){
        if (deadly == "spider"){            
            return createSpider(x,y);
        }
        if (deadly == "spikes"){
            return createSpikes(x,y);
        }
        else {return createSpider(x,y);}
    }
    
    public DeadlyObsticles createSpider(float x, float y){
        return spider = new DeadlyObsticles(x,y);
    }
    
    public ActiveEnemies createSpikes(float x, float y){
        return spikes = new ActiveEnemies(x,y);
    }
    
    public TiledMap getMap(){
        return map;
>>>>>>> againamy
    }
    
    public String getMapTextureName() {
        return mapTextureName;
    }

    public void init() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(mapFileName));
            String line = "";
            char split = ',';
            int lineNbr = 0;
            int tileWidth = 0;
            int tileHeight = 0;

            while ((line = br.readLine()) != null) {
                if (lineNbr == 0) {
                    mapTextureName = line;
                    lineNbr++;
                } else if (lineNbr == 1) {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) != split) {
                            builder.append(line.charAt(i));
                        } else {
                            tileWidth = Integer.parseInt(builder.toString());
                            builder.delete(0, builder.length());
                        }
                    }
                    tileHeight = Integer.parseInt(builder.toString());
                    lineNbr++;
                    tileMap = new int[tileWidth][tileHeight];
                } else {
                    StringBuilder builder = new StringBuilder();
                    int tileWidthNbr = 0;
                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) != split) {
                            builder.append(line.charAt(i));
                        } else {
                            tileMap[tileWidthNbr][lineNbr-2] = Integer.parseInt(builder.toString());
                            builder.delete(0, builder.length());
                            tileWidthNbr++;
                        }
                    }
                    lineNbr++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public int[][] getTileMap(){
        return tileMap;
    }
}
