package com.chessgame.Board;

import Login_Form.Login;
import Login_Form.SetUp;
import java.util.*;

import com.chessgame.Frame.Frame;
import com.chessgame.Game.Game;
import com.chessgame.Pieces.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Board {
	public static final int ROWS = 8;
	public static final int COLUMNS = 8;
	public int[][] grid;
	public Piece[][] pieces;
//	public Piece lastPieceMoved;
	public Move lastMove;
	public Piece died;
	public Stack<Move> lastMoves = new Stack<>();
	public Stack<Piece> deadPieces = new  Stack<>();
//	public  List<Piece> piecesList = new ArrayList<>();

	

	public Board() {
		grid = new int[ROWS][COLUMNS];
		pieces = new Piece[ROWS][COLUMNS];
	}

	//to add the piece to the piece list and the array of pieces
	public void setPieceIntoBoard(int x,int y,Piece piece) {
		if(piece != null) {
			grid[x][y] = piece.getValueInTheBoard();
			pieces[x][y] = piece;
		}else {
			grid[x][y] = 0;
			pieces[x][y] = null;
		}
	}

	// to update pieces
	public void updatePieces(int fromX,int fromY,int toX,int toY,Piece piece) {
		lastMove = new Move(fromX, fromY, toX, toY, piece);
		lastMoves.add(lastMove);
		if(pieces[toX][toY] != null) {
			died = pieces[toX][toY];
			deadPieces.add(died);
			Game.AllPieces.remove(died);
			Game.fillPieces();
		}else {
			deadPieces.add(null);
		}
		grid[fromX][fromY] = 0;
		grid[toX][toY] =  piece.getValueInTheBoard();
		pieces[fromX][fromY] = null;
		pieces[toX][toY] = piece;
	}

	// for undo feature
	public void undoMove(Game game) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
		if(!lastMoves.isEmpty()) {		// to check that the array list is not empty
			Move move = lastMoves.pop();	// to undo the last move
			Piece dead = deadPieces.pop();	 //	to get the last dead piece
			grid[move.getFromX()][move.getFromY()] = move.getPiece().getValueInTheBoard();	//	to plot the piece to undo its move in its last position
			pieces[move.getFromX()][move.getFromY()] = move.getPiece();
			
			move.getPiece().setXcord(move.getFromX());	// to set the x,y of the piece position
			move.getPiece().setYcord(move.getFromY());
			
			if(move.getPiece() instanceof Pawn) {	// to make the pawn able to perform its 2 steps move if it was in its initial position
				if(move.getPiece().getYcord() == (move.getPiece().isWhite() ? 6 : 1)) {
					((Pawn)	move.getPiece()).setFirstMove(true);
				}
			}
			
			if(move.getPiece() instanceof Rook) {	// this condition was made because to castle king and rook shouldn't have moved before
				if(((Rook) move.getPiece()).isJustMoved()) {
					((Rook) move.getPiece()).setHasMoved(false);
					((Rook) move.getPiece()).setJustMoved(false);
				}
			}

			if(move.getPiece() instanceof King) {	// this condition was made because to castle king and rook shouldn't have moved before
				if(((King) move.getPiece()).isJustMoved()) {
					((King) move.getPiece()).setHasMoved(false);
					((King) move.getPiece()).setJustMoved(false);
				}
			}

			if(dead != null) {
				Game.AllPieces.add(dead);
				Game.fillPieces();
				grid[move.getToX()][move.getToY()] = dead.getValueInTheBoard();
				pieces[move.getToX()][move.getToY()] = dead;
				dead.setXcord(move.getToX());
				dead.setYcord(move.getToY());
			}
			else {
				grid[move.getToX()][move.getToY()] = 0;
				pieces[move.getToX()][move.getToY()] = null;
			}
			game.changeSide();
		}

	}
	
	public Piece getPiece(int x,int y) {
		return pieces[x][y];
	}
	public int[][] getGrid() {
		return grid;
	}
	public int getXY(int x,int y) {
		return grid[x][y];
	}
	
	public void setXY(int x,int y,int v) {
		 grid[x][y] = v ;
	}

	public Board getNewBoard() {
		Board b = new Board();
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(this.getPiece(i, j) != null) {
					b.setPieceIntoBoard(i, j, this.getPiece(i, j).getClone());
				}
			}
		}
		return b;
	}

	// to save the current game to be continued
	public void continueGame(boolean player){
		StringBuilder s = new StringBuilder();
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(pieces[j][i] != null){
					switch (pieces[j][i].getClass().getName().split("Pieces.")[1]){
						case "Pawn" -> s.append(pieces[j][i].isWhite ? "P" : "p");
						case "Rook" -> s.append(pieces[j][i].isWhite ? "R" : "r");
						case "Knight" -> s.append(pieces[j][i].isWhite ? "N" : "n");
						case "Bishop" -> s.append(pieces[j][i].isWhite ? "B" : "b");
						case "King" -> s.append(pieces[j][i].isWhite ? "K" : "k");
						case "Queen" -> s.append(pieces[j][i].isWhite ? "Q" : "q");
					}
				}
				else s.append("1");
				if(j==7&&i!=7) s.append("/");
			}
		}
		if(player) s.append(" w ");
		else s.append(" b ");
		s.append(Frame.label1.time).append(" ");
		s.append(Frame.label2.time).append(" ");
		s.append(",").append(SetUp.player1.name).append(",").append(SetUp.player2.name);
		saveData(s.toString());
	}
	public static void saveData(String s){
		//this method saves the string which stores the positions, time, and players' names inside the text file
		String CurrentContinueFilePath = String.format("Continue/%s.txt", Login.usernamex);
        File file = new File(CurrentContinueFilePath);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                            writer.write(s);
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}
	}
}
