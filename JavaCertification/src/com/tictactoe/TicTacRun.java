package com.tictactoe;

public class TicTacRun {

	public static void main(String args []){
		TicTacToe ticTacToe = new TicTacToe(3,3);
		ticTacToe.addToken(0, 1,'X');
		ticTacToe.printBoard();
	}
}
