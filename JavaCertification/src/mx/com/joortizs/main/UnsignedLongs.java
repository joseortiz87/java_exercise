package mx.com.joortizs.main;

import java.math.BigInteger;
import java.util.Scanner;

public class UnsignedLongs {

	  private static final BigInteger TWO_32 = BigInteger.ONE.shiftLeft(32);
	    
	    public static void main(String[] args) {
	        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
	        Scanner in = new Scanner(System.in);
	        int testCases = in.nextInt();
	        for(int i=0;i<testCases;i++){
	            long tmpNum = in.nextLong();
	            tmpNum = ~tmpNum;
	            System.out.println(asUnsignedDecimalString(tmpNum));
	        }
	        in.close();
	    }
	    
	    public static String asUnsignedDecimalString(long l) {
	        BigInteger b = BigInteger.valueOf(l);
	         if(b.signum() < 0) {
	             b = b.add(TWO_32);
	        }
	        return b.toString();
	    }
	
}
