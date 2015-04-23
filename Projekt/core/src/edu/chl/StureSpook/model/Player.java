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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import java.util.HashMap;



/**
 *
 * @author emmafahlen
 */
public class Player implements Drawable{
    private float x,y;
    private String textureName = "player";
    private Rectangle shape;
    private Image image;
    private Graphics graphics;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    
    public Player(){
        x = 0; y = 0;
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

    public void updateMotion(){
        if(moveLeft){
            x = x-5;
        }else if(moveRight){
            x = x+5;
        }
    }
    
    public void setMoveLeft(boolean t){
        if(moveRight && t){
            moveRight = false;
        }
        moveLeft = true;
    }
    
    public void setMoveRight(boolean t){
        if(moveLeft && t){
            moveLeft = false;
        }
        moveRight = true;
    }
    @Override
    public String getTextureName() {
        return this.textureName;
    }

    @Override
    public void draw(SpriteBatch batch,HashMap<String,Texture> texture) {
        batch.draw(texture.get(this.textureName),this.x,this.y);
    }
}
