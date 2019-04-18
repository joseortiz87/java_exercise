package com.fcb;

public class Practice11 {

    public static final int MAX_CHAR = 256;

    public static void main(String args []){
        search("AAABABAA","AABA");
        search("AAABABAA","AAABABAA");
        search("ABCGAJKAKJHDKJHWERdUEWRULEWGRBDF","JK");
    }

    // in general O(n)
    public static void search(String txt,String pat){

        System.out.println("Search for " + pat + " in " + txt );

        int patSize = pat.length();
        int txtSize = txt.length();

        char [] patCount = new char[MAX_CHAR];
        char [] windowTextCount = new char[MAX_CHAR];

        //Count chars at pat
        // count chars at text for the first window
        // O(P)
        for(int i=0;i<patSize;i++){
            patCount[pat.charAt(i)]++;
            windowTextCount[txt.charAt(i)]++;
        }

        // Move window in the text and compare if match
        // O(T-P) * ( O(1) + O(1) + O(256) ) ~ O(T-P) * O(258) ~ O(T*256)
        for(int i=patSize;i<txtSize;i++){

            // O(256)
            if(compare(patCount,windowTextCount))
                System.out.println("Match index " + (i-patSize));

            //Add next element window
            // O(1)
            windowTextCount[txt.charAt(i)]++;

            //Substract first element previous window
            // O(1)
            windowTextCount[txt.charAt(i-patSize)]--;
        }

        if(compare(patCount,windowTextCount))
            System.out.println("Match index " + (txtSize-patSize));

    }

    public static boolean compare(char [] a,char [] b){
        // O(256) in case all are equal
        for(int i=0;i<MAX_CHAR;i++){
            if(a[i] != b[i]){
                return false;
            }
        }
        return true;
    }

}
