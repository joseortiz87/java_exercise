package mx.com.joortizs.main;

import java.util.*;

public class CommonChild {

    static int commonChild(String s1, String s2){
        // Complete this function
    	if(s1 == null || s2 == null || 
    			s1.isEmpty() || s2.isEmpty() || 
    			s1.length() != s2.length()){
    		return 0;
    	}
    	s1 = "0" + s1;
    	s2 = "0" + s2;
    	char [] X = s1.toCharArray();
    	char [] Y = s2.toCharArray();
    	int [][] C = new int [s1.length()][s2.length()];
    	for(int i=1;i<s1.length();i++){
    		for(int j=1;j<s2.length();j++){
    			if(X[i] == Y[j]){
    				C[i][j] = C[i-1][j-1] +1;
    			}else{
    				C[i][j] = Math.max(C[i][j-1], C[i-1][j]);
    			}
    		}
    	}
    	return C[s1.length()-1][s2.length()-1];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.next();
        String s2 = in.next();
        int result = commonChild(s1, s2);
        System.out.println(result);
        in.close();
    }
	
}
