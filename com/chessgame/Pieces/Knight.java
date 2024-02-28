package com.chessgame.Pieces;
import Login_Form.Login;
import com.chessgame.Board.Board;
import com.chessgame.Board.Move;

import javax.swing.*;

public class Knight extends Piece{
	
	public Knight(int x, int y, boolean isWhite, Board board, int value) {
		super(x, y, isWhite, board, value);                                 // calling the constructor from Piece class
	}

	public Knight(boolean isWhite){
		super(isWhite);                                    // calling constructor from Piece class
		if(Login.theme)
			image = new ImageIcon(isWhite ? "wnn.png" : "bnn.png");
		else
			image = new ImageIcon(isWhite ? "wn.png" : "bn.png");
	}

	// to get the image of piece(knight)
	@Override
	public void initializeSide(int value){
		super.initializeSide(value);
		if(isWhite()) {
			image = PieceImages.wn;
		}
		else {
			image = PieceImages.bn;
		}
	}
	// to detect possible moves
	public boolean canMove(int x ,int y, Board board) {
		if((board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == isWhite())) {    // if the place the knight want to go to has a piece on it and it has the same color
				cannotMove(x,y,board);                // cannot move method is called
				return false;
		}
		return knightMove(x,y,board);                 // the knight can move so KnightMove method is called
	}
	public boolean knightMove(int x,int y,Board board){
		int i = Math.abs(x-xCord);                     // get the absolute of the difference in x axis
		int j = Math.abs(y-yCord);                     // get the absolute of the difference in y axis

		if(i == 2 && j == 3) return true;
		return (i == 3 && j == 2);
	}
	public void cannotMove(int x, int y, Board board){
		if(knightMove(x,y,board))                                // if the piece of the same color on the square the knight can move to
			this.cannotMove.add(new Move(this.xCord,this.yCord,x,y,board.getPiece(x,y)));      // add to the list cannotMove the move which is impossible to the knight
	}
}
