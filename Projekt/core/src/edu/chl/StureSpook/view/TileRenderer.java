/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import edu.chl.StureSpook.model.World;
import java.util.Arrays;

/**
 *
 * @author Née
 */
public class TileRenderer {
    private int lastMapHash;
    private Texture bufferTexture;
    
    private void redrawBufferTexture(World model, TextureAtlas currentLevelTextureAtlas) {
        FrameBuffer fb = new FrameBuffer(Pixmap.Format.RGBA8888, model.getCurrentLevel().getTileWidth()*16, model.getCurrentLevel().getTileHeight()*16, false);
        // allocate temporary, uprojected batch to make sure no unwanted draw calls are flushed to frame buffer
        SpriteBatch batch = new SpriteBatch(); 
        fb.begin();
        batch.begin();
        int [][] tileMap = model.getCurrentLevel().getTileMap();
        for(int i = tileMap.length-1; i >= 0; i--){
            for(int j = tileMap[i].length-1; j >= 0; j--){
                if((tileMap[i][j] != -1)){
                    batch.draw(currentLevelTextureAtlas.findRegion(tileMap[i][j]+""),i*16,j*16);
                }
            }
        }
        batch.end();
        fb.end();
        this.bufferTexture = fb.getColorBufferTexture();
        
        batch.dispose();
        fb.dispose();
        
    }
    
    public void draw(World model, SpriteBatch unprojectedBatch, TextureAtlas currentLevelTextureAtlas, Camera camera) {
        if (this.mapHashHasChanged(model)) {
            redrawBufferTexture(model, currentLevelTextureAtlas);
        }
        unprojectedBatch.draw(bufferTexture, 
                0,
                0, 
                (int)(camera.position.x - (camera.viewportWidth/2)), 
                (int)(camera.position.y - (camera.viewportHeight/2)), 
                (int)camera.viewportWidth, 
                (int)camera.viewportHeight);
    }
    
    private boolean mapHashHasChanged(World model) {
        int hash = Arrays.deepHashCode(model.getCurrentLevel().getTileMap());
        boolean hasChanged = lastMapHash != hash;
        lastMapHash = hash;
        return hasChanged;
    }
    
}
