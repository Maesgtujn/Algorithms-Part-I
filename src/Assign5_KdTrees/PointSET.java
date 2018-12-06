package Assign5_KdTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.Stack;


public class PointSET {

    private SET<Point2D> set;

    /**
     * construct an empty set of points
     */
    public PointSET() {
        set = new SET<>();
    }

    /**
     * is the set empty?
     *
     * @return
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * number of points in the set
     *
     * @return
     */
    public int size() {
        return set.size();
    }

    /**
     * add the point to the set(if it is not already in the set)
     *
     * @param p
     */
    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        if (!contains(p))
            set.add(p);
    }

    /**
     * does the set contain point p ?
     *
     * @param p
     * @return
     */
    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        return set.contains(p);
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        for (Point2D p : set)
            p.draw();
    }

    /**
     * all points that are inside the rectangle (or on the boundary)
     *
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();
        RectHV rectHV = rect;
        Stack<Point2D> stack = new Stack<>();
        for (Point2D p : set) {
            if (rectHV.contains(p))
                stack.push(p);
        }

        return stack;

    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     *
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        double shortestSD = Double.POSITIVE_INFINITY;
        Point2D nearestP = null;
        if (isEmpty())
            return null;
        for (Point2D point2D : set){
            double squaredDistance = point2D.distanceSquaredTo(p);
            if (squaredDistance < shortestSD) {
                shortestSD = squaredDistance;
                nearestP = point2D;
            }
        }
        return nearestP;
    }

    /**
     * unit testing of the methods (optional)
     *
     * @param args
     */
    public static void main(String[] args) {
    }
}
