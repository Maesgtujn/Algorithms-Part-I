package DequesandRandomizedQueues;

import java.util.Iterator;

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

    private boolean check() {
    }

    /**
     * is the deque empty?
     * @return
     */
    public boolean isEmpty(){
        return false;
    }

    /**
     * return the number of items on the deque
     * @return
     */
    public int size(){
        return 0;
    }

    /**
     * add the item to the item from the front
     * @param item
     */
    public void addFirst(Item item){}

    /**
     * add the item to the end
     * @param item
     */
    public void addLast(Item item){}

    /**
     * remove and return the item from the front
     * @return
     */
    public Item removeFirst(){
        return null;
    }

    /**
     * remove and return the item from the end
     * @return
     */
    public Item removeLast(){
        return null;
    }

    /**
     * return an iterator over items in order from front to end
     * @return
     */
    public Iterator<Item> iterator(){
        return null;
    }

    //  unit testing
    public static void main(String[] args){


    }


}
