/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import edu.chl.StureSpook.model.Inventory;
import edu.chl.StureSpook.model.Item;

/**
 *
 * @author User
 */
public class GUIInventory implements GUIDrawable{
    private final float itemHeight;
    private final float itemWidth;
    private final float numItems;
    private final float width; //excluding bezel
    private final float bezelThickness;
    private final Inventory inventory;
    private float posX;
    private float posY;
    private final String bezelTextureLeft;
    private final String bezelTextureRight;
    private final String inventorySlotTexture;
    
    private int mouseOverIndex = -1;
    private int mouseOverIndexCached = -2;
    private BitmapFont tooltipFont;
    private BitmapFontCache tooltipCache;
    
    
    
    public GUIInventory(Inventory inventory) {
        this.inventory = inventory;
        
        this.itemHeight = 32;
        this.itemWidth = 32;
        this.numItems = this.inventory.getInventorySize();
        this.bezelThickness = 4;
        this.width = itemWidth*numItems;
        this.posX = 0;
        this.posY = 0;
        
        this.bezelTextureLeft = "inventoryBezel";
        this.bezelTextureRight = this.bezelTextureLeft;
        this.inventorySlotTexture = "inventorySlot";
        
        this.tooltipFont = new BitmapFont();
        this.tooltipFont.setColor(0, 0, 1, 1);
        this.tooltipCache = new BitmapFontCache(tooltipFont);
        
    }
    
    private void drawBezels(SpriteBatch batch, TextureAtlas atlas) {
        //Draw left bezel
        batch.draw(atlas.findRegion(bezelTextureLeft),
                posX, posY);
        
        //Draw right bezel
        batch.draw(atlas.findRegion(bezelTextureRight),
                posX + bezelThickness + numItems*itemWidth, 
                posY);
    }
    
    private void drawItems(SpriteBatch batch, TextureAtlas atlas) {
        for (int i = 0; i < this.numItems; i++) {
            Item item = this.inventory.getItem(i);
            if (item != null) {
                batch.draw(atlas.findRegion(item.getTextureName()),
                        posX+this.bezelThickness + i*this.itemWidth,
                        posY + this.bezelThickness);
            }
        }
    }
    
    private void drawItemSlots(SpriteBatch batch, TextureAtlas atlas) {
        for (int i = 0; i < this.numItems; i++) {
            batch.draw(atlas.findRegion(this.inventorySlotTexture),
                    posX + this.bezelThickness + i*this.itemWidth,
                    posY);
        }
    }
    
    private void drawTooltip(SpriteBatch batch, int itemIndex) {
        this.mouseOverIndex = itemIndex;
        
        if (this.mouseOverIndex != this.mouseOverIndexCached) {
            this.tooltipCache.setText(this.inventory.getItem(itemIndex).getName(),
                    posX + bezelThickness + itemIndex*itemWidth,
                    posY + itemHeight +bezelThickness*2);
        }
        
        this.tooltipCache.draw(batch);
        
        
    }
    
    private void drawTooltip(SpriteBatch batch, float mouseX, float mouseY) {
        mouseX -= (posX+bezelThickness);
        mouseY -= (posY+bezelThickness);
        
        if ((mouseX > 0) && (mouseY > 0)) {
            if ((mouseX < itemWidth*numItems) && (mouseY < itemHeight)) {
                mouseX = mouseX / itemWidth;
                if (this.inventory.getItem((int)mouseX) != null ) {
                    drawTooltip(batch,(int)mouseX);
                }
                
            }
        } else {
            this.mouseOverIndex = -1;
        }
    }
    
    public GUIInventory(Inventory inventory, float posX, float posY) {
        this(inventory);
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void draw(SpriteBatch batch, TextureAtlas atlas, float mouseX, float mouseY) {
        this.drawBezels(batch, atlas);
        this.drawItemSlots(batch, atlas);
        this.drawItems(batch, atlas);
        this.drawTooltip(batch, mouseX, mouseY);
    }
    
    public float getWidth() {
        return this.bezelThickness*2 + this.itemWidth*this.numItems;
    }
    
}
