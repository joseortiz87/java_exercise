package mx.com.joortizs.main;

import java.util.*;

public class ManasaStones {

	/*
	 * INPUT
	  	2
		3 
		1
		2
		4
		10
		100
		
	 *	OUTPUT
		2 3 4
		30 120 210 300
		
	 * */
	
	  public static void main(String[] args) {
	        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
	        Scanner in = new Scanner(System.in);
	        int testCases = in.nextInt();
	        for(int i=0;i<testCases;i++){
	            int n = in.nextInt();
	            int a = in.nextInt();
	            int b = in.nextInt();
	            
	            if(a > b){
	            	int aTemp = a;
	            	a = b;
	            	b = aTemp;
	            }
	            
	            int aTimes = n-1;
	            int bTimes = 0;
	            TreeSet<Integer> set = new TreeSet<Integer>();
	            StringBuilder strSum = new StringBuilder();
	            while(aTimes >= 0 && bTimes <= n-1){
	                int sum = 0;
	                sum += a*aTimes;
	                sum += b*bTimes;
	                set.add(sum);
	                
	                aTimes--;
	                bTimes++;
	            }
	            
	            for(Integer finalNum : set){
	            	strSum.append(" ");
                    strSum.append(finalNum);
	            }
	            System.out.println(strSum.toString().trim());
	        }
	        in.close();
	    }
	
}
