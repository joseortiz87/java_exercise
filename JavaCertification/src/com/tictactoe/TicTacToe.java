package com.tictactoe;

import java.util.Arrays;

public class TicTacToe {

	private char [][] grid;
	private int x;
	private int y;
	
	public TicTacToe(int x,int y){
		this.grid = new char [x][y];
		this.x = x;
		this.y = y;
		//Init Grid
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				this.grid[i][j] = '-';
			}
		}
	}
	
	public boolean addToken(int x,int y,char value){
		if(x >= this.x || y >= this.y){
			return false;
		}
		if(this.grid[x][y] != '-'){
			return false;
		}
		this.grid[x][y] =  value;
		return true;
	}

	public void printBoard() {
		for(char [] rows : grid){
			int j = 0;
			for(char value : rows){
				System.out.print(value);
				if(j < y-1){
					System.out.print('|');	
				}
				j++;
			}
			System.out.println("");
		}
	}
}
