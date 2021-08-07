package com.fcb;

import java.util.*;

public class FloodFill {
    public static void main(String args[]){
        int[][] image = new int[][]{
                {1,1,1},
                {1,1,0},
                {1,0,1}
        };
        System.out.println(floodFill(image,1,1,2));
    }
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(sr >= image.length || sc >= image[sr].length ) return image;
        Map<String,Boolean> visited = new HashMap<>();
        floodFillHelper(image,sr,sc,image[sr][sc],newColor,visited);
        return image;
    }
    public static void floodFillHelper(int[][] image,int i,int j,int oldColor,int newColor,Map<String,Boolean> visited){
        if(i < 0 || j < 0 || i >= image.length || j >= image[i].length) return;
        if(visited.containsKey(i+"_"+j)) return;
        if(image[i][j] != oldColor) return;
        image[i][j] = newColor;
        visited.put(i+"_"+j,true);
        System.out.println(i+"_"+j+"-"+image[i][j]);
        floodFillHelper(image,i-1,j,oldColor,newColor,visited); //up
        floodFillHelper(image,i+1,j,oldColor,newColor,visited); //down
        floodFillHelper(image,i,j-1,oldColor,newColor,visited); //left
        floodFillHelper(image,i,j+1,oldColor,newColor,visited); //right
    }
}
