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
        menuItems = new MenuItem [] {new MenuItem("New Game"), new MenuItem("Load Game"), 
            new MenuItem("Options"), new MenuItem("Quit")};
        
    }
    
    public void trigger() {
        if(menuItems[this.selectedIndex].getName().equals("New Game")) {
            this.pcs.firePropertyChange("New Game", 1, 0);
        }
        if(menuItems[this.selectedIndex].getName().equals("Load Game")) {
            this.pcs.firePropertyChange("Load Game", 1, 0);   
        }
        if (menuItems[this.selectedIndex].getName().equals("Options")) {
            this.pcs.firePropertyChange("New Game", 1, 0);
        }
        if (menuItems[this.selectedIndex].getName().equals("Quit")) {
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

    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

}

