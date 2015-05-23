/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

/**
 *
 * @author emmafahlen
 */
public class Inventory {
    private Item[] items;
  
    /**
     * Returns an item from the inventory
     * @param index the zero-based index of the inventory slot to return
     * @return the specified item, null if slot is empty
     */
    public Item getItem(int index) {
        // TODO: Add method body
        return null;
    }
    
    /**
     * 
     * @return an array of items in the inventory
     */
    public Item[] getItems() {
        // TODO: Add method body
        return null; // maybe we shouldn't return the items array, it would make it mutable
    }
    
    /**
     * Adds an item to inventory
     * @param item the item to add
     * @return true if item was added, false if inventory was full
     */
    public boolean addItem(Item item) {
        // TODO: Add method body
        return true;
    }
    
    /**
     * Removes an item from the inventory
     * @param index the index of the item to remove
     * @return the removed item, null if slot was empty
     */
    public Item removeItem(int index) {
        // TODO: Add method body
        return null;
    }
    
    /**
     * Removes an item from the inventory
     * @param item the item to remove
     * @return  true if the item was in the inventory, else false
     */
    public boolean removeItem(Item item) {
        // TODO: Add method body
        return true;
    }
    
}
