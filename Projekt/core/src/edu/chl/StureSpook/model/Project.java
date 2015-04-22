package edu.chl.StureSpook.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class Project implements GameModel{

    private World world;
    
    public Project(){
        
        world = new World();
    }
    
    public void update(float delta) {
        //Update game logic here
        this.world.movePlayer();
    }
    
    public DrawArg[] getImages(){
        return world.getImages();
        
    }
}
