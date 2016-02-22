package mx.com.joortizs.main;

import java.util.*;

public class RocksCompositions {
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int rocks = in.nextInt();
        int gemElements = 0;
        List<String> compositions = new ArrayList<String>();
        Map<Character,Boolean> map = new HashMap<Character,Boolean>();
        /*GET ALL ROCKS COMPOSITIONS*/
        for(int i=0;i<rocks;i++){
            compositions.add(in.next());
        }
        for(int j=0;j<rocks;j++){
            String tmpComposition = compositions.remove(0);
            for(int k=0;k<tmpComposition.length();k++){
                char tmpChar = tmpComposition.charAt(k);
                int count = 1;
                Boolean isgem = map.get(tmpChar);
                /*TAKE CARE OF REPEAT CHARS*/
                if(isgem == null){
	                for(String otherCompositions : compositions){
	                    if(otherCompositions.indexOf(tmpChar) >= 0){
	                    	count++;
	                    }
	                }
	                if(count == rocks){
	                	map.put(tmpChar,new Boolean(true));
	                }else{
	                	map.put(tmpChar,new Boolean(false));
	                }
                }
            }
            compositions.add(tmpComposition);
        }
        
        for(Map.Entry<Character, Boolean> entry : map.entrySet()){
        	if(entry.getValue()){
                gemElements++;
            }
        }
        System.out.println(gemElements);
        in.close();
    }
}
