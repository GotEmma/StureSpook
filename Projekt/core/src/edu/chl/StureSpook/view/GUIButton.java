/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 *
 * @author Née
 */
public class GUIButton implements GUIClickable, GUIDrawable{
    private float x,y,height,width;
    private String textureNameNonMouseover,textureNameMouseover,command;
    
    public GUIButton(String command,String textureNameNonMouseover, String textureNameMouseover, float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.textureNameNonMouseover = textureNameNonMouseover;
        this.textureNameMouseover = textureNameMouseover;
        this.command = command;
    }
    
    
    /**
    * Determines whether the coordinates (x,y) are inside this GUIButton's click boundaries.
    * Always returns false if button is unclickable.
    * @param x The x coordinate of the click to test.
    * @param y the y coordinate of the click to test. NOTE: This check uses y-down coordinates.
    */
    private boolean isClickInBoundaries(float x, float y) {
        boolean isInBoundsX = (x>this.x) && (x < this.x + this.width);
        boolean isInBoundsY = (y > this.y) && (y < this.y + this.height);
        return isInBoundsX && isInBoundsY;
        //return ((x > this.x)&&(x< this.x+this.width)) && ((y > this.y)&&(y< this.y+this.height)); 
        //the above equals: x is between this.x and (this.x + this.width) && same for y and height
    }
    
    
    @Override
    public void draw(SpriteBatch batch, TextureAtlas atlas, float mouseX, float mouseY) {
        if (this.isClickInBoundaries(mouseX,mouseY)) {
            //draw mouseover
            batch.draw(atlas.findRegion(textureNameMouseover),this.x,this.y);
        } else {
            //draw non-mouseover
            batch.draw(atlas.findRegion(textureNameNonMouseover),this.x,this.y);
        }
        
    }

    @Override
    public String click(float x, float y) {
        if (this.isClickInBoundaries(x, y)) {
            return this.command;
        } else {
            return null;
        }
    }
}
