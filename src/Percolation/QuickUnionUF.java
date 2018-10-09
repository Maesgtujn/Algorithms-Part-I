package Percolation;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF {
    private int[] id;
    private int[] sz;

    public QuickUnionUF(int N){
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
            sz[i] = 1;
        }

    }

    private int root(int i){
        int x = 0;
        while (i != id[i]){
            i = id[i];
            x++;}
        sz[i] += x;
        return i;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        if(sz[i] >= sz[j]){
            id[j] = i;
            sz[i] += sz[j];
        }
        else{
            id[i] = j;
            sz[j] = i;
        }
        StdOut.println("size of p: " + sz[i]);
        StdOut.println("size of q: " + sz[j]);
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickUnionUF uf = new QuickUnionUF(N);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)){
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }

    }
}
