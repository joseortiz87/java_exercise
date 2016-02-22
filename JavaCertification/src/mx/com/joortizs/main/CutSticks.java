package mx.com.joortizs.main;

import java.util.*;

public class CutSticks {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int stickLength = in.nextInt();
        List<Integer> stick = new ArrayList<Integer>();
        Integer minVal = 0;
        for(int i=0;i<stickLength;i++){
            stick.add(in.nextInt());
        }
        Collections.sort(stick);
        while(stick.size() > 0){
            System.out.println(stick.size());
            minVal = stick.remove(0);
            final int currentSize = stick.size();
            for(int j=0;j<currentSize;j++){
                if(stick.get(0).equals(minVal)){
                    stick.remove(0);
                }else{
                    break;
                }
            }
        }
        in.close();
    }
	
}
