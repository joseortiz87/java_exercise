package com.fcb;

public class Practice10 {

    public static void main(String[] args) {
        permute("ABC",0,2);
    }

    public static void permute(String str, int startIndex, int endIndex){
        if(startIndex == endIndex) {
            System.out.println(str);
        }else {
            for (int i = startIndex; i <= endIndex; i++) {
                str = swap(str,startIndex,i);
                permute(str, startIndex+1, endIndex);
                str = swap(str,startIndex,i);
            }
        }
    }

    public static String swap(String str,int indexOne,int indexTwo){
        char[] charArray = str.toCharArray();
        char temp = charArray[indexOne];
        charArray[indexOne] = charArray[indexTwo];
        charArray[indexTwo] = temp;
        return String.valueOf(charArray);
    }

    public static void printPermutation(String permutation){
        System.out.println(permutation);
    }

}
