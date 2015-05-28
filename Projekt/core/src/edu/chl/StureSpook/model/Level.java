/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author emmafahlen
 */
public class Level {

    private float width;
    private float height;
    private int tileWidth;
    private int tileHeight;
    private String mapFileName;
    private String mapTextureName;
    private int[][] tileMap;
    private final String backgroundImageName;
    private DeadlyObsticles spikes;
    private ActiveEnemies spider;
    private int[] collisionValues;
    private boolean[][] collidableMap;
    private List<DrawableWorldObjects> DrawableObjects;
    //private List<ActiveEnemies> ActiveEnemies;

    public List<DrawableWorldObjects> getDrawableObjects(){
        return DrawableObjects;
    }
    
    //public List<ActiveEnemies> getActiveEnemies(){
    //    return ActiveEnemies;
    //}
    
    public DeadlyObsticles getSpikes(){
        return spikes;
    }
    
    public ActiveEnemies getSpider(){
        return spider;
    }
    
    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public int getTileWidth() {
        return this.tileWidth;
    }

    public int getTileHeight() {
        return this.tileHeight;
    }

    public Level(String mapFileName, String backgroundImageName) {
        this.backgroundImageName = backgroundImageName;
        this.mapFileName = mapFileName;
        this.width = 1000; //Set using constructor later?
        this.height = 600;
    }

    public String getBackgroundImageName() {
        return backgroundImageName;
    }

    //Creates enemies and adds them to an arraylist 
    public void createEnemy(String enemy, float x, float y, float height, float width, float endX) {
        if (enemy == "spider") {
            DrawableObjects.add(createSpikes(enemy, x, y, height, width, endX));
            //ActiveEnemies.add(createSpider(x, y, enemy, height, width));
        }
        if (enemy == "spikes") {
            DrawableObjects.add(createSpikes(enemy, x, y, height, width, endX));
        }
    }
    //SKA VARA SPIDER!!!!!!
    public ActiveEnemies createSpikes(String str, float x, float y, float height, float width, float endX) {
        return spider = new ActiveEnemies(str, x, y, height, width, endX);
    }

    //public DeadlyObsticles createSpikes(float x, float y, String str, float height, float width) {
    //    return spikes = new DeadlyObsticles(str, x, y, height, width);
    //}

    public String getMapTextureName() {
        return mapTextureName;
    }

    public void init() {
        DrawableObjects = new ArrayList<DrawableWorldObjects>();
        //ActiveEnemies = new ArrayList<ActiveEnemies>();
        //createEnemy("spikes", 200, 64, 30, 30);
        createEnemy("spikes", 100, 64, 30, 30, 200);
        
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(mapFileName));
            String line = "";
            char split = ',';
            int lineNbr = 0;
            int tWidth = 0;
            int tHeight = 0;

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
                            tWidth = Integer.parseInt(builder.toString());
                            builder.delete(0, builder.length());
                        }
                    }
                    tHeight = Integer.parseInt(builder.toString());
                    lineNbr++;
                    tileMap = new int[tWidth][tHeight];
                    this.tileWidth = tWidth;
                    this.tileHeight = tHeight;
                } else if(lineNbr < tileHeight -2){
                    StringBuilder builder = new StringBuilder();
                    int tileWidthNbr = 0;
                    for (int j = 0; j < line.length(); j++) {
                        if (line.charAt(j) != split) {
                            builder.append(line.charAt(j));
                        } else {
                            tileMap[tileWidthNbr][tileHeight - 1 -(lineNbr - 2)] = Integer.parseInt(builder.toString());
                            builder.delete(0, builder.length());
                            tileWidthNbr++;
                        }
                    }
                    lineNbr++;
                }
                else{
                    //Add further data to the level here 
                    //such as enemies, items or moving platforms
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[][] getTileMap() {
        return tileMap;
    }

    // Kom ihåg att fixa inmatning av kollisionsvärden från csv filen 
    public boolean isCollidable(int x, int y) {
        return tileMap[x][y] != -1;
        /*
         for (int i = 0; i < collisionValues.length; i++) {
         if (tileMap[x][y] == collisionValues[i]) {
         return true;
         }
         }
         return false;
         */
    }

    public boolean isValueCollidable(int value) {
        for (int i = 0; i < collisionValues.length; i++) {
            if (collisionValues[i] == value) {
                return true;
            }
        }
        return false;
    }

    //Returns a boolean matrix which gives the position of collidable tiles 
    //from the tileMap.
    public boolean[][] getCollidableMap() {
        if (collidableMap == null) {
            collidableMap = new boolean[tileWidth][tileHeight];

            for (int i = 0; i < tileWidth; i++) {
                for (int j = 0; j < tileHeight; j++) {
                    if (isCollidable(i, j)) {
                        collidableMap[i][j] = true;
                    }
                }
            }
        }
        return collidableMap;
    }

    //Makes a maps values ready for garbage disposal
    //Needs to be updated when more arrays of objects are added to the level.
    void deinit() {
        tileMap = null;
        collisionValues = null;
        collidableMap = null;
    }
}
