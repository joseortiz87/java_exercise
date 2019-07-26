package com.visa;

import java.util.*;

public class VisaHackerRank {
    public static void main(String args []){
        braces(new String[]{"(){}[]"});
    }

    static String[] braces(String[] values) {
        if(values == null){
            return null;
        }
        String [] result = new String[values.length];
        for(int i=0;i<values.length;i++){
            LinkedList<Character> queue = new LinkedList<>();
            String value = values[i];
            String res = "YES";
            for(int j=0;j<value.length();j++){
                char item = value.charAt(j);
                if(item == '[' || item == '(' || item == '{' ){
                    queue.add(item);
                }else if(item == ']' || item == ')' || item == '}'){
                    if(queue.isEmpty()){
                        res = "NO";
                        break;
                    }
                    char openKey = '[';
                    switch(item){
                        case ')' :
                            openKey = '(';
                            break;
                        case '}' :
                            openKey = '{';
                            break;
                    }
                    char lastKey = queue.removeLast();
                    if(lastKey != openKey){
                        res = "NO";
                        break;
                    }
                }
            }
            if(!queue.isEmpty()){
                res = "NO";
            }
            result[i] = res;
        }
        return result;
    }
}
