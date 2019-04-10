package com.fcb;

public class SpiralArray {

    public static void main(String args []){
        spiralPrinter(spiral(0));
        spiralPrinter(spiral(1));
        spiralPrinter(spiral(2));
        spiralPrinter(spiral(3));
        spiralPrinter(spiral(4));
        spiralPrinter(spiral(8));
        spiralPrinter(spiral(16));
    }

    public static int[][] spiral(int n){
        if(n <= 0){
            return null;
        }
        int [][] spiral = new int [n][n];

        int rowIndex = 0;
        int columnIndex = 0;
        int nextDirection = 0;
        for(int count=1;count <= n*n;count++){
            spiral[rowIndex][columnIndex] = count;
            /*
            calculate next matrix position
             0. move to right
             1. then down
             2. then left
             3. then up (keep moving up until end)
            * */
            if(columnIndex+1 < n && spiral[rowIndex][columnIndex+1] == 0 && nextDirection == 0){
                columnIndex++;
            }else if(rowIndex+1 < n && spiral[rowIndex+1][columnIndex] == 0 && nextDirection == 0){
                rowIndex++;
            }else if(columnIndex-1 >= 0 && spiral[rowIndex][columnIndex-1] == 0 && nextDirection == 0){
                columnIndex--;
            }else if(rowIndex-1 >= 0 && spiral[rowIndex-1][columnIndex] == 0 && (nextDirection == 3 || nextDirection == 0)){
                rowIndex--;
                nextDirection = rowIndex-1 >= 0 && spiral[rowIndex-1][columnIndex] == 0 ? 3 : 0;
            }
        }

        return spiral;
    }

    public static void spiralPrinter(int[][] multiArray){
        if(multiArray != null){
            System.out.println("N" + multiArray.length);
            for(int [] array : multiArray){
                for(int i=0;i<array.length;i++){
                    String space = i < array.length-1 ? " " : "";
                    System.out.print(array[i] + space);
                }
                System.out.println();
            }
        }else{
            System.out.println("Invalid array!");
        }
    }
}
