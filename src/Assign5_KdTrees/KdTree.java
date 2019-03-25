package Assign5_KdTrees;


import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * The {@code KdTree} class represents a set of points
 * in the unit square. It supports efficient
 * <em>range search</em> (find all of the points contained
 * in a query rectangle) and <em>nearest neighbor search</em>
 * (find a closest point to a query point) by using a 2d-tree.
 *
 * @author jimmyli
 */
public class KdTree {

    private Node root;
    private int size;

    private static class Node {

        private Point2D p;              //  the point
        private RectHV rect;            //  the axis-aligned rectangle corresponding to this node
        private Node lb;                //  the left/bottom subtree
        private Node rt;                //  the right/top subtree
        private boolean isEvenLevel;    //  is the node at even level

        public Node(Point2D p, boolean isEvenLevel, RectHV rect) {
            this.p = p;
            this.isEvenLevel = isEvenLevel;
            this.rect = rect;
        }

    }

    /**
     * construct an empty set of points
     */
    public KdTree() {
    }

    /**
     * is the set empty?
     *
     * @return true if the 2d-tree is empty;
     * false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * number of points in the set
     *
     * @return the number of nodes in the 2d-tree
     */
    public int size() {
        return size;
    }

    /**
     * add the point to the set(if it is not already in the set)
     *
     * @param p the point
     * @throws IllegalArgumentException if the point is null
     */
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        root = insert(root, null, p, 0);
    }

    private Node insert(Node h, Node parent, Point2D p, int direction) {
        if (h == null) {
            //  if 2d-tree is null, then insert Node with a unit rectangle
            if (size++ == 0) return new Node(p, true, new RectHV(0, 0, 1, 1));

            RectHV rectOfH = parent.rect;   //  rectangle of Node x

            if (direction < 0)  //  go left sub-tree
            {
                if (parent.isEvenLevel) //  left sub-rectangle
                    rectOfH = new RectHV(parent.rect.xmin(), parent.rect.ymin(),
                            parent.p.x(), parent.rect.ymax());
                else    //  bottom sub-rectangle
                    rectOfH = new RectHV(parent.rect.xmin(), parent.rect.ymin(),
                            parent.rect.xmax(), parent.p.y());
            } else if (direction > 0) //  go right sub-tree
            {
                if (parent.isEvenLevel) //  right sub-rectangle
                    rectOfH = new RectHV(parent.p.x(), parent.rect.ymin(),
                            parent.rect.xmax(), parent.rect.ymax());
                else    //  top sub-rectangle
                    rectOfH = new RectHV(parent.rect.xmin(), parent.p.y(),
                            parent.rect.xmax(), parent.rect.ymax());
            }
            return new Node(p, !parent.isEvenLevel, rectOfH);
        }
        int cmp = compare(p, h.p, h.isEvenLevel);

        if (cmp < 0) h.lb = insert(h.lb, h, p, cmp);
        else if (cmp > 0) h.rt = insert(h.rt, h, p, cmp);
        return h;
    }

    private int compare(Point2D p, Point2D q, boolean isEven) {
        if (p == null || q == null) throw new NullPointerException();
        if (p.equals(q)) return 0;
        if (isEven) return p.x() < q.x() ? -1 : 1;
        else return p.y() < q.y() ? -1 : 1;
    }

    /**
     * Does the set contain point p ?
     *
     * @param p the point
     * @return true if the 2d-tree contains p;
     * false otherwise
     * @throws IllegalArgumentException if the point is null
     */
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        return contains(root, p);
    }

    private boolean contains(Node h, Point2D p) {
        if (h == null) return false;

        int cmp = compare(p, h.p, h.isEvenLevel);

        if (cmp < 0) return contains(h.lb, p);
        else if (cmp > 0) return contains(h.rt, p);
        else return true;
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        draw(root);
    }

    private void draw(Node h) {
        if (h == null) return;
        draw(h.lb);
        draw(h.rt);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.02);
        h.p.draw();
        //  draw the splitting line segment
        if (h.isEvenLevel) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.01);
            StdDraw.line(h.p.x(), h.rect.ymin(), h.p.x(), h.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius(0.01);
            StdDraw.line(h.rect.xmin(), h.p.y(), h.rect.xmax(), h.p.y());
        }
    }

    /**
     * all points that are inside the rectangle (or on the boundary)
     * Returns all points that are inside the rectangle as an {@code Iterable}.
     *
     * @param rect the rectangle
     * @return all points inside the rectangle
     */
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();

        Queue<Point2D> pointQueue = new Queue<>();

        range(root, pointQueue, rect);
        return pointQueue;
    }

    private void range(Node h, Queue<Point2D> pointQueue, RectHV rect) {
        if (h == null) return;
        if (rect.contains(h.p)) pointQueue.enqueue(h.p);
        //  if the left sub-rectangle intersects rect, then search the left-tree
        if (h.lb != null && rect.intersects(h.lb.rect))
            range(h.lb, pointQueue, rect);
        if (h.rt != null && rect.intersects(h.rt.rect))
            range(h.rt, pointQueue, rect);
    }

    /**
     * a nearest neighbor in the set to point p;
     * null if the set is empty
     *
     * @param p the point
     * @return a nearest neighbor in the 2d-tree to p
     */
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null)
            return null;
        return nearest(root, root.p, p);
    }

    private Point2D nearest(Node x, Point2D nearest, Point2D p) {
        if (x == null) return nearest;

        int cmp = compare(p, x.p, x.isEvenLevel);

        if (p.distanceSquaredTo(x.p) < p.distanceSquaredTo(nearest)) nearest = x.p;
        if (cmp < 0) {
            nearest = nearest(x.lb, nearest, p);
            //  compare the current nearest to the possible nearest in the other side
            if (x.rt != null)
                if (x.rt.rect.distanceSquaredTo(p) < nearest.distanceSquaredTo(p))
                    nearest = nearest(x.rt, nearest, p);
        } else if (cmp > 0) {
            nearest = nearest(x.rt, nearest, p);
            if (x.lb != null)
                if (x.lb.rect.distanceSquaredTo(p) < nearest.distanceSquaredTo(p))
                    nearest = nearest(x.lb, nearest, p);
        }
        return nearest;
    }

    /**
     * unit testing of the methods (optional)
     * Unit test the {@code KdTree} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        double timeOfInsert = 0.0;
        double timeOfNearest = 0.0;
        double timeOfRange = 0.0;
        KdTree kdtree = new KdTree();
        Stopwatch timer;
        Point2D p;

        for (int i = 0; i < 1000000; i++) {
            p = new Point2D(StdRandom.uniform(0.0, 1.0),
                    StdRandom.uniform(0.0, 1.0));
            timer = new Stopwatch();
            kdtree.insert(p);
            timeOfInsert += timer.elapsedTime();
        }
        StdOut.print("time cost of insert(random point)(1M times)    : ");
        StdOut.println(timeOfInsert);

        for (int i = 0; i < 100; i++) {
            p = new Point2D(StdRandom.uniform(0.0, 1.0),
                    StdRandom.uniform(0.0, 1.0));
            timer = new Stopwatch();
            kdtree.nearest(p);
            timeOfNearest += timer.elapsedTime();
        }
        StdOut.print("time cost of nearest(random point)(100 times)  : ");
        StdOut.println(timeOfNearest);

        for (int i = 0; i < 100; i++) {
            double xmin = StdRandom.uniform(0.0, 1.0);
            double ymin = StdRandom.uniform(0.0, 1.0);
            double xmax = StdRandom.uniform(0.0, 1.0);
            double ymax = StdRandom.uniform(0.0, 1.0);
            RectHV rect;

            if (xmin > xmax) {
                double swap = xmin;

                xmin = xmax;
                xmax = swap;
            }
            if (ymin > ymax) {
                double swap = ymin;

                ymin = ymax;
                ymax = swap;
            }
            rect = new RectHV(xmin, ymin, xmax, ymax);
            timer = new Stopwatch();
            kdtree.range(rect);
            timeOfRange += timer.elapsedTime();
        }
        StdOut.print("time cost of range(random rectangle)(100 times): ");
        StdOut.println(timeOfRange);
    }
}
