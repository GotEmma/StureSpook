/*

 */
package edu.chl.StureSpook.model;

import java.awt.Point;

/**
 *
 * @author Olof
 */
public class Edge {
    Point a,b;
    
    public Edge(Point a, Point b){
        this.a = a;
        this.b = b;
    }
    
    /**
     * Calculates the distance from the edge to a point
     * This is calculated as a average between the distance for it's start
     * and endpoint. This method works as long as every edge is the same size.
     * If edges of different sizes where to be introduced this method must
     * be updated. 
     */
    public float distanceTo(Point point){
        float distanceToA = (float)a.distance(point);
        float distanceToB = (float)b.distance(point);
        
        return (distanceToA+distanceToB)/2;
    }
    
    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }else if(other==this){
            return true;
        }else if(other.getClass()!=this.getClass()){
            return false;
        }
        Edge otherEdge = (Edge)other;
        return a.equals(otherEdge.getPointA()) && b.equals(otherEdge.getPointB());
    }
    
    public Point getPointA(){
        return a;
    }
    
    public Point getPointB(){
        return b;
    }
    
    public boolean contains(Point point){
        return a.equals(point) || b.equals(point);
    }
    
}
