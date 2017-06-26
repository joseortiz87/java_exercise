package com.fcb;

import java.util.*;

public class Practice1 {

	public static final HashMap<String,ArrayList<String>> nearByMap = new HashMap<String,ArrayList<String>>();
	
	public static void main(String args[]){
		for(String word : get_nearby_words("ababab")){
			System.out.println(word);
		}
	}
	
	static ArrayList<String> get_nearby_words(String word){
		//Null or empty word
		if(word == null || word.length() == 0){
			return new ArrayList<String>();
		}
		
		if(word.length() == 1){
			return new ArrayList<String>(Arrays.asList(new String[]{word}));
		}
		
		//Valid word
		return new ArrayList<String>(getPermutation(word,0));
	}
	
	static TreeSet<String> getPermutation(String word,int level){
		
		if(word.length() <= 1){
			return new TreeSet<String>(get_nearby_chars("" + word.charAt(0)));
		}
		
		TreeSet<String> permutations = new TreeSet<String>();
		TreeSet<String> subPermutations = getPermutation(word.substring(1),level+1);
		for(String nearbyString : get_nearby_chars("" + word.charAt(0)) ){
			for(String subword : subPermutations){
				if(level == 0){
					if(is_word(nearbyString + subword))
						permutations.add(nearbyString + subword);
				}else{
					permutations.add(nearbyString + subword);
				}
			}
		}
		return permutations;
	}
	
	
	/*
	 * Random seleccion for simplicity
	 * */
	static boolean is_word(String word){
		Random rand = new Random();
		return rand.nextBoolean();
	}
	
	static ArrayList<String> get_nearby_chars(String character){
		if(nearByMap.containsKey(character))
			return nearByMap.get(character);
		else
			return new ArrayList<String>();
	}
	
	static{
		nearByMap.put("a",new ArrayList<String>(Arrays.asList(new String[]{"a","s","q"})));
		nearByMap.put("b",new ArrayList<String>(Arrays.asList(new String[]{"b","v","n"})));
		nearByMap.put("c",new ArrayList<String>(Arrays.asList(new String[]{"c","v","w"})));
	}
}
