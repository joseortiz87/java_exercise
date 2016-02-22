package mx.com.joortizs.test;

import java.io.*;
import java.util.*;

public class SortingTest {

	public static void main(String args[]){
		int t = 200000;
		Random rand = new Random();
		File file = new File("E:/Usuarios/joortizs/wrksOpenShift2/testSorting.txt");
		
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		FileWriter fw = null;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(t + "\n");
			for(int i=0;i<t;i++){
				if(i > 0){
					bw.write(" ");
				}
				bw.write((rand.nextInt(20000001)-10000000) + "");
				bw.flush();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DONE!");
	}
	
}
