package stackandqueue;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.ListIterator;

public class StackOfStrings<Item> implements Iterable<Item>{

    private Node first = null;

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        public boolean hasNext(){
            return current != null;
        }
        public void remove(){

        }
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private class Node{
        Item item;
        Node next;
    }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;

    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;

    }

    public boolean isEmpty(){
        return first == null;
    }

    public static void main(String[] args){

        StackOfStrings<Object> stack = new StackOfStrings<>();
        while (!StdIn.isEmpty()){
            Object s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(stack.pop());
            else
                stack.push(s);
        }
    }
}
