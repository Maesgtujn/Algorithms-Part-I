package DequesandRandomizedQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class Deque<Item> implements Iterable<Item> {
    private int n;          //  size of the deque
    private Node first;     //  top of the deque
    private Node last;      //  bottom of the deque
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
    /**
     * construct an empty deque
     */
    public Deque(){
        first = null;
        last = null;
        n = 0;
        assert check();
    }

    /**
     * is the deque empty?
     * @return
     */
    public boolean isEmpty(){
        //TODO
        return (first == null || last == null);
    }

    /**
     * return the number of items on the deque
     * @return
     */
    public int size(){
        //TODO
        return n;
    }

    /**
     * add the item to the item from the front
     * @param item
     */
    public void addFirst(Item item){
        //TODO

    }

    /**
     * add the item to the end
     * @param item
     */
    public void addLast(Item item){
        if(item == null)
            throw new IllegalArgumentException("Null argument");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()){
            last.prev = null;
            first = last;}
        else{
            last.prev = oldlast;
            oldlast.next = last;}
        n++;
        assert check();
    }

    /**
     * remove and return the item from the front
     * @return
     */
    public Item removeFirst(){
        //TODO
        if (isEmpty())
            throw new NoSuchElementException("Deque is already null");
        Item item = first.item;
        first = first.next;
        if (isEmpty())
            last = null;
        else
            first.prev = null;
        assert check();
        return item;
    }

    /**
     * remove and return the item from the end
     * @return
     */
    public Item removeLast(){
        //TODO
        return null;
    }

    /**
     * return an iterator over items in order from front to end
     * @return
     */
    public Iterator<Item> iterator(){
        //TODO
        return null;
    }

    private boolean check() {
        if (n < 0){
            return false;
        }
        if (n == 0){
            if (first != null || last != null)
                return false;
        }
        else if (n == 1){
            if (first == null || last == null)
                return false;
            if (first.next != null || last.next != null)
                return false;
            if (first.prev != null || last.prev != null)
                return false;
        }

        //  check internal consistency of instance variable n
        int numberOfNodes = 0;
        for (Node x = first; x !=null && numberOfNodes <= n; x = x.next){
            numberOfNodes++;
        }
        if (numberOfNodes != n )
            return false;
        for (Node x = last; x !=null && numberOfNodes >= n; x = x.prev){
            numberOfNodes--;
        }
        if (numberOfNodes != 0 )
            return false;
        return true;
    }

    //  unit testing
    public static void main(String[] args){
        Deque<String> deque = new Deque<>();
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!item.equals("-"))
                deque.addLast(item);
            else if (!deque.isEmpty())
                StdOut.print(deque.removeFirst() + " ");
        }
        StdOut.println("(" + deque.size() + " left on deque)");


    }
}

