/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.view;

/**
 *
 * @author Olof
 */
public interface DesktopInputListener {
    public void keyDown(int input);
    
    public void keyUp(int input);
    
    public void mouseMoved(int x,int y);
}
