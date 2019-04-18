package com.fcb;

import java.util.*;

public class Practice8 {

    public static void main(String args []){
        System.out.println(sumPairsCount(new int[]{10, 12, 10, 15, -1, 7, 6,
                5, 4, 2, 1, 1, 1},11));
    }

    public static int sumPairsCount(int [] array,int sum){
        if(array == null || array.length == 0)
            return 0;

        int twice_count = 0;
        Map<Integer,Integer> itemsMap = new HashMap<>();
        // O(n)
        for(int item : array){
            if(!itemsMap.containsKey(item))
                itemsMap.put(item,0);

            itemsMap.put(item, itemsMap.get(item)+1);
        }
        // O(n)
        for (Integer key : array) {
            if(itemsMap.get(sum-key) != null)
                twice_count += itemsMap.get(sum-key);

            if (sum-key == key)
                twice_count--;
        }

        return twice_count/2;
    }

}
