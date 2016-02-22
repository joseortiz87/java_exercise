package mx.com.joortizs.main;

import java.util.*;

public class HourGlass {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int arr[][] = new int[6][6];
        for(int i=0; i < 6; i++){
            for(int j=0; j < 6; j++){
                arr[i][j] = in.nextInt();
            }
        }
        ArrayList<Integer> hourglass = new ArrayList<Integer>();
        for(int k=0;k<=3;k++){
            for(int m=0;m<=3;m++){
                int sum = 0;
                for(int column=0;column<3;column++){
                    sum += arr[k][m+column];
                    sum += arr[k+2][m+column];
                }
                sum += arr[k+1][m+1];
                hourglass.add(sum);
            }
        }
        Collections.sort(hourglass);
        System.out.println(hourglass.get(hourglass.size()-1));
        in.close();
    }
	
}
