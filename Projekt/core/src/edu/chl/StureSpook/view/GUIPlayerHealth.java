/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import edu.chl.StureSpook.model.Player;

/**
 *
 * @author emmafahlen
 */
public class GUIPlayerHealth implements GUIDrawable{
    public float x, y, width, height;
    public String texturenameFullHeart, activeTexture;
    private Player player;
    private final String inventorySlotTexture;
    private final String bezelTexture;
    
    public GUIPlayerHealth(Player player, float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.player = player;
        this.inventorySlotTexture = "inventorySlot";
        this.bezelTexture = "inventoryBezel";
        this.texturenameFullHeart = "texturenameFullHeart";

    }

    public String getActiveTexture(){
        return activeTexture;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }   

    private void drawItemSlots(SpriteBatch batch, TextureAtlas atlas) {
        for (int i = 0; i < 3; i++) {
            batch.draw(atlas.findRegion(this.inventorySlotTexture),
                    x + i*this.width,
                    y);
        }
    }
    private void drawBezels(SpriteBatch batch, TextureAtlas atlas) {
        batch.draw(atlas.findRegion(bezelTexture),
                x + 3*width, 
                y);
    }
    
    private void drawHearts(SpriteBatch batch, TextureAtlas atlas) {
        for (int i = 0; i < 3-this.player.getDeathCount(); i++) {
            batch.draw(atlas.findRegion(texturenameFullHeart),
                        x+ i*this.width,
                        y);
            
        }
    }
    
    @Override
    public void draw(SpriteBatch batch, TextureAtlas atlas, float mouseX, float mouseY) {
        drawItemSlots(batch, atlas);
        drawBezels(batch, atlas);
        drawHearts(batch, atlas);
        
    }
}
