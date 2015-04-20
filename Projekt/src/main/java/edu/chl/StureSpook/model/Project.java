package edu.chl.StureSpook.model;

import org.newdawn.slick.Image;

public class Project {

    private World world;
    
    public Project(){
        
        world = new World();
    }
    
    public void update() {
        
    }
    
    public Image[] getImages(){
        return world.getImages();
    }
}
