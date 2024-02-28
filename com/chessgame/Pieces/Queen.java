package com.chessgame.Pieces;
import Login_Form.Login;
import com.chessgame.Board.Board;
import com.chessgame.Board.Move;

import javax.swing.*;

public class Queen extends Piece {

	public Queen(int x, int y, boolean iswhite, Board board, int value) {
		super(x, y, iswhite, board, value);
//		this.pieceImage = PieceImages.QUEEN;
	}
	public Queen(boolean isWhite){
		super(isWhite);
		if (Login.theme)
			image = new ImageIcon(isWhite ? "wqn.png" : "bqn.png");
		else
			image = new ImageIcon(isWhite ? "wq.png" : "bq.png");
	}
	//detecting the image of the piece(queen)
	@Override
	public void initializeSide(int value){
		super.initializeSide(value);
		if(isWhite()) {
			image = PieceImages.wq;
		}
		else {
			image = PieceImages.bq;
		}
	}


	@Override
	public boolean canMove(int x, int y, Board board) {
			
			if(board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == isWhite()) {
				cannotMove(x,y,board);
				return false;
			}
		return queenMove(x,y,board);

	}

	public boolean queenMove(int x, int y, Board board){
		if(xCord == x && yCord ==y) return false;
		if(Math.abs(x-xCord) == Math.abs( y-yCord)) {
			return (queenMovesDiagonal(x, y, board));
		}
		if( x == xCord  || y == yCord ) {
			return queenMovesStraight(x, y, board);
		}
		return false;
	}
	
	public boolean queenMovesStraight(int x,int y, Board board) {
			// to move straight up
			if(x == xCord && (y<yCord )) {
					for(int i=yCord-1; i>y;i--) {
						if(board.getXY(x, i) != 0) {
							return false;
						}
					}
					return true;
			}
			// to move straight down
			if(x == xCord && (y>yCord )) {
				for(int i=yCord+1; i<y;i++) {
					if(board.getXY(x, i) != 0) {
						return false;
					}
				}
				return true;
			}
			// to move straight right
			if(y == yCord && (x>xCord )) {
				for(int i=xCord+1; i<x;i++) {
					if(board.getXY(i, y) != 0) {
						return false;
					}
				}
				return true;
			}
			// to move straight right
			if(y == yCord && (x<xCord )) {
				for(int i=xCord-1; i>x;i--) {
					if(board.getXY(i, y) != 0) {
						return false;
					}
				}
				return true;
			}

		return false;
	}

	public boolean queenMovesDiagonal(int x,int y, Board board) {
		// to move diagonally right-down
		if(x > xCord && y > yCord) {
			int j = yCord+1;
			for(int i=xCord+1; i<x; i++) {
				if(board.getXY(i, j) != 0) {
					return false;
				}
				j++;
			}
		}
		// to move diagonally left-up
		else if(x < xCord && y < yCord) {
			int j = yCord-1;
			for(int i=xCord-1; i>x; i--) {
				if(board.getXY(i, j) != 0) {
					return false;
				}
				j--;
			}
		}
		// to move diagonally right-up
		else if(x > xCord && y < yCord) {
			int j = yCord - 1;
			for(int i=xCord+1; i<x; i++) {
				if(board.getXY(i, j) != 0) {
					return false;
				}
				j--;
			}
		}
		// to move diagonally left-down
		else if(x < xCord && y > yCord) {
			int j = yCord+1;
			for(int i=xCord-1; i>x; i--) {
				if(board.getXY(i, j) != 0) {
					return false;
				}
				j++;
			}
		}

		return true;
	}
	public void cannotMove(int x, int y, Board board){
		if(queenMove(x,y,board))
			this.cannotMove.add(new Move(this.xCord,this.yCord,x,y,board.getPiece(x,y)));
	}
}
