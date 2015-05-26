/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.view;

import com.badlogic.gdx.InputProcessor;
import edu.chl.StureSpook.controller.DesktopInputListener;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Née
 */
public interface GameView extends PropertyChangeListener, InputProcessor{
    public void init();

    public void addInputListener(DesktopInputListener desktopInputHandler);
}
