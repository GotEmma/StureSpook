/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import java.awt.Point;
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

    private int tileWidth;
    private int tileHeight;
    private String mapName;
    private String mapFileName;
    private String mapTextureName;
    private int[][] tileMap;
    private final String skyBackgroundTextureName;
    private final String backgroundTextureName;
    private HeartItem heart;
    private int[] collisionValues;
    private boolean[][] collidableMap;
    private List<DrawableWorldObjects> DrawableObjects;
    //private List<ActiveEnemies> ActiveEnemies;

    
    public Level(String mapName) {
        this.skyBackgroundTextureName = "skyBackground";
        this.backgroundTextureName = "background";
        this.mapName = mapName;
        this.mapFileName = mapName + ".csv";
    }
    
    public List<DrawableWorldObjects> getDrawableObjects(){
        return DrawableObjects;
    }
    
    public void removeItem(DrawableWorldObjects dwo) {
        if (this.DrawableObjects.contains(dwo)){
            this.DrawableObjects.remove(dwo);
        }
    }
    
    public String getMapName(){
        return this.mapName;
    }
    
    public float getWidth() {
        return this.tileWidth*16;
    }

    public float getHeight() {
        return this.tileHeight*16;
    }

    public int getTileWidth() {
        return this.tileWidth;
    }

    public int getTileHeight() {
        return this.tileHeight;
    }


    public String getBackgroundImageName() {
        return backgroundTextureName;
    }
    
    public String getSkyBackgroundImageName() {
        return skyBackgroundTextureName;
    }

    //Creates enemies and adds them to an arraylist 
    public void createEnemy(String enemy, float x, float y, float height, float width, float endX) {
        if (enemy.equals("spider")) {
            DrawableObjects.add(createSpider(enemy, x, y, height, width, endX));
        }
        if (enemy.equals("spikes")) {
            DrawableObjects.add(createSpikes(enemy, x, y, height, width));
        }
    }
    
    public static ActiveEnemies createSpider(String str, float x, float y, float height, float width, float endX) {
        return new ActiveEnemies(str, x, y, height, width, endX);
    }

    public static DeadlyObsticles createSpikes(String str, float x, float y, float height, float width) {
        return new DeadlyObsticles(str, x, y, height, width);
    }
    
    public void createHeart(float x, float y, float width, float height){
        DrawableObjects.add(heart = new HeartItem(x,y,width,height));
    }

    public String getMapTextureName() {
        return mapTextureName;
    }
    
   

    public void init() {
        DrawableObjects = new ArrayList<DrawableWorldObjects>();
        parseLevel();
    }
    
    private void parseLevel() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("levels/"+mapFileName));
            String[] lineSplit;
            String regex = ",";
            int tWidth = 0;
            int tHeight = 0;
            
            //first line is map texture name
            mapTextureName = br.readLine();
            
            //second line is tile map size
            lineSplit = br.readLine().split(regex);
            tWidth = Integer.parseInt(lineSplit[0]);
            tHeight = Integer.parseInt(lineSplit[1]);
            tileMap = new int[tWidth][tHeight];
            this.tileWidth = tWidth;
            this.tileHeight = tHeight;
            
            //third line is collidable tiles
            lineSplit = br.readLine().split(regex);
            this.collisionValues = new int[lineSplit.length];
            for (int i = 0;i < lineSplit.length;i++) {
                this.collisionValues[i] = Integer.parseInt(lineSplit[i]);
            }
            
            //next tHeight number of lines is map data
            for (int y=0; y < tHeight;y++) {
                lineSplit = br.readLine().split(regex);
                for (int x=0; x < tWidth; x++) {
                    tileMap[x][tHeight - (y+1)] = Integer.parseInt(lineSplit[x]);
                }
            }
            
            //next line is number of objects
            String objectCount = br.readLine();
            
            //exit if no objects
            if (objectCount == null) { return; }
            
            int numObjects = Integer.parseInt(objectCount);
            
            //read numObjects lines and parse them as enemies
            for (int i=0; i< numObjects;i++) {
                parseEnemy(br.readLine().split(regex));
                //syntax:
                //heart: "heart,x,y"
                //door: "door,x,y,levelName,targetX,targetY"
            }
            
            
            br.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void parseEnemy(String[] args) {
        if (args[0].equals("heart")) {
            this.createHeart(Integer.parseInt(args[1]), Integer.parseInt(args[2]), 10, 10);
        } else if (args[0].equals("door")) {
            this.addDoor(Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3], new Point(Integer.parseInt(args[4]), Integer.parseInt(args[5])));
        }
    }
    
    private void addDoor(int x, int y, String leadsToLevel, Point nextStartPoint) {
        this.DrawableObjects.add(new Door(x,y,leadsToLevel,nextStartPoint));
    }

    public int[][] getTileMap() {
        return tileMap;
    }

    // Kom ihåg att fixa inmatning av kollisionsvärden från csv filen 
    public boolean isCollidable(int x, int y) {
        /*for (int i = 0; i < collisionValues.length; i++) {
            if (tileMap[x][y] == collisionValues[i]) {
                return true;
            }
        }
        return false;*/
        return tileMap[x][y] != -1;
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
