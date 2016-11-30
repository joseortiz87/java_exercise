package mx.com.joortizs.project.euler;

import java.math.*;
import java.util.*;

public class FactorialTrailingDigits160 {

	public static BigInteger BIG_TWO = new BigInteger("2");
	public static BigInteger BIG_THREE = new BigInteger("3");
	public static BigInteger BIG_TEN = new BigInteger("10");
	public static Long MAX_TRAILING_SEVEN = 9999999l;
	public static Long MAX_TRAILING_FIVE = 99999l;
	
	public static TreeMap<Long,Long> lookupFactorials = new TreeMap<Long,Long>(); 
	
	static{
		lookupFactorials.put(1l, BigInteger.ONE.longValue());
		lookupFactorials.put(10000000l,94688l); //10000000
		lookupFactorials.put(100000000l,80992l); //100000000
		lookupFactorials.put(1000000000l,18976l); //1000000000
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
				//long startTime = System.currentTimeMillis();
				System.out.println(new BigInteger(zeroReductionFactorial(bigN.longValue()).toString()).toString(base));
				//long stopTime = System.currentTimeMillis();
			    //long elapsedTime = stopTime - startTime;
			    //System.out.println("Time:" + elapsedTime);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static Long zeroReductionFactorial(Long bigN){
		if(lookupFactorials.containsKey(bigN)){
			return lookupFactorials.get(bigN);
		}
		Long counter = 2l;
		Long multi = 1l;
		Long factor = null;
		Short counter10 = 2;
		Long counterDec = 1l;
		Long nextMult10 = 10l;
		Long maxFactorailValue = lookupFactorials.floorKey(bigN);
		if(maxFactorailValue != null){
			counter = maxFactorailValue;
			multi = lookupFactorials.get(maxFactorailValue);
			counter10 = 0;
			counterDec = counter;
			nextMult10 = getNextMult10(maxFactorailValue);
		}
		while( counter <= bigN ){
			factor = counter;
			factor = truncateNumber(factor,MAX_TRAILING_SEVEN);
			if( counter10 == BIG_TEN.shortValue() ){
				if(counterDec == nextMult10){
					factor = null;
					nextMult10 = getNextMult10(nextMult10);
				}else{
					factor = zeroTrailingLogPower10(counterDec);
				}
				counterDec++;
				counter10 = 0;
			}
			if(factor != null && factor > 0)
				multi = multi*factor;
			multi = zeroTrailing(multi);
			multi = truncateNumber(multi,MAX_TRAILING_SEVEN);
			counter++;
			counter10 ++;
		}
		lookupFactorials.put(bigN, multi);
		return truncateNumber(multi,MAX_TRAILING_FIVE);
	}
	
	public static Long getNextMult10(Long nextMult10){
		return new Long(nextMult10.toString() + "0");
	}
	
	public static Long zeroTrailingLogPower10(Long bigN){
		Long powerOfTen = (long)Math.log10(bigN);
		if(powerOfTen == 1){
			return bigN/BIG_TEN.longValue();
		}else{
			Long frontPower = powerOfTen;
			Long backPower = powerOfTen-1;
			Long frontPowerNum = 0l;
			Long backPowerNum = 0l;
			while(backPower > 0){
				frontPowerNum = (long)Math.pow(BIG_TEN.doubleValue(), frontPower);
				backPowerNum = (long)Math.pow(BIG_TEN.doubleValue(), backPower);
				if(bigN%frontPowerNum == 0){
					return bigN/frontPowerNum;
				}
				if(bigN%backPowerNum == 0){
					return bigN/backPowerNum;
				}
				frontPower--;
				backPower--;
			}
		}
		return bigN;
	}
	
	public static Long zeroTrailing(Long bigN){
		if(bigN%BIG_TEN.longValue() == 0){
			return zeroTrailingLogPower10(bigN);
		}
		return bigN;
	}
		
	public static Long zeroTrailingString(Long bigN){
		String stringFactor = bigN.toString();
		for(int i=stringFactor.length()-1;i>=0;i--){
			if(stringFactor.charAt(i) != '0'){
				return new Long(stringFactor.substring(0, i+1));
			}
		}
		return BigInteger.ONE.longValue();
	}
	
	public static Long truncateNumber(Long bigN,Long trailing){
		if (bigN > trailing){
			String numberStr = bigN.toString();
			int numberLength = numberStr.length();
			return new Long(numberStr.substring(numberLength-(trailing.toString().length()), numberLength));
		}  
		return bigN;
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
