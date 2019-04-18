package com.fcb;

public class Practice12 {

    //Max Array Sum where subset has non-adjacent elements
    public static void main(String arg []){
        System.out.println(maxSubsetSum(new int []{3,7,4,6,5}));
        System.out.println(maxSubsetSum(new int []{2 ,1 ,5 ,8 ,4}));
        System.out.println(maxSubsetSum(new int []{3 ,5 ,-7 ,8 ,10}));
    }

    static int maxSubsetSum(int[] arr) {
        if (arr.length == 0) return 0;
        arr[0] = Math.max(0, arr[0]);
        if (arr.length == 1) return arr[0];
        arr[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < arr.length; i++)
            arr[i] = Math.max(arr[i-1], arr[i]+arr[i-2]);
        return arr[arr.length-1];
    }

}
