package mx.com.joortizs.main;

import java.util.Scanner;

public class CamelCase {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int wordCounter = 0;
        if(s != null && s.length() > 0) {
        	wordCounter++;
        	for(char letter : s.toCharArray()) {
            	if(letter >= 65 && letter <= 90) {
            		wordCounter++;
            	}
            }
        }
        System.out.println(wordCounter);
        in.close();
    }
}
