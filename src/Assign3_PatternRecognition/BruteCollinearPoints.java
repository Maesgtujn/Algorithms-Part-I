package Assign3_PatternRecognition;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {

    private int numOfSegments = 0;
    private LineSegment[] segmentsArray;

    /**
     * finds all line segments containing 4 points
     *
     * @param points points array from input
     */
    public BruteCollinearPoints(Point[] points) {
        validate(points);
        int numOfPoints = points.length;
        Point[] copyPoints = new Point[numOfPoints];
        System.arraycopy(points, 0, copyPoints, 0, numOfPoints);

        Arrays.sort(copyPoints);

        for (int i = 0; i < numOfPoints - 1; i++) {
            if (copyPoints[i].compareTo(copyPoints[i + 1]) == 0)
                throw new IllegalArgumentException("contain a repeated point");
        }

        segmentsArray = new LineSegment[4 * numOfPoints];

        for (int i = 0; i < numOfPoints - 3; i++) {
            for (int j = i + 1; j < numOfPoints - 2; j++) {
                for (int k = j + 1; k < numOfPoints - 1; k++) {
                    for (int t = k + 1; t < numOfPoints; t++) {

                        if (copyPoints[i].slopeTo(copyPoints[j]) == copyPoints[i].slopeTo(copyPoints[k]) &&
                                copyPoints[i].slopeTo(copyPoints[j]) == copyPoints[i].slopeTo(copyPoints[t])) {

                            addSegment(new LineSegment(copyPoints[i], copyPoints[t]));
                        }

                    }
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

    private void validate(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("array is null");
        for (Point point : points) {
            if (point == null)
                throw new IllegalArgumentException("point is null");
        }
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
     * @return segments array
     */
    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[numOfSegments];

        System.arraycopy(segmentsArray, 0, lineSegments, 0, numOfSegments);
        return lineSegments;

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

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);

        }

    }
}
