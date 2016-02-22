package mx.com.joortizs.main;

import java.util.Scanner;

public class Pagram {
	
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        final Scanner in = new Scanner(System.in);
        final String inputString = in.nextLine().toLowerCase();
        boolean isPangram = true;
        char initChar = 'a';
        for(int i=0;i<26;i++){
            if(inputString.indexOf(initChar) == -1){
                isPangram = false;
                break;
            }
            initChar++;
        }
        if(isPangram){
            System.out.println("pangram");
        }else{
            System.out.println("not pangram");
        }
        in.close();
    }

}
