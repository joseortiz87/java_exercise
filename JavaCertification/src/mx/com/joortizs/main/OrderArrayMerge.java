package mx.com.joortizs.main;

import java.util.stream.IntStream;

public class OrderArrayMerge {
	public static void main(String args[]){
		int [] a = {1,3,4,7,9,12,21};
		int [] b = {2,4,5,6,8};
		int [] a3 = mergeSortedArrays(a, b);
		IntStream.of(a3)
		.forEach(x -> System.out.print(x + ","));
	}
	
	public static int[] mergeSortedArrays(int[] a,int[] b){
		int i = 0;
		int j = 0;
		int k = 0;

		if(a == null || b == null){
			return new int [0];
		}
		
		int [] a3 = new int[a.length + b.length];
		
		//1. MERGE
		while( i < a.length && j < b.length ){
			if(a[i] < b[j]){
				a3[k++] = a[i++];
			}else{
				a3[k++] = b[j++];
			}
		}
		
		//2. PUT MISSING ELEMENTS FROM A
		while(i < a.length){
			a3[k++] = a[i++];
		}
		
		//3. PUT MISSING ELEMENTS FROM B
		while(j < b.length){
			a3[k++] = b[j++];
		}
		
		return a3;
	}
}
