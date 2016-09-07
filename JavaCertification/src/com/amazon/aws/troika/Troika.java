package com.amazon.aws.troika;

import java.util.*;

public class Troika {

		public static void main(String args[]){
			String s = "{if( x < 123456_juak > 20 ){ joseOrtiz = 'new string'; }}";
			System.out.println(isBalanced(s));
		}
		
		public static boolean isBalanced(String s){
			Stack<Character> stack  = new Stack<Character>();
			char tempChar;
			for(int i=0;i<s.length();i++){
				tempChar = s.charAt(i);
				if('[' == tempChar || '(' == tempChar || 
						'{' == tempChar || '<' == tempChar){
					stack.push(new Character(tempChar));
				}else{
					
					switch(tempChar){
						case ']': 
								if(stack.isEmpty()) return false;
		                		if(stack.pop() != '[') return false;
								break;
						case ')': 
								if(stack.isEmpty()) return false;
              		  			if(stack.pop() != '(') return false;
              		  			break;	
						case '}': 
							if(stack.isEmpty()) return false;
          		  			if(stack.pop() != '{') return false;
          		  			break;	
						case '>': 
							if(stack.isEmpty()) return false;
          		  			if(stack.pop() != '<') return false;
          		  			break;
					}
				}
			}
			return stack.isEmpty();
		}
}
