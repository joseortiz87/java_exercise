package com.fcb;

import java.util.HashSet;
import java.util.Set;

public class Practice13 {

    public static void main(String arg []){
        findMaxPair(24,new int[]{-1,3,8,2,9,5},new int[]{4,1,2,10,5,20});
    }

    public static void findMaxPair(int sum,int [] a,int [] b){
        if( a == null || b == null)
            return;

        //O(N)
        Set<Integer> setA = new HashSet<>();
        for(int i=0;i<a.length;i++){
            setA.add(a[i]);
        }
        int minDiff = 0;
        int minNumber = 0;
        int tmpB = 0;
        boolean initialize = true;
        //O(N) * O(SUM-N)
        for(int j=0;j<b.length;j++){
            int diff = sum-b[j];
            if(setA.contains(diff)){
                printPair(diff,b[j]);
            }else{
                for(int k=1;k<diff;k++){
                    if((initialize && setA.contains(diff+k)) || (setA.contains(diff+k) && k < minDiff)){
                        minDiff = k;
                        minNumber = diff+k;
                        tmpB = b[j];
                        initialize = false;
                    }
                    if((initialize && setA.contains(diff-k)) || (setA.contains(diff-k) && k < minDiff)){
                        minDiff = k;
                        minNumber = diff-k;
                        tmpB = b[j];
                        initialize = false;
                    }
                    if(!initialize && k >= minDiff) break;
                }
            }
        }
        if(minNumber > 0 && tmpB > 0) printPair(minNumber,tmpB);
    }

    public static void matrixSolution(int sum,int [] a,int [] b){

    }

    public static void printPair(int a,int b){
        System.out.println(a + "," + b);
    }
}
