package Mergesort;


import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdIn;

public class Merge {

    private static final int CUTOFF = 7;

    private static Comparable[] aux;

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        for (int k= lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++){
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }

        assert isSorted(a, lo, hi);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if (hi <= lo + CUTOFF - 1){
            Insertion.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
//        System.out.printf("#low: %d, mid: %d, high: %d\n" , lo, mid, hi);
        sort(a, aux, lo, mid);
//        System.out.printf("##low: %d, mid: %d, high: %d\n" , lo, mid, hi);
        sort(a, aux, mid+1, hi);
//        System.out.printf("###low: %d, mid: %d, high: %d\n" , lo, mid, hi);
        if (!less(a[mid+1], a[mid]))
            return;
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        //TODO
        for (int k = lo + 1; k <= hi; k++)
            if (less(a[k], a[k - 1]))
                return false;
        return true;
    }

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args){
        String[] a = new String[4];
        for (int i = 0; i < 4; i++){
            a[i] = StdIn.readString();
        }
        sort(a);
        for (String x : a){
            System.out.print(x);
        }
    }
}
