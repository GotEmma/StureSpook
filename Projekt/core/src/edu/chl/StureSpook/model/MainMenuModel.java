/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.StureSpook.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Née
 */
public class MainMenuModel implements GameModel{
    private MenuItem [] menuItems;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private int selectedIndex;
    
    

    @Override
    public void update() {
        //key-events?
        pcs.firePropertyChange("logic updated", 1, 0);
    }

    @Override
    public void init() {
        menuItems = new MenuItem [] {new MenuItem("New Game", "newGameMenuItem"), new MenuItem("Load Game", "loadGameMenuItem"), 
            new MenuItem("Options", "settingsMenuItem"), new MenuItem("Quit", "quitGameMenuItem")};
        
    }
    
    public void trigger() {
        if(getSelectedItem().getName().equals("New Game")) {
            this.pcs.firePropertyChange("New Game", 1, 0);
        }
        if(getSelectedItem().getName().equals("Load Game")) {
            this.pcs.firePropertyChange("Load Game", 1, 0);   
        }
        if (getSelectedItem().getName().equals("Options")) {
            this.pcs.firePropertyChange("Options", 1, 0);
        }
        if (getSelectedItem().getName().equals("Quit")) {
            this.pcs.firePropertyChange("Quit", 1, 0);
        }
    }
    
    public void moveDown(){
        selectedIndex = (selectedIndex + 1) % this.menuItems.length;
    }
    public void moveUp(){
        selectedIndex = (selectedIndex-1 >= 0) ? (selectedIndex-1) : (menuItems.length-1);
    }
    
    public MenuItem getSelectedItem(){
        return menuItems[selectedIndex];
    }
    
    public int getSelectedIndex(){
        return selectedIndex;
    }
    
    public MenuItem [] getMenuItems(){
        return menuItems;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

}

