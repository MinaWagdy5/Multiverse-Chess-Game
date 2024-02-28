package com.chessgame.Pieces;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.chessgame.Board.Board;
import com.chessgame.Board.Move;
import com.chessgame.Frame.Frame;
import com.chessgame.Game.Game;

public abstract class Piece implements Cloneable{//to enable the creation of a new piece that is an exact copy of an existing piece
	protected int xCord;
	protected int yCord;
	public boolean isWhite;	// try to exchange it with isWhite method and change it to be a private variable
	protected boolean isAlive;
	protected int valueInTheBoard;

	protected Board board;
	public static int size = 80;//size of the tile
	protected List<Move> moves = new ArrayList<>();     // to store all possible moves for every alive piece(this)
	protected List<Move> cannotMove = new ArrayList<>();// to store impossible moves but one of your pieces are in
	protected ImageIcon  image;
	
	public boolean makeMove(int toX, int toY, Board board) {
		Move move = new Move(xCord, yCord, toX, toY, this);
		if(!alive()) {
			return false;
		}
		for(Move m: moves) {
			if(m.compareTo(move) == 0) {
				board.updatePieces(xCord, yCord, toX, toY,this);
				xCord = toX;
				yCord = toY;
				return true;
			}
		}
		return false;
	}
	public abstract boolean canMove(int x ,int y, Board board);

	@SuppressWarnings("unlikely-arg-type")
	public boolean alive() {
		if (board.getXY(xCord, yCord) != valueInTheBoard || board.getXY(xCord, yCord) == 0 || board.getPiece(xCord, yCord) == null) {
			isAlive = false;
			Game.AllPieces.remove(getClass());
			//later we may use this method "getClass().getName()"
			// to get the name of the piece on the board to save the current unfinished game as string ,
			// or we may use instanceof operator to detect the piece class.
		}
		return isAlive;
	}
	//detecting the color of the piece
	public void initializeSide(int value){
		valueInTheBoard = value;
	}
	
	public Piece(int x,int y,boolean isWhite,Board board, int value) {
		this.xCord = x;
		this.yCord = y;
		this.isWhite = isWhite;
		isAlive = true;
		this.board = board;
		//every piece has an integer value represent it and the sign represent the color of it
		//example: white pawn take value 5 and black pawn take value -5
		initializeSide(value);
		board.setPieceIntoBoard(x, y, this);
	}
	public Piece(boolean isWhite){
		this.isWhite = isWhite;
		isAlive = false;
	}
	
	public void showMoves(Graphics g, JPanel panel) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		for(Move m: moves) {
			//remember arrayList moves will have possible moves (blocks that you can go may be empty or have enemy piece)
			//if there is enemy piece in this block set yellow color and if it is empty set green color
			if(board.getPiece(m.getToX(), m.getToY()) != null && board.getPiece(m.getToX(), m.getToY()).isWhite() != isWhite()) {
				g.setColor(Color.GREEN);
			}else {
				g.setColor(Color.GREEN);
			}
			g.fillOval((m.getToX()*size) + size/3, (m.getToY()*size) + size/3, size/3, size/3);
			g2.setColor(Color.DARK_GRAY);
			if(Game.drag) {
				g2.fillRect(m.getFromX()*size, m.getFromY()*size, size, size);
			}
			else {
				g2.drawRect(m.getFromX()*size, m.getFromY()*size, size, size);
			}
		}
		panel.revalidate();
		panel.repaint();
	}
	public void show_impossible_moves(Graphics g , JPanel panel){
		Graphics2D g2 = (Graphics2D) g;
		for(Move m: cannotMove){
			//remember arrayList cannotMoves have the blocks that you your piece has the required mechanism to go to but there is ally piece
			//so we set red color
			if(board.getPiece(m.getToX(), m.getToY()) != null && board.getPiece(m.getToX(), m.getToY()).isWhite() == isWhite()){
				g2.setColor(Color.RED);
				g2.fillOval((m.getToX()*size) + size/3, (m.getToY()*size) + size/3, size/3, size/3);
			}
		}
		panel.revalidate();
		panel.repaint();
	}
	
	public void draw(Graphics g, boolean drag, JPanel panel) {
			g.drawImage(image.getImage(), xCord*Piece.size, yCord*Piece.size, Piece.size, Piece.size, panel);
			panel.revalidate();
			panel.repaint();
	}
	public void drawDead(JPanel panel){
		JLabel label = new JLabel(this.image);     //create label for every dead piece
		panel.add(label);
		panel.revalidate(); 	// revalidate the panel to update the layout
		panel.repaint();	 // repaint the panel to update the display
	}
	//	?????????
	public void draw2(Graphics g, boolean player, int x, int y, JPanel panel) {
			g.drawImage(image.getImage(), x - Piece.size/2, y- Piece.size/2, Piece.size, Piece.size, panel);
			panel.revalidate();
			panel.repaint();
	}

	// to add all possible moves to moves list of each piece of the team who has to play
	public void fillAllPseudoLegalMoves(Board board) {
		moves = new ArrayList<>();
		cannotMove = new ArrayList<>();
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(canMove(i, j, board)) {
					moves.add(new Move(xCord, yCord, i, j, this));
				}
			}
		}
	}
	public int getXcord() {
		return xCord;
	}

	public void setXcord(int xcord) {
		this.xCord = xcord;
	}

	public int getYcord() {
		return yCord;
	}
	public void setYcord(int ycord) {
		this.yCord = ycord;
	}

	public boolean isWhite() {
		return isWhite;
	}

//	public void setWhite(boolean isWhite) {
//		this.isWhite = isWhite;
//	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

//	public void setValueInTheboard(int value) {
//		this.valueInTheBoard = value;
//	}
	public int getValueInTheBoard() {
		return valueInTheBoard;
	}
	public List<Move> getMoves() {
		return moves;
	}
//	public void setMoves(List<Move> moves) {
//		this.moves = moves;
//	}

	//	to return a piece of the same type
	public Piece getClone() {
		try {
			return (Piece) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
