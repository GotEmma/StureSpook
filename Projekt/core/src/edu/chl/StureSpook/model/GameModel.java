/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

/**
 *
 * @author Née
 */
public interface GameModel {
    
    /**
    * Updates the game logic 
    *
    * @param  delta  the time elapsed since last update
    */
    public void update(float delta);
    public DrawArg[] getImages();
}
