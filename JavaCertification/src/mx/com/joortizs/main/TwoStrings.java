package mx.com.joortizs.main;
import java.util.*;

public class TwoStrings {

	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int i=0;i<t;i++){
			String tmpStr = in.next();
			Set<Character> set1 = new HashSet<Character>();
			Set<Character> set2 = new HashSet<Character>();
			
			for(int j=0;j<tmpStr.length();j++){
				set1.add(tmpStr.charAt(j));
			}
			
			tmpStr = in.next();
			for(int j=0;j<tmpStr.length();j++){
				set2.add(tmpStr.charAt(j));
			}
			
			set1.retainAll(set2);
			if(set1.size() > 0){
				System.out.println("YES");
			}else{
				System.out.println("NO");
			}
		}
		
		in.close();
    }
	
}
