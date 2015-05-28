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
    private Level[] levels = new Level[1];
    private Player player;    
    private Flashlight flashlight;
    private int currentLevel;
    private Options options;
    
    public World(){
        currentLevel = 0;
        levels[0] = new Level("testTileMap.csv","skyMoonBackground");
        player = new Player();
        player.setX(50);
        player.setY(50);
        flashlight = new Flashlight();
        options = Options.getInstance();
    }
    
    @Override
    public void init(){
        this.initLevels();
    }
    
    //If player collides with enemy, increase death count by one
    public void playerTakesHarm(){
        for(DrawableWorldObjects dwo : getCurrentLevel().getDrawableObjects()){
            if(collide(player, dwo)){
                player.deathCounter(1);
                playerFromEnemy(player, dwo);
                System.out.println("DeathCount + 1");
            }
        }
    }
    //Pushes player from enemy when they have collided
    public void playerFromEnemy(Player player, DrawableWorldObjects object){
        if (player.getX()>object.getX()){
            player.setDX(0);
            player.setX(player.getX() + 4);
        }
        else if (player.getX()<object.getX()){
            player.setDX(0);
            player.setX(player.getX() - 4);
        }
    }
    public void die(){
        System.out.println("Player is DEAD");
    }
    
    @Override
    public void update(){
        
        playerTakesHarm();
        System.out.println(player.getDeathCount());
        
        if (player.isDead()){
            die();
        }
        
        player.updateMotion();
        
        applyCollision(player, this.getCurrentLevel());
        flashlight.setStartPoint(player.getX()+10, player.getY()+10);
        pcs.firePropertyChange("logic updated", 1, 0);
    }
    //Checks if player collides with any DrawableWorldObjects like enemys
    //on the X-axis and returns true if it does
    public boolean collideX(Player player, DrawableWorldObjects object){
        if (player.getX()>object.getX()-player.getWidth()) { //inte för litet
            if (player.getX()<object.getX()+object.getWidth()) { //inte heller för stort
                System.out.println("X true");
                return true;
                
            }
            
        }
        return false;
    }

    //Checks if player collides with any DrawableWorldObjects like enemys
    //on the Y-axis and returns true if it does
    public boolean collideY(Player player, DrawableWorldObjects object){
        if (player.getY()>object.getHeight()-player.getHeight()) { //inte för litet
            if (player.getY()<object.getY()+object.getHeight()) { //inte heller för stort
                System.out.println("Y true");
                return true;   
            } 
        }
        return false;
    }
    //Checks if player collides with any DrawableWorldObjects on both
    //the X-axis and the Y-axis, and returns true if it does
    public boolean collide(Player player, DrawableWorldObjects object){
        if(collideX(player, object) && collideY(player, object)){
            return true;
        }
        return false;
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
        float playerheight = 20;
        float playerwidth = 20;
        
        
        Point[] points = new Point[]{
            new Point(10,0), //bottom center
            new Point(10,20),//top center
            new Point(20,10),//center left
            new Point(0,10),//cencer right
        };
        
        //first check bottom middle point
        for (Point p : points) {
            float currentX = p.x + player.getX();
            float currentY = p.y + player.getY();
            if (tilemap[util.floatToTile(currentX)][util.floatToTile(currentY)] != -1) {
                if (p.x < playerwidth/2) { //if point is right of player center
                    //handle as right point
                    xLowerLimit = Math.max(xLowerLimit, (util.floatToTile(currentX)*16+16));
                    continue;
                } else if (p.x > playerwidth/2){ //if point is left of player center
                    //handle as left point
                    xUpperLimit = Math.min(xUpperLimit, (util.floatToTile(currentX-16)*16));

                    continue;
                }
                
                if (p.y < playerheight/2) { //if point is below player center
                    //handle as bottom point
                    yLowerLimit = Math.max( yLowerLimit, (util.floatToTile(currentY)*16+16) );
                    //continue;
                } else if (p.y > playerheight/2){ //if point is above player center
                    //handle as top point
                    yUpperLimit = Math.min( yUpperLimit, (util.floatToTile(currentY)*16) -20);
                    //continue;
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
    

}
