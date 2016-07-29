package mx.com.joortizs.main;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class MaximumSubArraySum {
	
	public static class TestCase{
		public TestCase(long[] pais,long[] numbers){
			this.pais = pais;
			this.numbers = numbers;
		}
		public long[] pais;
		public long[] numbers;
	}

    public static void main(String[] args) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    	//Scanner in = new Scanner(System.in);
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	Long q = Long.parseLong(in.readLine());
    	ArrayList<TestCase> listTestCase = new ArrayList<TestCase>();
    	Integer n = null;
		Long m = null;
		Long[] prefix = null;
		TreeSet<Long> tree = null;
		Long curr = 0l;
		Long maxSoFar = null;
		Long startTime = null;
		Long stopTime = null;
		while(q-- > 0l){
			listTestCase.add(new TestCase(Stream.of(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray(),
					 Stream.of(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray()));
		}
		
    	for(TestCase testCase :  listTestCase){
    		long[] pais = testCase.pais;
    		long[] numbers = testCase.numbers;
    		n = new Long(pais[0]).intValue();
    		m = pais[1];
    		prefix = new Long[n];
    		tree = new TreeSet<Long>();
    		startTime = System.currentTimeMillis();
    		
    		//Prefix
    		curr = 0l;
    		maxSoFar = null;
    		for(int j=0;j < n;j++){
    			curr = (numbers[j] % m + curr) % m;
    			prefix[j] = curr;
    			maxSoFar = (maxSoFar ==  null || curr > maxSoFar) ? curr : maxSoFar;
    		}
    		
    		//IF WE HAVE M-1 THAT IS THE MAX, IF NOT WE SEARCH IN SUBSUMS
    		if(m.equals(maxSoFar+1) || n == 2){
    			stopTime = System.currentTimeMillis();
    			//System.out.println(maxSoFar + " - " + (stopTime - startTime) + "ms");
    			System.out.println(maxSoFar);
    		}else{
    			//Sums Modular
    			tree.add(prefix[0]);
        		for(int h=2;h < n;h++){
        			Long currentNumber = prefix[h];
        			Long ceiling = tree.ceiling(currentNumber);
        			if(ceiling == null){
        				tree.add(prefix[h-1]);
        				continue;
        			}
        			
        			if(ceiling.equals(currentNumber)){
        				tree.remove(ceiling);
        				Long greaterCeiling = tree.ceiling(currentNumber);
        				if(greaterCeiling == null){
        					tree.add(ceiling);
        					tree.add(prefix[h-1]);
        					continue;
        				}
        				tree.add(ceiling);
        				ceiling = greaterCeiling;
        			}
        			
        			Long newMax = (currentNumber - ceiling + m) % m; // %m
        			maxSoFar = maxSoFar > newMax ? maxSoFar : newMax;
        			tree.add(prefix[h-1]);
        		}
        		stopTime = System.currentTimeMillis();
        		//System.out.println(maxSoFar + " - " + (stopTime - startTime) + "ms");
        		System.out.println(maxSoFar);
    		}
    	}
    	
    	in.close();
    }
	
}
