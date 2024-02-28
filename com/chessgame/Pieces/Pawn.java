package com.chessgame.Pieces;
import com.chessgame.Board.Board;
import com.chessgame.Board.Move;
import com.chessgame.Game.Game;

import javax.swing.*;

public class Pawn extends Piece {
	private boolean firstMove;
	private boolean moved2Squares = false;
	public Pawn(int x, int y, boolean isWhite, Board board, int value) {
		super(x, y, isWhite, board, value);
		if(y == 6 && isWhite || y == 1 && !isWhite) firstMove = true;	//to detect if it can move 2 or 1 step
	}
	public Pawn(boolean isWhite){
		super(isWhite);
		image = new ImageIcon(isWhite ? "wp.png" : "bp.png");
	}

	//detecting the image of the piece(pawn)
	public void initializeSide(int value){
		super.initializeSide(value);
		if(isWhite()) {	//try to use (value>0) for this condition
			image = PieceImages.wp;
		}
		else {
			image = PieceImages.bp;
		}
	}

	@Override
	public boolean makeMove(int toX, int toY, Board board) {
		Move move = new Move(xCord, yCord, toX, toY, this);
		if(!alive()) {	// remove if not useful
			return false;
		}
		//	to capture after enpassant move
		if(moves.contains(move)) {
			if(toX == xCord + 1 && yCord -(isWhite ? 1 : -1) == toY) {
				if(board.getXY(toX, toY) == 0) {
					board.deadPieces.add(board.getPiece(xCord + 1, yCord));
					Game.AllPieces.remove(board.getPiece(xCord + 1, yCord));
					Game.fillPieces();
					board.setPieceIntoBoard(xCord + 1, yCord, null);

				}
			}
			if(toX == xCord -1 && yCord -(isWhite ? 1 : -1) == toY) {
				if(board.getXY(toX, toY) == 0) {
					board.deadPieces.add(board.getPiece(xCord - 1, yCord));
					Game.AllPieces.remove(board.getPiece(xCord - 1, yCord));
					Game.fillPieces();
					board.setPieceIntoBoard(xCord - 1, yCord, null);
				}
			}

			//when the pawn make 2 steps
			//used Math.abs() to make this condition work for both black&white
			if(firstMove && Math.abs((yCord - toY)) == 2){
				moved2Squares = true;
			}
			removeEnpassant();
			board.updatePieces(xCord, yCord, toX, toY,this);
			xCord = toX;
			yCord = toY;
			firstMove = false;
			return true;
		}
		return false;
	}


	private void removeEnpassant() {	//	to remove en-passant if it's not used after the 2 steps move of the enemy pawn
		for(Piece p: Game.AllPieces) {
			if(p instanceof Pawn && p != this) {
				((Pawn) p ).setMoved2Squares(false);
			}
		}
	}

	public boolean madeToTheEnd() {
		if(isWhite && yCord == 0) {
			return true;
		}

		if(!isWhite && yCord == 7) {
			return true;
		}
		return false;
	}

	//return true for possible moves
	public boolean canMove(int x, int y, Board board) {
		int enpassant = isWhite ? -1 : 1;
		if(xCord > 0 && xCord  < 7) {
			if(board.getXY(xCord + 1, yCord) == enpassant) {
				Pawn rightPawn = (Pawn) board.getPiece(xCord + 1, yCord);
				if(x == rightPawn.getXcord() && y == rightPawn.getYcord() + enpassant && rightPawn.isMoved2Squares()) {
					return true;
				}
			}
			if(board.getXY(xCord - 1, yCord) == enpassant) {
				Pawn leftPawn = (Pawn) board.getPiece(xCord - 1, yCord);
				if(x == leftPawn.getXcord() && y == leftPawn.getYcord() + enpassant && leftPawn.isMoved2Squares()) {
					return true;
				}
			}
		}
		if (xCord == 0){
			if(board.getXY(xCord + 1, yCord) == enpassant) {
				Pawn rightPawn = (Pawn) board.getPiece(xCord + 1, yCord);
				if(x == rightPawn.getXcord() && y == rightPawn.getYcord() + enpassant && rightPawn.isMoved2Squares()) {
					return true;
				}
			}
		}
		if (xCord == 7){
			if(board.getXY(xCord - 1, yCord) == enpassant) {
				Pawn leftPawn = (Pawn) board.getPiece(xCord - 1, yCord);
				if(x == leftPawn.getXcord() && y == leftPawn.getYcord() + enpassant && leftPawn.isMoved2Squares()) {
					return true;
				}
			}
		}

		//something blocking the way of the same color(team)
		if ((board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == isWhite())) {
			cannotMove(x,y,board);
			return false;
		}

		//can't move diagonally if it isn't  for capture
		if (xCord != x && board.getPiece(x, y) == null) {
			return false;
		}

		if (isWhite) {

			//move two square at beginning
			//(board.getPiece(x, y) == null) this condition is to check if there isa piece in front of the pawn
			//(board.getPiece(x, y + 1) == null) this condition is to make sure the pawn will move 2 steps

			if (firstMove) {

				if (x == xCord && (y == yCord - 1 || y == yCord - 2) && board.getPiece(x, y) == null && board.getPiece(x, y + 1) == null) {
					return true;
				}
			}
			//move forward 1 tile(square)
			if (x == xCord && y == yCord - 1 && board.getPiece(x, y) == null) {
				return true;
			}
			return capture(x, y, board);

		}
		else {
			if (firstMove) {
				if (x == xCord && (y == yCord + 1 || y == yCord + 2) && board.getPiece(x, y) == null && board.getPiece(x, y - 1) == null) {
					return true;
				}
			}
			if (x == xCord && y == yCord + 1 && board.getPiece(x, y) == null) {
				return true;
			}

			return capture(x, y, board);
		}
	}

	//to capture
	public boolean capture(int x, int y, Board board) {
		if(isWhite()) {
			if (y == yCord - 1 && x == xCord + 1 || y == yCord - 1 && x == xCord ) {
				return true;
			}
			return y == yCord - 1 && x == xCord - 1;
		}
		else {
			if (y == yCord + 1 && x == xCord + 1 || y == yCord + 1 && x == xCord ) {
				return true;
			}
			return y == yCord + 1 && x == xCord - 1;
		}
	}
	public void cannotMove(int x, int y, Board board){
		if(capture(x,y,board))
			this.cannotMove.add(new Move(this.xCord,this.yCord,x,y,board.getPiece(x,y)));
	}

//	public void removeEnpassantCapturedPiece(int x, int y) {
//
//	}
//
//	public boolean isFirstMove() {
//		return firstMove;
//	}

	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}

	public boolean isMoved2Squares() {
		return moved2Squares;
	}

	public void setMoved2Squares(boolean moved2Squares) {
		this.moved2Squares = moved2Squares;
	}
}
