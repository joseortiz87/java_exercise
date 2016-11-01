package mx.com.joortizs.project.euler;

import java.math.*;
import java.util.*;

public class FactorialTrailingDigits160 {

	public static BigInteger BIG_TWO = new BigInteger("2");
	public static BigInteger BIG_THREE = new BigInteger("3");
	public static BigInteger BIG_TEN = new BigInteger("10");
	
	public static ArrayList<BigDecimal> coefficients = new ArrayList<BigDecimal>(); 
	public static BigDecimal factorA = new BigDecimal(Math.log(2*Math.PI)/2);
	
	static{
		coefficients.add(new BigDecimal(109535241009d/48264275462d));
		coefficients.add(new BigDecimal((double)29944523/19733142));
		coefficients.add(new BigDecimal((double)22999/22737));
		coefficients.add(new BigDecimal((double)195/371));
		coefficients.add(new BigDecimal((double)53/210));
		coefficients.add(new BigDecimal((double)1/30));
	}
	
	public static void main(String args[]){
		try(Scanner in = new Scanner(System.in)){
			int base = in.nextInt();
			long queries = in.nextLong();
			String n = null;
			BigInteger bigN = null;
			in.nextLine();
			while(in.hasNextLine()){
				n = in.nextLine();
				bigN = new BigInteger(n, base);
				//bigN = doFactorial(bigN);
				//bigN = digammaFuction(bigN);
				//System.out.println(doTrailingDigit(bigN.toString(base)));
				System.out.println(zeroReductionFactorial(bigN).toString(base));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static BigInteger zeroReductionFactorial(BigInteger bigN){
		if(bigN.compareTo(BigInteger.ONE) != 1){
			return BigInteger.ONE;
		}
		BigInteger counter = BIG_TWO;
		BigInteger multi = BigInteger.ONE;
		BigInteger factor = null;
		BigInteger counter10 = BIG_TWO;
		while( counter.compareTo(bigN) != 1  ){
			factor = counter;
			if( counter10.compareTo(BIG_TEN) == 0 ){
				factor = zeroTrailing(factor);
				counter10 = BigInteger.ONE;
			}
			multi = multi.multiply(factor);
			if( multi.remainder(BIG_TEN).compareTo(BigInteger.ZERO) == 0 ){
				multi = zeroTrailing(multi);
			}
			multi = truncateNumber(multi);
			counter = counter.add(BigInteger.ONE);
			counter10 = counter10.add(BigInteger.ONE);
		}
		return multi;
	}
	
	public static BigInteger zeroTrailing(BigInteger bigN){
		String stringFactor = bigN.toString();
		for(int i=stringFactor.length()-1;i>=0;i--){
			if(stringFactor.charAt(i) != '0'){
				return new BigInteger(stringFactor.substring(0, i+1));
			}
		}
		return BigInteger.ONE;
	}
	
	public static BigInteger truncateNumber(BigInteger bigN){
		int numberLength = (int)Math.log10(bigN.intValue())+1;
		return (numberLength > 7) ? 
				new BigInteger(bigN.toString().substring(numberLength-7, numberLength)) : 
					bigN;
	}
	
	/**
	 * Mult reduction aproach
	 * @author joortizs
	 * @param bigN
	 * @return
	 */
	public static BigInteger doFactorial(BigInteger bigN){
		BigInteger factorial = new BigInteger(bigN.toString());
		BigInteger factor = new BigInteger(bigN.toString());
		boolean isEven = bigN.remainder(BIG_TWO).compareTo(BigInteger.ZERO) == 0;
		BigInteger limitFactor = isEven ? BIG_TWO : BIG_THREE;
		if(bigN.compareTo(BigInteger.ZERO) == 0){
			return BigInteger.ONE;
		}
		if(!isEven){
			BigDecimal tmpdec = new BigDecimal(factorial).divide(new BigDecimal(BIG_TWO));
			factorial = factorial.multiply(tmpdec.setScale(0, RoundingMode.UP).toBigInteger());
		}
		while(limitFactor.compareTo(factor) == -1 ){
			factor = factor.subtract(BIG_TWO);
			bigN = bigN.add(factor);
			factorial = factorial.multiply(bigN);
		}
		return factorial;
	}
	
	/**
	 * Digamma Aproximation
	 * @author joortizs
	 * @param bigN
	 * @return
	 */
	public static BigInteger digammaFuction(BigInteger bigN){
		BigDecimal pz = new BigDecimal(0);
		BigDecimal pk = new BigDecimal(bigN);
		BigDecimal z = new BigDecimal(bigN);
		for(BigDecimal an : coefficients){
			BigDecimal tempZ = new BigDecimal(0);
			pk = tempZ.add(z).add(an.divide(pk,15,RoundingMode.HALF_UP));
		}
		pk = new BigDecimal((double)1/12).divide(pk,15,RoundingMode.HALF_UP);
		pz = pk.add(factorA).subtract(z).add( (new BigDecimal(bigN).add(new BigDecimal(1/2))).multiply( new BigDecimal(Math.log(z.doubleValue())) ));
		z = new BigDecimal(Math.exp(pz.doubleValue()));
		return z.setScale(0, RoundingMode.UP).toBigInteger();
	}
	
	public static String doTrailingDigit(String stringFactor){
		int index = 0;
		for(int i=stringFactor.length()-1;i>=0;i--){
			if(stringFactor.charAt(i) != '0'){
				index = i;
				break;
			}
		}
		if(index-4 < 0){
			return stringFactor.substring(0, index+1);
		}
		return stringFactor.substring(index-4, index+1);
	}
	
}
