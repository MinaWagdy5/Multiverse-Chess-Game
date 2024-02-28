package com.chessgame.Pieces;
import Login_Form.Login;
import com.chessgame.Board.Board;
import com.chessgame.Board.Move;
import com.chessgame.Board.PopupExample;
import com.chessgame.Game.Game;

public class King extends Piece {
	private boolean hasMoved;
	private boolean justMoved = false;
	private Rook rook = null;
	
	public King(int x, int y, boolean isWhite, Board board, int value) {
		super(x, y, isWhite, board, value);
		hasMoved = false;	//to detect if it's possible to castle
//		this.pieceImage = PieceImages.KING;
	}
	//detecting the image of the piece(king)
	public void initializeSide(int value){
		super.initializeSide(value);
		if(isWhite()) {
			image = PieceImages.wk;
		}
		else {
			image = PieceImages.bk;
		}
	}
	
	public boolean makeMove(int x, int y, Board board) {
		Move move = new Move(xCord, yCord, x, y, this);
		if(!alive()) {
			return false;
		}
		for(Move m: moves) {
			if(m.compareTo(move) == 0) {
				getRook(x, board);
				board.updatePieces(xCord, yCord, x, y,this);
				xCord = x;
				yCord = y;
				if(rook != null && !this.hasMoved && !rook.HasMoved()) {
					if(x == rook.getXcord() - 1 || x == rook.getXcord() + 2) {
						rook.castleDone(xCord, board);
					}					
				}
				justMoved = !hasMoved;
				hasMoved = true;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean canMove(int x, int y, Board board) {
		//if the place that it is going to have ally piece, go to method cannotMove
		if (board.getPiece(x,y) != null && board.getPiece(x,y).isWhite() == isWhite())
			cannotMove(x,y,board);

		//check if the king has the required mechanisms to reach this block(in method kingMoves)
		if( kingMoves(x,y,board)) {
			if(board.getPiece(x, y) == null) {
				return true;
			}
			else {
				return board.getPiece(x, y).isWhite() != isWhite();
			}
		}
		
		// for castling move
		getRook(x, board);
		if(rook != null && (rook.HasMoved() || this.hasMoved)) {
			return false;
		}
		else if(rook != null){
			
			for(int k=xCord + 1; k<rook.getXcord(); k++) {
				if(board.getPiece(k, yCord) != null) {
					return false;
				}
				for(Move m: Game.allEnemyMove) {
					if((m.getToX() == k || m.getToX() == xCord) && m.getToY() == yCord) {
						return false;
					}
				}
			}	
			if(x == rook.getXcord() - 1 && y == yCord) {
				return true;
			}
			
			for(int k=xCord - 1; k>rook.getXcord(); k--) {
				if(board.getPiece(k, yCord) != null) {
					return false;
				}
				for(Move m: Game.allEnemyMove) {
					if((m.getToX() == k || m.getToX() == xCord) && m.getToY() == yCord) {
						return false;
					}
				}
			}
			if(x == rook.getXcord() + 2 && y == yCord) {
				return true;
			}
			
		}
		return false;
	}
	
	private void getRook(int x, Board board) {
		if(isWhite()) {
			if(x >= xCord) {
				if(board.getPiece(7, 7) != null && board.getPiece(7, 7) instanceof Rook){
					rook = (Rook) board.getPiece(7, 7);
				}
			}
			else{
				if(board.getPiece(0, 7) != null && board.getPiece(0, 7) instanceof Rook) {
					rook = (Rook) board.getPiece(0, 7);
				}
			}
		}
		else {
			if(x >= xCord) {
				if(board.getPiece(7, 0) != null && board.getPiece(7, 0) instanceof Rook) {
					rook = (Rook) board.getPiece(7, 0);					
				}
			}
			else{
				if(board.getPiece(0,0) != null && board.getPiece(0,0) instanceof Rook) {
					rook = (Rook) board.getPiece(0, 0);						
				}
			}
		}
	}
	
	public boolean isInCheck() {
		for(Move m: Game.allEnemyMove) {
			if(m.getToX() == xCord && m.getToY() == yCord) {
				return true;
			}
		}
		return false;
	}
	public boolean kingMoves(int x, int y, Board board){
		int i = Math.abs(xCord - x);
		int j = Math.abs(yCord - y);
		//( j == 1 && i == 1 ) for moving diagonally, ((i+j) == 1) for moving up,down,right or left
		return ( j == 1 && i == 1 || (i+j) == 1);
	}
	public void cannotMove(int x, int y, Board board){
		if(kingMoves(x,y,board))
			this.cannotMove.add(new Move(this.xCord,this.yCord,x,y,board.getPiece(x,y)));
	}
	public boolean isJustMoved() {
		return justMoved;
	}
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
	public void setJustMoved(boolean justMoved) {
		this.justMoved = justMoved;
	}
}
