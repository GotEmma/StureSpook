/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import edu.chl.StureSpook.Options;
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
    
    private void applyCollision(Player p, Level l) {
        List collidable = Arrays.asList(new int[]{0,1});
        int[][] tilemap = l.getTileMap();
        
        float xLowerLimit = 0;
        float xUpperLimit = 1e9f;
        float yLowerLimit = p.getY();
        float yUpperLimit = p.getY();
        float playerheight = 20;
        float playerwidth = 20;
        float[] points = {10,0, 4,4, 16,4, 10,20, 4,16, 16,16};//change these later when changing player dimensions
        
        //first check bottom middle point
        //float x = p.getX() + playerwidth/2; //x and y are juggling values
        //float y = p.getY();
        for (int i = 0; i<5 ;i+=2) {
            if (collidable.contains(  tilemap[util.floatToTile(points[i]+p.getX())]
                    [util.floatToTile(points[i+1]+p.getY())]  )) {
                if (points[i+1] < playerheight/2) { //if point is below player center
                    //handle as bottom point
                    yLowerLimit = Math.max( yLowerLimit, util.floatToTile(points[i+1]+p.getY())*16+16 );
                } else if (points[i+1] > playerheight/2){ //if point is above player center
                    //handle as top point
                    yUpperLimit = Math.min( yUpperLimit, util.floatToTile(points[i+1]+p.getY())*16 );
                }
                
                if (points[i] < playerwidth/2) { //if point is right of player center
                    //handle as right point
                    xLowerLimit = Math.max(xLowerLimit, util.floatToTile(points[i]+p.getX())*16+16);
                } else if (points[i] > playerwidth/2){ //if point is left of player center
                    //handle as left point
                    xUpperLimit = Math.max(xUpperLimit, util.floatToTile(points[i]+p.getX())*16);
                }
            
            }
            
        }
        
        
        p.setX(Math.max(p.getX(),xLowerLimit));
        p.setX(Math.min(p.getX(),xUpperLimit));
        p.setY(Math.max(p.getY(),yLowerLimit));
        p.setY(Math.min(p.getY(),yUpperLimit));
 
        if (p.getY() == yLowerLimit) {
            p.setDY(0.0f);
        }
    }

}
