package test;

import java.util.Enumeration;
import java.util.Vector;

public class VectorDemo {

    public static void main(String[] args){
        Vector v = new Vector(3, 2);
        System.out.println("Initial size: " + v.size());
        System.out.println("Initial capacity: " + v.capacity());
        v.addElement(Integer.valueOf(1));
        v.addElement(2);
        v.addElement(3);
        v.addElement(4);
        System.out.println("Capacity after four additions: " + v.capacity());
        v.addElement(5.45);
        System.out.println("Current capacity: " + v.capacity());
        System.out.println(v);

        Enumeration enumeration = v.elements();
        while (enumeration.hasMoreElements())
            System.out.print(enumeration.nextElement() + " ");
        System.out.println();
    }
}
