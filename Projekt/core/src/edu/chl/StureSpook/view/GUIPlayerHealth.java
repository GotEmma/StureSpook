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
    public String texturenameFullHeart, texturenameHeart1, texturenameHeart2, texturenameHeart3, texturenameHeart4, texturenameEmptyHeart,activeTexture;
    private Player player;
    
    public GUIPlayerHealth(Player player, float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    @Override
    public void draw(SpriteBatch batch, TextureAtlas atlas, float mouseX, float mouseY) {
        if(player.getDeathCount() == 0){
            //draw full heart
            batch.draw(atlas.findRegion(texturenameFullHeart),this.x,this.y);
            activeTexture = texturenameFullHeart;   
        }
        else if(player.getDeathCount() == 1){
            //draw heart 1
            batch.draw(atlas.findRegion(texturenameHeart1),this.x,this.y);
            activeTexture = texturenameHeart1;
        }
        else if(player.getDeathCount() == 2){
            //draw heart 2
            batch.draw(atlas.findRegion(texturenameHeart2),this.x,this.y);
            activeTexture = texturenameHeart2;
        }
        else if(player.getDeathCount() == 3){
            //draw heart 3
            batch.draw(atlas.findRegion(texturenameHeart3),this.x,this.y);
            activeTexture = texturenameHeart3;
        }
        else if(player.getDeathCount() == 4){
            //draw heart 4
            batch.draw(atlas.findRegion(texturenameHeart4),this.x,this.y);
            activeTexture = texturenameHeart4;
        }
        else {
            //draw empty heart
            batch.draw(atlas.findRegion(texturenameEmptyHeart),this.x,this.y);
            activeTexture = texturenameEmptyHeart;
        }    
    }
}
