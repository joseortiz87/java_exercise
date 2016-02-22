package mx.com.joortizs.main;

import java.util.*;

public class MatrixRotation {

	public static void main(String args []){
		/* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		Scanner in = new Scanner(System.in);
		int numberRows = in.nextInt();
		int numberColumns = in.nextInt();
		long rotations = in.nextLong();
		int [][] matrix = new int[numberRows][numberColumns];
		for(int i=0;i<numberRows;i++){
			for(int j=0;j<numberColumns;j++){
				matrix[i][j] = in.nextInt();
			}
		}
		
		//Matrix rotation
		matrix = rotation(matrix,numberRows,numberColumns,rotations);
		
		//Print matrix
		for(int m=0;m<numberRows;m++){
			StringBuilder strTmp = new StringBuilder();
			for(int n=0;n<numberColumns;n++){
				strTmp.append(matrix[m][n] + " ");
			}
			System.out.println(strTmp.toString().trim());
		}
		
		in.close();
	}
	
	private static int [][] rotation(int [][] matrix,int numberRows,int numberColumns,long rotations){
		int [][] roratedMatrix = new int[numberRows][numberColumns];
		int diagonal = 0;
		boolean isEndDiagonal = false;
		do{
			int minRowIndex = diagonal;
			int maxRowIndex = (numberRows-1)-diagonal;
			int minColumnIndex = diagonal;
			int maxColumnIndex = (numberColumns-1)-diagonal;
			int row = minRowIndex;
			int column = minColumnIndex;
			
			if(maxRowIndex > minRowIndex && maxColumnIndex > minColumnIndex){
				LinkedList<Integer> square = new LinkedList<Integer>();
				do{
					square.addLast(matrix[row][column]);
					if(column == minColumnIndex && row < maxRowIndex){
						row++;
					}else if(row == maxRowIndex && column < maxColumnIndex){
						column++;
					}else if(column == maxColumnIndex && row > minRowIndex){
						row--;
					}else if(row == minRowIndex && column > minColumnIndex){
						column--;
					}
				}while(row != diagonal || column != diagonal);
				for(long rota=0;rota<rotations%square.size();rota++){
					Integer tmp = square.removeLast();
					square.addFirst(tmp);
				}
				do{
					roratedMatrix[row][column] = square.removeFirst();
					if(column == minColumnIndex && row < maxRowIndex){
						row++;
					}else if(row == maxRowIndex && column < maxColumnIndex){
						column++;
					}else if(column == maxColumnIndex && row > minRowIndex){
						row--;
					}else if(row == minRowIndex && column > minColumnIndex){
						column--;
					}
				}while(row != diagonal || column != diagonal);
				diagonal++;
			}else{
				isEndDiagonal = true;
			}
		}while(!isEndDiagonal);
		matrix = roratedMatrix;
		return matrix;
	}	
}
