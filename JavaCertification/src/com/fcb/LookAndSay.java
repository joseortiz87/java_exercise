package com.fcb;

public class LookAndSay {

    /*
    input 10

    output
    1
    11
    21
    1211
    111221
    312211
    13112221
    1113213211
    31131211131221
    13211311123113112211
     */

    public static void main(String args []){
        print_look_and_say_seq(10);
    }

    public static void print_look_and_say_seq(int n){
        String value = "1";
        System.out.println(n);
        for(int i= 1;i<=n;i++){
            System.out.println(value);
            value = look_and_say(value);
        }
    }

    public static String look_and_say(String value){
        if("1".equals(value)){
            return "11";
        }

        char previousValue = '0';
        int count = 0;
        StringBuilder lookAndSay = new StringBuilder();
        char[] arrayValue = value.toCharArray();
        for(int i=0;i<value.length();i++){
            char ch = value.charAt(i);
            if(previousValue != ch){
                if(previousValue != '0'){
                    lookAndSay.append(count).append(previousValue);
                }
                previousValue = ch;
                count = 1;
            }else{
                count++;
            }
            if(i == value.length()-1){
                lookAndSay.append(count).append(previousValue);
            }
        }

        return lookAndSay.toString();
    }
}
