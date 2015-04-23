/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.HashMap;

/**
 *
 * @author User
 */
public class Flashlight implements DrawableShape {
    private float startPointX,startPointY;
    private float endPointX,endPointY;
    
    public void setStartPoint(float x, float y) {
        this.startPointX = x;
        this.startPointY = y;
    }
    
    public void setEndPoint(float x, float y) {
        this.endPointX = x;
        this.endPointY = y;
    }

    @Override
    public void draw(ShapeRenderer renderer) {
        renderer.line(startPointX, startPointY, endPointX, endPointY);
        
    }
    
}
