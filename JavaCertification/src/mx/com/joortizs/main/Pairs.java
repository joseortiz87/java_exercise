package mx.com.joortizs.main;

import java.util.*;

public class Pairs {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long k = in.nextLong();
        int count = 0;
        HashMap<Long,Integer> map = new HashMap<Long,Integer>();
        ArrayList<Long> array = new ArrayList<Long>();
        while(n > 0){
        	Long temp = new Long(in.nextLong());
            array.add(temp);
            map.put(temp, 1);
            n--;
        }
        
        for(Long number : array ){
        	if(map.containsKey(new Long(number+k))){
        		count++;
        	}
        }
        System.out.println(count);
        in.close();
    }
}
