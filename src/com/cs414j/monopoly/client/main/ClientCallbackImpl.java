package com.cs414j.monopoly.client.main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.swing.JOptionPane;

import com.cs414j.monopoly.client.view.EndForm;
import com.cs414j.monopoly.client.view.PlayerDetailForm;
import com.cs414j.monopoly.common.Player;
import com.cs414j.monopoly.common.PlayerColor;
import com.cs414j.monopoly.common.Token;
import com.cs414j.monopoly.common.view.SpecialBlocks.Others;
import com.cs414j.monopoly.controller.EndTimerTask;
import com.cs414j.monopoly.controller.MonopolyMain;
import com.cs414j.monopoly.controller.MonopolyOptions;
import com.cs414j.monopoly.view.ButtonValidate;
import com.cs414j.monopoly.view.PropertyUI;

public class ClientCallbackImpl extends UnicastRemoteObject implements ClientCallback {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	public ClientCallbackImpl(String name) throws RemoteException {
		this.name = name;
	}

	@Override
	public void changeBoardImage(int leftDie, int rightDie) throws RemoteException {
		MonopolyMain._leftDie.setValue(leftDie);
		MonopolyMain._rightDie.setValue(rightDie);
		MonopolyMain.panel.changeTokenPosition(ClientMain.store.getSelectedTokens());

	}

	@Override
	public String getName() {
		return name;

	}

	@Override
	public void startMonopolyGame(int END_TIME_MIN) throws RemoteException {
		ClientMain.detail.setVisible(false);
		MonopolyMain.init();
		new EndTimerTask(END_TIME_MIN * 1000 * 60);

	}

	@Override
	public void showErrorStartGame(String message) throws RemoteException {
		ClientMain.detail.setVisible(false);
		JOptionPane.showMessageDialog(null, message);

	}

	@Override
	public void changeOtherPlayerDetailsAndControlButton(Player p) throws RemoteException {
		MonopolyOptions.changePlayerDetails(p);
		System.out.println("current:" + p.getName() + " myPlayer:" + PlayerDetailForm.myPlayer.getName());
		if (PlayerDetailForm.myPlayer.getName().equals(p.getName())) {
			MonopolyOptions.initButtonSettings();
		} else {
			MonopolyOptions.disableAll();
		}

	}
	
	@Override
	public void changePlayerDetails() throws RemoteException {
		MonopolyOptions.changePlayerDetails(ClientMain.store.getCurrentPlayer());

	}

	public void showMsg(String otherMessage) throws RemoteException {
		System.out.println("Message recieved:" + otherMessage);
		JOptionPane.showMessageDialog(null, otherMessage);

	}

	@Override
	public void auctionProperty(String name) throws RemoteException {
		MonopolyOptions.displayAuctionForm(name);

	}

	@Override
	public int getBidValue() throws RemoteException {
		return MonopolyOptions.getBidValue();

	}

	@Override
	public void endGame() throws RemoteException {
		MonopolyMain.frame.dispose();
		EndForm f = new EndForm();
		f.setVisible(true);

	}

	@Override
	public void moveChance(String propertyName) throws RemoteException {
		System.out.println("Property Name:" + propertyName);
		moveToken(propertyName);
	}

	private void moveToken(String propertyName) throws RemoteException {
		MoveChance position = null;
		for (MoveChance mv : MoveChance.values()) {
			if (mv.name().replace("_", " ").equals(propertyName)) {
				position = mv;
			}
		}
		Token t = ClientMain.store.getCurrentPlayer().getToken();
		for(Others o: Others.values()) {
			if(t.getxCoordinate() == o.getXpoint() && t.getyCoordinate() == o.getYpoint()) {
				ButtonValidate.ischance =  true;
			}
		}
		t.setxCoordinate(position.getXpoint());
		t.setyCoordinate(position.getYpoint());
		if(t.getxCoordinate() == MoveChance.Visiting_Jail.getXpoint() && 
				t.getyCoordinate() ==  MoveChance.Visiting_Jail.getYpoint()) {
			MonopolyMain.fromGoToJail  = true;
		}
		List<Token> temp = ClientMain.store.getSelectedTokens();
		temp.set(ClientMain.store.getSelectedTokens().indexOf(ClientMain.store.getCurrentPlayer().getToken()), t);
		ClientMain.store.setSelectedTokens(temp);
		ClientMain.store.getCurrentPlayer().setToken(t);
		MonopolyMain.panel.changeTokenPosition(temp);
		ButtonValidate.landOnBlock(ClientMain.store.getCurrentPlayer().getToken());
		ClientMain.store.changeOtherPlayerBoard(PlayerDetailForm.myClient, MonopolyMain._leftDie.getValue(),
				MonopolyMain._rightDie.getValue());


	}

	@Override
	public void buyPropertyAction(String pName) throws RemoteException {
		Player currentPlayer = ClientMain.store.getCurrentPlayer();
		Player auctionPlayer = null;
		if(!currentPlayer.getName().equals(pName)) {
			for(Player p: ClientMain.store.getPlayers()) {
				if(p.getName().equals(pName)) {
					auctionPlayer = p; 
				} 
			}
		} else {
			auctionPlayer = currentPlayer;
		}
		PropertyUI prop = new PropertyUI(currentPlayer.getToken().getxCoordinate(), currentPlayer.getToken().getyCoordinate(),
				auctionPlayer.getName(), PlayerColor.valueOf(auctionPlayer.getColor()));
		if(!MonopolyOptions.properties.containsKey(prop)) {
			MonopolyOptions.properties.put(prop, 0);
		} else {
			int val = MonopolyOptions.properties.get(prop);
			val ++;
			MonopolyOptions.properties.put(prop, val);
		}
		MonopolyMain.panel.addPlayerComponents(MonopolyOptions.properties);
		
	}

}
