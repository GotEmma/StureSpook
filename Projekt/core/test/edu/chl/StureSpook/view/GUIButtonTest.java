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
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 *
 * @author Linneas
 */
public class GUIButtonTest {
    
    
    private GUIButton button;
    private String testCommand = "test";
    private SpriteBatch testBatch;
    private TextureAtlas testAtlas;
    
    
    public GUIButtonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        button = new GUIButton(testCommand, "testNonMouseOver", "testMouseOver", 0, 0, 20, 20);
        testBatch = mock(SpriteBatch.class);
        
        testAtlas = mock(TextureAtlas.class);
       // when(testAtlas.findRegion(anyString())).thenReturn(null);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testDraw() {
        button.draw(testBatch, testAtlas, 10, 10);
        assertEquals(button.getActiveTexture(), "testMouseOver");
        
        button.draw(testBatch, testAtlas, 0, 0);
        assertEquals(button.getActiveTexture(), "testNonMouseOver"); 
        
        button.draw(testBatch, testAtlas, 25, 25);
        assertEquals(button.getActiveTexture(), "testNonMouseOver");
        
    }

    @Test
    public void testClickInBounds() {
        String s = button.click(10, 10);
        assertEquals(s, testCommand);
    }
    
    @Test
    public void testClickOutOfBounds() {
        String s = button.click(25, 25);
        assertEquals(s, "oob");
    }
    
}
