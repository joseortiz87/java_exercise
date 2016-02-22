package mx.com.joortizs.main;

import java.util.*;

public class Encryption1 {

	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        String sentence = in.nextLine().trim().replaceAll(" ", "");
        int l = sentence.length();
        double lFloor = Math.floor(Math.sqrt(l));
        double lCeil = Math.ceil(Math.sqrt(l));
        int minRows = (int)lCeil;
        int minColumns = (int)lCeil;
        StringBuilder encrytp = new StringBuilder();
        for(int rows=(int)lFloor;rows<=lCeil;rows++){
        	for(int columns=rows;columns<=lCeil;columns++){
        		if(rows >= lFloor && columns >= rows && lCeil >= columns){
        			if((rows*columns) < (minRows*minColumns) && (rows*columns) >= l){
        				minRows = rows;
            			minColumns = columns;
        			}
        		}
        	}
        }
        
        for(int i=0;i<minColumns;i++){
        	for(int j=0;j<minRows;j++){
        		if((j*minColumns+i) <= l-1){
        			encrytp.append(sentence.charAt(j*minColumns+i));
        		}
        	}
        	encrytp.append(" ");
        }
        System.out.println(encrytp.toString());
        
        in.close();
    }
	
}
