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

    public List<DrawableWorldObjects> getDrawableObjects(){
        return DrawableObjects;
    }
    
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

    public void createEnemy(String enemy, float x, float y) {
        if (enemy == "spider") {
            DrawableObjects.add(createSpider(x, y, enemy));
        }
        if (enemy == "spikes") {
            DrawableObjects.add(createSpikes(x, y, enemy));
        }
    }

    public ActiveEnemies createSpider(float x, float y, String str) {
        return spider = new ActiveEnemies(str, x, y);
    }

    public DeadlyObsticles createSpikes(float x, float y, String str) {
        return spikes = new DeadlyObsticles(str, x, y);
    }

    public String getMapTextureName() {
        return mapTextureName;
    }

    public void init() {
        DrawableObjects = new ArrayList<DrawableWorldObjects>();
        createEnemy("spider", 32, 32);
        createEnemy("spikes", 50, 32);
        
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
}
