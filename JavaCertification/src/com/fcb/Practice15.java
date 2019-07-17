package com.fcb;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Practice15 {

    static int stepPerms(int n) {
        Map<Integer,Integer> memoization = new HashMap<>();
        return stepPermsHelper(n,0,memoization);
    }

    static int stepPermsHelper(int n,int step,Map<Integer,Integer> memoization){
        if(step > n)
            return 0;
        if(step == n)
            return 1;
        int tmpStep = step+1;
        int step1 = memoization.containsKey(tmpStep) ? memoization.get(tmpStep) : stepPermsHelper(n,tmpStep,memoization);
        tmpStep++;
        int step2 = memoization.containsKey(tmpStep) ? memoization.get(tmpStep) : stepPermsHelper(n,tmpStep,memoization);
        tmpStep++;
        int step3 = memoization.containsKey(tmpStep) ? memoization.get(tmpStep) : stepPermsHelper(n,tmpStep,memoization);
        return step1 + step2 + step3;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("outputPractice15.txt"));

        int s = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int sItr = 0; sItr < s; sItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int res = stepPerms(n);

            bufferedWriter.write(String.valueOf(res));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
