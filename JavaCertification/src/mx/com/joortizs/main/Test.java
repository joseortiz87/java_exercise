package mx.com.joortizs.main;

import java.util.*;

public class Test{
	
	public static void main(String [] args){
		int[] a = {1};
		Test t = new Test();
		t.increment(a);
		System.out.println(a[a.length-1]);
		
		HashMap<String,ArrayList<Integer>> map = new HashMap<String,ArrayList<Integer>>();
		ArrayList<Integer> pointer = new ArrayList<Integer>();
		pointer.add(new Integer(1));
		map.put("A",pointer);
		map.put("B",pointer);
		
		ArrayList<Integer> pointer2 = map.get("A");
		pointer2.add(new Integer(3));
		
		ArrayList<Integer> pointerA = map.get("A");
		ArrayList<Integer> pointerB = map.get("B");
	}
	
	void increment(int[] i){
		i[i.length-1]++;
	}
	
}
