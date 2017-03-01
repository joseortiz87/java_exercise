package com.fcb;

public class Practice3 {

	public static void main(String args[]){
		int [] arr1 = {3,7,8,5,2,1,9,5,4};
		int [] arr2 = {1,12,5,26,7,14,3,7,2};
		quickSort(arr1,0,arr1.length-1);
		quickSort(arr2,0,arr2.length-1);
		System.out.println("Array1: ");
		for(int num : arr1){
			System.out.print(num + " ");
		}
		System.out.println("\nArray2: ");
		for(int num : arr2){
			System.out.print(num + " ");
		}
	}
	
	public static void quickSort(int [] numbers,int left,int right){
		int index = partition(numbers, left, right);
	    if (left < index - 1)
	    	quickSort(numbers, left, index - 1);
	    if (index < right)
	    	quickSort(numbers, index, right);
	}
	
	public static int partition(int [] numbers,int left,int right){
		int i = left, j = right;
	    int tmp;
	    int pivot = numbers[(left + right) / 2];
	    while (i <= j) {
            while (numbers[i] < pivot)
                  i++;
            while (numbers[j] > pivot)
                  j--;
            if (i <= j) {
                  tmp = numbers[i];
                  numbers[i] = numbers[j];
                  numbers[j] = tmp;
                  i++;
                  j--;
            }
	    }
	    return i;
	}
	
}
