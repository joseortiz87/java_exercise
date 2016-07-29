package mx.com.joortizs.main;

import java.util.*;

public class IceCreamParlor {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        Integer testCases = scan.nextInt();
        for(int i = 0;i<testCases;i++){
            Integer m = scan.nextInt();
            Integer n = scan.nextInt();
            Integer[] items = new Integer[n];
            for(int j=0;j<n;j++){
                items[j] = scan.nextInt();
            }
            //SEARCH FOR PRICE
            Integer pivot = 0;
            while(pivot < n-1){
                if(items[pivot] < m){
	                for(int k=pivot+1;k<n;k++){
	                    if(items[k] < m && (items[pivot] + items[k]) == m  ){
	                        System.out.println((pivot+1) + " " + (k+1));
	                        pivot = n;
	                        break;
	                    }
	                }
                }
                pivot++; 
            }
        }
        scan.close();
    }
	
}
