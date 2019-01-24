package com.uber;

import java.util.*;

/**
 * 
 * @author yh07021
 *
 *
input
5
2
1
2
1
3
1

output
6
 */

public class Runner {
	  public static void main(String args[] ) throws Exception {
	        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
	        Scanner scanner = new Scanner(System.in);
	        int size = scanner.nextInt();
	        List<Integer> list = new ArrayList<>();
	        Map<Integer,Boolean> mapOdd = new HashMap<>(); 
	        Map<String,Integer> mapOddSubArrays = new HashMap<>();
	        int maxOdd = 0;
	        for(int i=0;i<size;i++){
	            int element = scanner.nextInt();
	            mapOdd.put(i,element%2 != 0);
	            list.add(element);
	        }
	        maxOdd = scanner.nextInt();
	        
	        int i = 0;
	        int j = 0;
	        int oddCount = 0;
	        StringBuilder tmpArrayStr = new StringBuilder();
	        for(i=0;i<size;i++){
	        	tmpArrayStr.append(list.get(i));
	        	
	        	if(mapOdd.get(i)) {
	        		oddCount++;
	            }
	        	
	        	if(tmpArrayStr.length() < size && oddCount <= maxOdd) {
	        		mapOddSubArrays.put(tmpArrayStr.toString(), oddCount);
                }else {
	            	i = size -1;
	            }
	        	
	            if(i == size -1) {
	            	j++;
	            	i = j-1;
	            	oddCount = 0;
	            	tmpArrayStr = new StringBuilder();
	            }
	        }
	        
	        System.out.println(mapOddSubArrays.size());
	        scanner.close();   
	    }
}
