package com.hackerrank.openbracket;

import java.util.*;

public class FindingSubsequence {

    static String solve(String s, int k) {
        if(k <= 0){
        	return "";
        }
        if(s == null || s.isEmpty()) {
        	return "";
        }
        
        if(k > s.length()) {
        	return "";
        }
        
        //ALL LETTERS IN S MUST BE THE SAME
        if(k == s.length()) {
        	char tmp = s.charAt(0);
        	for(char ch : s.toCharArray()) {
        		if(tmp != ch) {
        			return "";
        		}
        	}
        	return s;
        }
        
        //k >= 1
    	//Create ordered map for count ...O(n)
    	HashMap<Character,Integer> map = new HashMap<Character,Integer>();
    	Character tmpch = null;
    	Integer tmpCount = null;
    	for(char ch : s.toCharArray()) {
    		tmpch = new Character(ch);
    		if(map.containsKey(tmpch)) {
    			tmpCount = map.get(tmpch);
    			tmpCount ++;
    			map.put(tmpch, tmpCount);
    		}else {
    			map.put(tmpch, new Integer(1));
    		}
    	}
    	
    	//Delete letters that don't appear at least k times ...O(n)
    	StringBuilder strRes = new StringBuilder();
    	for(char ch : s.toCharArray()) {
    		tmpch = new Character(ch);
    		if(map.containsKey(tmpch) && map.get(tmpch) >= k) {
        		strRes.append(ch);
        	}
    	}
    	
    	//Natural Order Set of substrings
    	TreeSet<String> subStrSet = new TreeSet<String>();
    	subStrSet = permutations(strRes.toString());
    	NavigableSet<String> descen = subStrSet.descendingSet();
        return descen.first();
    }
    
    private static TreeSet<String> permutations(String input) {
        if (input.length() == 1) {
        	TreeSet<String> a = new TreeSet<String>();
            a.add(input);
            return a;
        }
        TreeSet<String> returnSet = new TreeSet<>();

        for (int i = 0; i < input.length(); i++) {
            String prefix = input.substring(i, i + 1);
            TreeSet<String> permutations = permutations(input.substring(i + 1));
            returnSet.add(prefix);
            returnSet.addAll(permutations);
            Iterator<String> it = permutations.iterator();
            while (it.hasNext()) {
                returnSet.add(prefix + it.next());
            }
        }
        return returnSet;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int k = in.nextInt();
        String result = solve(s, k);
        System.out.println(result);
        in.close();
    }
}
