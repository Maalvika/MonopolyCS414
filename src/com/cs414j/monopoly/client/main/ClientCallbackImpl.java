package com.cs414j.monopoly.client.main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.swing.JOptionPane;

import com.cs414j.monopoly.client.view.EndForm;
import com.cs414j.monopoly.client.view.MoveChance;
import com.cs414j.monopoly.client.view.PlayerDetailForm;
import com.cs414j.monopoly.common.Player;
import com.cs414j.monopoly.common.Token;
import com.cs414j.monopoly.controller.EndTimerTask;
import com.cs414j.monopoly.controller.MonopolyMain;
import com.cs414j.monopoly.controller.MonopolyOptions;
import com.cs414j.monopoly.view.ButtonValidate;

public class ClientCallbackImpl extends UnicastRemoteObject implements ClientCallback{
	
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
    	new EndTimerTask(END_TIME_MIN*1000*60);
		
	}

	@Override
	public void showErrorStartGame(String message) throws RemoteException {
		ClientMain.detail.setVisible(false);
		JOptionPane.showMessageDialog(null, "Sorry!!! Unable to start Game due to insuffient Players");
		
	}

	@Override
	public void changeOtherPlayerDetails(Player p) throws RemoteException {
		MonopolyOptions.changePlayerDetails(p);
		System.out.println("current:"+p.getName()+" myPlayer:"+PlayerDetailForm.myPlayer.getName());
		if(PlayerDetailForm.myPlayer.getName().equals(p.getName())) {
			MonopolyOptions.initButtonSettings();
		} else {
			MonopolyOptions.disableAll();
		}
		
	}
	
	public void showMsg(String otherMessage) throws RemoteException {
			MonopolyOptions.displayPopUp(otherMessage);
		
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
		
		
	}
	
	private void moveToken(String propertyName) throws RemoteException{
		MoveChance position = null ;
		for(MoveChance mv: MoveChance.values()) {
			if(mv.name().replace("_", " ").equals(propertyName)) {
				position = mv;
			}
		}
		Token t = ClientMain.store.getCurrentPlayer().getToken();
		t.setxCoordinate(position.getXpoint());
		t.setyCoordinate(position.getYpoint());
		List <Token> temp = ClientMain.store.getSelectedTokens();
		temp.set(ClientMain.store.getSelectedTokens().indexOf(ClientMain.store.getCurrentPlayer().getToken()), t);
		ClientMain.store.setSelectedTokens(temp);
		ClientMain.store.getCurrentPlayer().setToken(t);
		MonopolyMain.panel.changeTokenPosition(temp);
		
		ClientMain.store.changeOtherPlayerBoard(PlayerDetailForm.myClient,
				MonopolyMain._leftDie.getValue(), MonopolyMain._rightDie.getValue());
		
		ButtonValidate.landOnBlock(ClientMain.store.getCurrentPlayer().getToken());
		
	}
	

}
