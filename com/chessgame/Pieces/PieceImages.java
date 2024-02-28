package com.chessgame.Pieces;

import Login_Form.Login;

import java.awt.Color;
//import java.nio.file.Path;

import javax.swing.ImageIcon;

public class PieceImages {
	static Color WHITECOLOR = Color.WHITE;
	static Color BLACKCOLOR = Color.BLACK;

	static ImageIcon wk;
	static ImageIcon bk;
	static ImageIcon wr;
	static ImageIcon br;
	static ImageIcon wq;
	static ImageIcon bq;
	static ImageIcon wb;
	static ImageIcon bb;
	static ImageIcon wn;
	static ImageIcon bn;
	static ImageIcon wp;
	static ImageIcon bp;

	public PieceImages() {
		if(Login.theme){
			wk = new ImageIcon(getClass().getResource("/Login_Form/theme/White/wkingn.png"));
			bk = new ImageIcon(getClass().getResource("/Login_Form/theme/black/bkingn.png"));
			wr = new ImageIcon(getClass().getResource("/Login_Form/theme/White/wrn.png"));
			br = new ImageIcon(getClass().getResource("/Login_Form/theme/black/brn.png"));
			wq = new ImageIcon(getClass().getResource("/Login_Form/theme/White/wqn.png"));
			bq = new ImageIcon(getClass().getResource("/Login_Form/theme/black/bqn.png"));
			wb = new ImageIcon(getClass().getResource("/Login_Form/theme/White/wbn.png"));
			bb = new ImageIcon(getClass().getResource("/Login_Form/theme/black/bbn.png"));
			wn = new ImageIcon(getClass().getResource("/Login_Form/theme/White/wnn.png"));
			bn = new ImageIcon(getClass().getResource("/Login_Form/theme/black/bnn.png"));
			wp = new ImageIcon(getClass().getResource("/Login_Form/theme/White/wpn.png"));
			bp = new ImageIcon(getClass().getResource("/Login_Form/theme/black/bpn.png"));
		}
		else {
			wk = new ImageIcon("wk.png");
			bk = new ImageIcon("bk.png");
			wr = new ImageIcon("wr.png");
			br = new ImageIcon("br.png");
			wq = new ImageIcon("wq.png");
			bq = new ImageIcon("bq.png");
			wb = new ImageIcon("wb.png");
			bb = new ImageIcon("bb.png");
			wn = new ImageIcon("wn.png");
			bn = new ImageIcon("bn.png");
			wp = new ImageIcon("wp.png");
			bp = new ImageIcon("bp.png");
		}
		}
}
