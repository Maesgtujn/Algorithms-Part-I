package Assign3_PatternRecognition;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {

    private int numOfSegments = 0;
    private LineSegment[] segmentsArray;

    /**
     * finds all line segments containing 4 or more points
     *
     * @param points
     */
    public FastCollinearPoints(Point[] points) {
        validate(points);
        int numOfPoints = points.length;
        Point[] copyPoints = new Point[numOfPoints];
        System.arraycopy(points, 0, copyPoints, 0, numOfPoints);
        Arrays.sort(copyPoints);

        /* validate the repeated points */
        for (int i = 0; i < numOfPoints - 1; i++) {
            if (copyPoints[i].compareTo(copyPoints[i + 1]) == 0)
                throw new IllegalArgumentException("contain a repeated point");
        }

        segmentsArray = new LineSegment[2];

        for (int i = 0; i < numOfPoints - 3; i++) {

            Point p = copyPoints[i];

            Point[] slopeOrderPoints = new Point[numOfPoints];    //slopeOrder based on point p
            System.arraycopy(copyPoints, 0, slopeOrderPoints, 0, numOfPoints);


//            Arrays.sort(slopeOrderPoints, i + 1, numOfPoints, p.slopeOrder());
            Arrays.sort(slopeOrderPoints, p.slopeOrder());

            double flag = p.slopeTo(slopeOrderPoints[1]);
            int n = 0;
            for (int q = 2; q < numOfPoints; q++) {
                double slopePQ = p.slopeTo(slopeOrderPoints[q]);

                if (slopePQ == flag) {
                    n++;
                    if (q == numOfPoints - 1 && n >= 2 && p.compareTo(slopeOrderPoints[q - n]) < 0) {
                        addSegment(new LineSegment(p, slopeOrderPoints[q]));
                    }

                } else {
                    flag = slopePQ;
                    if (n >= 2 && p.compareTo(slopeOrderPoints[q - n - 1]) < 0) {
                        addSegment(new LineSegment(p, slopeOrderPoints[q - 1]));
                    }
                    n = 0;
                }
            }

        }
    }



    private void addSegment(LineSegment lineSegment) {
        if (numOfSegments == segmentsArray.length)
            resize(2 * segmentsArray.length);
        segmentsArray[numOfSegments++] = lineSegment;
    }

    private void resize(int capacity) {
        assert capacity >= numOfSegments;
        LineSegment[] temp = new LineSegment[capacity];
        System.arraycopy(segmentsArray, 0, temp, 0, numOfSegments);
        segmentsArray = temp;
    }

    /**
     * the number of line segments
     *
     * @return number
     */
    public int numberOfSegments() {
        return numOfSegments;
    }

    /**
     * the line segments
     *
     * @return segment array
     */
    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[numOfSegments];
        System.arraycopy(segmentsArray, 0, lineSegments, 0, numOfSegments);
        return lineSegments;

    }

    private void validate(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("array is null");
        for (Point point : points) {
            if (point == null)
                throw new IllegalArgumentException("point is null");
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        Long startTime1 = System.currentTimeMillis();
        FastCollinearPoints collinear = new FastCollinearPoints(points);

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
        }
        StdOut.println(collinear.segments().length);
        Long endTime1 = System.currentTimeMillis();

        StdOut.println("*************");

        Long startTime2 = System.currentTimeMillis();
        test collinear1 = new test(points);

        for (LineSegment segment : collinear1.segments()) {
            StdOut.println(segment);
        }
        StdOut.println(collinear.segments().length);
        Long endTime2 = System.currentTimeMillis();

        StdOut.println("我的执行时间：" + (endTime1 - startTime1) + "ms");
        StdOut.println("他的执行时间：" + (endTime2 - startTime2) + "ms");
    }
}
