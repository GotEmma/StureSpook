/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import java.awt.Point;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Née
 */
public class DoorTest {
    
    @Test
    public void testContains() {
        Door door = new Door(0,0,"testLevel", new Point(0,0));
        assertTrue(door.contains(16, 16));
        assertFalse(door.contains(50, 50));
    }

    @Test
    public void testGetters() {
        Point point = new Point(0,0);
        float x = 50;
        float y = 200;
        float width = 32;
        float height = 32;
        String goalLevel = "testLevel";
        
        Door door = new Door(x,y,goalLevel,point);
        
        assertEquals(x,door.getX(),0.1f);
        assertEquals(y,door.getY(),0.1f);
        assertEquals(width,door.getWidth(),0.1f);
        assertEquals(height,door.getHeight(),0.1f);
        assertEquals(point, door.getNextLvlStartPoint());
        assertEquals(goalLevel, door.getConnectedLevelKey());
        
    }

    
}
