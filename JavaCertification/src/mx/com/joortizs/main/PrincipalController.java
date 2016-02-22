package mx.com.joortizs.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PrincipalController {

	public static void main(String [] args){
		/* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    	Scanner sc=new Scanner(System.in);
        String A=sc.next();
        String B=sc.next();
        /* Enter your code here. Print output to STDOUT. */
        int sum = A.length() + B.length();
        String alphabetically = "Yes";
        String capitalize = "";
        List<String> list = new ArrayList<String>();
        list.add(A);
        list.add(B);
        Collections.sort(list);
        if(list.get(0).equals(A)){
        	alphabetically = "No";
        }
        capitalize = new String("" + A.charAt(0)).toUpperCase() + A.substring(1, A.length()) + " " + new String("" + B.charAt(0)).toUpperCase() + B.substring(1, B.length());
        System.out.println(sum);
        System.out.println(alphabetically);
        System.out.println(capitalize);
        sc.close();
	}
}
