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
public class ActiveEnemiesTest {

    @Test
    public void testGetters() {
        ActiveEnemies enemy = new ActiveEnemies("testTextureName",0,0,10,10,20);
        assertEquals(0,enemy.getX(),0.1f);
        assertEquals("testTextureName", enemy.getTextureName());
        assertEquals(20, enemy.getEndX(),0.1f);
        assertEquals(10,enemy.getHeight(),0.1f);
        assertEquals(10,enemy.getWidth(),0.1f);
        assertEquals(0, enemy.getY(),0.1f);
        
    }
    
    @Test
    public void testAct() {
        ActiveEnemies enemy = new ActiveEnemies("testTextureName",0,0,10,10,20);
        assertTrue(enemy.goingRight());
        assertEquals(0,enemy.getX(),0.1f);
        for (int i = 0; i < 30;i++) {
            enemy.act();
        }
        assertFalse(enemy.goingRight());
    }
    
}
