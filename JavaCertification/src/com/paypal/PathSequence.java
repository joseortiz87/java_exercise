package com.paypal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathSequence {

    public static void main(String arg []){
        System.out.println(longestSequence(
                new String [] {"/path","/co","/pink","/green","/blue","/red"},
                new String [] {"/a","/c","h","/pink","/green","/blue"}
        ));
    }

    public static List<String> longestSequence(String [] user0,String [] user1){
        List<String> listUser0 = Arrays.asList(user0);
        List<String> listUser1 = Arrays.asList(user1);
        List<String> maxSequence = new ArrayList<>();
        int index0 = 0;
        while(index0 < user0.length){
            String path0 = listUser0.get(index0);
            List<String> localSequence = new ArrayList<>();
            if(listUser1.contains(path0)){
                int nextIndex0 = index0 +1;
                int startIndex = listUser1.indexOf(path0) +1;
                localSequence.add(path0);
                while(nextIndex0 < listUser0.size() &&
                        startIndex < listUser1.size() &&
                        listUser1.get(startIndex).equals(listUser0.get(nextIndex0))){
                    localSequence.add(listUser1.get(startIndex));
                    startIndex++;
                    nextIndex0++;
                }
            }
            if(localSequence.size() > maxSequence.size()){
                maxSequence = localSequence;
            }
            index0++;
        }
        return maxSequence;
    }
}
