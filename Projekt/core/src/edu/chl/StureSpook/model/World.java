/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import edu.chl.StureSpook.Options;
import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;




/**
 *
 * @author emmafahlen
 */
public class World implements GameModel {
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private Level[] levels;
    private Player player;    
    private Flashlight flashlight;
    private int currentLevel;
    private Options options;
    private Inventory inventory;
    private boolean interactOnNextUpdate;
    
    public World(){
        currentLevel = 1;
        levels = new Level[5];
        levels[0] = new Level("testTileMap");
        levels[1] = new Level("overworld");
        levels[2] = new Level("woodenHouse");
        levels[3] = new Level("brickHouse");
        levels[4] = new Level("brickHouseLevel2");
        
        player = new Player();
        player.setX(50);
        player.setY(50);
        flashlight = new Flashlight();
        options = Options.getInstance();
        inventory = new Inventory();
    }
    
    @Override
    public void init(){
        this.initLevels();
    }
    
    //If player collides with enemy, increase death count by one
    public void playerTakesHarm(){
        for(DrawableWorldObjects dwo : getCurrentLevel().getDrawableObjects()){
            if(dwo.getClass() == ActiveEnemies.class || dwo.getClass() == DeadlyObsticles.class){
                if(util.collide(player, dwo)){
                    player.deathCounter(1);
                    playerFromEnemy(player, dwo);
                }
            }
        }
    }
    
    public void playerGetsLife(){
        HeartItem itemToRemove = null;
        for(DrawableWorldObjects dwo : getCurrentLevel().getDrawableObjects()){
            if(dwo.getClass() == HeartItem.class){
                HeartItem he = (HeartItem) dwo;
                if(util.collide(player, he)){
                    player.deathCounterMinus();
                    itemToRemove = he;
                }
            }
        }
        if (itemToRemove != null) {
            getCurrentLevel().removeItem(itemToRemove);
        }
    }
    
    public void enemyAction(){
        for(DrawableWorldObjects dwo : getCurrentLevel().getDrawableObjects()){
            if(dwo.getClass() == ActiveEnemies.class){
                ActiveEnemies ae = (ActiveEnemies) dwo;
                ae.act();
            }
        }
    }
    
    //Pushes player from enemy when they have collided
    public void playerFromEnemy(Player player, DrawableWorldObjects object){
        for(DrawableWorldObjects dwo : getCurrentLevel().getDrawableObjects()){
            if(dwo.getClass() == ActiveEnemies.class){
                if (player.getX()>object.getX()){
                    player.setDX(0);
                    player.setX(player.getX() + 4);
                }
                else if (player.getX()<object.getX()){
                    player.setDX(0);
                    player.setX(player.getX() - 4);
                }
            }
        }
    }
    public void die(){
        //System.out.println("Player is DEAD");
    }
    
    @Override
    public void update(){
        
        playerTakesHarm();
        
        enemyAction();
        playerGetsLife();
        
        if (player.isDead()){
            die();
        }
        
        if (interactOnNextUpdate) {
            this.playerInteract();
            interactOnNextUpdate = false;
        }
        
        player.updateMotion();
        
        applyCollision(player, this.getCurrentLevel());
        flashlight.setStartPoint(player.getX()+10, player.getY()+10);
        pcs.firePropertyChange("logic updated", 1, 0);
    }
    
    public void setMoveLeft(boolean t) {
        player.setMoveLeft(t);
    }

    public void setMoveRight(boolean t){
        player.setMoveRight(t);
    }

    public void setJump(){
        player.setJump();
    }
    
    public void setFlashlightPosition(int x, int y) {
        flashlight.setEndPoint(x, y);
    }
   
    public Player getPlayer(){
        return player;
    }

    public float[] getFlashlightPolygon() {
        return flashlight.getPolygon();
    }

    public Level getCurrentLevel() {
        return levels [currentLevel];
    }
    
    @Override
    public void addPropertyChangeListener(PropertyChangeListener l){
        pcs.addPropertyChangeListener(l);
    }
    
    @Override
    public void removePropertyChangeListener(PropertyChangeListener l){
        pcs.removePropertyChangeListener(l);
    }
    
    public void initLevels(){
        levels[currentLevel].init();
        
        //As of now you only need to update this once 
        flashlight.updateCollidableMap(levels[currentLevel].getCollidableMap());
    }
    
    public void deinitLevel(int level){
        levels[level].deinit();
    }

    public void setCrouch(boolean t) {
        if(options.getCrouchToggle() && t){
            player.toggleCrouch();
        } else if(!options.getCrouchToggle()){
            player.setCrouch(t);         
        } 
    }
    
    private void applyCollision(Player player, Level l) {
        int[][] tilemap = l.getTileMap();
        
        float xLowerLimit = 0;
        float xUpperLimit = 1e9f;
        float yLowerLimit = 0;
        float yUpperLimit = 1e9f;
        
        
        Point[] points;
        if (!player.getCrouch()) {
               points = new Point[]{
            //left points
            new Point(0,(int)(0.25*player.getHeight()) ),
            new Point(0,(int)(0.75*player.getHeight()) ),
            
            //right points
            new Point((int)player.getWidth(),(int)(0.25*player.getHeight()) ),
            new Point((int)player.getWidth(),(int)(0.75*player.getHeight()) ),
            
            //top and bottom middle points
            new Point((int)player.getWidth()/2,(int)player.getHeight()),
            new Point((int)player.getWidth()/2,0)
            
            };
        } else {
            points = new Point[]{
            //top and bottom middle points
            new Point((int)player.getWidth()/2,0),
            new Point((int)player.getWidth()/2,(int)player.getHeight()),
            
            //left point
            new Point(0,(int)(0.5*player.getHeight()) ),
            
            //right point
            new Point((int)player.getWidth(),(int)(0.5*player.getHeight()) )
            };
        }
        
        //first check bottom middle point
        for (Point p : points) {
            float currentX = p.x + player.getX();
            float currentY = p.y + player.getY();
            //if (tilemap[util.floatToTile(currentX)][util.floatToTile(currentY)] != -1) {
            if (this.getCurrentLevel().isCollidable(util.floatToTile(currentX), util.floatToTile(currentY))) {
                if (p.y == 0) { //if point is below player center
                    //handle as bottom point
                    yLowerLimit = Math.max( yLowerLimit, (util.floatToTile(currentY)*16+16) );
                    //continue;
                } else if (p.y == player.getHeight()){ //if point is above player center
                    //handle as top point
                    yUpperLimit = Math.min( yUpperLimit, (util.floatToTile(currentY)*16) -player.getHeight());
                    //continue;
                }
                
                if (p.x == 0) { //if point is right of player center
                    //handle as right point
                    xLowerLimit = Math.max(xLowerLimit, (util.floatToTile(currentX)*16+16));
                } else if (p.x == player.getWidth()){ //if point is left of player center
                    //handle as left point
                    xUpperLimit = Math.min(xUpperLimit, (util.floatToTile(currentX-16)*16));
                }
                
                
                
                
                
            }
            
        }
        
        player.setX(Math.max(player.getX(),xLowerLimit));
        player.setX(Math.min(player.getX(),xUpperLimit));
        player.setY(Math.max(player.getY(),yLowerLimit));
        player.setY(Math.min(player.getY(),yUpperLimit));
        
        if (player.getY() == yLowerLimit) {
            player.setDY(0.0f);
        }
        
        if (tilemap[util.floatToTile(player.getX()+10)][util.floatToTile(player.getY()-4)] != -1) { 
            player.setOnGround(true);
        } else {
            player.setOnGround(false);
        }
        
    }
    
    private void changeLevel(int newLevel){ 
        int lastLevel = currentLevel;
        currentLevel = newLevel;
        initLevels();
        deinitLevel(lastLevel);
    }
    
    public void changeLevel(String levelName, Point goalPoint) {
        for (int i = 0;i<levels.length;i++) {
            if (levelName.equals(levels[i].getMapName())) {
                changeLevel(i);
                player.setX(goalPoint.x);
                player.setY(goalPoint.y);
            }
        }
    }
    
    public void setInteract(){
        this.interactOnNextUpdate = true;
    }
    
    private void playerInteract(){
        DrawableWorldObjects itemToGet = null;
        for (DrawableWorldObjects dwo : getCurrentLevel().getDrawableObjects()) {
            if (util.collide(player,dwo)) {
                if (dwo.getClass() == Door.class) {
                    Door door = (Door)dwo;
                    changeLevel(door.getConnectedLevelKey(), door.getNextLvlStartPoint());
                } else if (dwo.getClass() == WorldItem.class) {
                    itemToGet = dwo;
                }
            }
        }
        if (itemToGet != null) {
            getItem(itemToGet);
        }
        
    }
    
    private void getItem(DrawableWorldObjects dwo) {
        WorldItem itemToRemove = null;
        if(dwo.getClass() == WorldItem.class){
            WorldItem item = (WorldItem) dwo;
            if(util.collide(player, item)){
                inventory.addItem(item.getItem());
                itemToRemove = item;
            }
        }
        if (itemToRemove != null) {
            getCurrentLevel().removeItem(itemToRemove);
        }
    }
    
    public Inventory getInventory() {
        return this.inventory;
    }
    

}
