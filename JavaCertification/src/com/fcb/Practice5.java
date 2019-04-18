package com.fcb;

public class Practice5 {

    public static void main(String args[]){
        System.out.println(numberOfWays("12345"));
    }

    public static int numberOfWays(String code){
        return helper(code,code.length(),new Integer[code.length()]);
    }

    public static int helper(String data,int k,Integer [] memory){
        if(k == 0){
            return 1;
        }
        int s = data.length() - k;
        if(data.charAt(s) == '0'){
            return 0;
        }
        int result;
        if(memory[k-1] != null){
            result = memory[k-1];
        }else{
            result = helper(data,k-1,memory);
            memory[k-1] = result;
        }
        if( k >= 2 && Integer.parseInt(data.substring(s,s+2)) <= 26 ){
            int tempRes;
            if(memory[k-2] != null){
                tempRes = memory[k-2];
            }else{
                tempRes = helper(data,k-2,memory);
                memory[k-2] = tempRes;
            }
            result += tempRes;
        }
        return result;
    }

}
