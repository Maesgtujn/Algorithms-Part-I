package ElementarySorts;

import static ElementarySorts.Selection.exch;
import static ElementarySorts.Selection.less;

public class Insertion {
    public static void sort(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++)
            for (int j = i; j > 0; j--)
                if (less(a[j], a[j-1]))
                    exch(a, j, j-1);
                else break;
    }
}
