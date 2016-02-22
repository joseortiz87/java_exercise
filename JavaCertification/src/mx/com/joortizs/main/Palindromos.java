package mx.com.joortizs.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Palindromos {

	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        final String anagram = in.nextLine();
        int pareCount = 0;
        int oddCount = 0;
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for(int i=0;i<anagram.length();i++){
            char tmpChar = anagram.charAt(i);
            Integer tmpCount = map.get(tmpChar);
            if(tmpCount != null){
            	map.put(tmpChar, new Integer(tmpCount.intValue()+1));
            }else{
            	map.put(tmpChar, 1);
            }
        }
        
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
        	if(entry.getValue()%2 == 0){
        		pareCount++;
        	}else{
        		oddCount++;
        	}
        }
        
        if((anagram.length()%2 == 0 && pareCount > 0 && oddCount == 0) || 
        		(anagram.length()%2 != 0 && pareCount >= 0 && oddCount == 1)){
        	System.out.println("YES");
        }else{
        	System.out.println("NO");
        }
        
        in.close();
    }
	
}
