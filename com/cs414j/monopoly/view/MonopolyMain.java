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
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MonopolyMain {

	public static List<String> players;
	private static JFrame frame;
	private static Container contentPane;
	private static String currentpName;
    private static int currentScore;
    public static Die _leftDie;     
    public static Die _rightDie;

	static JPanel panelImg = new JPanel() {
		public void paintComponent(Graphics g) {
			Image img = new ImageIcon("images/background.jpg").getImage();
			Dimension size = new Dimension(50, 50);
			setPreferredSize(size);
			setMinimumSize(size);
			setMaximumSize(size);
			setSize(size);
			setLayout(null);
			g.drawImage(img, 0, 0, null);
		}
	};

	public static void init() {

		// added Monopoly game board image
		frame = new JFrame();
		contentPane = frame.getContentPane();
		JPanel board = new JPanel();
		board.add(addBoardImage());
		board.add(addDiePanel());
		contentPane.setLayout(new BorderLayout());
		contentPane.add(board, BorderLayout.CENTER);
		//added the panel
		setPlayerDetails();
		// added the die
		frame.pack();
		frame.setVisible(true);
	}
	
	private static ImagePanel addBoardImage(){
		final BufferedImage img = new ImageUtility().scaleImage(1000, 1000, "images/background.jpg");
		ImagePanel panel = new ImagePanel(new ImageIcon(img).getImage());
		return panel;
	}
	
	private static JPanel addDiePanel(){
		JPanel diePanel = new JPanel();
		diePanel.setLayout(new GridLayout(1, 2,5,5));
		_leftDie = new Die();
		_rightDie = new Die();
		diePanel.add(_leftDie);
		diePanel.add(_rightDie);
		return diePanel;
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
	
	public static void setPlayerDetails() {
		contentPane.add(new MonopolyOptions(frame, currentpName, currentScore), BorderLayout.EAST);

	}

	// public static void main(String args[]) {
	// javax.swing.SwingUtilities.invokeLater(new Runnable() {
	// public void run() {
	// MonopolyMain.init();
	// }
	// });
	// }

}