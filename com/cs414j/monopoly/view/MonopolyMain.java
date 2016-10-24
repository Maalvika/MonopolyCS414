package com.cs414j.monopoly.view;

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

public class MonopolyMain {

	public static List<String> players;
	private static JFrame frame;
	private static Container contentPane;
	static String currentpName;
    static int currentScore;
    public static Die _leftDie;     
    public static Die _rightDie;
    public static ImagePanel panel;
    public static List<Token> tokens = new LinkedList<>();
	private static JPanel boardPanel;
    

	public static void init() {

		// added Monopoly game board image
		prepareTokenList();
		boardPanel = addBoardImage();
		frame = new JFrame();
		contentPane = frame.getContentPane();
		JPanel board = new JPanel(new GridLayout(1, 2));
		board.add(addDiePanel());
		board.add(new MonopolyOptions(frame, currentpName, currentScore));
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(boardPanel,BorderLayout.CENTER);
		contentPane.add(board,BorderLayout.EAST);
		//added the panel
		//setPlayerDetails();
		// added the die
		frame.pack();
		frame.setVisible(true);
	}
	
	private static JPanel addBoardImage() {

		final BufferedImage img = new ImageUtility().scaleImage(1000, 980, "images/background.jpg");
		panel = new ImagePanel(new ImageIcon(img).getImage(), tokens);
		return panel;
	}

	public static void changeBoardImage() {

		int tokenIndex = players.indexOf(currentpName);
		Token t = adjustPosition(tokens.get(tokenIndex));
		tokens.set(tokenIndex, t);
		panel.changeTokenPosition(tokens);
	}

	private static Token adjustPosition(Token t) {
		
		if(t.getxCoordinate()==10 || t.getxCoordinate() == 70) {
			if((t.getyCoordinate()-80*(_leftDie.getValue()+_rightDie.getValue()))>=70) {
				if(t.getyCoordinate() == 930) {
					t.setyCoordinate(t.getyCoordinate()-80*(_leftDie.getValue()+_rightDie.getValue()+1));
				} else {
					t.setyCoordinate(t.getyCoordinate()-80*(_leftDie.getValue()+_rightDie.getValue()));
				}
			} else {		
				int ypoint = (t.getyCoordinate()-70)/80;
				//since the corner is a bigger block so we need 1 step more to get the 
				//token at correct position
				int xpoint = (_leftDie.getValue()+_rightDie.getValue())-ypoint+1;
				System.out.println("x1:"+xpoint+"y1:"+ypoint);
				t.setxCoordinate(t.getxCoordinate()+80*xpoint);
				System.out.println("xc1:"+t.getxCoordinate()+"yc1:"+t.getyCoordinate());
				t.setyCoordinate(70);
			}
		} else if(t.getyCoordinate()==70) {
			if((t.getxCoordinate()+80*(_leftDie.getValue()+_rightDie.getValue()))<890) {
				t.setxCoordinate(t.getxCoordinate()+80*(_leftDie.getValue()+_rightDie.getValue()));
			} else {		
				int xpoint = (890-t.getxCoordinate())/80;
				//since the corner is a bigger block so we need 1 step more to get the 
				//token at correct position
				int ypoint = (_leftDie.getValue()+_rightDie.getValue())-xpoint;
				System.out.println("x2:"+xpoint+"y2:"+ypoint);
				t.setxCoordinate(890);
				t.setyCoordinate(t.getyCoordinate()+80*ypoint);
				System.out.println("xc2:"+t.getxCoordinate()+"yc2:"+t.getyCoordinate());
				
			}
			
		}else if(t.getxCoordinate()==890) {
			System.out.println("c");
			if((t.getyCoordinate()+80*(_leftDie.getValue()+_rightDie.getValue()))<870) {
				System.out.println("a");
				t.setyCoordinate(t.getyCoordinate()+80*(_leftDie.getValue()+_rightDie.getValue()));
			} else {	
				System.out.println("b");
				int ypoint = (870-t.getyCoordinate())/80;
				//since the corner is a bigger block so we need 1 step more to get the 
				//token at correct position
				int xpoint = (_leftDie.getValue()+_rightDie.getValue())-ypoint;
				System.out.println("x:"+xpoint+"y:"+ypoint);
				t.setyCoordinate(870);
				t.setxCoordinate(t.getxCoordinate()-80*xpoint);
				System.out.println("xc3:"+t.getxCoordinate()+"yc3:"+t.getyCoordinate());
			}
			
		}else if(t.getyCoordinate() == 870 && (t.getxCoordinate()!=10 || t.getxCoordinate()!=70)) {
			if(t.getxCoordinate()-80*(_leftDie.getValue()+_rightDie.getValue())>10) {
				t.setxCoordinate(t.getxCoordinate()-80*(_leftDie.getValue()+_rightDie.getValue()));
			} else if (t.getxCoordinate()==90) {
				t.setxCoordinate(10);
			}else {
				int xpoint = ((t.getxCoordinate()-10)/80)-1;
				//since the corner is a bigger block so we need 1 step more to get the 
				//token at correct position
				int ypoint = (_leftDie.getValue()+_rightDie.getValue())-xpoint;
				System.out.println("x:"+xpoint+"y:"+ypoint);
				t.setxCoordinate(10);
				t.setyCoordinate(t.getyCoordinate()-80*ypoint);
				System.out.println("xc4:"+t.getxCoordinate()+"yc4:"+t.getyCoordinate()); 
			}
			
		}
		return t;
		
	}
	private static void prepareTokenList() {
		tokens.add(new Token(TokenUrls.CAR, 10, 870));
		tokens.add(new Token(TokenUrls.SHIP, 70, 870));
		switch (players.size()) {
		case 3:
			tokens.add(new Token(TokenUrls.HORSE, 70, 930));
			break;
		case 4:
			tokens.add(new Token(TokenUrls.HORSE, 70, 930));
			tokens.add(new Token(TokenUrls.SHOE, 10, 930));
			break;
		}
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

	public static void setPlayers(List<String> players) {
		MonopolyMain.players = players;
		currentpName = players.get(0);
		currentScore = 0;
		
	}

	public static void setPlayerDetails(String playerName, int score) {
		currentpName = playerName;
		currentScore = score;
		contentPane.add(new MonopolyOptions(frame, playerName, score), BorderLayout.EAST);

	}
	
	public static JPanel setPlayerDetails() {
		JPanel p = new JPanel();
		p.add(new MonopolyOptions(frame, currentpName, currentScore));
		return p;

	}
}