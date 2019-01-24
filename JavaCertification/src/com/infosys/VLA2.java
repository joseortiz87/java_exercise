package com.infosys;

import java.util.Comparator;
import java.util.stream.Stream;

public class VLA2 implements Comparator<VLA2>{
	public static void main(String args []) {
		String [] votes = {"Alex",
				"Michael",
				"Harry" ,
				"Dave" ,
				"Michael" ,
				"Victor" ,
				"Harry" ,
				"Alex" ,
				"Mary" ,
				"Mary"};
		
		java.util.HashMap<String,Integer> voteCountMap = new java.util.HashMap<>();
		Stream.of(votes).forEach(name -> {
			Integer count = new Integer(1);
			if(voteCountMap.containsKey(name)) {
				count = voteCountMap.get(name);
				count++;
			}
			voteCountMap.put(name, count);
		});
		
		int maxValueInMap = 0; 
        String winner = ""; 
        for (java.util.Map.Entry<String,Integer> entry : voteCountMap.entrySet()) 
        { 
            String name  = entry.getKey(); 
            Integer count = entry.getValue(); 
            if (count > maxValueInMap) 
            { 
                maxValueInMap = count; 
                winner = name; 
            } 
            else if (count == maxValueInMap && 
                winner.compareTo(name) <= 0) { 
                winner = name; 
            }
        }
		
		System.out.println(winner);
		
	}

	@Override
	public int compare(VLA2 o1, VLA2 o2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void m(int i,int[] arr ) {
		arr[i] = 6;
		i =5;
	}
}
