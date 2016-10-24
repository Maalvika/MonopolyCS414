package com.cs414j.monopoly.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MonopolyOptions extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private static JLabel tokenLabel;
	private static JLabel nameLabel;
	private static JLabel scoreLabel;
	static JButton rollDice;
	static JButton buy;
	static JButton pay;
	static JButton build;
	static JButton mortgage;
	static JButton tax;
	static JButton conti;

	public MonopolyOptions(JFrame frame, String playerName, int playerScore) {
		super(new BorderLayout());
		this.frame = frame;
		JLabel optionLabel = new JLabel("<html><h2> Select the options to choose:</h2></html>");
		JPanel player = createPlayerDetails(playerName, playerScore);
		player.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		player.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));

		JPanel options = createGameOptions();
		options.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		options.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));

		// Lay out the main panel.
		add(optionLabel, BorderLayout.NORTH);
		add(options, BorderLayout.CENTER);
		add(player, BorderLayout.SOUTH);

	}

	private JPanel createPlayerDetails(String name, int score) {
		JLabel title = new JLabel("<html><font size=\"6\">PLAYER DETAILS</font></html>", JLabel.CENTER);
		JPanel playerDetails = new JPanel();
		nameLabel = new JLabel("<html><font size=\"4\"> Player Name: <b>" + name + "</b></font></html>");
		tokenLabel = new JLabel("<html><font size=\"4\"> Player's Token: <b>"
				+ MonopolyMain.tokens.get(MonopolyMain.players.indexOf(name)).getToken().name() + "</b></font></html>");
		scoreLabel = new JLabel("<html><font size=\"4\"> Player Current Score: <b>" + score + "</b></font></html>");
		playerDetails.setLayout(new BoxLayout(playerDetails, BoxLayout.PAGE_AXIS));
		playerDetails.add(title);
		playerDetails.add(nameLabel);
		playerDetails.add(tokenLabel);
		playerDetails.add(scoreLabel);
		return playerDetails;

	}

	public static void changePlayerDetails(String name, int score) {
		nameLabel.setText("<html><font size=\"4\"> Player Name: <b>" + name + "</b></font></html>");
		tokenLabel.setText("<html><font size=\"4\"> Player's Token: <b>"
				+ MonopolyMain.tokens.get(MonopolyMain.players.indexOf(name)).getToken().name() + "</b></font></html>");
		scoreLabel.setText("<html><font size=\"4\"> Player Current Score: <b>" + score + "</b></font></html>");
	}

	private JPanel createGameOptions() {
		JPanel gameOptions = new JPanel();
		rollDice = new JButton("Roll Dice");
		conti = new JButton("Continue");
		buy = new JButton("Buy Property");
		// buy.setPreferredSize(new Dimension(width, height));
		pay = new JButton("Pay Rent");
		build = new JButton("Build on Property");
		mortgage = new JButton("Mortgage Property");
		tax = new JButton("Pay Tax");
		// playerDetails.setLayout(new BoxLayout(playerDetails,
		// BoxLayout.Y_AXIS));
		gameOptions.setLayout(new GridLayout(4, 2, 10, 100));
		gameOptions.add(rollDice);
		gameOptions.add(conti);
		gameOptions.add(buy);
		gameOptions.add(pay);
		gameOptions.add(build);
		gameOptions.add(mortgage);
		gameOptions.add(tax);
		buy.setEnabled(false);
		conti.setEnabled(false);
		pay.setEnabled(false);
		build.setEnabled(false);
		mortgage.setEnabled(false);
		tax.setEnabled(false);
		rollDice.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rollDiceActionPerformed(evt);
			}
		});
		conti.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				contiActionPerformed(evt);
			}
		});
		return gameOptions;

	}

	protected void contiActionPerformed(ActionEvent evt) {
		rollDice.setEnabled(true);

	}

	protected void rollDiceActionPerformed(ActionEvent evt) {
		MonopolyMain._leftDie.roll();
		MonopolyMain._rightDie.roll();
		MonopolyMain.changeBoardImage();
		ButtonValidate.landOnBloack(MonopolyMain.tokens.get(MonopolyMain.players.indexOf(MonopolyMain.currentpName)));

	}

}
