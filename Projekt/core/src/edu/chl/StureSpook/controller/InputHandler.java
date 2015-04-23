/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.controller;

import edu.chl.StureSpook.model.GameModel;

/**
 *
 * @author Olof
 */
public interface InputHandler {
    public void keyDown(int input, GameModel model);
    
    public void keyUp(int input, GameModel model);
    
    public void mouseMoved(int x,int y, GameModel model);
}
