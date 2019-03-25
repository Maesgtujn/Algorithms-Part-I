package Week6_HashTables;

import edu.princeton.cs.algs4.In;

public class SeparateChainingHashST<Key, Value> {
    private int M = 97;               //  number of chains
    private Node[] st = new Node[M];  //  array of chains

    private static class Node{
        private Object key;
        private Object val;
        private Node next;

        private Node(Object key, Object val, Node old){
            this.key = key;
            this.val = val;
            this.next = old;
        }
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key){
        int i = hash(key);
        for (Node x = st[i]; x !=null; x = x.next)
            if (key.equals(x.key) ) return (Value) x.val;
        return null;
    }

    public void put(Key key, Value val){
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next)
            if (key.equals(x.key)){
                x.val = val;
                return;
            }
        st[i] = new Node(key, val, st[i]);
    }

    public static void main(String[] args){
        SeparateChainingHashST<Integer, String> separateChainingHashST =
                new SeparateChainingHashST<>();
        separateChainingHashST.put(10, "apple");
        separateChainingHashST.put(107, "banana");
        separateChainingHashST.put(22, "orange");
    }
}
