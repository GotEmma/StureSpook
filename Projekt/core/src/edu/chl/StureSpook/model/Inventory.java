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
    private static final int DEFAULT_INVENTORY_SIZE = 5;
    private Item[] items;
    
    public Inventory() {
        this(Inventory.DEFAULT_INVENTORY_SIZE);
    }
    
    public Inventory(int inventorySize) {
        items = new Item[inventorySize];
    }
    
    public int getInventorySize() {
        return Inventory.DEFAULT_INVENTORY_SIZE;
    }
  
    /**
     * Returns an item from the inventory
     * @param index the zero-based index of the inventory slot to return
     * @return the specified item, null if slot is empty
     */
    public Item getItem(int index) {
        return this.items[index];
    }
    
    /**
     * 
     * @return an array of items in the inventory
     */
    public Item[] getItems() {
        return this.items.clone();
    }
    
    /**
     * Adds an item to inventory
     * @param item the item to add
     * @return true if item was added, false if inventory was full
     */
    public boolean addItem(Item item) {
        for (int i=0; i< this.items.length; i++) {
            if (this.items[i] == null) {
                this.items[i] = item;
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Removes an item from the inventory
     * @param index the index of the item to remove
     * @return the removed item, null if slot was empty
     */
    public Item removeItem(int index) {
        Item i = this.items[index];
        this.items[index] = null;
        return i;
    }
    
    /**
     * Removes an item from the inventory
     * @param item the item to remove
     * @return  true if the item was in the inventory, else false
     */
    public boolean removeItem(Item item) {
        for (int i=0; i< this.items.length; i++) {
            if (this.items[i].equals(item)) {
                this.items[i] = null;
                return true;
            }
        }
        
        return false;
    }
    
}
