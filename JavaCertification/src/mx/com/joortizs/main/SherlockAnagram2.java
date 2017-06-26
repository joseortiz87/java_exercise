package mx.com.joortizs.main;
import java.util.*;

public class SherlockAnagram2 {
	
    static int sherlockAndAnagrams(String s){
        // Complete this function
        LinkedHashMap<String,Integer> subStringMap = new LinkedHashMap<String,Integer>();
        Integer globalSum = 0;
        //CALCULATE PAIRS
        for(int i = 0;i<s.length();i++){
            for(int j=i;j<s.length();j++){
            	addToMap(s.substring(i,j+1),subStringMap);
            }
        }
        for(String key : subStringMap.keySet()){
        	if(subStringMap.get(key) > 1){
        		int k = subStringMap.get(key);
        		int n = k-1;
        		globalSum += (2*k*n-(n*n)-n)/(2);
        	}
        }
        return globalSum;
    }
    
    static void addToMap(String str,LinkedHashMap<String,Integer> subStringMap){
    	//SORT STRING
    	char [] subStringChars = str.toCharArray();
        Arrays.sort(subStringChars);
        str = new String(subStringChars);
        if(subStringMap.containsKey(str)){
            Integer count = subStringMap.get(str);
            subStringMap.put(str,++count);
        }else{
            subStringMap.put(str,new Integer(1));
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            String s = in.next();
            int result = sherlockAndAnagrams(s);
            System.out.println(result);
        }
        in.close();
    }
	
}
