package edu.chl.StureSpook.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class Project implements GameModel{

    private World world;
    
    public Project(){
        
        world = new World();
    }
    
    
    @Override
    public void update(float delta) {
        //Update game logic here
        this.world.updateWorld();
    }
    
    @Override
    public DrawableSprite[] getSprites(){
        return world.getSprites();
        
    }
    
    @Override
    public DrawableShape[] getShapes() {
        return world.getShapes();
    }

    @Override
    public void setMoveLeft(boolean t) {
        world.movePlayerLeft(t);
    }

    @Override
    public void setMoveRight(boolean t) {
        world.movePlayerRight(t);
    }
    
    @Override
    public void setJump(){
        world.setJump();
    }

    @Override
    public void setFlashlightPosition(int x, int y) {
        world.setFlashlightPosition(x, y);
    }
    
    @Override
    public GameTile[][] getTiles() {
        return world.getTiles();
    }

    @Override
    public World getWorld() {
        return this.world; //To change body of generated methods, choose Tools | Templates.
    }

    public float[] getFlashlightPolygon() {
        return new float[1];
    }
    
    
}
