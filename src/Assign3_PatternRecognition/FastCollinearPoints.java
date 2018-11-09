package Assign3_PatternRecognition;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {

    private int num = 0;
    private LineSegment[] segmentsArray;
    /**
     * finds all line segments containing 4 or more points
     * @param points
     */
    public FastCollinearPoints(Point[] points){
        validate(points);
        int N = points.length;

        Arrays.sort(points);
        /* validate the repeated points */
        for (int i = 0; i < N - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException("contain a repeated point");
        }

        segmentsArray = new LineSegment[N * 4];

        for (int i = 0; i < N-3; i++){

            Point p = points[i];

            Point[] slopeOrderPoints = new Point[N];  //slopeOrder based on point p
            System.arraycopy(points, 0, slopeOrderPoints, 0, N);

            Arrays.sort(slopeOrderPoints, i+1, N, p.slopeOrder());

            double flag = p.slopeTo(slopeOrderPoints[i+1]);
            int n = 0;
            for (int q = i+2; q < N; q++){
                double slopePQ = p.slopeTo(slopeOrderPoints[q]);

                if (slopePQ == flag ){
                    n++;
                    if (q == N-1){
                        if (n >= 2){
                            LineSegment lineSegment = new LineSegment(p, slopeOrderPoints[q]);
                            segmentsArray[num] = lineSegment;
                            num++;
                        }
                    }

                }
                else {
                    flag = slopePQ;
                    if (n >= 2){
                        LineSegment lineSegment = new LineSegment(p, slopeOrderPoints[q-1]);
                        segmentsArray[num] = lineSegment;
                        num++;
                    }
                    n = 0;
                }

            }
        }
    }

    /**
     * the number of line segments
     * @return
     */
    public int numberOfSegments(){
        return num;
    }

    /**
     * the line segments
     * @return
     */
    public LineSegment[] segments(){
        LineSegment[] lineSegments = new LineSegment[num];
        if(num > 0) {
            System.arraycopy(segmentsArray, 0, lineSegments, 0, num);
            return lineSegments;
        }
        return null;
    }
    private void validate(Point[] points){
        if (points == null)
            throw new IllegalArgumentException("array is null");
        for (int i = 0; i < points.length; i++){
            if (points[i] == null)
                throw new IllegalArgumentException("point is null");
        }
    }

    public static void main(String[] args){
        In in =  new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++){
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x,y);
        }

        //  draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0 ,32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points){
            p.draw();
        }
        StdDraw.show();

        //  print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        if (collinear.segments() != null) {
            for (LineSegment segment : collinear.segments()) {
                StdOut.println(segment);
                segment.draw();
            }
            StdDraw.show();
        }

    }
}
