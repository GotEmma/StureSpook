/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;




/**
 *
 * @author emmafahlen
 */
public class World implements GameModel {
    
    private Level[] levels = new Level[1];
    
    private Player player;
    
    private Platform testPlatform;
    
    private Flashlight flashlight;
    private int currentLevel;
    
    public World(){
        currentLevel = 0;
        levels[0] = new Level("testBackground");
        player = new Player();
        player.setX(50);
        player.setY(50);
        flashlight = new Flashlight();
        flashlight.setStartPoint(player.getX()+10, player.getY()+10);
    }
    
    @Override
    public void update(float delta){
        
        player.updateMotion();
        flashlight.setStartPoint(player.getX()+10, player.getY()+10);
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
    
    

}
