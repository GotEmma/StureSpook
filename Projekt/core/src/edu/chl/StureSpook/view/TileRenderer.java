/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;
import edu.chl.StureSpook.model.World;
import java.util.Arrays;

/**
 *
 * @author Née
 */
public class TileRenderer {
    private int lastMapHash;
    private Texture bufferTexture;
    private boolean initialised = false;
    private FrameBuffer fb;
    
    private void redrawBufferTexture(World model, TextureAtlas currentLevelTextureAtlas, Camera camera) {
        int [][] tileMap = model.getCurrentLevel().getTileMap();
        if (!initialised) {
            //initialise buffers only once
            fb = new FrameBuffer(Pixmap.Format.RGBA8888, 
                    tileMap.length*16, 
                    tileMap[0].length*16, 
                    false);
            bufferTexture = fb.getColorBufferTexture();
            initialised = true;
        }
        // allocate temporary, uprojected batch to make sure no unwanted draw calls are flushed to frame buffer
        SpriteBatch batch = new SpriteBatch(); 
        fb.begin();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //set batch to scale to frame buffer
        Matrix4 matrix = new Matrix4();
        matrix.setToOrtho2D(0, 0, fb.getWidth(),fb.getHeight());
        batch.setProjectionMatrix(matrix);
        
        TextureRegion tileset = currentLevelTextureAtlas.findRegion("tileset");
        int srcX = tileset.getRegionX();
        int srcY = tileset.getRegionY();
        int srcWidth = tileset.getRegionWidth();
        int srcHeight = tileset.getRegionHeight();
        System.out.println(srcX + ", "+ srcY + ", "+ srcWidth + ", "+ srcHeight);
        
        //draw tile map to buffer
        batch.begin();
        for(int i = tileMap.length-1; i >= 0; i--){
            for(int j = tileMap[i].length-1; j >= 0; j--){
                if((tileMap[i][j] != -1)){
                    System.out.println((tileMap[i][j] % 10)*16 + ", " + (tileMap[i][j] / 10)*16);
                    /*batch.draw(tileset.getTexture(),
                            i*16,
                            j*16,
                            0,//srcX + (tileMap[i][j] % 10)*16,
                            0,//srcY + (tileMap[i][j] / 10)*16,
                            16,
                            16);*/
                    batch.draw(currentLevelTextureAtlas.findRegion("tile", tileMap[i][j]), i*16, j*16);
                    
                    //batch.draw(new TextureRegion(tileset,0,srcWidth-16, 16,16), i*16, j*16);
                    //if (batch.renderCalls >= batch.maxSpritesInBatch-10) {
                        //flush batch if almost full
                      //  batch.flush();
                    //}
                }
            }
        }
        
        //flush tiles to buffer and 
        batch.end();
        fb.end();
        batch.dispose();
        //fb.dispose();
        
    }
    
    public void draw(World model, SpriteBatch unprojectedBatch, TextureAtlas currentLevelTextureAtlas, Camera camera) {
        if (this.mapHashHasChanged(model)) {
            redrawBufferTexture(model, currentLevelTextureAtlas, camera);
        }
        
        
        boolean wasDrawing = unprojectedBatch.isDrawing();
        //start batch if not already drawing
        if (!wasDrawing) { unprojectedBatch.begin(); } 
        unprojectedBatch.draw(bufferTexture, 
                -5, //5px padding on each side to stop edges potentially flickering
                -5, 
                Math.min((int)camera.viewportWidth, fb.getWidth() - (int)(camera.position.x - (camera.viewportWidth/2)) )+10, //limit goal size to prevent wrapping of undersized tile maps
                Math.min((int)camera.viewportHeight, fb.getHeight() - (int)(camera.position.y - (camera.viewportHeight/2)) )+10,
                (int)(camera.position.x - (camera.viewportWidth/2))-5, //read only from camera field to increase efficiency
                (int)(camera.position.y - (camera.viewportHeight/2))-5, 
                Math.min((int)camera.viewportWidth, fb.getWidth() - (int)(camera.position.x - (camera.viewportWidth/2)) )+10, //limit size to read from source texture to increase efficiency
                Math.min((int)camera.viewportHeight, fb.getHeight() - (int)(camera.position.y - (camera.viewportHeight/2)) )+10,
                false,
                true);
        //stop batch again if batch was not drawing
        if (!wasDrawing) { unprojectedBatch.end(); } 
        
    }
    
    private boolean mapHashHasChanged(World model) {
        int hash = Arrays.deepHashCode(model.getCurrentLevel().getTileMap());
        boolean hasChanged = lastMapHash != hash;
        lastMapHash = hash;
        return hasChanged;
    }
    
    
}
