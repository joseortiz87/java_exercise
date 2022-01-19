package com.fcb;

import java.util.*;

public class Interview1 {

	public static void main(String args[]){
		ArrayList<Integer> test1 = new ArrayList<Integer>(Arrays.asList(new Integer[]{22,300,1217,5,1,0,11}));
		ArrayList<Integer> test2 = new ArrayList<Integer>(Arrays.asList(new Integer[]{3,5,10,14,15,17,21,25,30,32}));
		System.out.println(isSumInArray(test1,12));
		System.out.println(findPositiveClosestSum(test2,16));
	}
	public static boolean isSumInArray(ArrayList<Integer> array,Integer sum){
		// 0 1 5 11 22 300 1217
		// x + y = sum
		// sum - x = y
		// 12 - 0 = 12
		// 12 - 1 = 11
		// 12 - 5 = 7
		// 12 - 11 = 1
		// 12 - 22 = -10
		HashSet<Integer> xMap = new HashSet<>();
		for(Integer x : array) {
			Integer tmpDiff = sum - x;
			if(xMap.contains(tmpDiff)) return true;
			xMap.add(x);
		}
		return false;
	}

	public static String findPositiveClosestSum(ArrayList<Integer> array,Integer sum){
		// x + y = sum
		// sum - x = y
		// 16
		// 3 5 10 14 15 17 21 25 30 32
		// 32 + 3 - 16 = 19
		// 30 + 3 - 16 = 17
		// 25 + 3 - 16 =
		int l = 0;
		int r = array.size()-1;
		int res_l = 0;
		int res_r = 0;
		int minSUM = Integer.MAX_VALUE;
		while(l < r){
			if(Math.abs(array.get(l) + array.get(r) - sum) < minSUM){
				res_l = l;
				res_r = r;
				minSUM = Math.abs(array.get(l) + array.get(r) - sum);
			}
			if(array.get(l) + array.get(r) > sum) r--; else l++;
		}
		return array.get(res_l) + "+" + array.get(res_r) + "=" + (array.get(res_l)+array.get(res_r));
	}
}
