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
	}
	
}
