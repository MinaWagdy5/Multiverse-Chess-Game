package com.chessgame.Pieces;
import Login_Form.Login;
import com.chessgame.Board.Board;
import com.chessgame.Board.Move;

import javax.swing.*;

public class Rook extends Piece {

	private boolean hasMoved;
	private boolean justMoved = false;	//try to delete it and replace it by hasMoved

	public Rook(int x, int y, boolean isWhite, Board board, int value) {
		super(x, y, isWhite, board, value);
		hasMoved = false;	//to detect if it's possible to castle
//		this.pieceImage = PieceImages.ROOK;
	}
	public Rook(boolean isWhite){
		super(isWhite);
		if (Login.theme)
			image = new ImageIcon(isWhite ? "wrn.png" : "brn.png");
		else
			image = new ImageIcon(isWhite ? "wr.png" : "br.png");
	}
	//to get the image of piece(rook)
	public void initializeSide(int value){ //to plot the rook after initializing its side(black/white)
		super.initializeSide(value);
		if(isWhite()) {
			image = PieceImages.wr;
		}
		else {
			image = PieceImages.br;
		}
	}
	
	@Override
	public boolean makeMove(int toX, int toY, Board board) {
		if(super.makeMove(toX, toY, board)) {
			if(!hasMoved) {
				justMoved = true;
			}else {
				justMoved = false;
			}
			hasMoved = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean canMove(int x, int y, Board board) {

		if (board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == isWhite()) {
			cannotMove(x,y,board);
			return false;
		}
		return rookMove(x,y,board);
	}
	public boolean rookMove(int x, int y, Board board){
		//	to move up
		if (x == xCord && (y < yCord)) {
			for (int i = yCord - 1; i > y; i--) {
				if (board.getXY(x, i) != 0) {
					return false;
				}
			}
			return true;
		}
		// to move down
		if (x == xCord && (y > yCord)) {
			for (int i = yCord + 1; i < y; i++) {
				if (board.getXY(x, i) != 0) {
					return false;
				}
			}
			return true;
		}
		//	to move right
		if (y == yCord && (x > xCord)) {
			for (int i = xCord + 1; i < x; i++) {
				if (board.getXY(i, y) != 0) {
					return false;
				}
			}
			return true;
		}
		//	to move left
		if (y == yCord && (x < xCord)) {
			for (int i = xCord - 1; i > x; i--) {
				if (board.getXY(i, y) != 0) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	public void cannotMove(int x, int y, Board board){
		if(rookMove(x,y,board)) {
			this.cannotMove.add(new Move(this.xCord, this.yCord, x, y, board.getPiece(x, y)));
		}
	}
	
	public void castleDone(int x, Board board) {
		if(x == 6) {
			board.updatePieces(xCord, yCord, x-1, yCord,this);
			xCord = x-1;
		}
		else {
			board.updatePieces(xCord, yCord, x+1, yCord,this);
			xCord = x+1;
		}
		hasMoved = true;
	}
	
	public boolean HasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	public boolean isJustMoved() {
		return justMoved;
	}

	public void setJustMoved(boolean justMoved) {
		this.justMoved = justMoved;
	}

	public void setIMage(ImageIcon iMage){
		this.image = iMage;
	}
}
