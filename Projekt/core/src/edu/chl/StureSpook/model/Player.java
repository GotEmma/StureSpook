/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;



/**
 *
 * @author emmafahlen
 */
public class Player {
    private float x,y,dx,dy,ddy;
    private String textureNameStandStill = "playerStanding";
    private boolean moveLeft;
    private boolean moveRight;
    private boolean jump;
    private boolean crouch;
    private boolean onGround;
    private float height = 30;
    private float crouchedHeight = 12;
    private float width = 20;
    private int deathCount = 0;
    
    public Player(){
        x = 0; y = 0; ddy = 1;
    }
    
    public void setX(float x){ this.x = x; }
    public void setY(float y){ this.y = y; }
    
    public float getX(){ return this.x; }
    public float getY(){ return this.y; }
    
    public float getHeight(){ return crouch ? crouchedHeight : this.height; }
    public float getWidth(){ return this.width; }
    
    public int getDeathCount(){
        return this.deathCount;
    }
    
    public boolean isDead(){
        if(deathCount > 3){
            return true;
        }
        return false;
    }
    
    
    public void deathCounter(int i){
        if(this.deathCount <=3){
            this.deathCount = deathCount + i;
        }
    }
    
    public void resetDeathCount(){
        this.deathCount = 0;
    }
    
    
    public void deathCounterMinus(){
        if(this.deathCount>=1){
            this.deathCount --;
        }
    }
    
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
        float ddx = 0.15f;
        float friction = 0.8f;
        
        float currentDDX = ddx * (this.crouch ? 0.5f : 1);
        float currentMaxSpeed = maxSpeed * (this.crouch ? 0.5f : 1);
        
        if(moveLeft){
            if(dx > 0) dx*=friction;
            dx = Math.max(-currentMaxSpeed, dx-currentDDX);
        }else if(moveRight){
            if(dx < 0) dx*=friction;
            dx = Math.min(currentMaxSpeed, dx+currentDDX);
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
    }
    
    public boolean getCrouch(){
        
        return crouch;
    }
    
    public void toggleCrouch(){
        this.crouch = !crouch;
    }
    public String getTextureNameStandStill(){
        return this.textureNameStandStill;
    }
    
    public void setDY(float dy) {
        this.dy = dy;
    }
    
    public void setDX(float dx) {
        this.dx = dx;
    }
}
