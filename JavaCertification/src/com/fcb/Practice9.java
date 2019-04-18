package com.fcb;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Practice9 {

    public static final String YES = "YES";
    public static final String NO = "NO";

    // Complete the isBalanced function below.
    static String isBalanced(String s) {
        if(s == null || s.length() == 0){
            return NO;
        }
        Deque<Character> stack = new ArrayDeque<Character>();
        for(char character : s.toCharArray()){
            if(character == '(' || character == '[' || character == '{'){
                //put open bracket in stack
                stack.push(character);
            }else if(character == ')' || character == ']' || character == '}'){
                //find closing but there no open bracket in stack
                if(stack.size() == 0){
                    return NO;
                }
                //last open bracket in stack has to match closing bracket
                Character lastBracket = stack.pop();
                if( (lastBracket == '(' && character != ')') ||
                        (lastBracket == '[' && character != ']') ||
                        (lastBracket == '{' && character != '}') ){
                    return NO;
                }
            }
        }
        return stack.isEmpty() ? YES : NO;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String s = scanner.nextLine();

            String result = isBalanced(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
