/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import java.beans.PropertyChangeListener;

/**
 *
 * @author Née
 */
public interface GameModel {
    public void update();
    public void init();
    
    public void addPropertyChangeListener(PropertyChangeListener l);
    public void removePropertyChangeListener(PropertyChangeListener l);
    
}
