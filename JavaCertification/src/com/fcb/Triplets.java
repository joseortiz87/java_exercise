package com.fcb;

import java.util.*;

public class Triplets {
    public static void main(String args[]){
        int[] image = new int[]{
                -1,0,-1,1,2,-4
        };
        System.out.println(threeSum(image)); //[[-1, -1, 2], [-1, 0, 1]]
    }
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        if(nums.length < 3) return triplets;
        Arrays.sort(nums);
        Map<String,Boolean> visitedTriplets = new HashMap<>();
        for (int i = 0; i < nums.length - 2; i++) {

            // To find the other two elements, start two index variables
            // from two corners of the array and move them toward each
            // other
            int l = i + 1; // index of the first element in the remaining elements
            int r = nums.length - 1; // index of the last element
            while (l < r) {
                if (nums[i] + nums[l] + nums[r] == 0) {
                    String key = nums[i] + "_" + nums[l] + "_" + nums[r];
                    if(!visitedTriplets.containsKey(key)){
                        triplets.add(Arrays.asList(nums[i],nums[l],nums[r]));
                        visitedTriplets.put(key,true);
                    }
                    l++;
                }
                else if (nums[i] + nums[l] + nums[r] < 0)
                    l++;
                else
                    r--;
            }
        }
        return triplets;
    }
}
