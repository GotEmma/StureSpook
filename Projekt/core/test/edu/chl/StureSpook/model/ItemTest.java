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
public class ItemTest {
    @Test
    public void testItem() {
        Item item = new Item("testItemName","testItemTextureName");
        assertEquals(item.getName(), "testItemName");
        assertEquals(item.getTextureName(), "testItemTextureName");
        
    }

    
}
