package com.cs414j.monopoly.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cs414j.monopoly.model.Player;
import com.cs414j.monopoly.view.SpecialBlocks.PurchasePropertyList;

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
	static Map<PropertyUI, Integer> properties = new HashMap<>();

	public MonopolyOptions(JFrame frame, Player p) {
		super(new BorderLayout());
		this.frame = frame;
		JLabel optionLabel = new JLabel("<html><h2> Select the options to choose:</h2></html>");
		JPanel player = createPlayerDetails(p);
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

	private JPanel createPlayerDetails(Player p) {
		JLabel title = new JLabel("<html><font size=\"6\">PLAYER DETAILS</font></html>", JLabel.CENTER);
		JPanel playerDetails = new JPanel();
		nameLabel = new JLabel("<html><font size=\"4\"> Player Name: <b>" + p.getName() + "</b></font></html>");
		tokenLabel = new JLabel("<html><font size=\"4\"> Player's Token: <b>"
				+ p.getToken().getTokenURL().name() + "</b></font></html>");
		scoreLabel = new JLabel("<html><font size=\"4\"> Player's Current Balance: <b>" + p.getBalance() + "</b></font></html>");
		playerDetails.setLayout(new BoxLayout(playerDetails, BoxLayout.PAGE_AXIS));
		playerDetails.add(title);
		playerDetails.add(nameLabel);
		playerDetails.add(tokenLabel);
		playerDetails.add(scoreLabel);
		return playerDetails;

	}

	public static void changePlayerDetails(Player p) {
		nameLabel.setText("<html><font size=\"4\"> Player Name: <b>" + p.getName() + "</b></font></html>");
		tokenLabel.setText("<html><font size=\"4\"> Player's Token: <b>"
				+ p.getToken().getTokenURL().name() + "</b></font></html>");
		scoreLabel.setText("<html><font size=\"4\"> Player's Current Balance: <b>" + p.getBalance()
		+ "</b></font></html>");
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
		addActionListener();
		return gameOptions;
		
	}
	

	protected void payRentActionPerformed(ActionEvent evt) {
		String currentProperty = getPropertyName(MonopolyMain.currentPlayer.getToken());
		int diceValue =MonopolyMain._leftDie.getValue() + MonopolyMain._rightDie.getValue();
		MonopolyMain.currentPlayer.payRent(currentProperty, MonopolyMain.board, diceValue);
		
	}
	
	protected void taxActionPerformed(ActionEvent evt) {
		MonopolyMain.currentPlayer.payTax();
		
	}

	
	protected void mortgageActionPerformed(ActionEvent evt) {
		Set<String> prop = new HashSet<>();
		prop.add("abc");
		prop.add("def");
		prop.add("Boston");
		new MortgageOptions(prop);
		
	}

	protected void buyActionPerformed(ActionEvent evt) {
		Player player = MonopolyMain.currentPlayer;
		Token currentToken = player.getToken();
		PropertyUI p = new PropertyUI(currentToken.getxCoordinate(), currentToken.getyCoordinate(), player.getName());
		if(!properties.containsKey(p)) {
			properties.put(p, 0);
		}
		String currentProperty = getPropertyName(currentToken).replaceAll("_", " ");
		player.buyProperty(currentProperty, MonopolyMain.bank, MonopolyMain.board);
		
	}

	protected void buildActionPerformed(ActionEvent evt) {
		Player player = MonopolyMain.currentPlayer;
		Token currentToken = player.getToken();
		PropertyUI p = new PropertyUI(currentToken.getxCoordinate(), currentToken.getyCoordinate(), player.getName());
		if(properties.containsKey(p) && properties.get(p)!=4) {
			properties.put(p, properties.get(p)+1);
		} 	else {
			properties.put(p, 1);
		}
		MonopolyMain.panel.buildHouse(properties);
	}

	protected void contiActionPerformed(ActionEvent evt) {
		initButtonSettings();
		switchToNextTurn();
		changePlayerDetails(MonopolyMain.currentPlayer);
		

	}

	protected void rollDiceActionPerformed(ActionEvent evt) {
		MonopolyMain._leftDie.roll();
		MonopolyMain._rightDie.roll();
		MonopolyMain.changeBoardImage();
		ButtonValidate.landOnBlock(MonopolyMain.currentPlayer.getToken());
		int diceValue = MonopolyMain._leftDie.getValue()+MonopolyMain._rightDie.getValue();
		MonopolyMain.currentPlayer.moveForward(diceValue);
	}
	
	private void switchToNextTurn(){
		int currentIndex = MonopolyMain.players.indexOf(MonopolyMain.currentPlayer);
		if(currentIndex == MonopolyMain.players.size()-1) {
			MonopolyMain.currentPlayer = MonopolyMain.players.get(0);
		} else {
			MonopolyMain.currentPlayer = MonopolyMain.players.get(currentIndex+1);
		}
	}
	
	private void initButtonSettings() {
		MonopolyOptions.rollDice.setEnabled(true);
		MonopolyOptions.buy.setEnabled(false);
		MonopolyOptions.conti.setEnabled(false);
		MonopolyOptions.pay.setEnabled(false);
		MonopolyOptions.build.setEnabled(false);
		MonopolyOptions.tax.setEnabled(false);
		// TODO: check player balance before enabling the button
		MonopolyOptions.mortgage.setEnabled(false);

	}
	
	private void addActionListener(){
		
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
		build.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buildActionPerformed(evt);
			}
		});
		buy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buyActionPerformed(evt);
			}
		});
		
		mortgage.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				mortgageActionPerformed(evt);
			}
		});
		
		tax.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				taxActionPerformed(evt);
			}
		});
		
		pay.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				payRentActionPerformed(evt);
			}
		});
		
	}


	private String getPropertyName(Token t) {

		if (t.getxCoordinate() == 10) {
			for (PurchasePropertyList.LVBlocks block : PurchasePropertyList.LVBlocks.values()) {
				if (block.getYpoint() == t.getyCoordinate()) {
					return block.name();
				}
			}

		} else if (t.getyCoordinate() == 70) {

			for (PurchasePropertyList.UHBlocks block : PurchasePropertyList.UHBlocks.values()) {
				if (block.getXpoint() == t.getxCoordinate()) {
					return block.name();
				}
			}

		} else if (t.getxCoordinate() == 890) {
			for (PurchasePropertyList.RVBlocks block : PurchasePropertyList.RVBlocks.values()) {
				if (block.getYpoint() == t.getyCoordinate()) {
					return block.name();
				}
			}

		} else if (t.getyCoordinate() == 870) {
			for (PurchasePropertyList.LHBlocks block : PurchasePropertyList.LHBlocks.values()) {
				if (block.getXpoint() == t.getxCoordinate()) {
					return block.name();
				}
			}

		}
		return " ";
	}

}
