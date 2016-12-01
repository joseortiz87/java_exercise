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
		while(counter < 10l){
			/*
			System.out.println(counter + " - " + 
					FactorialTrailingDigits160.zeroTrailingLogPower10((long)counter));
					*/
			counter = counter+10l;
		}		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Time: " + elapsedTime);
	    
	    java.util.ArrayList<Long> test1 = new java.util.ArrayList<Long>();
	    test1.add(new Long(10l));
	    test1.add(new Long(20l));
	    test1.add(new Long(22l));
	    test1.add(new Long(120l));
	    test1.add(new Long(100l));
	    test1.add(new Long(1020l));
	    test1.add(new Long(9220l));
	    test1.add(new Long(1000l));
	    
	    for(Long val : test1 ){
	    	System.out.println(val + " - bin: " + Long.toUnsignedString(val, 2)
	    	+ " - zeros: " + Long.numberOfTrailingZeros(val));
	    }
	    
	    FactorialPrimeSwing fac= new FactorialPrimeSwing();
	    for(int i=1;i<60;i++){
	    	System.out.println(i + "! - " + fac.factorial(new Long(i)));
	    }
	}
	
}
