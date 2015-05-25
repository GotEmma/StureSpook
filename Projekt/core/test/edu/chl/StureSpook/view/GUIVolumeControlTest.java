/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Matchers;
import static org.mockito.Mockito.*;

/**
 *
 * @author User
 */
public class GUIVolumeControlTest {
    private GUIVolumeControl volumeControl;
    private SpriteBatch testBatch;
    private TextureAtlas testAtlas;
    
    @Before
    public void setUp() {
        volumeControl = new GUIVolumeControl(0.5f,0,0);
        testBatch = mock(SpriteBatch.class);
        
        testAtlas = mock(TextureAtlas.class);
        when(testAtlas.findRegion(anyString())).thenReturn(null);
        
    }
    
    @After
    public void tearDown() {
        volumeControl = null;
    }

    @Test
    public void testMuteUnmute() {
        String command = volumeControl.click(10,10);
        assertEquals("volumeChanged",command);
        assertEquals(0,volumeControl.getVolume(),0.1f);
        
        command = volumeControl.click(10, 10);
        assertEquals("volumeChanged",command);
        assertEquals(0.5f,volumeControl.getVolume(),0.1f);
        
        //Test additional code branches:
        command = volumeControl.click(50, 10);
        assertEquals("oob",command);
        assertEquals(0.5f,volumeControl.getVolume(),0.1f);
        
        command = volumeControl.click(-10, 10);
        assertEquals("oob",command);
        assertEquals(0.5f,volumeControl.getVolume(),0.1f);
        
        command = volumeControl.click(10, 50);
        assertEquals("oob",command);
        assertEquals(0.5f,volumeControl.getVolume(),0.1f);
        
        command = volumeControl.click(10, -10);
        assertEquals("oob",command);
        assertEquals(0.5f,volumeControl.getVolume(),0.1f);
    }
    
    @Test
    public void testExpandAndMinimise() {
        assertFalse(volumeControl.getExpanded()); //check that control is unexpanded by default
        
        volumeControl.draw(testBatch, testAtlas, 100, 10); //feed out of bounds mouse coordinates to control
        assertFalse(volumeControl.getExpanded()); //control shouldn't have expanded
        
        volumeControl.draw(testBatch, testAtlas, 10, 10); //feed in bounds coordinates to control
        assertTrue(volumeControl.getExpanded()); //control should have expanded
        
        volumeControl.draw(testBatch, testAtlas, 10, 50); // feed in expanded bounds coordinates to control
        assertTrue(volumeControl.getExpanded()); //control should still be expanded
        
        volumeControl.draw(testBatch, testAtlas, 100, 50); // feed out of bounds coordinates to control
        assertFalse(volumeControl.getExpanded()); //control should have minimised
    }
    
    @Test
    public void testMouseOverAndChangeVolume() {
        volumeControl.draw(testBatch, testAtlas, 10, 10);
        assertTrue(volumeControl.getExpanded()); //Control should have expanded
        String command = volumeControl.click(10, 50); //click on slider area
        assertEquals("volumeChanged",command);
    }
    
    @Test
    public void testMouseOverAndMinimiseAndAttemptToChangeVolume() {
        volumeControl.draw(testBatch, testAtlas, 10, 10); //feed in bounds click to expand
        assertTrue(volumeControl.getExpanded());
        
        volumeControl.draw(testBatch, testAtlas, 50, 50); //feed out of bounds click to minimise
        assertFalse(volumeControl.getExpanded());
        
        String command = volumeControl.click(10, 50); //click outide unexpanded bounds
        assertEquals("oob",command);
    }
}
