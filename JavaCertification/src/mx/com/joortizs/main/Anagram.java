package mx.com.joortizs.main;
import java.util.*;

public class Anagram {
	
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		Scanner in = new Scanner(System.in);
		List<String> casesList = new ArrayList<String>(); 
		int testCases = in.nextInt();
		
		//READING DATA
		for(int i=0;i<testCases;i++){
			casesList.add(in.next());
		}
		
		//START PROCESS WITH TEST CASES
		for(String caseStr : casesList){
			int caseLength = caseStr.length();
			if(caseLength%2 != 0){
				System.out.println(-1);  //unequal length
			}else{
				StringBuffer str1 = new StringBuffer(caseStr.substring(0,caseLength/2));
				StringBuffer str2 = new StringBuffer(caseStr.substring(caseLength/2));
				for(int j=0;j<str2.length();j++){
					int index = str1.indexOf(str2.charAt(j)+"");
					if(index >= 0){
						str1.deleteCharAt(index);
					}
				}
				System.out.println(str1.length());
			}
		}
		
		in.close();
    }
}
