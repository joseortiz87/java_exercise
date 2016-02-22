package mx.com.joortizs.main;
import java.util.*;

public class MakeItAnagram {

	/*
	 *  fcrxzwscanmligyxyvym
		jxwtrhvujlmrpdoqbisbwhmgpmeoke
		30
	 * */

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    	Scanner in = new Scanner(System.in);
    	List<StringBuffer> input = new ArrayList<StringBuffer>();
    	int deletions = 0;
    	input.add(new StringBuffer(in.next()));
    	input.add(new StringBuffer(in.next()));
    	for(int i=0;i<input.size();i++){
    		StringBuffer tmpStr = input.remove(0);
    		List<Integer> toDeleteIndexs = new ArrayList<Integer>(); 
    		for(int j=0;j<tmpStr.length();j++){
    			char tmpChar = tmpStr.charAt(j);
				for(StringBuffer strCompared : input){
					int index = strCompared.indexOf(tmpChar + "");
					if(index >= 0){
						strCompared.deleteCharAt(index);
						toDeleteIndexs.add(new Integer(j));
					}
				}
    		}
    		Collections.sort(toDeleteIndexs);
    		Collections.reverse(toDeleteIndexs);
    		for(int toDeleteIndex : toDeleteIndexs){
				tmpStr.deleteCharAt(toDeleteIndex);
			}
    		input.add(tmpStr);
    	}
    	for(StringBuffer newBuffers : input){
    		deletions += newBuffers.length();
    	}
    	
    	System.out.println(deletions);
    	in.close();
    }
	
}
