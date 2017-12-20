package mx.com.joortizs.main;

import java.util.LinkedList;
import java.util.Scanner;

public class SuperReducedString {

	private static final String empty_string = "Empty String";
    static String super_reduced_string(String s){
        // Complete this function
    	if(s == null || s.length() < 2) {
    		return empty_string;
    	}
    	char prevChar = s.charAt(0);
    	LinkedList<Character> linkedList = new LinkedList<Character>();
    	linkedList.add(prevChar);
    	for(int i=1;i<s.length();i++) {
    		char currentChar = s.charAt(i);
    		if(prevChar == currentChar && !linkedList.isEmpty()) {
    			linkedList.removeLast();
    			prevChar = linkedList.isEmpty() ? '.' : linkedList.getLast();
    		}else {
    			linkedList.add(currentChar);
    			prevChar = currentChar;
    		}
    	}
    	if(linkedList.isEmpty()) {
    		return empty_string;
    	}
    	StringBuilder res = new StringBuilder();
    	for(Character tmp : linkedList) {
    		res.append(tmp);
    	}
    	return res.toString();
    }
    
    

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String result = super_reduced_string(s);
        System.out.println(result);
        in.close();
    }
}
