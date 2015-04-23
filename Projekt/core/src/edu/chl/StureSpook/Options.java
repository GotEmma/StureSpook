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
public abstract class Options {
    
    private static Input.Keys walkLeftKey;
    private static Input.Keys walkRightKey;
    private static Input.Keys jumpKey;
    private static Input.Keys crouchKey;
    
    public static Input.Keys getWalkLeftKey() {return Options.walkLeftKey; }
    public static void setWalkLeftKey(Input.Keys k) { Options.walkLeftKey = k; }
    
    public static Input.Keys getWalkRightKey() {return Options.walkRightKey; }
    public static void setWalkRightKey(Input.Keys k) { Options.walkRightKey = k; }
    
    public static Input.Keys getJumpKey() {return Options.jumpKey; }
    public static void setJumpKey(Input.Keys k) { Options.jumpKey = k; }
    
    public static Input.Keys getCrouchKey() {return Options.crouchKey; }
    public static void setCrouchKey(Input.Keys k) { Options.crouchKey = k; }
    
}
