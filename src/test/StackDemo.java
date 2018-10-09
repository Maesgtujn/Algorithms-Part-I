package test;

import edu.princeton.cs.algs4.Stack;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class StackDemo {

    static void showpush(Stack<Integer> stack, int a){
        stack.push(a);
        System.out.println();
    }

    public static void main(String args[]){
        Stack<Integer> stack = new Stack<>();
        System.out.println("stack: " + stack);
        stack.push(42);https://www.runoob.com/css/css-tutorial.html
        stack.push(66);
        stack.push(99);
        stack.pop();
        System.out.println(stack);
        stack.pop();
        stack.pop();
        try {
            stack.pop();
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
    }
}
