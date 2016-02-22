package mx.com.joortizs.main;


public class Test{
	
	public static void main(String [] args){
		int[] a = {1};
		Test t = new Test();
		t.increment(a);
		System.out.println(a[a.length-1]);
	}
	
	void increment(int[] i){
		i[i.length-1]++;
	}
	
}
