package com.hackerrank.kit;

import java.io.*;
import java.util.*;

public class SubPalindrom {
    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
        long res = 0l;
        if(null == s || s.length() == 0){
            return res;
        }
        for(int i=0;i<s.length();i++){
            Stack<Character> palindromStack = new Stack<>();
            palindromStack.push(s.charAt(i));
            res++;
            boolean isCharChange = false;
            //aaaaa
            for(int j=i+1;j<s.length();j++){
                if(!isCharChange && s.charAt(j) != palindromStack.peek()){
                    isCharChange = true;
                }else if(!isCharChange && s.charAt(j) == palindromStack.peek()){
                    res++;
                }else{
                    if(s.charAt(j) != palindromStack.pop()){
                        break;
                    }
                    if(palindromStack.isEmpty()){
                        res++;
                        break;
                    }
                }
            }
        }
        return res;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("subpalindrom_output.txt"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
