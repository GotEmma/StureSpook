/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.controller;

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
    }

    @Override
    public void keyUp(int input) {
    }

    @Override
    public void mouseMoved(int x, int y) {
    }
    
}
