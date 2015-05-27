/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import edu.chl.StureSpook.model.MainMenuModel;

/**
 *
 * @author User
 */
public class ProjectMenuInputHandler implements DesktopInputListener {
    private MainMenuModel model;
    
    public ProjectMenuInputHandler(MainMenuModel model) {
        this.model = model;
    }
    
    @Override
    public void keyDown(int input) {
        if(input == Keys.DOWN || input == Keys.S){
            model.moveDown();
        }
    }

    @Override
    public void keyUp(int input) {
        if(input == Keys.UP  || input == Keys.W){
            model.moveUp();
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        
    }
    
}
