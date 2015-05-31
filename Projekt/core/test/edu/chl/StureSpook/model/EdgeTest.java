/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import java.awt.Point;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Née
 */
public class EdgeTest {
  
    @Test
    public void testDistanceTo() {
        Edge edge = new Edge(new Point(0,0), new Point(20,20));
        Point a = new Point(10,10);
        Point b = new Point(10,20);
        float distanceA = edge.distanceTo(a);
        float distanceB = edge.distanceTo(b);
        assertTrue(distanceA < distanceB);
    }

    @Test
    public void testEquals() {
        Edge edgeA = new Edge(new Point(0,0), new Point(0,20));
        Edge edgeB = new Edge(new Point(0,0), new Point(0,20));
        Edge edgeC = new Edge(new Point(0,0), new Point(20,0));
        
        assertTrue(edgeA.equals(edgeB));
        assertFalse(edgeA.equals(edgeC));
        assertTrue(edgeC.equals(edgeC));
        
    }

    @Test
    public void testGetPoint() {
        Point a = new Point(10,10);
        Point b = new Point(10,20);
        Edge edge = new Edge(a,b);
        assertEquals(a,edge.getPointA());
        assertEquals(b,edge.getPointB());
    }

    @Test
    public void testContains() {
        Point a = new Point(10,10);
        Point b = new Point(10,20);
        Edge edge = new Edge(a,b);
        assertTrue(edge.contains(a));
        assertTrue(edge.contains(b));
    }
    
}
