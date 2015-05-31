/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Linneas
 */
public class MainMenuModelTest {
    private MainMenuModel model;
    private TestListener listener;
    
    
    @Before
    public void setUp() {
        model = new MainMenuModel();
        model.init();
        listener = new TestListener();
        model.addPropertyChangeListener(listener);
    }
    
    @After
    public void tearDown() {
        model.removePropertyChangeListener(listener);
        model = null;
        listener = null;
    }

    @Test
    public void testUpdate() {
        model.update();
        assertTrue(listener.isTriggered());
        assertEquals("logic updated", listener.getEvent().getPropertyName());
    }

    @Test
    public void testTrigger() {
        model.trigger();
        assertTrue(listener.isTriggered());
        assertEquals("New Game", listener.getEvent().getPropertyName());
    }

    @Test
    public void testMoveDown() {
        int firstIndex = model.getSelectedIndex();
        model.moveDown();
        assertEquals(firstIndex+1, model.getSelectedIndex());
    }

    @Test
    public void testMoveUp() {
        int firstIndex = model.getSelectedIndex();
        model.moveDown();
        model.moveDown();
        model.moveUp();
        assertEquals(firstIndex+1, model.getSelectedIndex());
    }
    
    @Test
    public void testMenuLoopFirstToLast() {
        model.moveUp();
        int expectedIndex = model.getMenuItems().length-1;
        assertEquals(expectedIndex, model.getSelectedIndex());
    }
    
    @Test
    public void testMenuLoopLastToFirst() { 
        // depends on FirstToLast, make sure that one works before trying to fix this.
        int originalIndex = model.getSelectedIndex();
        model.moveUp();
        model.moveDown();
        assertEquals(originalIndex, model.getSelectedIndex());
    }

    @Test
    public void testGetMenuItems() {
        //depends on moveDown
        MenuItem[] items = model.getMenuItems();
        for (MenuItem i : items) {
            assertEquals(i,model.getSelectedItem());
            model.trigger();
            assertEquals(listener.event.getPropertyName(),i.getName());
            model.moveDown();
        }
    }
    
    private class TestListener implements PropertyChangeListener {
        private PropertyChangeEvent event;
        private boolean triggered = false;
        
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            this.event = evt;
            this.triggered = true;
        }
        
        public boolean isTriggered() {
            return this.triggered;
        }
        
        public PropertyChangeEvent getEvent() {
            return this.event;
        }
    
    }
    
}
