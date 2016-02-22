package mx.com.joortizs.test;

public class StringSplit {

	public static void main(String[] args){
		String str = "done|{}|hecho|52323|";
		String [] split = str.split("\\|");
		for(String a : split){
			System.out.println(a);
		}
	}
	
}
