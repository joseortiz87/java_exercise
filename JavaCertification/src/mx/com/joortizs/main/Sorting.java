package mx.com.joortizs.main;

import java.util.*;

public class Sorting {

	  public static void main(String[] args) {
	        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
	        Scanner in = new Scanner(System.in);
	        int length = in.nextInt();
	        TreeSet<Integer> setA = new TreeSet<Integer>();
	        for(int i=0;i<length;i++){
	            setA.add(in.nextInt());
	        }
	        long startTime = System.currentTimeMillis();
	        Integer[] arrayA = setA.toArray(new Integer[setA.size()]);
	        long elapsedTime = System.currentTimeMillis() - startTime;
	        System.out.println("\n(1)Total elapsed time:  " + (elapsedTime/1000.0) + " seconds.\n");
	        ArrayList<Pair> pairs = new ArrayList<Pair>();
	        int minAbsDiff = -1;
	        length = arrayA.length;
	        startTime = System.currentTimeMillis();
	        for(int j=0;j<length-1;j++){
	        	Pair tmpPair = new Pair(arrayA[j],arrayA[j+1]);
	        	int tmpAbsDiff = tmpPair.getAbsDiff();
                if(minAbsDiff == -1 || tmpAbsDiff <= minAbsDiff){
                    minAbsDiff = tmpAbsDiff;
                    if(pairs.size() > 0 && pairs.get(pairs.size()-1).getAbsDiff() > minAbsDiff){
                    	pairs.remove(pairs.size()-1);
                    }
                    pairs.add(tmpPair);
                }  
	        }
	        elapsedTime = System.currentTimeMillis() - startTime;
	        System.out.println("\n(2)Total elapsed time:  " + (elapsedTime/1000.0) + " seconds.\n");
	        int count = 0;
	        startTime = System.currentTimeMillis();
	        for(Pair pair : pairs){
	            if(pair.getAbsDiff() == minAbsDiff){
	            	if(count > 0){
	            		System.out.print(" ");
	            	}
	                System.out.print(pair.toString());
	                count++;
	            }
	        }
	        elapsedTime = System.currentTimeMillis() - startTime;
	        System.out.println("\n(3)Total elapsed time:  " + (elapsedTime/1000.0) + " seconds.\n");
	        in.close();
	    }
	  
	    public static class Pair{
	        private int a;
	        private int b;
	        private int absDiff;
	        public Pair(int a,int b){
	            this.a = a;
	            this.b = b;
	            absDiff = Math.abs(b-a);         
	        }
	        public int getAbsDiff(){
	            return absDiff;
	        }
	        public String toString(){
	            return a + " " + b;
	        }
	    }
	
}
