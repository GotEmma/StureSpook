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
public class Flashlight {
    private float startPointX,startPointY;
    private float endPointX,endPointY;
    private double visionDegree = Math.PI/6;
    
    public void setStartPoint(float x, float y) {
        this.startPointX = x;
        this.startPointY = y;
    }
    
    public void setEndPoint(float x, float y) {
        this.endPointX = x;
        this.endPointY = y;
    }

    public float getStartPointX() {return this.startPointX; }
    public float getStartPointY() {return this.startPointY; }
    
    public float getEndPointX() {return this.endPointX; }
    public float getEndPointY() {return this.endPointY; }

    public float[] getPolygon() {
        float relativeX = this.endPointX-this.startPointX;
        float relativeY = this.endPointY-this.startPointY;
        float[] polygon = {this.startPointX, this.startPointY, 
                           relativeX*(float)Math.cos(visionDegree/2)-relativeY*(float)Math.sin(visionDegree/2)+this.startPointX, relativeY*(float)Math.cos(visionDegree/2)+relativeX*(float)Math.sin(visionDegree/2)+this.startPointY,
                           relativeX*(float)Math.cos(-visionDegree/2)-relativeY*(float)Math.sin(-visionDegree/2)+this.startPointX, relativeY*(float)Math.cos(-visionDegree/2)+relativeX*(float)Math.sin(-visionDegree/2)+this.startPointY
                           };
        return polygon;
    }
    
    
}
