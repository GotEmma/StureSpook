package edu.chl.StureSpook.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class Project implements GameModel{

    private World world;
    
    public Project(){
        
        world = new World();
    }
    
    
    @Override
    public void update(float delta, boolean[] WASD) {
        //Update game logic here
        this.world.movePlayer(WASD);
    }
    
    public Drawable[] getImages(){
        return world.getImages();
        
    }
}
