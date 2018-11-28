package test;

import edu.princeton.cs.algs4.StdOut;

public class ThreadDemo extends Thread {
    private Thread t;
    private String threadName;

    ThreadDemo(String name){
        threadName = name;
        StdOut.println("Creating " + threadName);
    }

    public void run(){
        StdOut.println("Running " + threadName);
        try{
            for (int i = 4; i > 0; i--){
                StdOut.println("Thread: " + threadName + ", " + i);
                Thread.sleep(50);
            }
        }catch (InterruptedException e){
            StdOut.println("Thread " + threadName + " exiting.");
        }
    }

    public void start(){
        StdOut.println("Starting " + threadName);
        if (t == null){
            t = new Thread(this, threadName);
            t.start();
        }
    }

    public static void main(String[] args){
        ThreadDemo T1 = new ThreadDemo("Thread-1");
        T1.start();

        ThreadDemo T2 = new ThreadDemo("Thread-2");
        T2.start();
    }
}
