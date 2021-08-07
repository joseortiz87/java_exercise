package com.amazon.aws;

public class MatrixDecoder {
    public static void main(String args []){
        System.out.println(decodeString(3,"mnes__ya_____mi"));
    }

    public static String decodeString(int numberOfRows,String encodeString){
        if(numberOfRows == 1 || encodeString.length() == 0) return encodeString;
        int numberOfColumns = encodeString.length() / numberOfRows;
        String[][] matrix = new String[numberOfRows][numberOfColumns];
        int row = 0;
        int column = 0;
        StringBuilder decodeBuilder = new StringBuilder();
        encodeString = encodeString.trim();
        // O(n)
        for(int i=0;i<encodeString.length();i++){
            Character tmpChar = encodeString.charAt(i);
            matrix[row][column] = tmpChar != '_' ? tmpChar.toString() : " ";
            if(column == numberOfColumns -1) {
                column = 0;
                row++;
            } else{
                column++;
            }
        }
        // O(n/r * r) ~ O(n)
        for(int i=0;i<numberOfColumns;i++){
            int rowIndex = 0;
            int columnIndex = i;
            while(rowIndex < numberOfRows && columnIndex < numberOfColumns){
                decodeBuilder.append(matrix[rowIndex][columnIndex]);
                rowIndex++;
                columnIndex++;
            }
        }
        return decodeBuilder.toString().trim();
    }
}
