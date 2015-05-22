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
import java.util.Arrays;
import java.util.List;




/**
 *
 * @author emmafahlen
 */
public class World implements GameModel {
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private Level[] levels = new Level[1];
    private Player player;    
    private Platform testPlatform;
    private Flashlight flashlight;
    private int currentLevel;
    private Options options;
    
    public World(){
        currentLevel = 0;
        levels[0] = new Level("testTileMap.csv","testBackground");
        player = new Player();
        player.setX(50);
        player.setY(50);
        flashlight = new Flashlight();
        options = Options.getInstance();
    }
    
    @Override
    public void update(float delta){
        
        player.updateMotion();
        
        applyCollision(player, this.getCurrentLevel());
        flashlight.setStartPoint(player.getX()+10, player.getY()+10);
        pcs.firePropertyChange("logic updated", 1, 0);
    }
    
    @Override
    public void setMoveLeft(boolean t) {
        player.setMoveLeft(t);
    }
    
    @Override
    public void setMoveRight(boolean t){
        player.setMoveRight(t);
    }

    @Override
    public DrawableSprite[] getSprites() {
        DrawableSprite[] sprites = new DrawableSprite[1];
        
        
        //images[1] = testPlatform.getImage();
        return sprites;
    }
    
    @Override
    public DrawableShape[] getShapes() {
        DrawableShape[] shapes = new DrawableShape[1];
        
        return shapes;
    }
    
    @Override
    public void setJump(){
        player.setJump();
    }
    
    @Override
    public void setFlashlightPosition(int x, int y) {
        flashlight.setEndPoint(x, y);
    }
    @Override
    public Player getPlayer(){
        return player;
    }
    
    @Override
    public GameTile[][] getTiles(){
        return new GameTile[1][1];
    }

    @Override
    public float[] getFlashlightPolygon() {
        Flashlight f = this.flashlight;
        float [] polygon = {f.getStartPointX(), f.getStartPointY(), f.getEndPointX(), f.getEndPointY()};
        
        return polygon;
    }

    @Override
    public Level getCurrentLevel() {
        return levels [currentLevel];
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l){
        pcs.addPropertyChangeListener(l);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener l){
        pcs.removePropertyChangeListener(l);
    }
    
    public void initLevels(){
        for(Level l:levels){
            l.init();
        }
    }

    @Override
    public void setCrouch(boolean t) {
        if(options.getCrouchToggle() && t){
            player.toggleCrouch();
        } else {
            player.setCrouch(t);
        }
    }
    
    private void applyCollision(Player player, Level l) {
        List collidable = Arrays.asList(new int[]{0,1});
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
        //float x = p.getX() + playerwidth/2; //x and y are juggling values
        //float y = p.getY();
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
    

}
