package com.cs414j.monopoly.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
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

import com.cs414j.monopoly.common.Player;
import com.cs414j.monopoly.server.main.ServerMain;
import com.cs414j.monopoly.server.model.MonopolyServerStore;
import com.cs414j.monopoly.view.AuctionPanel;
import com.cs414j.monopoly.view.ButtonValidate;
import com.cs414j.monopoly.view.MortgageOptions;
import com.cs414j.monopoly.view.PropertyUI;
import com.cs414j.monopoly.client.main.ClientMain;
import com.cs414j.monopoly.client.view.EndForm;
import com.cs414j.monopoly.client.view.PlayerDetailForm;
import com.cs414j.monopoly.common.*;
import com.cs414j.monopoly.common.view.SpecialBlocks.Corner;
import com.cs414j.monopoly.common.view.SpecialBlocks.PurchasePropertyList;

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
	public static JButton auction;
	private static int rolledDoubleSix = 0;
	public static Map<PropertyUI, Integer> properties = new HashMap<>();
	private static MonopolyStore serverStore = ClientMain.store;
	

	public MonopolyOptions(JFrame frame, Player p) throws RemoteException {
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

	private JPanel createPlayerDetails(Player p) throws RemoteException {
		JLabel title = new JLabel("<html><font size=\"6\">PLAYER DETAILS: "+
					PlayerDetailForm.myPlayer.getName()+"</font></html>", JLabel.CENTER);
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

	public static void changePlayerDetails(Player p) throws RemoteException {
		nameLabel.setText("<html><font size=\"4\"> Player Name: <b>" + p.getName() + "</b></font></html>");
		tokenLabel.setText("<html><font size=\"4\"> Player's Token: <b>"
				+ p.getToken().getTokenURL().name() + "</b></font></html>");
		scoreLabel.setText("<html><font size=\"4\"> Player's Current Balance: <b>" + p.getBalance()
		+ "</b></font></html>");
	}

	private JPanel createGameOptions() throws RemoteException {
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
		auction = new JButton("Auction");
		// playerDetails.setLayout(new BoxLayout(playerDetails,
		// BoxLayout.Y_AXIS));
		gameOptions.setLayout(new GridLayout(5, 2, 10, 100));
		gameOptions.add(rollDice);
		gameOptions.add(conti);
		gameOptions.add(buy);
		gameOptions.add(pay);
		gameOptions.add(build);
		gameOptions.add(mortgage);
		gameOptions.add(tax);
		gameOptions.add(endGame);
		gameOptions.add(auction);
		if(PlayerDetailForm.myPlayer.getName().equals(serverStore.getCurrentPlayer().getName())) {
			initButtonSettings(); 
		} else {
			disableAll();
		}
		addActionListener();
		return gameOptions;
		
	}
	protected void endGameActionPerformed(ActionEvent evt) throws RemoteException {
		MonopolyMain.frame.dispose();
		EndForm f=new EndForm();
		f.setVisible(true);
		
		
	}
	

	protected void payRentActionPerformed(ActionEvent evt) throws RemoteException {
		String currentProperty = getPropertyName(ClientMain.store.getCurrentPlayer().getToken());
		int diceValue =MonopolyMain._leftDie.getValue() + MonopolyMain._rightDie.getValue();
		ClientMain.store.getCurrentPlayer().payRent(currentProperty, diceValue);
		disableButtonSettings();
		
	}
	
	protected void taxActionPerformed(ActionEvent evt) throws RemoteException {
		ClientMain.store.getCurrentPlayer().payTax();
		disableButtonSettings();
		
	}

	
	protected void mortgageActionPerformed(ActionEvent evt) throws RemoteException {
		Set<String> prop = ClientMain.store.getCurrentPlayer().OwnedSquareName();
		new MortgageOptions(prop);
		disableButtonSettings();
		
	}

	protected void buyActionPerformed(ActionEvent evt) throws RemoteException {
		Player player = ClientMain.store.getCurrentPlayer();
		Token currentToken = player.getToken();
		PropertyUI p = new PropertyUI(currentToken.getxCoordinate(), currentToken.getyCoordinate(), player.getName());
		if(!properties.containsKey(p)) {
			properties.put(p, 0);
		}
		String currentProperty = getPropertyName(currentToken);
		player.buyProperty(currentProperty);
		displayPopUp("Congrats!!!! "+currentProperty+" is yours. \n Your new balance is: $"+ClientMain.store.getCurrentPlayer().getBalance());
		ButtonValidate.buyPropertyEnabled = false;
		disableButtonSettings();
		
	}

	protected void buildActionPerformed(ActionEvent evt) throws RemoteException {
		Player player = ClientMain.store.getCurrentPlayer();
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

	protected void contiActionPerformed(ActionEvent evt) throws RemoteException {
		if (ButtonValidate.buyPropertyEnabled == false) {
			try {
				serverStore.switchToNextTurn(PlayerDetailForm.myClient);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//change details in other player
			//for this we need a method in server that calls client objects
		} else {
			auctionButtonSettings();
		}
	}
	
	protected void auctionActionPerformed(ActionEvent evt) throws RemoteException {
		ButtonValidate.buyPropertyEnabled = false;
		displayAuctionForm();
		
	}

	protected void rollDiceActionPerformed(ActionEvent evt) throws RemoteException {
		MonopolyMain._leftDie.roll();
		MonopolyMain._rightDie.roll();
		int diceValue = MonopolyMain._leftDie.getValue()+MonopolyMain._rightDie.getValue();
		if(diceValue == 12) {
			rolledDoubleSix += diceValue;
			if(rolledDoubleSix == 36) {
				rolledDoubleSix = 0;
				Token t = ClientMain.store.getCurrentPlayer().getToken();
				int x = Corner.JUST_VISITING.getXpoint();
				int y = Corner.JUST_VISITING.getYpoint();
				t.setxCoordinate(x);
				t.setyCoordinate(y);
				serverStore.setTokenCoordinates(t.getTokenURL(), x, y);
				ClientMain.store.getCurrentPlayer().setToken(t);
				MonopolyMain.panel.changeTokenPosition(serverStore.getSelectedTokens());
				displayPopUp("Oops!!! You rolled a double six, thrice. You'll have to go to jail");
				disableButtonSettings();
			} else {
				displayPopUp("Oops!!! You rolled a double six. Press Ok to roll again");
				initButtonSettings();
			}
		} else {
			 if(rolledDoubleSix > 0) {
				
				diceValue += rolledDoubleSix ;
			} 
			try {
				MonopolyMain.changeBoardImage(diceValue);
				serverStore.changeOtherPlayerBoard(PlayerDetailForm.myClient,
						MonopolyMain._leftDie.getValue(), MonopolyMain._rightDie.getValue());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			ButtonValidate.landOnBlock(ClientMain.store.getCurrentPlayer().getToken());
			ClientMain.store.getCurrentPlayer().moveForward(diceValue);
		}
	}
	
	
	private void disableAll(){
		buy.setEnabled(false);
		conti.setEnabled(false);
		pay.setEnabled(false);
		build.setEnabled(false);
		mortgage.setEnabled(false);
		tax.setEnabled(false);
		auction.setEnabled(false);
		rollDice.setEnabled(false);
		
	}
	
	public static void initButtonSettings() {
		MonopolyOptions.rollDice.setEnabled(true);
		MonopolyOptions.buy.setEnabled(false);
		MonopolyOptions.conti.setEnabled(false);
		MonopolyOptions.pay.setEnabled(false);
		MonopolyOptions.build.setEnabled(false);
		MonopolyOptions.tax.setEnabled(false);
		// TODO: check player balance before enabling the button
		MonopolyOptions.mortgage.setEnabled(false);
		MonopolyOptions.auction.setEnabled(false);
		
	}
	
   public static void disableButtonSettings() {
		MonopolyOptions.rollDice.setEnabled(false);
		MonopolyOptions.buy.setEnabled(false);
		MonopolyOptions.conti.setEnabled(false);
		MonopolyOptions.pay.setEnabled(false);
		MonopolyOptions.build.setEnabled(false);
		MonopolyOptions.tax.setEnabled(false);
		// TODO: check player balance before enabling the button
		MonopolyOptions.mortgage.setEnabled(false);
		MonopolyOptions.auction.setEnabled(false);

	}
	
	private static void auctionButtonSettings() {
		MonopolyOptions.conti.setEnabled(false);
		MonopolyOptions.buy.setEnabled(false);
		MonopolyOptions.auction.setEnabled(true);
		
	}
	
	private void addActionListener(){
		
		rollDice.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					rollDiceActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		conti.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					contiActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		build.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					buildActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		buy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					buyActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		mortgage.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					mortgageActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		tax.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					taxActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		pay.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					payRentActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		endGame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					endGameActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		auction.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					auctionActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				options[1]);
		switch(n) {
		case JOptionPane.OK_OPTION: 
			if(operation.equals("Jail Fee")) {
				try {
					ClientMain.store.getCurrentPlayer().paidJailPenalty();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case JOptionPane.NO_OPTION:
			//
			break;
			
		}
		disableButtonSettings();
	}
	
	private static void displayAuctionForm() throws RemoteException {
		JPanel ac = new AuctionPanel(ClientMain.store.getCurrentPlayer());
		ac.setVisible(true);
		//JOptionPane.showMessageDialog(null, ac, "Data Entry", JOptionPane.NO_OPTION);
		//JOptionPane.showOptionDialog(null, ac,"Data Entry", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
		Object[] options = {"OK"};
	    int n = JOptionPane.showOptionDialog(null,
	                   ac,"Auction Form",
	                   JOptionPane.PLAIN_MESSAGE,
	                   JOptionPane.QUESTION_MESSAGE,
	                   null,
	                   options,
	                   options[0]);
	    if(n==0) {
	    	int max = 0;
	    	Player winner = null;
	        for(int i=0; i<AuctionPanel.tempPlayer.size();i++) {
	        	if(max<Integer.parseInt(AuctionPanel.bidPrice[i].getText())) {
	        		max = Integer.parseInt(AuctionPanel.bidPrice[i].getText());
	        		winner = AuctionPanel.tempPlayer.get(i);
	        	}
	        }
	        Player player = ClientMain.store.getCurrentPlayer();
			Token currentToken = player.getToken();
			String currentProperty = MonopolyOptions.getPropertyName(currentToken);
			System.out.println("current:"+currentProperty);
	        winner.buyProperty(currentProperty);
	        MonopolyOptions.displayPopUp(winner.getName()+" bought the property  "+currentProperty+" for $"+max);
	        MonopolyOptions.disableButtonSettings();
	    }
	}
}
