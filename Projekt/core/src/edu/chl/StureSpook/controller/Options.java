/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.controller;

import com.badlogic.gdx.Input;

/**
 *
 * @author emmafahlen
 */
public class Options {

    private int walkLeftKey;
    private int walkRightKey;
    private int jumpKey;
    private int crouchKey;
    private boolean crouchToggle = true;
    private int interactKey;
    

    public int getWalkLeftKey() {return walkLeftKey; }
    public void setWalkLeftKey(int k) { walkLeftKey = k; }
    
    public int getWalkRightKey() {return walkRightKey; }
    public void setWalkRightKey(int k) { walkRightKey = k; }
    
    public int getJumpKey() {return jumpKey; }
    public void setJumpKey(int k) { jumpKey = k; }
    
    public int getCrouchKey() {return crouchKey; }
    public void setCrouchKey(int k) { crouchKey = k; }
    
    public void setCrouchToggle(boolean t){
        crouchToggle = t;
    }
    public boolean getCrouchToggle(){
        return crouchToggle;
    }

    public int getInteractKey() {return interactKey; }
    public void setInteractKey(int k){ interactKey = k; }
    
}
