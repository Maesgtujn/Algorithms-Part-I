package stackandqueue;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayQueueOfStrings {
    private String[] s;
    private int head = 0;
    private int tail = 0;

    public ResizingArrayQueueOfStrings(){
        s = new String[1];
    }

    public void enqueue(String item){
        if (tail == s.length){
            if (tail - head <= s.length / 2)
                resize(s.length);
            else
                resize(2 * s.length);
        }
        s[tail++] = item;
    }

    public String dequeue(){
        String item = s[head];
        s[head] = null;
        head = head + 1;
        if (tail > 0 && (tail - head) == s.length / 4)
            resize(s.length / 2);
        return item;
    }
    public boolean isEmpty(){
        return tail == head;
    }

    private void resize(int capacity){
        if (capacity == s.length){
            for (int i = head; i < tail; i++)
                s[i-head] = s[i];
        }
        else {
            String[] copy = new String[capacity];
            for (int i = head; i < tail; i++)
                copy[i - head] = s[i];
            s = copy;
        }
        tail = tail -head;
        head = 0;
    }

    public static void main(String[] args){

        ResizingArrayQueueOfStrings queue = new ResizingArrayQueueOfStrings();
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(queue.dequeue());
            else
                queue.enqueue(s);
        }
    }
}
