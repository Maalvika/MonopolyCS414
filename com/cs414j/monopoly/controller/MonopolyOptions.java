package com.cs414j.monopoly.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.cs414j.monopoly.model.Player;
import com.cs414j.monopoly.view.ButtonValidate;
import com.cs414j.monopoly.view.EndForm;
import com.cs414j.monopoly.view.MortgageOptions;
import com.cs414j.monopoly.view.PlayerForm;
import com.cs414j.monopoly.view.PropertyUI;
import com.cs414j.monopoly.view.Token;
import com.cs414j.monopoly.view.SpecialBlocks.Corner;
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
	public static JButton rollDice;
	public static JButton buy;
	public static JButton pay;
	public static JButton build;
	public static JButton mortgage;
	public static JButton tax;
	public static JButton conti;
	public static JButton endGame;
	private int rolledDoubleSix = 0;
	public static Map<PropertyUI, Integer> properties = new HashMap<>();

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
		endGame = new JButton("End Game");
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
		gameOptions.add(endGame);
		buy.setEnabled(false);
		conti.setEnabled(false);
		pay.setEnabled(false);
		build.setEnabled(false);
		mortgage.setEnabled(false);
		tax.setEnabled(false);
		addActionListener();
		return gameOptions;
		
	}
	protected void endGameActionPerformed(ActionEvent evt) {
		MonopolyMain.frame.dispose();
		EndForm f=new EndForm();
		f.setVisible(true);
		
		
	}
	

	protected void payRentActionPerformed(ActionEvent evt) {
		String currentProperty = getPropertyName(MonopolyMain.currentPlayer.getToken());
		int diceValue =MonopolyMain._leftDie.getValue() + MonopolyMain._rightDie.getValue();
		MonopolyMain.currentPlayer.payRent(currentProperty, MonopolyMain.board, diceValue);
		disableButtonSettings();
		
	}
	
	protected void taxActionPerformed(ActionEvent evt) {
		MonopolyMain.currentPlayer.payTax();
		disableButtonSettings();
		
	}

	
	protected void mortgageActionPerformed(ActionEvent evt) {
		Set<String> prop = MonopolyMain.currentPlayer.OwnedSquareName();
		new MortgageOptions(prop);
		
	}

	protected void buyActionPerformed(ActionEvent evt) {
		Player player = MonopolyMain.currentPlayer;
		Token currentToken = player.getToken();
		PropertyUI p = new PropertyUI(currentToken.getxCoordinate(), currentToken.getyCoordinate(), player.getName());
		if(!properties.containsKey(p)) {
			properties.put(p, 0);
		}
		String currentProperty = getPropertyName(currentToken);
		player.buyProperty(currentProperty, MonopolyMain.bank, MonopolyMain.board);
		displayPopUp("Congrats!!!! "+currentProperty+" is yours. \n Your new balance is: $"+player.getBalance());
		disableButtonSettings();
		
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
		disableButtonSettings();
	}

	protected void contiActionPerformed(ActionEvent evt) {
		initButtonSettings();
		switchToNextTurn();
		Player p = MonopolyMain.currentPlayer;
		changePlayerDetails(p);
		Token currentToken = p.getToken();
		if(currentToken.getxCoordinate() == Corner.JUST_VISITING.getXpoint() && 
						currentToken.getyCoordinate() == Corner.JUST_VISITING.getYpoint()) {
			
			
		}
	}

	protected void rollDiceActionPerformed(ActionEvent evt) {
		MonopolyMain._leftDie.roll();
		MonopolyMain._rightDie.roll();
		int diceValue = MonopolyMain._leftDie.getValue()+MonopolyMain._rightDie.getValue();
		if(diceValue == 12) {
			rolledDoubleSix += diceValue;
			if(rolledDoubleSix == 36) {
				rolledDoubleSix = 0;
				Token t = MonopolyMain.currentPlayer.getToken();
				t.setxCoordinate(Corner.JUST_VISITING.getXpoint());
				t.setyCoordinate(Corner.JUST_VISITING.getYpoint());
				PlayerForm.tokens.set(PlayerForm.tokens.indexOf(MonopolyMain.currentPlayer.getToken()), t);
				MonopolyMain.currentPlayer.setToken(t);
				MonopolyMain.panel.changeTokenPosition(PlayerForm.tokens);
				displayPopUp("Oops!!! You rolled a double six, thrice. You'll have to go to jail");
				disableButtonSettings();
			} else {
				displayPopUp("Oops!!! You rolled a double six. Press Ok to roll again");
				initButtonSettings();
			}
			
			// Todo: hook with backend rolled a double
			//MonopolyMain.currentPlayer.
		} else {
			 if(rolledDoubleSix > 0 && rolledDoubleSix <36) {
				
				diceValue = rolledDoubleSix;
				rolledDoubleSix = 0;
			} 
			MonopolyMain.changeBoardImage();
			ButtonValidate.landOnBlock(MonopolyMain.currentPlayer.getToken());
			MonopolyMain.currentPlayer.moveForward(diceValue);
		}
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
	
	private static void disableButtonSettings() {
		MonopolyOptions.rollDice.setEnabled(false);
		MonopolyOptions.buy.setEnabled(false);
		MonopolyOptions.conti.setEnabled(true);
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
		
		endGame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				endGameActionPerformed(evt);
			}
		});
		
	}

	public static String getPropertyName(Token t) {

		if (t.getxCoordinate() == 10) {
			for (PurchasePropertyList.LVBlocks block : PurchasePropertyList.LVBlocks.values()) {
				if (block.getYpoint() == t.getyCoordinate()) {
					return block.name().replaceAll("_", " ");
				}
			}

		} else if (t.getyCoordinate() == 70) {

			for (PurchasePropertyList.UHBlocks block : PurchasePropertyList.UHBlocks.values()) {
				if (block.getXpoint() == t.getxCoordinate()) {
					return block.name().replaceAll("_", " ");
				}
			}

		} else if (t.getxCoordinate() == 890) {
			for (PurchasePropertyList.RVBlocks block : PurchasePropertyList.RVBlocks.values()) {
				if (block.getYpoint() == t.getyCoordinate()) {
					return block.name().replaceAll("_", " ");
				}
			}

		} else if (t.getyCoordinate() == 870) {
			for (PurchasePropertyList.LHBlocks block : PurchasePropertyList.LHBlocks.values()) {
				if (block.getXpoint() == t.getxCoordinate()) {
					return block.name().replaceAll("_", " ");
				}
			}

		}
		return " ";
	}

	public static void displayPopUp(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public static void displayVotePopup(String message, String operation) {
		Object[] options = { "Pay", "Cancel"};
		int n = JOptionPane.showOptionDialog(null, message,
				"Ask User", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
				options[2]);
		switch(n) {
		case JOptionPane.OK_OPTION: 
			if(operation.equals("Jail Fee")) {
				MonopolyMain.currentPlayer.paidJailPenalty();
			}
			break;
		case JOptionPane.NO_OPTION:
			//
			break;
			
		}
		disableButtonSettings();
	}
}
