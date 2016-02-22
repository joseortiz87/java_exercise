package mx.com.joortizs.main;

import java.util.*;

/*
Write a function to determine whether an input string x is a substring of another input string y.
For example, "bat" is a substring of "abate", but not of "beat". Basically implement something similar string.substring without using this function or any other similar function (string.find, string.contains, etc)

More Examples:
yourFunction("first”,"The ford motor company introduce the fierce motor engine for the first time")
yourFunction(“defg”,”abcabcdabcdefabcdddefghijklm”)
              ^---             ^---

- Logic.
- Coding Skills.
- Quality of Code.
*/
public class StringCustom{

    public boolean subString(String search,String str){
        if((search == null && str ==null) || (search.length() > str.length())){
            return false;
        }    
        if(search.equals("") && str.equals("")){
            return true;
        }
        boolean isWrong = false;
        for(int i=0;i<str.length()-search.length();i++){
            isWrong = false;
            if(str.charAt(i) == search.charAt(0)){
                for(int k=1;k<search.length();k++){
                    if(str.charAt(i+k) != search.charAt(k)){
                        isWrong = true;
                        break;
                    }
                }
                if(!isWrong){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void main(String args[]){
    	StringCustom stringCustom = new StringCustom();
    	Scanner in = new Scanner(System.in);
    	String search = in.nextLine();
    	String str = in.nextLine();
    	System.out.println(stringCustom.subString(search,str));
    	in.close();
    }
}
