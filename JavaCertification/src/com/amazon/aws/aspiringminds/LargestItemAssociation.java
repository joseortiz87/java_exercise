package com.amazon.aws.aspiringminds;

import java.util.*;

public class LargestItemAssociation {

	public static void main(String args []){
		String [] res = largestItemAssociation(new String[][]{
			new String[]{"item1","item2"},
			new String[]{"item3","item4"} });
		for(String item : res){
			System.out.println(item);
		}
	}
	
	// METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
	// RETURN "null" IF NO ITEM ASSOCIATION IS GIVEN
	public static String[] largestItemAssociation(String[][] itemAssociation)
	{
		// WRITE YOUR CODE HERE
		//Edge cases
		if(itemAssociation == null ||
				itemAssociation.length == 0 ){
			return new String[0];
		}
		
		//Save the items in their groups in natural order
		ArrayList<TreeSet<String>> groups = new ArrayList<TreeSet<String>>();
		String [] result = new String[0];
		
		//Dictionary for grouping items with O(1) access to items in 
		// order to know the group and put it in the correct
		LinkedHashMap<String,Integer> itemGroupMap = new LinkedHashMap<String,Integer>();
		Integer group = 1;
		for(String[] items : itemAssociation){
			//first iteration group all in first group
			if(itemGroupMap.isEmpty()){
				TreeSet<String> itemSet = new TreeSet<String>();
				for(String item : items){
					itemGroupMap.put(item, group);
					itemSet.add(item);
				}
				groups.add(itemSet);
			}else{
				//Search for groups related by some item
				Integer groupRelated = null;
				TreeSet<String> itemSet = new TreeSet<String>();
				for(String item : items){
					//Find related group and save group
					if(itemGroupMap.containsKey(item)){
						groupRelated = itemGroupMap.get(item);
						break;
					}
				}
				//didn't find a related group increment group
				//in other case save within same group
				int flag = 0;
				if(groupRelated == null){
					group++;
					groupRelated = group;
					flag++;
				}else{
					itemSet = groups.get(groupRelated-1);
				}
				for(String item : items){
					itemGroupMap.put(item, groupRelated);
					itemSet.add(item);
				}
				if(flag == 0){
					groups.set(groupRelated-1, itemSet);
				}else{
					groups.add(itemSet);
				}
			}
		}
		
		//We have the groups lets find the biggest
		//we have 2 groups same size
		int bigSize = 0;
		LinkedList<TreeSet<String>> groups2 = new LinkedList<TreeSet<String>>();
		for(TreeSet<String> set : groups){
			if(set.size() > bigSize){
				bigSize = set.size();
				groups2.addFirst(set);
			}else if(set.size() == bigSize){
				int valueItem = new Integer(groups2.getFirst().first().replace("item",""));
				int valueItem2 = new Integer(set.first().replace("item",""));
				if(valueItem2 <= valueItem){
					groups2.addFirst(set);
				}else{
					groups2.add(1, set);
				}
			}
		}
		
		TreeSet<String> tmpgroup = groups2.getFirst();
		result = tmpgroup.toArray(new String[tmpgroup.size()]);
		return result;
	}
	// METHOD SIGNATURE ENDS
	
}
