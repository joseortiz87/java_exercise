package com.fcb;

public class EditDistance {

    /*
    Inserting one character anywhere in the word (including at the beginning and end)
    Removing one character
    Replacing one character
     */

    public static void main(String args []){
        System.out.println(OneEditApart("cat","dog"));
        System.out.println(OneEditApart("cat","cast"));
        System.out.println(OneEditApart("cat","cats"));
        System.out.println(OneEditApart("cat","act"));
    }

    public static boolean OneEditApart(String s1, String s2){
        if(s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0){
            return false;
        }

        int s1Index = 0;
        int s2Index = 0;
        int falseMatch = 0;
        while( s1Index < s1.length() && s2Index < s2.length() && falseMatch <= 1 ){
            if(s1.charAt(s1Index) != s2.charAt(s2Index)){
                falseMatch ++;
                if(s1.length() > s2.length()){
                    s1Index ++;
                }else if(s2.length() > s1.length()){
                    s2Index ++;
                }else{
                    s1Index ++;
                    s2Index ++;
                }
            }else{
                s1Index ++;
                s2Index ++;
            }
        }
        return falseMatch <= 1;
    }

}
