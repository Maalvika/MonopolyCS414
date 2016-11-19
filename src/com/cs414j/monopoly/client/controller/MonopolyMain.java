package com.cs414j.monopoly.client.controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.cs414j.monopoly.client.common.view.SpecialBlocks.Corner;
import com.cs414j.monopoly.client.main.ClientMain;
import com.cs414j.monopoly.client.main.InitcardDeck;
import com.cs414j.monopoly.client.view.Die;
import com.cs414j.monopoly.client.view.ImagePanel;
import com.cs414j.monopoly.client.view.ImageUtility;
import com.cs414j.monopoly.common.Player;
import com.cs414j.monopoly.common.Token;
import com.cs414j.monopoly.server.model.Bank;
import com.cs414j.monopoly.server.model.Board;



public class MonopolyMain {

	public static JFrame frame;
	private static Container contentPane;
    public static Die _leftDie;     
    public static Die _rightDie;
    public static ImagePanel panel;
	private static JPanel boardPanel;
	public static InitcardDeck cards;
	public static boolean fromGoToJail = false;
	
	
    

	public static void init() throws RemoteException {

		// added Monopoly game board image
		cards = new InitcardDeck();
		boardPanel = addBoardImage();
		frame = new JFrame();
		contentPane = frame.getContentPane();
		JPanel board = new JPanel(new GridLayout(1, 2));
		board.add(addDiePanel());
		board.add(new MonopolyOptions(frame, ClientMain.store.getCurrentPlayer()));
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(boardPanel,BorderLayout.CENTER);
		contentPane.add(board,BorderLayout.EAST);
		//added the panel
		//setPlayerDetails();
		// added the die
		frame.pack();
		frame.setVisible(true);
	} 
	
	private static JPanel addBoardImage() throws RemoteException {

		final BufferedImage img = new ImageUtility().scaleImage(1000, 980, "images/background.jpg");
		panel = new ImagePanel(new ImageIcon(img).getImage(), ClientMain.store.getSelectedTokens());
		return panel;
	}

	public static void changeBoardImage(int diceValue) throws RemoteException {
		
		System.out.println("dice value: "+diceValue);
		Token t = adjustPosition(ClientMain.store.getCurrentPlayer().getToken(), diceValue);
		if(checkForGoJail(t) == true) {
			fromGoToJail  = true;
			t = sendToJail(t);
		}
		List <Token> temp = ClientMain.store.getSelectedTokens();
		temp.set(ClientMain.store.getSelectedTokens().indexOf(ClientMain.store.getCurrentPlayer().getToken()), t);
		ClientMain.store.setSelectedTokens(temp);
		ClientMain.store.getCurrentPlayer().setToken(t);
		panel.changeTokenPosition(temp);
	}

	private static Token adjustPosition(Token t, int diceValue) throws RemoteException {

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
			if ((y - 80 * diceValue) > 70) {
				t.setyCoordinate(y - 80 * diceValue);
			} else if (y==70) {
				t.setxCoordinate(90);
			}else {
				int ypoint = (y - 70) / 80;
				// since the corner is a bigger block so we need 1 step more to
				// get the
				// token at correct position
				int xpoint = diceValue - ypoint +1;
				t.setxCoordinate(x + 80 * xpoint);
				t.setyCoordinate(70);
			}
		} else if (y == 70 && x!=890) {
			if ((x + 80 * diceValue) <=890) {
				t.setxCoordinate(x + 80 * diceValue);
			} else {
				int xpoint = (890 - x) / 80;
				// since the corner is a bigger block so we need 1 step more to
				// get the
				// token at correct position
				int ypoint = diceValue - xpoint;
				t.setxCoordinate(890);
				t.setyCoordinate(y + 80 * ypoint);

			}

		} else if (x == 890 && y!=870) {
			if ((y + 80 * diceValue) <= 870) {
				t.setyCoordinate(y + 80 * diceValue);
			} else {
				int ypoint = (870 - y) / 80;
				// since the corner is a bigger block so we need 1 step more to
				// get the
				// token at correct position
				int xpoint = diceValue - ypoint;
				t.setyCoordinate(870);
				t.setxCoordinate(x - 80 * xpoint);
			}

		} else if (y == 870 && x != 10 ) {
			if (x - 80 * diceValue >= 170) {
				t.setxCoordinate(x - 80 * diceValue);
			} else if(x - 80 * diceValue < 10) {
				int xpoint = ((x - 10) / 80) - 1;
				// since the corner is a bigger block so we need 1 step more to
				// get the
				// token at correct position
				int ypoint = diceValue - xpoint;
				t.setxCoordinate(10);
				t.setyCoordinate(y - 80 * ypoint);
				
				ClientMain.store.getCurrentPlayer().passGo(diceValue);
				MonopolyOptions.displayPopUp("$200 received");
			} else {
				t.setxCoordinate(10);
				ClientMain.store.getCurrentPlayer().passGo(diceValue);
				MonopolyOptions.displayPopUp("$200 received");
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

	
//	public static void setPlayerDetails(Player p) throws RemoteException {
//		currentPlayer = p;
//		contentPane.add(new MonopolyOptions(frame, currentPlayer), BorderLayout.EAST);
//
//	}
	
	public static JPanel setPlayerDetails() throws RemoteException {
		JPanel p = new JPanel();
		p.add(new MonopolyOptions(frame, ClientMain.store.getCurrentPlayer()));
		return p;

	}
	
	public static boolean checkForGoJail(Token t){
		if(t.getxCoordinate() ==  Corner.GO_JAIL.getXpoint() && 
				t.getyCoordinate() == Corner.GO_JAIL.getYpoint()) {
			return true;
		}
		return false;
	}
	
	public static Token sendToJail(Token t) throws RemoteException{
		t.setxCoordinate(Corner.JUST_VISITING.getXpoint());
		t.setyCoordinate(Corner.JUST_VISITING.getYpoint());
		ClientMain.store.getCurrentPlayer().landedOnGoToJail(30);
		return t;
	}
}