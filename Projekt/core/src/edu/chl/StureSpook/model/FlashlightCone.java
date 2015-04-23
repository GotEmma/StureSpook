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
public class FlashlightCone implements Drawable {
    private int startPointX,startPointY;
    private int endPointX,endPointY;
    
    public void setStartPoint(int x, int y) {
        this.startPointX = x;
        this.startPointY = y;
    }
    
    public void setEndPoint(int x, int y) {
        this.endPointX = x;
        this.endPointY = y;
    }
    
    @Override
    public String getTextureName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(SpriteBatch batch, HashMap<String, Texture> texture) {
        ShapeRenderer shaperenderer = new ShapeRenderer();
        shaperenderer.begin(ShapeRenderer.ShapeType.Line);
        shaperenderer.line(startPointX, startPointY, endPointX, endPointY);
        shaperenderer.end();
        
    }
    
}
