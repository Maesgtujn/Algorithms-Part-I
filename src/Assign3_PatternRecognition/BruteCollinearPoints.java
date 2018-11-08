package Assign3_PatternRecognition;


import java.util.Arrays;

public class BruteCollinearPoints {

    /**
     * finds all line segments containing 4 points
     * @param points
     */
    private int num = 0;
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points){
        validate(points);
        int N = points.length;
        Arrays.sort(points);
        LineSegment lineSegment;
        for (int i = 0; i < N - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException("contain a repeated point");
        }

        for (int i = 0; i < N - 3; i++) {
            for (int j = i + 1; j < N - 2; j++) {
                for (int k = j + 1; k < N - 1; k++) {
                    for (int t = k + 1; t < N; i++) {

                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) &&
                                points[i].slopeTo(points[j]) == points[i].slopeTo(points[t])) {
                            num++;
                            lineSegment = new LineSegment(points[i], points[t]);
                            segments[num] = lineSegment;

                        }

                    }
                }
            }
        }
    }

    private void validate(Point[] points){
        if (points == null)
            throw new IllegalArgumentException("array is null");
        for (int i = 0; i < points.length; i++){
            if (points[i] == null)
                throw new IllegalArgumentException("point is null");
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
        return segments;
    }
}
