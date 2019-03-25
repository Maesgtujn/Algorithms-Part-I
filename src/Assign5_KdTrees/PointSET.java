package Assign5_KdTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Stack;

/**
 * The {@code PointSET} class represents a points of points
 *
 */
public class PointSET {

    private SET<Point2D> points;

    /**
     * construct an empty points of points
     */
    public PointSET() {
        points = new SET<>();
    }

    /**
     * is the points empty?
     *
     * @return
     */
    public boolean isEmpty() {
        return points.isEmpty();
    }

    /**
     * number of points in the points
     *
     * @return
     */
    public int size() {
        return points.size();
    }

    /**
     * add the point to the points(if it is not already in the points)
     *
     * @param p
     */
    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        points.add(p);
    }

    /**
     * does the points contain point p ?
     *
     * @param p
     * @return
     */
    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        return points.contains(p);
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        for (Point2D p : points)
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

        Stack<Point2D> stack = new Stack<>();
        for (Point2D p : points) {
            if (rect.contains(p)) stack.push(p);
        }

        return stack;

    }

    /**
     * a nearest neighbor in the points to point p; null if the points is empty
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
        for (Point2D point2D : points){
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
        double timeOfInsert = 0.0;
        double timeOfNearest = 0.0;
        double timeOfRange = 0.0;
        PointSET brute = new PointSET();
        Stopwatch timer;
        Point2D p;

        for (int i = 0; i < 1000000; i++)
        {
            p = new Point2D(StdRandom.uniform(0.0, 1.0),
                    StdRandom.uniform(0.0, 1.0));
            timer = new Stopwatch();
            brute.insert(p);
            timeOfInsert += timer.elapsedTime();
        }
        StdOut.print("time cost of insert(random point)(1M times)    : ");
        StdOut.println(timeOfInsert);

        for (int i = 0; i < 100; i++)
        {
            p = new Point2D(StdRandom.uniform(0.0, 1.0),
                    StdRandom.uniform(0.0, 1.0));
            timer = new Stopwatch();
            brute.nearest(p);
            timeOfNearest +=  timer.elapsedTime();
        }
        StdOut.print("time cost of nearest(random point)(100 times)  : ");
        StdOut.println(timeOfNearest);

        for (int i = 0; i < 100; i++)
        {
            double xmin = StdRandom.uniform(0.0, 1.0);
            double ymin = StdRandom.uniform(0.0, 1.0);
            double xmax = StdRandom.uniform(0.0, 1.0);
            double ymax = StdRandom.uniform(0.0, 1.0);
            RectHV rect;

            if (xmin > xmax)
            {
                double swap = xmin;

                xmin = xmax;
                xmax = swap;
            }
            if (ymin > ymax)
            {
                double swap = ymin;

                ymin = ymax;
                ymax = swap;
            }
            rect = new RectHV(xmin, ymin, xmax, ymax);
            timer = new Stopwatch();
            brute.range(rect);
            timeOfRange += timer.elapsedTime();
        }
        StdOut.print("time cost of range(random rectangle)(100 times): ");
        StdOut.println(timeOfRange);
    }
}
