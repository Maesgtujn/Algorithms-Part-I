package Assign5_KdTrees;

import Assign3_PatternRecognition.Point;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {
    private static final boolean EVEN = true;   //  偶数层(垂直线)
    private static final boolean ODD = false;   //  奇数层(水平线)
    private Node root;
    /**
     * construct an empty set of points
     */
    public KdTree() {

    }

    private static class Node{

        private Point2D p;      //  the point
        private RectHV rect;    //  the axis-aligned rectangle corresponding to this node
        private Node lb;        //  the left/bottom subtree
        private Node rt;        //  the right/top subtree
        private boolean orientation;
        public Node(Point2D p, boolean orientation){
            this.p = p;
            this.orientation = orientation;
        }

    }

    /**
     * is the set empty?
     *
     * @return
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * number of points in the set
     *
     * @return
     */
    public int size() {
        return size(root);
    }

    private int size(Node x){
        if (x == null) return 0;
        return 1 + size(x.lb) + size(x.rt);
    }

    /**
     * add the point to the set(if it is not already in the set)
     *
     * @param p
     */
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        root = insert(root, p);
    }

    private Node insert(Node h, Point2D p){
        if (h == null) return new Node(p, EVEN);
        double cmp;
        if (h.orientation == EVEN)
            cmp = p.x() - h.p.x();
        else
            cmp = p.y() - h.p.y();
        if (cmp < 0) h.lb = insert(h.lb, p);
        else  h.rt = insert(h.rt, p);

        if (isEven(h) && (isEven(h.rt) || isEven(h.lb)))
            h = rotateOrient(h);

        return h;
    }

    private Node rotateOrient(Node h){
        if (h.rt != null)
            h.rt.orientation = ODD;
        else
            h.lb.orientation = ODD;
        return h;
    }

    private boolean isEven(Node h) {
        if (h == null) return false;
        return h.orientation == EVEN;
    }

    /**
     * does the set contain point p ?
     *
     * @param p
     * @return
     */
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return get(p) !=null;
    }

    private Point2D get(Point2D p){
        return  get(root, p);
    }

    private Point2D get(Node h, Point2D p){
        if (h == null)
            return null;
        double cmp;
        if (h.orientation == EVEN)
            cmp = p.x() - h.p.x();
        else
            cmp = p.y() - h.p.y();
        if (h.p.equals(p))
            return h.p;
        else if (cmp < 0)
            return get(h.lb, p);
        else
            return get(h.rt, p);



    }

    /**
     * draw all points to standard draw
     */
    public void draw() {

    }

    /**
     * all points that are inside the rectangle (or on the boundary)
     *
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect) {
        return null;
    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     *
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p) {
        return null;
    }

    /**
     * unit testing of the methods (optional)
     *
     * @param args
     */
    public static void main(String[] args) {
        KdTree kdTree = new KdTree();

        Point2D p1 = new Point2D(0.7, 0.2);
        Point2D p2 = new Point2D(0.5, 0.4);
        Point2D p3 = new Point2D(0.2,0.3);
        Point2D p4 = new Point2D(0.4,0.7);
        Point2D p5 = new Point2D(0.9,0.6);
        Point2D p6 = new Point2D(0.5, 0.4);
        kdTree.insert(p1);
        kdTree.insert(p2);
        kdTree.insert(p3);
        kdTree.insert(p4);
        kdTree.insert(p5);
        StdOut.println(kdTree.size());
        StdOut.println(kdTree.contains(p6));
    }
}
