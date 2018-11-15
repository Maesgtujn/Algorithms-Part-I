package Week4_PriorityQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Heap {

    public static void sort(Comparable[] pq) {
        int N = pq.length;
        for (int k = N / 2; k >= 1; k--) {
            sink(pq, k, N);
        }
        while (N > 1) {
            exch(pq, 1, N);
            sink(pq, 1, --N);
        }
    }

    private static void sink(Comparable[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq, j, j + 1))
                j++;
            if (!less(pq, k, j))
                break;
            exch(pq, k, j);
            k = j;
        }
    }

    /* convert from 1-based indexing to 0-base indexing  */
    private static boolean less(Comparable[] pq, int k, int j) {
        return pq[k - 1].compareTo(pq[j - 1]) < 0;
    }

    private static void exch(Comparable[] pq, int i, int n) {
        Comparable swap = pq[i - 1];
        pq[i - 1] = pq[n - 1];
        pq[n - 1] = swap;
    }

    public static void main(String[] args) {
        Comparable[] a = new Comparable[10];
        int i = 0;
        while (i <10){
            a[i] = StdIn.readInt();
            i++;
        }
        sort(a);
        for(Comparable j: a)
            StdOut.println(j);
    }
}


