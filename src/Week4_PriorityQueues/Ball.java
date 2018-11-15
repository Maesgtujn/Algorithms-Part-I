package Week4_PriorityQueues;

import edu.princeton.cs.algs4.StdDraw;

public class Ball {

    private double rx, ry;          //  position
    private double vx, vy;          //  velocity
    private final double radius = 0.5;    //  radius(半径)

    public Ball(){

    }

    public void move(double dt){
        if ((rx + vx*dt < radius) || (rx + vx*dt > 1.0 -radius)){vx = -vx;}
        if ((rx + vy*dt < radius) || (ry + vy*dt > 1.0 -radius)){vy = -vy;}
        rx = rx + vx*dt;
        ry = ry + vy*dt;
    }

    public void draw(){
        StdDraw.filledCircle(rx, ry, radius);
    }
}
