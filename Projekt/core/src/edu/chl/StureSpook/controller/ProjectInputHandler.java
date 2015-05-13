/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.controller;

import edu.chl.StureSpook.Options;
import edu.chl.StureSpook.model.GameModel;
import edu.chl.StureSpook.view.DesktopInputListener;

/**
 *
 * @author Olof
 */
public class ProjectInputHandler implements DesktopInputListener {
    
    private Options options = Options.getInstance();
    private GameModel model;
    
    public ProjectInputHandler(GameModel model){
        this.model = model;
    }
    
    public void keyDown(int input) {
        if(input == options.getWalkLeftKey()){
            model.setMoveLeft(true);
        }else if(input == options.getWalkRightKey()){
            model.setMoveRight(true);
        }else if(input==options.getJumpKey()){
            model.setJump();
        }
        else if(input==options.getCrouchKey()){
            model.setCrouch(true);
        }
    }

    public void keyUp(int input) {
        if(input==options.getWalkLeftKey()){
            model.setMoveLeft(false);
        }
        else if(input==options.getWalkRightKey()){
            model.setMoveRight(false);
        }
        else if(input==options.getCrouchKey()){
            model.setCrouch(false);
        }
    }

    public void mouseMoved(int x, int y) {
        model.setFlashlightPosition(x,y);
    }
    
}
