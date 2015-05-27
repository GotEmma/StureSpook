/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import static com.badlogic.gdx.Gdx.graphics;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import java.util.HashMap;



/**
 *
 * @author emmafahlen
 */
public class Player {
    private float x,y,dx,dy,ddy;
    private String textureNameStandStill = "playerStanding";
    private Rectangle shape;
    private Image image;
    private Graphics graphics;
    private boolean moveLeft;
    private boolean moveRight;
    private boolean jump;
    private boolean crouch;
    private boolean onGround;
    private float height = 60;
    private float width = 30;
    
    public Player(){
        x = 0; y = 0; ddy = 1;
        //shape = new Rectangle(x,y,20,20);
        
        
        //image = new Image((int)shape.getWidth(),(int)shape.getHeight());
        //graphics = image.getGraphics();
        
        //graphics.setColor(Color.yellow);
        //graphics.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
    }
    
    public void setX(float x){ this.x = x; }
    public void setY(float y){ this.y = y; }
    
    public float getX(){ return this.x; }
    public float getY(){ return this.y; }
    public float getHeight(){ return this.height; }
    public float getWidth(){ return this.width; }
    
    public void setOnGround(boolean onGround){ this.onGround = onGround; } 
    
    /** 
     *  Returns the x coordinate of the tile on which the player's bottom left
     * hand corner is located on the tilemap.
     */ 
    public int getPlayerTilemapX() { return ((int)this.x) / 16; }
    
    /** 
     *  Returns the y coordinate of the tile on which the player's bottom left
     * hand corner is located on the tilemap.
     */
    public int getPlayerTilemapY() { return ((int)this.y) / 16; }

    public void updateMotion(){
        float maxSpeed = 3.5f;
        float ddx = 0.09f;
        float friction = 0.8f;
        if(moveLeft){
            if(dx > 0) dx*=friction;
            dx = Math.max(-maxSpeed, dx-ddx);
        }else if(moveRight){
            if(dx < 0) dx*=friction;
            dx = Math.min(maxSpeed, dx+ddx);
        }else if(Math.abs(dx)>0){
            dx*=friction;
            if(Math.abs(dx)<1.5*ddx){
                dx = 0;
            }
        }
        x = x+dx;
        
        if(jump){
            if(onGround){
                dy = 15;
            }
            jump = false;
        }
        
        y=y+dy;
        dy= dy-ddy;
        
        // Simple out of bounds test
        if(y<0){
            y=0;
        }
        
    }
    
    public void setMoveLeft(boolean t){
        if(moveRight && t){
            moveRight = false;
        }
        moveLeft = t;
    }
    public boolean getMoveLeft(){
        return moveLeft;
    }
    
    public void setMoveRight(boolean t){
        if(moveLeft && t){
            moveLeft = false;
        }
        moveRight = t;
    }
    
    public boolean getMoveRight(){
        return moveRight;
    }
    
    public void setJump(){
        jump = true;
    }
    
    public boolean isJumping(){
        
        return dy > 0;
    }
    
    public void setCrouch(boolean t){
       crouch = t;
       System.out.println(crouch);
    }
    
    public boolean getCrouch(){
        
        return crouch;
    }
    
    public void toggleCrouch(){
        this.crouch = !crouch;
        System.out.println("togglat" + crouch);
    }
    public String getTextureNameStandStill(){
        return this.textureNameStandStill;
    }
    
    public void setDY(float dy) {
        this.dy = dy;
    }

}
