/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

/**
 *
 * @author Linneas
 */
public class MenuItem {
    private String name;
    private int key;
    
    public MenuItem(String name){
        this.name = name;
    
    }
    public MenuItem(String name, int key){
        this.key = key;
    }
    
}
