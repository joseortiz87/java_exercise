package com.hired;

public class LargestSubstring {

    public static void main(String args []){
        System.out.println(solution("abcdefbbbbabcdefg"));
    }

    public static long solution(String s){
        int n = s.length();
        int prev_index = 1;
        int currentSubstringLength = 0;
        int maxSubstringLength = 0;
        int [] visited = new int [255];
        // init visited to -1
        for(int i=0;i<visited.length;i++){
            visited[i] = -1;
        }
        visited[s.charAt(0)] = 0;
        for(int i=1;i<n;i++){
            prev_index = visited[s.charAt(i)];
            if(prev_index == -1 || i - currentSubstringLength > prev_index) {
                currentSubstringLength++;
            }else{
                if(currentSubstringLength > maxSubstringLength){
                    maxSubstringLength = currentSubstringLength;
                }
                currentSubstringLength = i - prev_index;
            }
            visited[s.charAt(i)] = i;
        }
        if(currentSubstringLength > maxSubstringLength){
            maxSubstringLength = currentSubstringLength;
        }
        return maxSubstringLength;
    }
}
