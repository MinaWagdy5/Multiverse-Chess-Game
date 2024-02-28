package com.chessgame.Game;

import Login_Form.Login;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.*;
import java.util.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Login_Form.MainMenu;
import Login_Form.Player;
import Login_Form.SetUp;
import com.chessgame.Board.Board;
import com.chessgame.Board.Move;
import com.chessgame.Board.PopupExample;
import com.chessgame.Frame.Frame;
import com.chessgame.Frame.LabelTimer;
import com.chessgame.Pieces.*;

public class Game {

	public static Board board = new Board();
	static King wk;
	static King bk;
	static ArrayList<Piece> wPieces = new ArrayList<>();    // contains white pieces
	static ArrayList<Piece> bPieces = new ArrayList<>();    // contains black pieces
	public static boolean player = true;
	public Piece active = null;
	public static boolean drag = false;
	public static ArrayList<Piece> AllPieces = new ArrayList<>();    // contains all pieces
	//	ArrayList<Move> allPossiblesMoves = new ArrayList<>();
	public static List<Move> allEnemyMove = new ArrayList<>();
	public static boolean gameOver = false;


	public Game() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
		new PieceImages(); 		// ???????????????????
		String game = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		// load game to continue
		String CurrentHistoryFilePath = String.format("Continue/%s.txt", Login.usernamex);
		File file = new File(CurrentHistoryFilePath);
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			if ((game = reader.readLine()) != null) {
				String[] parts = game.split(" ");
				Frame.label1 = new LabelTimer(Integer.parseInt(parts[2]) / 60,Integer.parseInt(parts[2]) % 60);
				Frame.label2 =new LabelTimer(Integer.parseInt(parts[3]) / 60,Integer.parseInt(parts[3]) % 60);
				String[] part = game.split(",");
				SetUp.player1.name=part[1];
				SetUp.player2.name=part[2];
			} else game = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		} catch (IOException e) {
			System.out.println("An error occurred while reading the file.");
			e.printStackTrace();
		}
		loadFenPosition(game);//we stopped at 'w'
		start();
	}


	private static void saveHistory() {
		Frame.label1.timer.stop();
		Frame.label2.timer.stop();
		String CurrentHistoryFilePath = String.format("History/%s.txt", Login.usernamex);
		String player1_name = "";
		String player2_name = "";

		try {
			String CurrentContinueFilePath = String.format("Continue/%s.txt", Login.usernamex);
			File file = new File(CurrentContinueFilePath);
			Scanner scan = new Scanner(file);
			scan.useDelimiter("[,\n]");        // make splitting to the string


			player1_name = scan.next();
			player1_name = scan.next();
			player2_name = scan.next();

			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write("");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "an error occured" + e);
		}


		try {

			FileOutputStream HistoryFile = new FileOutputStream(CurrentHistoryFilePath, true);
			PrintWriter printer = new PrintWriter(HistoryFile);

			if (player) {
				if (wk.isInCheck() || Frame.label1.time == 0) {//black wins
					printer.println(player1_name + "," + "Lose" + "," + "Win" + "," + player2_name);

				} else {//stalemate
					printer.println(player1_name + "," + "draw" + "," + "draw" + "," + player2_name);

				}
			} else {
				if (bk.isInCheck() || Frame.label2.time == 0) {//white wins
					printer.println(player1_name + "," + "Win" + "," + "Lose" + "," + player2_name);

				} else {//stalemate
					printer.println(player1_name + "," + "draw" + "," + "draw" + "," + player2_name);
				}
			}
			printer.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "an error occured" + e);
		}
		SetUp.frame.dispose();
		MainMenu mainMenu = new MainMenu();
		mainMenu.setVisible(true);
		Game.AllPieces =new ArrayList<>();
		Game.board =new Board();
		Game.allEnemyMove = new ArrayList<>();
		Game.drag = false;
		Game.player = true;
		Game.gameOver = false;
	}



	private static void DeadPieces() {
		StringBuilder s = new StringBuilder();
		for(Piece p : board.deadPieces){
			if(p != null){
				switch (p.getClass().getName().split("Pieces.")[1]){
					case "Pawn" -> s.append(p.isWhite ? "P" : "p");
					case "Rook" -> s.append(p.isWhite ? "R" : "r");
					case "Knight" -> s.append(p.isWhite ? "N" : "n");
					case "Bishop" -> s.append(p.isWhite ? "B" : "b");
					case "King" -> s.append(p.isWhite ? "K" : "k");
					case "Queen" -> s.append(p.isWhite ? "Q" : "q");
				}
				s.append(p.getXcord());
				s.append(p.getYcord());
				s.append("/");
			}
		}
		saveDeadPieces(s.toString());
	}

	private static void saveDeadPieces(String s) {
		String DeadPiecesFilePath = String.format("DeadPieces/%s.txt", Login.usernamex);
		File file = new File(DeadPiecesFilePath);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(s);
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}
	}



	public void start() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
		fillPieces();
		generatePlayersTurnMoves(board);
		generateEnemyMoves(board);
		checkPlayersLegalMoves();
		if (board.pieces[0][6] != null) {
			if (board.pieces[0][6].makeMove(0,5,board)) {
				System.out.println("hhhhhhh");
				tryToPromote(board.pieces[0][5]);
				changeSide();
			}
		}
		if (board.pieces[0][1] != null) {
			if (board.pieces[0][1].makeMove(0,2,board)) {
				System.out.println("hhhhhhh");
				tryToPromote(board.pieces[0][2]);
				changeSide();
			}
		}
	}

	public void draw(Graphics g, int x, int y, JPanel panel) {
		drawBoard(g);
		drawPiece(g, panel);
		drawPossibleMoves(g, panel);
		drag(active, x, y, g, panel);
		drawKingInCheck(player, g, panel);
		drawMoves(g, panel);
	}
	//
	public static void generatePlayersTurnMoves(Board board) {
		List<Move> allPlayersMove = new ArrayList<>();
		for (Piece p : AllPieces) {
			if (p.isWhite() == player) {
				p.fillAllPseudoLegalMoves(board);
				allPlayersMove.addAll(p.getMoves());
			}
		}
	}
	// to add all possible moves of each piece of the team that will play after the next move
	public static void generateEnemyMoves(Board board) {
		allEnemyMove = new ArrayList<>();
		for (Piece p : AllPieces) {
			if (p.isWhite() != player) {
				p.fillAllPseudoLegalMoves(board);
				allEnemyMove.addAll(p.getMoves());
			}
		}
	}

	public void changeSide() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
		player = !player;
		generateEnemyMoves(board);
		generatePlayersTurnMoves(board);
		checkPlayersLegalMoves();
//		checkMate();
		board.continueGame(player);
		if(player && !gameOver){
			Frame.label1.timer.start();
			Frame.label2.timer.stop();
		}
		else if(!gameOver){
			Frame.label1.timer.stop();
			Frame.label2.timer.start();
		}
		checkMate();
		drawDeadPieces();
		DeadPieces();
		if (Login.theme && board.deadPieces.size() != 0) {
			Piece p = board.deadPieces.pop();
			board.deadPieces.add(p);
			if (p != null && !(wk.isInCheck() || bk.isInCheck())) {
				new PopupExample(board.pieces[p.getXcord()][p.getYcord()]);
			}
			else if (p == null && !(wk.isInCheck() || bk.isInCheck()))
			{
				new PopupExample (true);
			}
			else
			{
				new PopupExample();
			}
          PopupExample.counter=0;
		}


	}

	public void selectPiece(int x, int y) {
		if (active == null && board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == player) {
			active = board.getPiece(x, y);
		}
	}

	public static void checkMate() {
		if (player) {
			for (Piece p : wPieces) {
				if (!p.getMoves().isEmpty()) {
					return;
				}
			}
		} else {
			for (Piece p : bPieces) {
				if (!p.getMoves().isEmpty()) {
					return;
				}
			}
		}
		if (player) {
			if (wk.isInCheck()) {
				saveHistory();
				JOptionPane.showMessageDialog(null, "check mate " + (!player ? "white" : "black") + " wins");

			} else {
				saveHistory();
				JOptionPane.showMessageDialog(null, "stalemate ");

			}
		} else {
			if (bk.isInCheck()) {
				saveHistory();
				JOptionPane.showMessageDialog(null, "check mate " + (!player ? "white" : "black") + " wins");

			} else {
				saveHistory();
				JOptionPane.showMessageDialog(null, "stalemate ");
			}
		}
		gameOver = true;
//		Frame.label1.timer.stop();
//		Frame.label2.timer.stop();
	}

	//	check legal moves of each piece of the team that has to play
	public static void checkPlayersLegalMoves() {
		List<Piece> pieces;
		if (player) {
			pieces = wPieces;
		} else {
			pieces = bPieces;
		}
		for (Piece p : pieces) {
			checkLegalMoves(p);
		}
	}

	/*
	Copying and cloning pieces and board is necessary in this method for a few reasons.
Firstly, we need to make a copy of the board in order to apply the possible moves of the active piece without modifying
 the actual game state. We don't want to make actual moves on the game board and then undo them if they turn out to be
 illegal - this would be inefficient and could cause bugs if the undo operation is not implemented correctly.
So, by cloning the board and applying moves to the clone, we can check for legality without affecting the actual game state.

Secondly, we need to clone the active piece and the enemy pieces in order to generate all possible moves on the cloned board.
 If we were to generate moves on the original pieces, this would again modify the actual game state, which we want to avoid.
 By cloning the pieces, we can generate moves on the clones without affecting the original game pieces.

Finally, by cloning the pieces, we can ensure that any modifications made to the clones during the legality check
do not affect the original pieces. For example, the active piece might be moved during the check to see whether
a move would result in checkmate for the active player's king. If we modified the original piece directly,
this would affect the game state and potentially cause bugs. But by cloning the piece and making any necessary
modifications to the clone, we can ensure that the original game state is not affected
	 */
	// in brief, this method is for, when your king is in check no possible move is done unless it was to protect your
	// king or to move it
	// and this method is also for the case when you want to move a piece that protect the king

	public static void checkLegalMoves(Piece piece) {
		List<Move> movesToRemove = new ArrayList<>();
		Board clonedBoard ;
		Piece clonedActive = piece.getClone();

		for (Move move : clonedActive.getMoves()) {
			clonedBoard = board.getNewBoard();
			clonedActive = piece.getClone();

			clonedActive.makeMove(move.getToX(), move.getToY(), clonedBoard);

			List<Piece> enemyPieces;
			Piece king ;

			if (piece.isWhite()) {
				enemyPieces = bPieces;
				king = wk;
			} else {
				enemyPieces = wPieces;
				king = bk;
			}

			for (Piece enemyP : enemyPieces) {

				Piece clonedEnemyPiece = enemyP.getClone();
				clonedEnemyPiece.fillAllPseudoLegalMoves(clonedBoard);

				for (Move bMove : clonedEnemyPiece.getMoves()) {
					if (!(clonedActive instanceof King) && bMove.getToX() == king.getXcord()
							&& bMove.getToY() == king.getYcord()
							&& clonedBoard.getGrid()[enemyP.getXcord()][enemyP.getYcord()] == enemyP
							.getValueInTheBoard()) {
						movesToRemove.add(move);
					} else if (clonedActive instanceof King) {
						if (bMove.getToX() == clonedActive.getXcord() && bMove.getToY() == clonedActive.getYcord()
								&& clonedBoard.getGrid()[enemyP.getXcord()][enemyP.getYcord()] == enemyP
								.getValueInTheBoard()) {
							movesToRemove.add(move);
						}
					}
				}

			}

		}

		for (Move move : movesToRemove) {
			piece.getMoves().remove(move);
		}
	}

	public void drag(Piece piece, int x, int y, Graphics g, JPanel panel) {
		if (piece != null && drag) {
			piece.draw2(g, player, x, y, panel);
		}
	}

	public void move(int x, int y) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
		if (active != null) {
			if (active.makeMove(x, y, board)) {
				tryToPromote(active);
				changeSide();
				active = null;
			}
			drag = false;
		}
	}

	public void drawKingInCheck(boolean isWhite, Graphics g, JPanel panel) {
		g.setColor(Color.RED);
		if (isWhite && wk.isInCheck()) {
			g.drawRect(wk.getXcord() * Piece.size, wk.getYcord() * Piece.size, Piece.size, Piece.size);
		} else if (bk.isInCheck()) {
			g.drawRect(bk.getXcord() * Piece.size, bk.getYcord() * Piece.size, Piece.size, Piece.size);
		}
		panel.revalidate();
		panel.repaint();
	}

	public void drawBoard(Graphics g) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 1) {
					g.setColor(new Color(122, 61, 1));
				} else {
					g.setColor(new Color(254, 198, 103));
				}
				g.fillRect(i * Piece.size, j * Piece.size, Piece.size, Piece.size);
			}
		}
	}

	public void tryToPromote(Piece p) {
		if (p instanceof Pawn) {
			if (((Pawn) p).madeToTheEnd()) {
				choosePiece(p, showMessageForPromotion());
			}
		}
	}

	//used when promoting a pawn
	public int showMessageForPromotion() {
		Object[] options = {"Queen", "Rook", "Knight", "Bishop"};

		drag = false;
		return JOptionPane.showOptionDialog(null, "Choose Piece To Promote to", null, JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}

	//to plot the chosen piece
	public static void choosePiece(Piece p, int choice) {
		switch (choice) {
			case 1 -> {
				AllPieces.remove(p);
				p = new Rook(p.getXcord(), p.getYcord(), p.isWhite(), board, p.isWhite() ? 5 : -5);
				AllPieces.add(p);
			}
			case 2 -> {
				AllPieces.remove(p);
				p = new Knight(p.getXcord(), p.getYcord(), p.isWhite(), board, p.isWhite() ? 3 : -3);
				AllPieces.add(p);
			}
			case 3 -> {
				AllPieces.remove(p);
				p = new Bishop(p.getXcord(), p.getYcord(), p.isWhite(), board, p.isWhite() ? 3 : -3);
				AllPieces.add(p);
			}
			default -> {
				AllPieces.remove(p);
				p = new Queen(p.getXcord(), p.getYcord(), p.isWhite(), board, p.isWhite() ? 8 : -8);
				AllPieces.add(p);
			}
		}
		fillPieces();
	}

	//to draw possible moves
	public void drawPossibleMoves(Graphics g, JPanel panel) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		if (active != null) {
			active.showMoves(g2, panel);
		}
	}

	public void drawMoves(Graphics g, JPanel panel) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		if (active != null) {
			active.show_impossible_moves(g2, panel);
		}

	}


	public void drawPiece(Graphics g, JPanel panel) {
		for (Piece p : AllPieces) {
			p.draw(g, false, panel);
		}

	}

	//detect the current position of pieces on the board
	public void loadFenPosition(String fenString) {
		String[] parts = fenString.split(" ");
		String position = parts[0];
		int row = 0, col = 0;
		for (char c : position.toCharArray()) {
			if (c == '/') {
				row++;
				col = 0;
			}
			if (Character.isLetter(c)) { //if lower case is black , if upper case is white
				addToBoard(col, row, c, !Character.isLowerCase(c));
				col++;
			}
			if (Character.isDigit(c)) {
				col += Integer.parseInt(String.valueOf(c));
			}
		}
		player = parts[1].equals("w");
	}

	//responsible for plotting a dead pieces
	public static void drawDeadPieces(){
		if(Frame.bDead != null && Frame.wDead != null) {
			Frame.bDead.removeAll();               // to remove all the black dead pieces
			Frame.wDead.removeAll();               // to remove all the white dead pieces
			Frame.wDead.repaint();
			Frame.bDead.repaint();                 // repaint the panel to update the display
			for (Piece p : board.deadPieces) {
				if (p != null) p.drawDead(p.isWhite ? Frame.wDead : Frame.bDead);
			}
		}
	}

	public static void timeOut(){
		if(Frame.label1.time == 0 || Frame.label2.time == 0){
			saveHistory();
			JOptionPane.showMessageDialog(null, "Time out " + (!player ? "white" : "black") + " wins");
//			System.exit(0);
		}
	}


	// to detect the white and black pieces and add them to the right list
	public static void fillPieces() {
		wPieces = new ArrayList<>();
		bPieces = new ArrayList<>();
		for (Piece p : AllPieces) {
			if (p.isWhite()) {
				wPieces.add(p);
			} else {
				bPieces.add(p);
			}
		}
	}

	// to detect the piece to be plotted on the board
	public void addToBoard(int x, int y, char c, boolean isWhite) {
		switch (String.valueOf(c).toUpperCase()) {
			case "R" -> AllPieces.add(new Rook(x, y, isWhite, board, isWhite ? 5 : -5));
			case "N" -> AllPieces.add(new Knight(x, y, isWhite, board, isWhite ? 3 : -3));
			case "B" -> AllPieces.add(new Bishop(x, y, isWhite, board, isWhite ? 3 : -3));
			case "Q" -> AllPieces.add(new Queen(x, y, isWhite, board, isWhite ? 8 : -8));
			case "K" -> {
				King king = new King(x, y, isWhite, board, isWhite ? 10 : -10);
				AllPieces.add(king);
				if (isWhite) {
					wk = king;
				} else {
					bk = king;
				}
			}
			case "P" -> AllPieces.add(new Pawn(x, y, isWhite, board, isWhite ? 1 : -1));
		}
	}
}