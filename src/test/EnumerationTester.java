package test;

import java.util.Enumeration;
import java.util.Vector;

public class EnumerationTester {
    public static void main(String[] args){
        Enumeration<String> days;
        Vector<String> dayNames = new Vector<String>();
        dayNames.add("Sunday");
        dayNames.add("Monday");
        days = dayNames.elements();
        while (days.hasMoreElements()){
            System.out.println(days.nextElement());
        }
    }
}
