package com.hackerrank.kit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TripleSum {
    // Complete the triplets function below.
    static long triplets(int[] a, int[] b, int[] c) {
        a = Arrays.stream(a).sorted().distinct().toArray(); //O(n*log(n))
        b = Arrays.stream(b).sorted().distinct().toArray(); //O(n*log(n))
        c = Arrays.stream(c).sorted().distinct().toArray(); //O(n*log(n))
        long globalTripletsCount = 0l;
        int indexA = 0;
        int indexC = 0;
        for(int indexB=0;indexB<b.length;indexB++){
            int pivotB = b[indexB];
            while(indexA < a.length && a[indexA] <= pivotB){
                indexA++;
            }
            while(indexC < c.length && c[indexC] <= pivotB){
                indexC++;
            }
            globalTripletsCount += (long)indexA * (long)indexC;
        }
        System.out.println(globalTripletsCount); //9593177511025
        return globalTripletsCount;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] lenaLenbLenc = scanner.nextLine().split(" ");

        int lena = Integer.parseInt(lenaLenbLenc[0]);

        int lenb = Integer.parseInt(lenaLenbLenc[1]);

        int lenc = Integer.parseInt(lenaLenbLenc[2]);

        int[] arra = new int[lena];

        String[] arraItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lena; i++) {
            int arraItem = Integer.parseInt(arraItems[i]);
            arra[i] = arraItem;
        }

        int[] arrb = new int[lenb];

        String[] arrbItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenb; i++) {
            int arrbItem = Integer.parseInt(arrbItems[i]);
            arrb[i] = arrbItem;
        }

        int[] arrc = new int[lenc];

        String[] arrcItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenc; i++) {
            int arrcItem = Integer.parseInt(arrcItems[i]);
            arrc[i] = arrcItem;
        }

        long ans = triplets(arra, arrb, arrc);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
