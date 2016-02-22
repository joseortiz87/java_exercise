package mx.com.joortizs.main;

import java.util.*;

public class AngryProfessor {

	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int testCases = in.nextInt();
		for(int i=0;i<testCases;i++){
			int students = in.nextInt();
			int minStudents = in.nextInt();
			int studentsOnTime = 0;
			for(int j=0;j<students;j++){
				int tmpTime = in.nextInt();
				if(tmpTime <= 0){
					studentsOnTime++;
				}
			}
			if(studentsOnTime >= minStudents){
				System.out.println("NO");
			}else{
				System.out.println("YES");
			}
		}
		
		in.close();
	}
	
}
