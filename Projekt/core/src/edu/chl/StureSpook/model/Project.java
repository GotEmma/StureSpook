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
    
    public Drawable[] getImages(){
        return world.getImages();
        
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
}
