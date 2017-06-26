package com.fcb;

import java.util.*;

public class Practice2 {

	public static void main(String args []){
		ArrayList<String> a = new ArrayList<String>(Arrays.asList(new String[]{"RAT","CAT","BAT"}));
		ArrayList<String> b = new ArrayList<String>(Arrays.asList(new String[]{"RAT","CAT","MAT"}));
		RemoveDulicatesInterator remov1 = new RemoveDulicatesInterator(a,b);
		while(remov1.hasNext()){
			System.out.println(remov1.next());
		}
	}
	
	public static final class RemoveDulicatesInterator implements Iterator<String>{

		private List<String> iterator;
		private int cursor;
		private final int end;
		
		public RemoveDulicatesInterator(List<String> list1,List<String> list2){
			TreeSet<String> set = new TreeSet<String>();
			for(String str : list1){
				set.add(str);
			}
			for(String str : list2){
				set.add(str);
			}
			this.cursor = 0;
			this.end = set.size();
			iterator = new ArrayList<String>(set);
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return cursor < end;
		}

		@Override
		public String next() {
			// TODO Auto-generated method stub
			if(this.hasNext()){
				return iterator.get(cursor++);
			}
			return null;
		}
		
	}
	
}
