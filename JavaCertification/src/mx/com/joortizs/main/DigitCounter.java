package mx.com.joortizs.main;

import java.util.HashMap;

/**
 * 0,1,2,3,4,5,6,7,8,9

int input = 1024 //10234567890
input*m = 2048
input*3 = 3072
.
.
.
input * x = z
System.out.println(z)
 * @author yh07021
 *
 */
public class DigitCounter {

	
	public static void main(String args []) {
		int value = 1024;
		System.out.println(value);
		System.out.println(getZValue(value));
	}
	
	public static int getZValue(int input){
	    
	    int m = 1;
	    int z = input;
	    HashMap<Integer,Boolean> integerMap = new HashMap<>();
	    int counter = 0;

	    while(counter < 10){
	       z = z * m;
	       
	       //Digits
	       int temp = z;
	       while(temp > 0){
	          int digit = z%10;
	          if(!integerMap.containsValue(digit)){
	        	  counter++;
	        	  if(counter == 10){
	                  break;
	              }
	              integerMap.put(digit,true); //
	          }
	          temp = temp /10;
	       }
	       
	       m++;
	    }
	    
	    return z;
	}
	
}
