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
public class GUIVolumeControl implements GUIClickable, GUIDrawable{
    private boolean expanded;
    private float volume;
    private boolean muted;
    private float x,y,width,unexpandedHeight,expandedHeight;
    private final String unmutedTextureName, mutedTextureName, expansionTextureName, sliderTextureName;
    private float sliderHeight;
    
    public GUIVolumeControl(float volume, float x, float y) {
        
        this.unmutedTextureName = "soundButtonUnmuted";
        this.mutedTextureName = "soundButtonMuted";
        this.expansionTextureName = "soundButtonExpansion";
        this.sliderTextureName = "soundButtonSlider";
        this.width = 32;
        this.unexpandedHeight = 32;
        this.expandedHeight = 96;
        this.sliderHeight = 16;
        this.volume = volume;
        this.x = x;
        this.y = y;
    }
    
    private boolean isClickInExpandedBoundaries(float x, float y) {
        boolean isInBoundsX = (x>this.x) && (x < this.x + this.width);
        boolean isInBoundsY = (y>this.y) && (y < this.y + this.expandedHeight);
        return isInBoundsX && isInBoundsY;
    }
    
    private void setVolume(float mouseY) {
        float yMin = y + unexpandedHeight + (sliderHeight/2);
        float yMax = yMin + (expandedHeight-unexpandedHeight)-sliderHeight;
        float volumeFraction = (mouseY-yMin) / (yMax-yMin);
        volumeFraction = Math.min(1, volumeFraction);//trim top
        volumeFraction = Math.max(0, volumeFraction);//trim bottom
        this.volume = volumeFraction;
        System.out.println(this.volume);
    }
    
    private void toggleMute() {
        this.muted = !this.muted;
    }
    
    private boolean isClickInUnexpandedBoundaries(float x, float y) {
        boolean isInBoundsX = (x>this.x) && (x < this.x + this.width);
        boolean isInBoundsY = (y>this.y) && (y < this.y + this.unexpandedHeight);
        return isInBoundsX && isInBoundsY;
    }
    
    public float getVolume() {
        return muted ? 0 : this.volume;
    }
    
    public boolean getExpanded() {
        return this.expanded;
    }
    
    @Override
    public String click(float x, float y) {
        if (this.isClickInUnexpandedBoundaries(x, y)) {
            this.toggleMute();
            return "volumeChanged";
        } else if (this.expanded && this.isClickInExpandedBoundaries(x, y)) {
            this.setVolume(y);
            return "volumeChanged";
        }
        return "oob";
    }
    
    @Override
    public void draw(SpriteBatch batch, TextureAtlas atlas, float mouseX, float mouseY) {
        if (this.isClickInUnexpandedBoundaries(mouseX, mouseY)) {
            expanded = true;
        }
            
        if (!this.isClickInExpandedBoundaries(mouseX, mouseY)) {
            // if not in expanded bounds, unexpand control.
            expanded = false;
        }
        //draw unexpanded
        batch.draw(atlas.findRegion(muted ? mutedTextureName : unmutedTextureName), x, y);
        if (this.expanded) {
            //draw expansion
            batch.draw(atlas.findRegion(expansionTextureName), x, y+unexpandedHeight);
            //draw slider
            float maxY = y+expandedHeight-sliderHeight;
            float minY = y+unexpandedHeight;
            batch.draw(atlas.findRegion(sliderTextureName), x, minY + volume*(maxY-minY));
        }
        
    }
    
     
}
