package com.chessgame.Pieces;

import Login_Form.Login;
import com.chessgame.Board.*;

import javax.swing.*;

public class Bishop extends Piece {

	public Bishop(int x, int y, boolean isWhite, Board board, int value) {
		super(x, y, isWhite, board, value);
//		this.pieceImage = PieceImages.BISHOP;
	}
	public Bishop(boolean isWhite){
		super(isWhite);
		if (Login.theme)
			image = new ImageIcon(isWhite ? "wbn.png" : "bbn.png");
		else
			image = new ImageIcon(isWhite ? "wb.png" : "bb.png");
	}
	//detecting the image of the piece(bishop)
	public void initializeSide(int value) {
		super.initializeSide(value);
		if (isWhite()) {
			image = PieceImages.wb;
		} else {
			image = PieceImages.bb;
		}
	}

	@Override
	public boolean canMove(int x, int y, Board board) {

		if (board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == isWhite()) {
			cannotMove(x,y,board);
			return false;
		}
		return bishopMoves(x, y, board);
	}
	public boolean bishopMoves(int x, int y, Board board) {

		int i = Math.abs(x - xCord);
		int j = Math.abs(y - yCord);
		if (i == j && i<=3 && i !=0) {
			return true;
		}
		// to move horizontally
		if (j == 0) {
			if (i == 1) {
				if (board.getPiece(x, y) == null || board.getPiece(x, y).isWhite() == isWhite()) {
					return true;
				} else {
					return board.getPiece(x, y).isWhite() != isWhite();
				}
			} else return false;
		}
		return false;
	}
	public void cannotMove(int x, int y, Board board){
		if(bishopMoves(x,y,board))
			this.cannotMove.add(new Move(this.xCord,this.yCord,x,y,board.getPiece(x,y)));
	}
}