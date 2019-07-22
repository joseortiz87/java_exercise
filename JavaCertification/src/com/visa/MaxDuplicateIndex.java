package com.visa;

/*
BINARY SEARCH
 */
public class MaxDuplicateIndex {
    public static void main(String args []){
        int[] arr = {1, 1, 1, 2, 2, 2, 5, 5, 6, 6};
        System.out.println(search(arr, 0, arr.length-1,1));
    }

    public static int search(int[] arr, int low, int high,int target){
        if(high >= low){
            int mid = (low + high - 1)/2;
            if(arr[mid] == target){
                int lastindex = mid;
                mid++;
                while(mid < arr.length && arr[mid] == target){
                    lastindex = mid;
                    mid++;
                }
                return lastindex;
            }else if(arr[mid] > target){
                return search(arr,low,mid-1,target);
            }else{
                return search(arr,mid+1,high,target);
            }
        }
        return -1;
    }
}
