package com.cs414j.monopoly.client.main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

import com.cs414j.monopoly.client.view.PlayerDetailForm;
import com.cs414j.monopoly.common.Player;
import com.cs414j.monopoly.controller.EndTimerTask;
import com.cs414j.monopoly.controller.MonopolyMain;
import com.cs414j.monopoly.controller.MonopolyOptions;

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
	public void showError(String message) throws RemoteException {
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
			MonopolyOptions.disableButtonSettings();
		}
		
	}

}
