package mx.com.joortizs.main;

import java.util.*;

public class ScherlockBeast {

	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int testCases = in.nextInt();
		for(int i=0;i<testCases;i++){
			int digits = in.nextInt();
			if(digits <= 2){
				System.out.println(-1);
			}else{
				int count5 = 0;
				int count3 = digits;
				int beastNumber5 = 0;
				int beastNumber3 = 0;
				for(int k=-1;k<digits;k++){
					if(k != -1){
						count3--;
						count5++;
					}
					if(count3%5 == 0 && count5%3 == 0){
						beastNumber5 = count5;
						beastNumber3 = count3;
					}
				}
				if((beastNumber5+beastNumber3) == digits){
					System.out.println(getString4Digits(beastNumber3,beastNumber5));
				}else{
					System.out.println(-1);
				}
			}
		}
		in.close();
	}
	
	public static String getString4Digits(int number3,int number5){
		StringBuffer buffer = new StringBuffer();
		for(int j=0;j<number5;j++){
			buffer.append("5");
		}
		for(int i=0;i<number3;i++){
			buffer.append("3");
		}
		return buffer.toString();
	}
	
}
