package DequesandRandomizedQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;   //  array of items
    private int n;      //  number of elements on randomizedQueue
    /**
     * construct an empty randomized queue
     */
    public RandomizedQueue(){
        a = (Item[]) new Object[2];
        n = 0;
    }

    /**
     * is the randomized queue empty?
     * @return
     */
    public boolean isEmpty(){
        return n == 0;
    }

    /**
     * return the number of items on the randomized queue
     * @return
     */
    public int size(){
        return n;
    }

    /**
     * resize the underlying array holding the elements
     * @param capacity
     */
    private void resize(int capacity){
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        if (n >= 0) System.arraycopy(a, 0, temp, 0, n);
        a = temp;

    }

    /**
     * add the item
     * @param item
     */
    public void enqueue(Item item){
        if(item == null)
            throw new IllegalArgumentException("Null argument");
        if (n == a.length)
            resize(2*a.length);
        a[n++] = item;

    }

    /**
     * remove and return a random item
     * @return
     */
    public Item dequeue(){
        if (isEmpty())
            throw new NoSuchElementException("randomizedQueue underflow");
        int rand = StdRandom.uniform(n);
        Item item = a[rand];
        a[rand] = a[n-1];
        a[n-1] = null;
        n--;
        if (n > 0 && n == a.length/4)
            resize(a.length/2);
        return item;
    }

    /**
     * return a random item (but do not remove it)
     * @return
     */
    public Item sample(){
        if (isEmpty())
            throw new NoSuchElementException("randomizedQueue underflow");
        int rand = StdRandom.uniform(n);
        return a[rand];
    }

    /**
     * return an independent iterator over items in random order
     * @return
     */
    public Iterator<Item> iterator(){
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int index;
        private final Item[] shuffledArray;

        public ArrayIterator(){
            index = 0;
            shuffledArray = (Item[]) new Object[n];
            if (n >= 0) System.arraycopy(a, 0, shuffledArray, 0, n);
            StdRandom.shuffle(shuffledArray, 0, n);
        }

        public boolean hasNext(){
            return index < n;
        }

        public Item next(){
            if (!hasNext())
                throw new NoSuchElementException();
            return shuffledArray[index++];
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args){
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while(!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else if (!queue.isEmpty()) {
                StdOut.println(queue.dequeue() + " ");
                for (String s : queue) {
                    StdOut.println("外层循环:      " + s);
                    for (String x : queue)
                        StdOut.println("内层循环： " + x);
                }
            }
        }
    }



}
