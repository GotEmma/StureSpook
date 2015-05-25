/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.StureSpook.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 *
 * @author User
 */
public class Flashlight {

    private float startPointX, startPointY;
    private float endPointX, endPointY;
    private Point startPoint = new Point(0, 0);
    private Point visionConePointA = new Point(0, 0);
    private Point visionConePointB = new Point(0, 0);

    //Vision parameters
    private double visionRadian = Math.PI / 6;
    private float distanceVisible = 200;

    private Point[] points;
    private Edge[] edges;

    private int test = 0;

    public void setStartPoint(float x, float y) {
        this.startPointX = x;
        this.startPointY = y;
        startPoint.setLocation(Math.round(startPointX), Math.round(startPointY));
    }

    public void setEndPoint(float x, float y) {
        this.endPointX = x;
        this.endPointY = y;

        //Creates the 2 points that the visioncone is made of. (rotates the endpoint given by the mouse)
        float relativeX = this.endPointX - this.startPointX;
        float relativeY = this.endPointY - this.startPointY;
        visionConePointA.setLocation((int) (relativeX * (float) Math.cos(visionRadian / 2) - relativeY * (float) Math.sin(visionRadian / 2) + this.startPointX), (int) (relativeY * (float) Math.cos(visionRadian / 2) + relativeX * (float) Math.sin(visionRadian / 2) + this.startPointY));
        visionConePointB.setLocation((int) (relativeX * (float) Math.cos(-visionRadian / 2) - relativeY * (float) Math.sin(-visionRadian / 2) + this.startPointX), (int) (relativeY * (float) Math.cos(-visionRadian / 2) + relativeX * (float) Math.sin(-visionRadian / 2) + this.startPointY));

    }

    public float getStartPointX() {
        return this.startPointX;
    }

    public float getStartPointY() {
        return this.startPointY;
    }

    public float getEndPointX() {
        return this.endPointX;
    }

    public float getEndPointY() {
        return this.endPointY;
    }

    public float[] getPolygon() {
        float[] polygon = {this.startPointX, this.startPointY, (float) visionConePointA.x, (float) visionConePointA.y, (float) visionConePointB.x, (float) visionConePointB.y};

        if (test < 100) {
            test++;
            return polygon;
        } else {
            return computeVisionPolygon();
        }

    }

    //Updates the points and edges used to calculate the raycasting
    //As of now only needs to be updated when you enter a new level.
    //If moving collidable object are introduced this needs to be updated.
    public void updateCollidableMap(boolean[][] map) {
        HashSet<Point> pointSet = new HashSet<Point>();
        HashSet<Edge> edgeSet = new HashSet<Edge>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j]) {
                    Point lowLeft = new Point(16 * i, 16 * j);
                    Point topLeft = new Point(16 * i, 16 * j + 16);
                    Point lowRight = new Point(16 * i + 16, 16 * j);
                    Point topRight = new Point(16 * i + 16, 16 * j + 16);
                    pointSet.add(lowLeft);
                    pointSet.add(topLeft);
                    pointSet.add(lowRight);
                    pointSet.add(topRight);

                    Edge low = new Edge(lowLeft, lowRight);
                    Edge left = new Edge(lowLeft, topLeft);
                    Edge right = new Edge(lowRight, topRight);
                    Edge top = new Edge(topLeft, topRight);
                    edgeSet.add(low);
                    edgeSet.add(left);
                    edgeSet.add(right);
                    edgeSet.add(top);

                }
            }
        }
        points = new Point[pointSet.size()];
        edges = new Edge[edgeSet.size()];
        pointSet.toArray(points);
        edgeSet.toArray(edges);
    }

    //Sorts edges relative to their distance from startPoint
    //Uses a recursive quicksort helpfunction.
    private void sortEdges() {
        int high = edges.length - 1;
        int low = 0;
        quicksort(edges, low, high);
    }

    private void quicksort(Edge[] list, int low, int high) {
        int i = low;
        int j = high;
        Edge pivot = list[low + (high - low) / 2];
        while (i <= j) {
            while (list[i].distanceTo(startPoint) < pivot.distanceTo(startPoint)) {
                i++;
            }
            while (list[j].distanceTo(startPoint) > pivot.distanceTo(startPoint)) {
                j--;
            }
            if (i <= j) {
                Edge temp = list[i];
                list[i] = list[j];
                list[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j) {
            quicksort(list, low, j);
        }
        if (high > i) {
            quicksort(list, i, high);
        }
    }

    //Traces the line created by a and b and returns the point where this line
    //intersects with the edge. If it never intersects it returns null
    //Uses a simple equation solving method. 
    private Point rayTracing(Point a, Point b, Edge intersection) {
        float k1 = ((float) (a.y - b.y)) / ((float) (a.x - b.x));
        float m1 = a.y - k1 * a.x;

        float newX;
        float newY;
        if (intersection.getPointA().y == intersection.getPointB().y) {
            newY = intersection.getPointA().y;
            newX = (newY - m1) / k1;
            if (newX > Math.max(intersection.getPointA().x, intersection.getPointB().x) || newX < Math.min(intersection.getPointA().x, intersection.getPointB().x)) {
                return null;
            }
        } else if (intersection.getPointA().x == intersection.getPointB().x) {
            newX = intersection.getPointA().x;
            newY = k1 * newX + m1;
            if (newY > Math.max(intersection.getPointA().y, intersection.getPointB().y) || newY < Math.min(intersection.getPointA().y, intersection.getPointB().y)) {
                return null;
            }
        } else {
            float k2 = ((float) (intersection.getPointA().y - intersection.getPointB().y)) / ((float) (intersection.getPointA().x - intersection.getPointB().x));
            float m2 = intersection.getPointA().y - k2 * intersection.getPointA().x;
            newX = (m2 - m1) / (k1 - k2);
            newY = k1 * newX + m1;
        }
        return new Point(Math.round(newX), Math.round(newY));
    }

    private boolean insideVisionCone(Point p) {
        float pointRadian = computeRadian(p);
        float aRadian = computeRadian(visionConePointA);
        float bRadian = computeRadian(visionConePointB);
        if (aRadian > bRadian) {
            return pointRadian <= aRadian && pointRadian >= bRadian && computeDistance(p) <= distanceVisible;
        } else {
            return pointRadian <= bRadian && pointRadian >= aRadian && computeDistance(p) <= distanceVisible;
        }
    }

    // Radian in relation to the startposition
    private float computeRadian(Point p) {
        return (float) Math.atan2(p.y - startPoint.y, p.x - startPoint.x);

    }

    // Distance relative to the startPoint
    private float computeDistance(Point p) {
        return (float) Math.sqrt((((float) p.x) - startPointX) * (((float) p.x) - startPointX) + (((float) p.y) - startPointY) * (((float) p.y) - startPointY));
    }

    private float[] computeVisionPolygon() {
        PriorityQueue<Point> polygon = new PriorityQueue<Point>(50, new RadianComparator());
        //System.out.println("size points: " + points.length);

        PriorityQueue<Point> sortedPoints = new PriorityQueue<Point>(points.length, new RadianComparator());
        sortedPoints.addAll(Arrays.asList(points));

        sortEdges();
        //Start point
        polygon.add(startPoint);

        //First point
        for (Edge edge : edges) {
            if (insideVisionCone(edge.getPointA()) || insideVisionCone(edge.getPointB())) {
                Point raytraced = rayTracing(startPoint, visionConePointA, edge);
                if (raytraced != null) {
                    polygon.add(raytraced);
                    break;
                }
            }
        }

        while (!sortedPoints.isEmpty()) {
            Point point = sortedPoints.poll();
            if (insideVisionCone(point)) {
                for (Edge edge : edges) {
                    Point raytraced = rayTracing(startPoint, point, edge);
                    if (insideVisionCone(edge.getPointA()) || insideVisionCone(edge.getPointB())) {
                        if (raytraced != null) {
                            /*
                             System.out.println("startPoint: (" + startPoint.x + ", " + startPoint.y + ")");
                             System.out.println("point: (" + point.x + ", " + point.y + ")");
                             System.out.println("e1: (" + edge.getPointA().x + ", " + edge.getPointA().y + ")");
                             System.out.println("e2: (" + edge.getPointB().x + ", " + edge.getPointB().y + ")");
                             System.out.println("raytraced: (" + raytraced.x + ", " + raytraced.y + ")");
                             */
                            polygon.add(raytraced);
                            break;
                        }
                    }
                }
            }
        }

        //Last point
        for (Edge edge : edges) {
            if (insideVisionCone(edge.getPointA()) || insideVisionCone(edge.getPointB())) {
                Point raytraced = rayTracing(startPoint, visionConePointB, edge);
                if (raytraced != null) {
                    polygon.add(raytraced);
                    break;
                }
            }
        }
        
        /*
        System.out.println("Size polygon: " + polygon.size());
        System.out.println("vision a: (" + visionConePointA.x + ", " + visionConePointA.y + ")");
        System.out.println("vision b: (" + visionConePointB.x + ", " + visionConePointB.y + ")");
        */
        
        //Transforms the points to float
        float[] returned = new float[2 * polygon.size()];
        int j = 0;
        while (!polygon.isEmpty()) {
            Point point = polygon.poll();
            //System.out.println("Point: (" + point.x + ", " + point.y + ")");
            returned[j] = point.x;
            j++;
            returned[j] = point.y;
            j++;
        }

        return returned;
    }

    private class RadianComparator implements Comparator<Point> {

        // Returns 1 if a's radian is larger than b's
        // and -1 if the other way around.
        // Returns 0 if a and b is the same Point or if they have the same radian.
        @Override
        public int compare(Point a, Point b) {

            if (a.equals(b)) {
                return 0;
            }
            float radianA = computeRadian(a);
            float radianB = computeRadian(b);
            if (radianA > radianB) {
                return 1;
            } else if (radianA < radianB) {
                return -1;
            } else {
                return 0;
            }
        }
    }

}
