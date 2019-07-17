package com.amazon.aws;

import java.util.*;
import java.util.stream.Collectors;

public class GCD {

    public static void main(String args []){
        System.out.println(calculateGCD(new int[]{2,4,6,8,10}));
        System.out.println(calculateGCD(new int[]{2,7}));
        System.out.println(calculateGCD(new int[]{54,24}));
    }

    public static int calculateGCD(int [] arr){
        List<Integer> numbers = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.sort(numbers);
        Integer maxNumber = numbers.get(0);
        List<Set<Integer>> divisorsArray = new ArrayList<>();
        for(int i = 0;i<numbers.size();i++){
            Set<Integer> divisors = new HashSet<>();
            divisors.add(1);
            for(int j=2;j<=maxNumber;j++){
                if(numbers.get(i)%j == 0){
                    divisors.add(j);
                }
            }
            divisorsArray.add(divisors);
        }
        Set<Integer> resSet = divisorsArray.get(0);
        for(int i=1;i<divisorsArray.size();i++){
            resSet.retainAll(divisorsArray.get(i));
        }
        List<Integer> gcdList = new ArrayList<>(resSet);
        Collections.sort(gcdList);
        return gcdList.get(gcdList.size()-1);
    }

}
