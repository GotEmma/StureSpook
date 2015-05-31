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
public class DeadlyObsticlesTest {

    @Test
    public void testDeadlyObsticles() {
        String textureName = "testTextureName";
        float x = 25;
        float y = 20;
        float height = 30;
        float width = 150;
        DeadlyObsticles dob = new DeadlyObsticles(textureName, x, y, height, width);
        
        assertEquals(textureName,dob.getTextureName());
        assertEquals(x,dob.getX(),0.1f);
        assertEquals(y,dob.getY(),0.1f);
        assertEquals(width,dob.getWidth(),0.1f);
        assertEquals(height,dob.getHeight(),0.1f);
        
    }

    
}
