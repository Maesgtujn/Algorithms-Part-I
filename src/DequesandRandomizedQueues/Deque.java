package DequesandRandomizedQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;


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
        return (first == null || last == null);
    }

    /**
     * return the number of items on the deque
     * @return
     */
    public int size(){
        return n;
    }

    /**
     * add the item to the item from the front
     * @param item
     */
    public void addFirst(Item item){
        if (item == null)
            throw new IllegalArgumentException("Null argument");
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        first.next = oldfirst;
        if (isEmpty())
            last = first;
        else
            oldfirst.prev = first;
        n++;
        assert check();

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
        if (isEmpty())
            throw new NoSuchElementException("Deque is already null");
        Item item = first.item;
        first = first.next;
        if (isEmpty())
            last = null;
        else
            first.prev = null;
        n--;
        assert check();
        return item;
    }

    /**
     * remove and return the item from the end
     * @return
     */
    public Item removeLast(){
        if (isEmpty())
            throw new NoSuchElementException("Deque is already null");
        Item item = last.item;
        last = last.prev;
        if (isEmpty())
            first = null;
        else
            last.next = null;
        n--;
        assert check();
        return item;
    }

    /**
     * return an iterator over items in order from front to end
     * @return
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
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
        for (Node x = last; x !=null && numberOfNodes >= 0; x = x.prev){
            numberOfNodes--;
        }
        return numberOfNodes == 0;
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

