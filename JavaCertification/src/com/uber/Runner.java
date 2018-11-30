package com.uber;

import java.util.*;

/**
 * 
 * @author yh07021
 *
 *
input
5
2
1
2
1
3
1

output
6
 */

public class Runner {
	  public static void main(String args[] ) throws Exception {
	        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
	        Scanner scanner = new Scanner(System.in);
	        int size = scanner.nextInt();
	        List<Integer> list = new ArrayList<>();
	        Map<Integer,Boolean> mapOdd = new HashMap<>(); 
	        Set<SubArray> subarrays = new HashSet<>();
	        int maxOdd = 0;
	        for(int i=0;i<size;i++){
	            int element = scanner.nextInt();
	            mapOdd.put(i,element%2 != 0);
	            list.add(element);
	        }
	        maxOdd = scanner.nextInt();

	        int j = 0;
	        int oddCount = 0;
	        ArrayList<Integer> tmpSubarray = new ArrayList<>();
	        for(int i=j;i<size;i++){
	            if(!mapOdd.get(i) || (mapOdd.get(i) && oddCount+1 <= maxOdd)){
	                tmpSubarray.add(list.get(i));
	                if(tmpSubarray.size() < size) {
	                	SubArray subarray = new SubArray(tmpSubarray);
		                if(!subarrays.contains(subarray)) {
		                	subarrays.add(subarray);
		                }
	                }
	                if(mapOdd.get(i)) {
 	                	oddCount++;
 	                }
	            }else {
	            	i = size -1;
	            }
	            if(i == size -1) {
	            	j++;
	            	i = j-1;
	            	oddCount = 0;
	            	tmpSubarray = new ArrayList<>();
	            }
	        }
	        System.out.println(subarrays.size());
	        scanner.close();
	        
	    }

	    static class SubArray{
	        int [] list;
	        SubArray(ArrayList<Integer> list){
	            this.list = new int[list.size()];
	            this.list = list.stream().mapToInt(i->i).toArray();
	        }
	        SubArray(int [] list){
	            this.list = list;
	        }
	        
	        @Override
	        public int hashCode() {
	        	int hashcode = 1;
	        	for(int i : this.list) {
	        		hashcode = (int) (i ^ (i >>> 32));
	        	}
	            return hashcode;
	        }
	        
	        public boolean equals(Object obj) {
	            if(obj != null && obj instanceof SubArray){
	            	SubArray objSubArray = (SubArray) obj;
	                return Arrays.equals(this.list,objSubArray.list);
	            }
	            return false;
	        }
	    }
}
