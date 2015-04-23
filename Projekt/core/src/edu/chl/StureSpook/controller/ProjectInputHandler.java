/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.controller;

import edu.chl.StureSpook.Options;
import edu.chl.StureSpook.model.GameModel;

/**
 *
 * @author Olof
 */
public class ProjectInputHandler implements InputHandler {
    
    private Options options = Options.getInstance();
    
    @Override
    public void keyDown(int input, GameModel model) {
        if(input == options.getWalkLeftKey()){
            model.setMoveLeft(true);
        }else if(input == options.getWalkRightKey()){
            model.setMoveRight(true);
        }
    }

    @Override
    public void keyUp(int input, GameModel model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(int input, GameModel model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
