/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import java.awt.Graphics;

/**
 *
 * @author emmafahlen
 */
public interface Enemy {
 
    public float getX();
    
    public float getY();
  
    public String getTextureName();
    
    public void setX(float x);
    
    public void setY(float y);
}
