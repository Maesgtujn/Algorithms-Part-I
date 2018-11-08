package ElementarySorts;

import java.util.Comparator;

public class Point2D {
    public final Comparator<Point2D> POLAR_ORDER = new PolarOrder();
    private final double x, y;


    public Point2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * ab to ac whether clockwise(-1) or counter-clockwise(+1)
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static int ccw(Point2D a, Point2D b, Point2D c){
        double area2 = (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
        if (area2 < 0)
            return -1;  //  clockwise
        else if (area2 > 0)
            return +1;  //  counter-clockwise
        else
            return 0;   //  collinear
    }
    private class PolarOrder implements Comparator<Point2D>{

        @Override
        /**
         * q1 makes larger polar angle returns +1
         */
        public int compare(Point2D q1, Point2D q2) {
            double dy1 = q1.y - y;
            double dy2 = q2.y - y;

            if (dy1 == 0 && dy2 == 0)       //  p, q1, q2 horizontal
                return 0;
            else if (dy1 >= 0 && dy2 < 0)   //  q1 above p; q2 below p
                return -1;
            else if (dy2 >= 0 && dy1 < 0)   //  q1 below p; q2 above p
                return +1;
            else return -ccw(Point2D.this, q1, q2);
            /* to access invoking point from within inner class 从内部类中访问调用点 */
        }
    }
}
