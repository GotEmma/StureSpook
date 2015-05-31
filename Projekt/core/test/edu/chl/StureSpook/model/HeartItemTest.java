/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Née
 */
public class HeartItemTest {
    
    @Test
    public void testHeartItem() {
        float x= 20; 
        float y = 50; 
        float width= 15; 
        float height=20;
        String textureName = "smallHeart";
        
        HeartItem hi = new HeartItem(x,y,width,height);
        assertEquals(x,hi.getX(),0.1f);
        assertEquals(y,hi.getY(),0.1f);
        assertEquals(width,hi.getWidth(),0.1f);
        assertEquals(height,hi.getHeight(),0.1f);
        assertEquals(textureName,hi.getTextureName());
    }
    
}
