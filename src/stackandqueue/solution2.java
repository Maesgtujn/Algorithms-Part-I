package stackandqueue;

import java.util.Scanner;

public class solution2 {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        while (s.hasNextInt()) {
            int n = s.nextInt();
            int[] ability = new int[n];
            for (int i = 0; i < n; i++) {
                ability[i] = s.nextInt();
            }
            int k = s.nextInt();
            int d = s.nextInt();


            long[][] maxProduct = new long[n][k];

            long[][] minProduct = new long[n][k];


            for (int i = 0; i < n; i++) {
                maxProduct[i][0] = ability[i];
                minProduct[i][0] = ability[i];
            }

            long max = Long.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < k; j++) {
                    for (int p = i - 1; p >= Math.max(i - d, 0); p--) {
                        maxProduct[i][j] = Math.max(maxProduct[i][j],
                                maxProduct[p][j - 1] * ability[i]);
                        maxProduct[i][j] = Math.max(maxProduct[i][j],
                                minProduct[p][j - 1] * ability[i]);
                        minProduct[i][j] = Math.min(minProduct[i][j],
                                minProduct[p][j - 1] * ability[i]);
                        minProduct[i][j] = Math.min(minProduct[i][j],
                                maxProduct[p][j - 1] * ability[i]);
                    }
                }
                max = Math.max(max, maxProduct[i][k - 1]);
            }

            System.out.println(max);

        }

    }
}

