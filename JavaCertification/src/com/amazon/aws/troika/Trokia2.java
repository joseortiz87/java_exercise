package com.amazon.aws.troika;

import java.util.*;

public class Trokia2 {
	   private static Map<Character,Character> map = new HashMap<Character,Character>();
	   static{
	      map.put('(','(');
	      map.put('[','[');
	      map.put('{','{');
	      map.put(')','(');
	      map.put(']','[');
	      map.put('}','{');
	   } 
	    
	   public static void main(String []argh)
	   {
	      Scanner sc = new Scanner(System.in);
	      Stack<Character> stack = new Stack<Character>();
	      while (sc.hasNext()) {
	         String input=sc.next();
	         //Complete the code
	         stack.clear();
	         if(input.length() > 0){
	             for(int i=0;i<input.length();i++){
	                 char ch = input.charAt(i);
	                 if(map.containsKey(ch)){
	                     if(map.get(ch) == ch){
	                         stack.push(ch);
	                     }else{
	                         if(stack.empty()){
	                             stack.push(ch);
	                             break;
	                         }
	                         if(map.get(ch) != stack.pop()){
	                           break;   
	                         }
	                     }
	                 }
	             }
	         }
	         System.out.println(stack.empty());
	      }
	      sc.close();
	   }
}
