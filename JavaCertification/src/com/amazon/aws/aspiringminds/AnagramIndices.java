package com.amazon.aws.aspiringminds;

import java.util.*;

public class AnagramIndices {

	public static void main(String args []){
		for(Integer n : getAnagramIndices("bbbababaaabbbb","ab")){
			System.out.println(n);
		}
	}
	
	public static List<Integer> getAnagramIndices(String haystack, String needle)
    {
        // WRITE YOUR CODE HERE  
		//In edge cases return empty array 
		if(needle == null || haystack == null || 
				needle.isEmpty() || haystack.isEmpty() ||
				needle.length() > haystack.length()	){
			return new ArrayList<Integer>();
		}
		
		//Always check in lower case
		haystack = haystack.toLowerCase();
		needle = needle.toLowerCase();
		ArrayList<Integer> result = new ArrayList<Integer>();
		char [] needleArray = needle.toCharArray();
		/*
		 * 2 cases:
		 * 	when needle is same size then haystack -> check if is the same word
		 *  other --> search anagrams
		*/
		if(needle.length() == haystack.length()){
			char [] haystackArray = haystack.toCharArray();
			//Sort characters by natual order in order to compare them
			Arrays.sort(needleArray);
			Arrays.sort(haystackArray);
			needle = new String(needleArray);
			haystack = new String(haystackArray);
			//needle is anagram of haystack?
			if(needle.equals(haystack)){
				result.add(new Integer(0));
			}
		}else{
			int frontIndex = 0;
			int backIndex = needle.length();
			Arrays.sort(needleArray);
			needle = new String(needleArray);
			//Set a window to look for anagrams of the size of needle
			//WE SHIFT THE WINDOW TO THE RIGHT
			while(backIndex <= haystack.length()){
				//get the substring of needles lenght
				String tmp = haystack.substring(frontIndex, backIndex);
				char[] tmpArray = tmp.toCharArray();
				Arrays.sort(tmpArray); //natural order
				tmp = new String(tmpArray);
				if(tmp.equals(needle)){
					result.add(frontIndex);
				}
				frontIndex++;
				backIndex++; //move window
			}
		}
		
		return result;
    }
	
}
