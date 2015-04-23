/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.HashMap;

/**
 *
 * @author Née
 */
public interface DrawableSprite {
    public void draw(SpriteBatch batch, HashMap<String,Texture> texture);
}
