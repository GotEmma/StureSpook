/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook;

import com.badlogic.gdx.Input;

/**
 *
 * @author emmafahlen
 */
public class Options {
    
    private static Options instance;
    
    private int walkLeftKey;
    private int walkRightKey;
    private int jumpKey;
    private int crouchKey;
    
    public static Options getInstance(){
        if(instance == null){
            instance = new Options();
        }
        return instance;
    }
    
    private Options(){
        
    }
    public int getWalkLeftKey() {return walkLeftKey; }
    public void setWalkLeftKey(int k) { walkLeftKey = k; }
    
    public int getWalkRightKey() {return walkRightKey; }
    public void setWalkRightKey(int k) { walkRightKey = k; }
    
    public int getJumpKey() {return jumpKey; }
    public void setJumpKey(int k) { jumpKey = k; }
    
    public int getCrouchKey() {return crouchKey; }
    public void setCrouchKey(int k) { crouchKey = k; }
    
}
