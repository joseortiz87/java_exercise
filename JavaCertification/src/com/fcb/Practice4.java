package com.fcb;

import java.util.*;

public class Practice4 {

	public static void main(String args[]){
		TreeSet<Integer> set = sumCombination(10, 15, 55);
		for(Integer num : set){
			System.out.println(num);
		}
	}
	
	public static TreeSet<Integer> sumCombination(int coin1,int coin2,int coin3){
		int n = 0;
		int q = 0;
		int p = 0;
		int sumPrev = 0;
		TreeSet<Integer> tree = new TreeSet<>();
		do{
			int tmp = (n * coin1 + q * coin2 + p * coin3);
			sumPrev = tmp > sumPrev ? tmp : sumPrev;
			tree.add(tmp);
			if(q > n && p == q){
				n++;
				q = 0;
				p = 0;
			}else{
				if(p < q){
					p++;
				}else if(p == q){
					p++;
				}else{
					int swap = q;
					q = p;
					p = swap;
				}
			}
		}while(sumPrev <= 1000);
		return tree;
	}
	
}
