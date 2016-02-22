package mx.com.joortizs.main;

import java.util.Scanner;

public class FunnyString {

	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int testCases = in.nextInt();
        for(int i=0;i<testCases;i++){
        	String strinIn = in.next();
            StringBuffer tmpStr = new StringBuffer(strinIn);
            StringBuffer tmpStrR = new StringBuffer(strinIn).reverse();
            boolean isFunny = true;
            if(tmpStr.length() >= 2){
                for(int j=1;j<tmpStr.length();j++){
                    int sj = Math.abs(tmpStr.charAt(j)-tmpStr.charAt(j-1));
                    int rj = Math.abs(tmpStrR.charAt(j)-tmpStrR.charAt(j-1));
                    if(sj != rj){
                        isFunny = false;
                        break;
                    }
                }
            }else{
                isFunny = false;
            }
            
            if(isFunny){
                System.out.println("Funny");
            }else{
                System.out.println("Not Funny");
            }
        } 
        in.close();
    }
	
}
