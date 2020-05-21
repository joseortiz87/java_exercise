package com.apple;

import java.util.Map;
import java.util.stream.Collectors;

public class CustomSort {

    public static void main(String arg []){
        System.out.println(customSort("subpasasllttkktduudrroommmm","talrd"));
    }

    public static String customSort(String toSort,String sortPattern){
        if(null == sortPattern || sortPattern.length() == 0){
            return toSort;
        }
        if(null == toSort || toSort.length() <= 1){
            return toSort;
        }

        // O(N)
        Map<Character,StringBuilder> mapChars = toSort.chars().boxed().collect(
                Collectors.toMap(key -> new Character((char)key.intValue()),
                                 val -> {
                                     StringBuilder str = new StringBuilder();
                                     str.append((char)val.intValue());
                                     return str;
                                 },
                                (oldVal,newVal) -> {
                                    oldVal.append(newVal);
                                    return oldVal;
                                }));

        StringBuilder sortString = new StringBuilder();
        // O(M)
        sortPattern.chars().forEach(item -> {
            Character key = (char)item;
            if(mapChars.containsKey(key)){
                sortString.append(mapChars.get(key));
                mapChars.remove(key);
            }
        });

        // O(N-M)
        mapChars.values().forEach(values -> sortString.append(values));
        return sortString.toString();
    }
}
