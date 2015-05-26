/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.StureSpook.view;

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
    private List<DesktopInputListener> listeners = new ArrayList<DesktopInputListener>();
    
    public MainMenuView(MainMenuModel model) {
        this.model = model;
    }

    @Override
    public void init() {
        //load stuff here
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.render();
    }

    private void render() {
        //DRAW VIEW
    }

    @Override
    public void addInputListener(DesktopInputListener desktopInputHandler) {
        listeners.add(desktopInputHandler);
    }

    @Override
    public boolean keyDown(int i) {
        return false;
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
