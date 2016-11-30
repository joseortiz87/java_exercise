package mx.com.joortizs.project.euler;

import java.math.BigInteger;
import java.text.DecimalFormat;

public class TestingFactorial {

	public static void main(String arg[]){
		BigInteger bigN = new BigInteger("12345678910");
		int numberLength = (int)(Math.log10(bigN.doubleValue())+1);
		String number = bigN.toString().substring(numberLength-8, numberLength);
		
		DecimalFormat format = new DecimalFormat("########");
		System.out.println(number);
		
		Long x = new Long(10);
		System.out.println(x << 10);
		
		
		System.out.println(54010700 + " - " + 
				FactorialTrailingDigits160.zeroTrailingLogPower10((long)54010700));
		
		long startTime = System.currentTimeMillis();
		Long counter = 10l;
		while(counter < 1000000000000000000l){
			/*
			System.out.println(counter + " - " + 
					FactorialTrailingDigits160.zeroTrailingLogPower10((long)counter));
					*/
			counter = counter+10l;
		}		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println(elapsedTime);
	}
	
}
