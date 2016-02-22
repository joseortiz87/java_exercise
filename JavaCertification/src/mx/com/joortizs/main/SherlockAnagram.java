package mx.com.joortizs.main;
import java.util.*;

public class SherlockAnagram {

	/*
	 * 
5
ifailuhkqq
hucpoltgty
ovarjsnrbf
pvmupwjjjf
iwwhrlkpek

3
2
2
6
3
	 * */
	
	public static void main(String args []){
		Scanner in = new Scanner(System.in);
		int testCases = in.nextInt();
		List<String> cases = new ArrayList<String>();
		for(int i=0;i<testCases;i++){
			cases.add(in.next());
		}
		
		for(String tmp : cases){
			List<String> subStr = new ArrayList<String>();
			int anagramaticPairs = 0;
			for(int i=0;i<tmp.length();i++){
				for(int j=i;j<tmp.length();j++){
					subStr.add(tmp.substring(i,j+1));
				}
			}
			
			while(!subStr.isEmpty()){
				String tmpStr = subStr.remove(0);
				for(String otherSubStr : subStr){
					if(tmpStr.length() == otherSubStr.length()){
						if(isAnagrammaticPair(tmpStr,otherSubStr)){
							anagramaticPairs++;
						}
					}
				}
			}
			System.out.println(anagramaticPairs);
		}
		in.close();
	}
	
	public static boolean isAnagrammaticPair(String str1,String str2){
		StringBuilder build1 = new StringBuilder(str1);
		StringBuilder build2 = new StringBuilder(str2);
		
		for(int i=0;i<build1.length() && build2.length() > 0;i++){
			String tmpChar = build1.charAt(i) + "";
			int index = build2.indexOf(tmpChar);
			if(index >= 0){
				build2.deleteCharAt(index);
			}
		}
		
		if(build2.length() == 0){
			//System.out.println(build1);
			return true;
		}else{
			return false;
		}
	}

}
