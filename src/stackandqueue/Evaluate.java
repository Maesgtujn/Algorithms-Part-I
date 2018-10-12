package stackandqueue;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Evaluate {
    public static void main(String[] args){

        ResizingArrayStackOfStrings<String> ops = new ResizingArrayStackOfStrings<>();
        ResizingArrayStackOfStrings<Double> vals = new ResizingArrayStackOfStrings<>();

        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (s.equals("("));
            else if (s.equals("+"))
                ops.push(s);
            else if (s.equals("*"))
                ops.push(s);
            else if (s.equals(")")){
                String op = ops.pop();
                if (op.equals("+"))
                    vals.push(vals.pop() + vals.pop());
                else if (op.equals("*"))
                    vals.push(vals.pop() * vals.pop());
            }
            else vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
    }
}
