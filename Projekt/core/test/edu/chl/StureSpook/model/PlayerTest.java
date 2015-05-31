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
 * @author User
 */
public class PlayerTest {
    private Player player;
    
    public PlayerTest() {
    }
    
    @Before
    public void setUp() {
        player = new Player();
    }
    
    @After
    public void tearDown() {
        player = null;
    }

    @Test
    public void testMoveRight() {
        float x1 = player.getX();
        player.setMoveRight(true);
        player.updateMotion();
        float x2 = player.getX();
        assertTrue(x2 > x1);
    }
    
    @Test
    public void testMoveLeft() {
        float x1 = player.getX();
        player.setMoveLeft(true);
        player.updateMotion();
        float x2 = player.getX();
        assertTrue(x2 < x1);
    }
    
    @Test
    public void testJump() {
        float y1 = player.getY();
        player.setJump();
        player.setOnGround(true);
        player.updateMotion();
        float y2 = player.getY();
        assertTrue(y2 > y1);
    }
    
    @Test
    public void testUnableToJump() {
        float y1 = player.getY();
        player.setJump();
        player.setOnGround(false);
        player.updateMotion();
        float y2 = player.getY();
        assertFalse(y2 > y1);
    }
    
    @Test
    public void testStandStill() {
        float x1 = player.getX();
        float y1 = player.getY();
        player.updateMotion();
        float x2 = player.getX();
        float y2 = player.getY();
        assertTrue(x1 == x2);
        assertTrue(y1 == y2);
    }

    
}
