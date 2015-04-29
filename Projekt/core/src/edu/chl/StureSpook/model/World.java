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
public class World {
    
    private Level[] levels = new Level[1];
    
    private Player player;
    
    private Platform testPlatform;
    
    private Flashlight flashlight;
    
    public World(){
        testPlatform = new Platform(200,200,100,50);
        player = new Player();
        player.setX(50);
        player.setY(50);
        flashlight = new Flashlight();
        flashlight.setStartPoint(player.getX()+10, player.getY()+10);
    }
    
    public void updateWorld(){
        
        player.updateMotion();
        flashlight.setStartPoint(player.getX()+10, player.getY()+10);
    }
    
    public void movePlayerLeft(boolean t) {
        player.setMoveLeft(t);
    }
    
    public void movePlayerRight(boolean t){
        player.setMoveRight(t);
    }

    public DrawableSprite[] getSprites() {
        DrawableSprite[] sprites = new DrawableSprite[1];
        sprites[0] = player;
        
        //images[1] = testPlatform.getImage();
        return sprites;
    }
    
    public DrawableShape[] getShapes() {
        DrawableShape[] shapes = new DrawableShape[1];
        shapes[0] = flashlight;
        return shapes;
    }
    
    public void setJump(){
        player.setJump();
    }
    
    public void setFlashlightPosition(int x, int y) {
        flashlight.setEndPoint(x, y);
    }
    
    public GameTile[][] getTiles(){
        return new GameTile[1][1];
    }
}
