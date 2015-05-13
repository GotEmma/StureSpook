/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import java.beans.PropertyChangeListener;

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
    public DrawableSprite[] getSprites();
    public DrawableShape[] getShapes();
    public void setMoveLeft(boolean t);
    public void setMoveRight(boolean t);
    public void setJump();
    public void setCrouch(boolean t);
    public void setFlashlightPosition(int x, int y);
    public GameTile[][] getTiles();
    public float[] getFlashlightPolygon();
    public Level getCurrentLevel();
    public Player getPlayer();
    public void addPropertyChangeListener(PropertyChangeListener l);
    public void removePropertyChangeListener(PropertyChangeListener l);
    public void initLevels();
}
