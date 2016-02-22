package mx.com.joortizs.theads;

import java.util.ArrayList;

public class ThreadTest1 {

	private static int number;
	private static int divisors;
	private static final int theads = 10;
	
	synchronized private static void updateMaxDivisor(int num,int div){
		if(div >= divisors){
			number = num;
			divisors = div;
			System.out.println("New max divisor " + number + " with " + divisors + " divisors.");
		}
	} 
	
	private static int findDivisors(int number){
		int divisorCount = 0;
		for(int j=1;j<=number;j++){
			if(number%j == 0){
				divisorCount++;
			}
		}
		return divisorCount;
	}
	
	private static class FindMaxDivisorsThread extends Thread {
        int min, max;
        public FindMaxDivisorsThread(int min, int max) {
            this.min = min;
            this.max = max;
        }
        public void run() {
        	System.out.println(getId() + "- Run (" + min + "-" + max + ").");
            for(int i=min;i<=max;i++){
            	int tmpDivisor = findDivisors(i);
            	updateMaxDivisor(i,tmpDivisor);
            }
        }
    }
	
	public static void main(String args[]){
		int min = 1;
		int max = 10000;
		int step = (int)((max+1-min)/theads);
		ArrayList<FindMaxDivisorsThread> theadsList = new ArrayList<FindMaxDivisorsThread>();
		for(int i=min;i<max; i=i+step){
			theadsList.add(new FindMaxDivisorsThread(i,i+step-1));
		}
		long startTime = System.currentTimeMillis();
		for(FindMaxDivisorsThread theadMaxDivisor : theadsList){
			theadMaxDivisor.run();
		}
		for(FindMaxDivisorsThread theadMaxDivisor : theadsList){
			while(theadMaxDivisor.isAlive()){
				try {
					theadMaxDivisor.join();
                }
                catch (InterruptedException e) {
                }
			}
		}
	    long elapsedTime = System.currentTimeMillis() - startTime;
	    System.out.println("Max divisor " + number + " with " + divisors + " divisors.");
	    System.out.println("\nTotal elapsed time:  " + (elapsedTime/1000.0) + " seconds.\n");
	}
	
}
