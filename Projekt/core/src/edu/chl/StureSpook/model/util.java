/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.StureSpook.model;

/**
 *
 * @author Née
 */
public class util {
    public static int floatToTile(float x){ return (int)x/16; }
    
    //Checks if player collides with any DrawableWorldObjects like enemys
    //on the X-axis and returns true if it does
    private static boolean collideX(Player player, DrawableWorldObjects object){
        if (player.getX()>object.getX()-player.getWidth()) { //inte för litet
            if (player.getX()<object.getX()+object.getWidth()) { //inte heller för stort
                return true;
                
            }
            
        }
        return false;
    }

    //Checks if player collides with any DrawableWorldObjects like enemys
    //on the Y-axis and returns true if it does
    private static boolean collideY(Player player, DrawableWorldObjects object){
        if (player.getY()>object.getHeight()-player.getHeight()) { //inte för litet
            if (player.getY()<object.getY()+object.getHeight()) { //inte heller för stort
                return true;   
            } 
        }
        return false;
    }
    
    //Checks if player collides with any DrawableWorldObjects on both
    //the X-axis and the Y-axis, and returns true if it does
    public static boolean collide(Player player, DrawableWorldObjects object){
        if(collideX(player, object) && collideY(player, object)){
            return true;
        }
        return false;
    }

}
