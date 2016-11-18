package com.cs414j.monopoly.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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

import com.cs414j.monopoly.server.main.ServerMain;
import com.cs414j.monopoly.server.model.MonopolyServerStore;
import com.cs414j.monopoly.view.AuctionPanel;
import com.cs414j.monopoly.view.ButtonValidate;
import com.cs414j.monopoly.view.MortgageOptions;
import com.cs414j.monopoly.view.PropertyUI;
import com.cs414j.monopoly.client.main.ClientMain;
import com.cs414j.monopoly.client.view.AllPlayerInfoPanel;
import com.cs414j.monopoly.client.view.EndForm;
import com.cs414j.monopoly.client.view.FixedDieValuePanel;
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
	//ID24
	public static JButton UnMortgage;
	public static JButton rollFixedNum;
	public static JButton seeAllDetails;
	private static int rolledDoubleSix = 0;
	public static Map<PropertyUI, Integer> properties = new HashMap<>();
	private static MonopolyStore serverStore = ClientMain.store;
	private static AuctionPanel ac;

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
		JLabel title = new JLabel(
				"<html><font size=\"6\">PLAYER DETAILS: " + PlayerDetailForm.myPlayer.getName() + "</font></html>",
				JLabel.CENTER);
		JPanel playerDetails = new JPanel();
		nameLabel = new JLabel("<html><font size=\"4\"> Player Name: <b>" + p.getName() + "</b></font></html>");
		tokenLabel = new JLabel("<html><font size=\"4\"> Player's Token: <b>" + p.getToken().getTokenURL().name()
				+ "</b></font></html>");
		scoreLabel = new JLabel(
				"<html><font size=\"4\"> Player's Current Balance: <b>" + p.getBalance() + "</b></font></html>");
		playerDetails.setLayout(new BoxLayout(playerDetails, BoxLayout.PAGE_AXIS));
		playerDetails.add(title);
		playerDetails.add(nameLabel);
		playerDetails.add(tokenLabel);
		playerDetails.add(scoreLabel);
		return playerDetails;

	}

	public static void changePlayerDetails(Player p) throws RemoteException {
		nameLabel.setText("<html><font size=\"4\"> Player Name: <b>" + p.getName() + "</b></font></html>");
		tokenLabel.setText("<html><font size=\"4\"> Player's Token: <b>" + p.getToken().getTokenURL().name()
				+ "</b></font></html>");
		scoreLabel.setText(
				"<html><font size=\"4\"> Player's Current Balance: <b>" + p.getBalance() + "</b></font></html>");
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
		//ID24
		UnMortgage = new JButton("UnMortgage Property");
		rollFixedNum = new JButton("Roll Fixed Num");
		seeAllDetails = new JButton("Show Everyone's Score");
		// playerDetails.setLayout(new BoxLayout(playerDetails,
		// BoxLayout.Y_AXIS));
		gameOptions.setLayout(new GridLayout(6, 2, 10, 100));
		gameOptions.add(rollDice);
		gameOptions.add(rollFixedNum);
		gameOptions.add(conti);
		gameOptions.add(buy);
		gameOptions.add(pay);
		gameOptions.add(build);
		gameOptions.add(mortgage);
		gameOptions.add(tax);
		gameOptions.add(auction);
		//ID24
		gameOptions.add(UnMortgage);
		gameOptions.add(seeAllDetails);
		gameOptions.add(endGame);
		if (PlayerDetailForm.myPlayer.getName().equals(serverStore.getCurrentPlayer().getName())) {
			initButtonSettings();
		} else {
			disableAll();
		}
		addActionListener();
		return gameOptions;

	}

	protected void endGameActionPerformed(ActionEvent evt) throws RemoteException {
		MonopolyMain.frame.dispose();
		EndForm f = new EndForm();
		f.setVisible(true);
		ClientMain.store.endGameIfAnyPlayerQuits(PlayerDetailForm.myPlayer.getName());
	}

	protected void payRentActionPerformed(ActionEvent evt) throws RemoteException {
		String currentProperty = getPropertyName(ClientMain.store.getCurrentPlayer().getToken());
		System.out.println("currentProp:"+currentProperty);
		if (ButtonValidate.ischance == false) {
			System.out.println("is chance false");
			int diceValue = MonopolyMain._leftDie.getValue() + MonopolyMain._rightDie.getValue();
			ClientMain.store.getCurrentPlayer().payRent(currentProperty, diceValue);
			displayPopUp("You paid rent: $" + ClientMain.store.getCurrentPlayer().getRent(currentProperty)
					+ " to Player: " + ClientMain.store.getCurrentPlayer().getOwner(currentProperty).getName());
			ClientMain.store.sendRentMessageToOwner(currentProperty);

		} else {
			System.out.println("is chance true");
			ButtonValidate.ischance = false;
			Object[] options = { "OK" };
			int n = JOptionPane.showOptionDialog(null, "Press OK to roll Dice", "Roll Dice",
					JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			if (n == 0) {
				MonopolyMain._leftDie.roll();
				MonopolyMain._rightDie.roll();
				int diceValue = MonopolyMain._leftDie.getValue() + MonopolyMain._rightDie.getValue();
				System.out.println("diceValue: " + diceValue);
				displayPopUp("You paid rent: $" + diceValue * 10 + " to Player: "
						+ ClientMain.store.getCurrentPlayer().getOwner(currentProperty).getName());
				ClientMain.store.callUtilityRent(diceValue, currentProperty);
			}
		}
		disableButtonSettings();
	}

	protected void taxActionPerformed(ActionEvent evt) throws RemoteException {
		ClientMain.store.getCurrentPlayer().payTax();
		displayPopUp("You paid your tax!!! You new balance is: " + ClientMain.store.getCurrentPlayer().getBalance());
		disableButtonSettings();

	}

	protected void mortgageActionPerformed(ActionEvent evt) throws RemoteException {
		Set<String> prop = ClientMain.store.getCurrentPlayer().OwnedSquareName();
		new MortgageOptions(prop);
		ButtonValidate.landOnBlock(ClientMain.store.getCurrentPlayer().getToken());

	}

	protected void UnMortgageActionPerformed(ActionEvent evt) throws RemoteException {
		Set<String> prop = ClientMain.store.getCurrentPlayer().mortgagedSquareName();
		new MortgageOptions(prop);
		

	}


	protected void buyActionPerformed(ActionEvent evt) throws RemoteException {
		Player player = ClientMain.store.getCurrentPlayer();
		Token currentToken = player.getToken();
		PropertyUI p = new PropertyUI(currentToken.getxCoordinate(), currentToken.getyCoordinate(), player.getName(),
				PlayerColor.valueOf(player.getColor()));
		if (!properties.containsKey(p)) {
			properties.put(p, 0);
		}
		MonopolyMain.panel.addPlayerComponents(properties);
		String currentProperty = getPropertyName(currentToken);
		player.buyProperty(currentProperty);
		ButtonValidate.buyPropertyEnabled = false;
		if (!ClientMain.store.getCurrentPlayer().isLessBalance()) {
			displayPopUp("Congrats!!!! " + currentProperty + " is yours. \n Your new balance is: $"
					+ ClientMain.store.getCurrentPlayer().getBalance());

			String otherMessage = "Property: " + currentProperty + " is sold to Player: " + player.getName();
			ClientMain.store.placePropertyToken();
			ClientMain.store.sendMessageToAll(otherMessage);
		}
		disableButtonSettings();

	}

	protected void buildActionPerformed(ActionEvent evt) throws RemoteException {
		Player player = ClientMain.store.getCurrentPlayer();
		Token currentToken = player.getToken();
		PropertyUI p = new PropertyUI(currentToken.getxCoordinate(), currentToken.getyCoordinate(), player.getName(),
				PlayerColor.valueOf(player.getColor()));
		if (properties.containsKey(p) && properties.get(p) != 4) {
			properties.put(p, properties.get(p) + 1);
		} else {
			properties.put(p, 1);
		}
		MonopolyMain.panel.addPlayerComponents(properties);
		ClientMain.store.placePropertyToken();
		disableButtonSettings();
	}

	protected void contiActionPerformed(ActionEvent evt) throws RemoteException {
		System.out.println("auction enabled:"+ButtonValidate.buyPropertyEnabled);
		if (ButtonValidate.buyPropertyEnabled == false) {
			try {
				serverStore.switchToNextTurn(PlayerDetailForm.myClient);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// change details in other player
			// for this we need a method in server that calls client objects
		} else {
			auctionButtonSettings();
		}
	}

	protected void auctionActionPerformed(ActionEvent evt) throws RemoteException {
		ButtonValidate.buyPropertyEnabled = false;
		Player player = ClientMain.store.getCurrentPlayer();
		Token currentToken = player.getToken();
		String currentProperty = getPropertyName(currentToken);
		ClientMain.store.sendPropertyForAuction(currentProperty);
		disableButtonSettings();
	}

	protected void rollFixedNumActionPerformed(ActionEvent evt) {
		FixedDieValuePanel diePanel = new FixedDieValuePanel();
		Object[] options = { "OK" };
		int n = JOptionPane.showOptionDialog(null, diePanel, "Dice Value", JOptionPane.INFORMATION_MESSAGE,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if (n == 0) {
			diePanel.setDiceValue();
		}
		MonopolyMain._leftDie.setValue(diePanel.getLeftDieValue());
		MonopolyMain._rightDie.setValue(diePanel.getRightDieValue());
		int diceValue = MonopolyMain._leftDie.getValue() + MonopolyMain._rightDie.getValue();
		try {
			diceActionOnRoll(diceValue);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void showDetailsActionPerformed(ActionEvent evt) throws HeadlessException, RemoteException {
		Object[] options = { "OK" };
		JOptionPane.showOptionDialog(null, new AllPlayerInfoPanel(), "Player Information",
				JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

	}

	protected void rollDiceActionPerformed(ActionEvent evt) throws RemoteException {
		MonopolyMain._leftDie.roll();
		MonopolyMain._rightDie.roll();
		int diceValue = MonopolyMain._leftDie.getValue() + MonopolyMain._rightDie.getValue();
		diceActionOnRoll(diceValue);

	}

	// disable all player
	public static void disableAll() {
		buy.setEnabled(false);
		conti.setEnabled(false);
		pay.setEnabled(false);
		build.setEnabled(false);
		mortgage.setEnabled(false);
		//ID24
		UnMortgage.setEnabled(false);
		tax.setEnabled(false);
		auction.setEnabled(false);
		rollDice.setEnabled(false);
		rollFixedNum.setEnabled(false);

	}

	// enable roll dice and disable all
	public static void initButtonSettings() {
		MonopolyOptions.rollDice.setEnabled(true);
		rollFixedNum.setEnabled(true);
		MonopolyOptions.buy.setEnabled(false);
		MonopolyOptions.conti.setEnabled(false);
		MonopolyOptions.pay.setEnabled(false);
		MonopolyOptions.build.setEnabled(false);
		MonopolyOptions.tax.setEnabled(false);
		// TODO: check player balance before enabling the button
		MonopolyOptions.mortgage.setEnabled(false);
		//ID24
		MonopolyOptions.UnMortgage.setEnabled(false);
		MonopolyOptions.auction.setEnabled(false);

	}

	// Disable all but enable continue button
	public static void disableButtonSettings() {
		MonopolyOptions.rollDice.setEnabled(false);
		rollFixedNum.setEnabled(false);
		MonopolyOptions.buy.setEnabled(false);
		MonopolyOptions.conti.setEnabled(true);
		MonopolyOptions.pay.setEnabled(false);
		MonopolyOptions.build.setEnabled(false);
		MonopolyOptions.tax.setEnabled(false);
		// TODO: check player balance before enabling the button
		MonopolyOptions.mortgage.setEnabled(false);
		//ID24
		MonopolyOptions.UnMortgage.setEnabled(false);
		MonopolyOptions.auction.setEnabled(false);

	}

	// enable auction button
	private static void auctionButtonSettings() {
		MonopolyOptions.conti.setEnabled(false);
		MonopolyOptions.buy.setEnabled(false);
		MonopolyOptions.auction.setEnabled(true);

	}

	private void addActionListener() {

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

		UnMortgage.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					UnMortgageActionPerformed(evt);
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

		rollFixedNum.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rollFixedNumActionPerformed(evt);

			}
		});

		seeAllDetails.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					showDetailsActionPerformed(evt);
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
		Object[] options = { "Pay", "Cancel" };
		int n = JOptionPane.showOptionDialog(null, message, "Ask User", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
		switch (n) {
		case JOptionPane.OK_OPTION:
			if (operation.equals("Jail Fee")) {
				try {
					ClientMain.store.getCurrentPlayer().paidJailPenalty();
					MonopolyMain.fromGoToJail = false;
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

	public static void displayAuctionForm(String propertyName) throws RemoteException {
		ac = new AuctionPanel(propertyName);
		ac.setVisible(true);
		Object[] options = { "Place Your Bid" };
		int n = JOptionPane.showOptionDialog(null, ac, "Auction Form", JOptionPane.PLAIN_MESSAGE,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (n == 0) {
			ac.setBidValue();
		}
	}

	public static int getBidValue() {
		return ac.getBidValue();
	}

	private static void diceActionOnRoll(int diceValue) throws RemoteException {
		Token t = ClientMain.store.getCurrentPlayer().getToken();
		if (diceValue == 12) {
			rolledDoubleSix += diceValue;
			if (rolledDoubleSix == 36) {
				rolledDoubleSix = 0;
				int x = Corner.JUST_VISITING.getXpoint();
				int y = Corner.JUST_VISITING.getYpoint();
				t.setxCoordinate(x);
				t.setyCoordinate(y);
				List<Token> temp = ClientMain.store.getSelectedTokens();
				temp.set(ClientMain.store.getSelectedTokens().indexOf(ClientMain.store.getCurrentPlayer().getToken()),
						t);
				ClientMain.store.setSelectedTokens(temp);
				ClientMain.store.getCurrentPlayer().setToken(t);
				MonopolyMain.panel.changeTokenPosition(serverStore.getSelectedTokens());
				ClientMain.store.changeOtherPlayerBoard(PlayerDetailForm.myClient, MonopolyMain._leftDie.getValue(),
						MonopolyMain._rightDie.getValue());
				displayPopUp("Oops!!! You rolled a double six, thrice. You'll have to go to jail");
				MonopolyMain.fromGoToJail = true;
				disableButtonSettings();

			} else {
				displayPopUp("Oops!!! You rolled a double six. Press Ok to roll again");
				initButtonSettings();
			}
		} else {
			if (rolledDoubleSix > 0) {

				diceValue += rolledDoubleSix;
			}
			if (t.getxCoordinate() == Corner.JUST_VISITING.getXpoint()
					&& t.getyCoordinate() == Corner.JUST_VISITING.getYpoint() && MonopolyMain.fromGoToJail == true) {
				if (PlayerDetailForm.myPlayer.hasJailPass()) {
					PlayerDetailForm.myPlayer.setHasJailPass(false);
					MonopolyMain.fromGoToJail = false;
					displayPopUp("Using your \"Get out of Jail card\"");
				} else {
					displayVotePopup("Do you want to pay $50 to get out of Jail", "Jail Fee");
				}

			}
			if (MonopolyMain.fromGoToJail == false) {
				try {
					MonopolyMain.changeBoardImage(diceValue);
					serverStore.changeOtherPlayerBoard(PlayerDetailForm.myClient, MonopolyMain._leftDie.getValue(),
							MonopolyMain._rightDie.getValue());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				ClientMain.store.getCurrentPlayer().moveForward(diceValue);
				ButtonValidate.landOnBlock(ClientMain.store.getCurrentPlayer().getToken());
			}
		}

	}

}
