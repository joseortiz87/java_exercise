package com.hired;

import java.util.*;

public class FirstIndexNonRepeatedChar {

    public static void main(String args []){
        System.out.println(solution("aaaabbbchhjjkkllmmnnoopp"));
    }

    public static long solution(String s) {
        // Type your solution here
        Map<Character,Integer> frecuencyOneMap = new HashMap<Character,Integer>();
        Map<Character,Integer> duplicateMap = new HashMap<Character,Integer>();
        if(null != s && s.length() > 0){
            for(int i=0;i<s.length();i++){
                char currentChar = s.charAt(i);
                if(!duplicateMap.containsKey(currentChar)){
                    if(frecuencyOneMap.containsKey(currentChar)){
                        frecuencyOneMap.remove(currentChar);
                        duplicateMap.put(currentChar,i);
                    }else{
                        frecuencyOneMap.put(currentChar,i);
                    }
                }
            }
            if(frecuencyOneMap.size() > 0){
                List<Integer> indexes = new ArrayList<Integer>(frecuencyOneMap.values());
                Collections.sort(indexes);
                return (long)indexes.get(0);
            }else{
                return -1l;
            }
        }
        return -1l;
    }
}
