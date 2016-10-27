package com.cs414j.monopoly.controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.cs414j.monopoly.model.Bank;
import com.cs414j.monopoly.model.Board;
import com.cs414j.monopoly.model.Player;
import com.cs414j.monopoly.view.Die;
import com.cs414j.monopoly.view.ImagePanel;
import com.cs414j.monopoly.view.ImageUtility;
import com.cs414j.monopoly.view.PlayerForm;
import com.cs414j.monopoly.view.Token;

public class MonopolyMain {

	public static List<Player> players;
	public static JFrame frame;
	private static Container contentPane;
	public static Player currentPlayer;
    public static Die _leftDie;     
    public static Die _rightDie;
    public static ImagePanel panel;
	private static JPanel boardPanel;
	public static Board board;
	public static Bank bank;
    

	public static void init() {

		// added Monopoly game board image
		initBackendValues();
		boardPanel = addBoardImage();
		frame = new JFrame();
		contentPane = frame.getContentPane();
		JPanel board = new JPanel(new GridLayout(1, 2));
		board.add(addDiePanel());
		board.add(new MonopolyOptions(frame, currentPlayer));
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(boardPanel,BorderLayout.CENTER);
		contentPane.add(board,BorderLayout.EAST);
		//added the panel
		//setPlayerDetails();
		// added the die
		frame.pack();
		frame.setVisible(true);
	} 
	
	private static void initBackendValues() {
		
		board = Board.getInstance();
		bank = new Bank();
		
	}
	
	private static JPanel addBoardImage() {

		final BufferedImage img = new ImageUtility().scaleImage(1000, 980, "images/background.jpg");
		panel = new ImagePanel(new ImageIcon(img).getImage(), PlayerForm.tokens);
		return panel;
	}

	public static void changeBoardImage() {

		Token t = adjustPosition(currentPlayer.getToken());
		PlayerForm.tokens.set(PlayerForm.tokens.indexOf(currentPlayer.getToken()), t);
		currentPlayer.setToken(t);
		panel.changeTokenPosition(PlayerForm.tokens);
	}

	private static Token adjustPosition(Token t) {

		int x = t.getxCoordinate();
		int y = t.getyCoordinate();
		if (x == 70 && y == 930) {
			t.setxCoordinate(10);
			t.setyCoordinate(870);
			x = 10;
			y = 870;
		} else if (x == 10 && y == 930) {
			t.setyCoordinate(870);
			y = 870;
		} else if (x==70 && y==870) {
			t.setxCoordinate(10);
			x=10;
		}
		if (x == 10) {
			if ((y - 80 * (_leftDie.getValue() + _rightDie.getValue())) > 70) {
				t.setyCoordinate(y - 80 * (_leftDie.getValue() + _rightDie.getValue()));
			} else if (y==70) {
				t.setxCoordinate(90);
			}
				else {
				int ypoint = (y - 70) / 80;
				// since the corner is a bigger block so we need 1 step more to
				// get the
				// token at correct position
				int xpoint = (_leftDie.getValue() + _rightDie.getValue()) - ypoint + 1;
				t.setxCoordinate(x + 80 * xpoint);
				t.setyCoordinate(70);
			}
		} else if (y == 70) {
			if ((x + 80 * (_leftDie.getValue() + _rightDie.getValue())) < 890) {
				t.setxCoordinate(x + 80 * (_leftDie.getValue() + _rightDie.getValue()));
			} else {
				int xpoint = (890 - x) / 80;
				// since the corner is a bigger block so we need 1 step more to
				// get the
				// token at correct position
				int ypoint = (_leftDie.getValue() + _rightDie.getValue()) - xpoint;
				t.setxCoordinate(890);
				t.setyCoordinate(y + 80 * ypoint);

			}

		} else if (x == 890) {
			if ((y + 80 * (_leftDie.getValue() + _rightDie.getValue())) < 870) {
				t.setyCoordinate(y + 80 * (_leftDie.getValue() + _rightDie.getValue()));
			} else {
				int ypoint = (870 - y) / 80;
				// since the corner is a bigger block so we need 1 step more to
				// get the
				// token at correct position
				int xpoint = (_leftDie.getValue() + _rightDie.getValue()) - ypoint;
				t.setyCoordinate(870);
				t.setxCoordinate(x - 80 * xpoint);
			}

		} else if (y == 870 && x != 10 ) {
			if (x - 80 * (_leftDie.getValue() + _rightDie.getValue()) >= 170) {
				t.setxCoordinate(x - 80 * (_leftDie.getValue() + _rightDie.getValue()));
			} else if(x - 80 * (_leftDie.getValue() + _rightDie.getValue()) < 10) {
				int xpoint = ((x - 10) / 80) - 1;
				// since the corner is a bigger block so we need 1 step more to
				// get the
				// token at correct position
				int ypoint = (_leftDie.getValue() + _rightDie.getValue()) - xpoint;
				t.setxCoordinate(10);
				t.setyCoordinate(y - 80 * ypoint);
				currentPlayer.passGo(_leftDie.getValue() + _rightDie.getValue());
			} else {
				t.setxCoordinate(10);
				currentPlayer.passGo(_leftDie.getValue() + _rightDie.getValue());
			}

		}
		return t;

	}
	
	private static JPanel addDiePanel() {
		JPanel diePanel = new JPanel();
		diePanel.setLayout(new GridLayout(1, 2, 5, 5));
		_leftDie = new Die();
		_rightDie = new Die();
		diePanel.add(_leftDie);
		diePanel.add(_rightDie);
		JPanel temp = new JPanel();
		temp.add(diePanel);
		return temp;
	}

	public static void setPlayers(List<Player> players) {
		MonopolyMain.players = players;
		currentPlayer = players.get(0);
		
	}

	public static void setPlayerDetails(Player p) {
		currentPlayer = p;
		contentPane.add(new MonopolyOptions(frame, currentPlayer), BorderLayout.EAST);

	}
	
	public static JPanel setPlayerDetails() {
		JPanel p = new JPanel();
		p.add(new MonopolyOptions(frame, currentPlayer));
		return p;

	}
}