/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.StureSpook.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import edu.chl.StureSpook.controller.DesktopInputListener;
import edu.chl.StureSpook.controller.ProjectInputHandler;
import edu.chl.StureSpook.model.MainMenuModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Née
 */
public class MainMenuView implements GameView{
    private MainMenuModel model;
    private List<DesktopInputListener> listeners;
    private SpriteBatch batch;
    private TextureAtlas mainMenuTextureAtlas;
    private float listFrameBezel = 3;
    private float listFrameX = 150 - listFrameBezel;
    private float listFrameY = 45 - listFrameBezel;
    private float listFrameHeight = 245;
    private float listFrameWidth = 500;
    private float menuItemHeight = 35;
    
    
    
    
    public MainMenuView(MainMenuModel model) {
        this.model = model;
        listeners = new ArrayList<DesktopInputListener>();
    }

    @Override
    public void init() {
        batch = new SpriteBatch();
        mainMenuTextureAtlas = new TextureAtlas("packed/mainMenu.pack") ; 
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.render();
    }

    private void render() {
        //DRAW VIEW
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        
        //Draw menu background
        batch.draw(mainMenuTextureAtlas.findRegion("mainMenuBackground"), 0, 0);
        
        //Draw list frame
        batch.draw(mainMenuTextureAtlas.findRegion("mainMenuListFrame"), 
                listFrameX, listFrameY);
        
        
        //Draw frame around (behind) selected item
        batch.draw(mainMenuTextureAtlas.findRegion("menuSelection"), 
                getMenuX(), getMenuY(model.getSelectedIndex()));
        
        //Draw menu items
        for(int i = 0; i < model.getMenuItems().length; i++) {
            batch.draw(mainMenuTextureAtlas.findRegion(model.getMenuItems()[i].getTextureName()),
                    getMenuX(), getMenuY(i)); //add positions
        
        }  
        batch.end();
    }
    
    public float getMenuX(){
        return listFrameX + listFrameBezel;
    }
    
    
    public float getMenuY(int i){
        float y1 = listFrameY + listFrameHeight + listFrameBezel;
        return y1 - (menuItemHeight*(i + 1));
    }

    @Override
    public void addInputListener(DesktopInputListener desktopInputHandler) {
        listeners.add(desktopInputHandler);
    }

    @Override
    public boolean keyDown(int i) {
        for(DesktopInputListener l:listeners){
            l.keyDown(i);
        }
        return true;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }

}

