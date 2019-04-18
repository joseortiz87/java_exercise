package com.fcb;

public class Practice6 {

    public static void main(String args[]){
        subset(new int []{1,2,3,4,5},new Integer [5],0);
    }

    public static void subset(int [] collection,Integer [] subset,int i){
        if( i ==  collection.length ){
            printSet(subset);
        }else{
            subset[i] = null;
            subset(collection,subset,i+1);
            subset[i] = collection[i];
            subset(collection,subset,i+1);
        }
    }

    public static void printSet(Integer [] subset){
        boolean isAllNull = true;
        System.out.println();
        for(Integer item : subset){
            if(item != null ){
                isAllNull = false;
                System.out.print(item + ",");
            }
        }
        if(isAllNull)
            System.out.print("-");
    }

}
