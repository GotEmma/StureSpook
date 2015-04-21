/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;



/**
 *
 * @author emmafahlen
 */
public class World {
    
    private Level[] levels = new Level[1];
    
    private Player player;
    
    private Platform testPlatform;
    
    public World(){
        testPlatform = new Platform(200,200,100,50);
        player = new Player();
        player.setX(50);
        player.setY(50);
    }
    
    public void movePlayer() {
        this.player.setX(this.player.getX()+3.0f);
    }

    public DrawArg[] getImages() {
        DrawArg[] images = new DrawArg[1];
        images[0] = player.getDrawArg();
        //images[1] = testPlatform.getImage();
        return images;
    }
    
}
