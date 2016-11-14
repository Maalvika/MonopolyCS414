package com.cs414j.monopoly.client.main;

import java.rmi.RemoteException;

import com.cs414j.monopoly.common.Player;

public interface ClientCallback extends java.rmi.Remote{
	
	public String getName() throws RemoteException;	
	
	public void startMonopolyGame(int END_TIME_MIN) throws RemoteException;
	
	public void showError(String message) throws RemoteException;
	
	public void changeBoardImage(int leftDie, int rightDie) throws RemoteException;
	
	public void changeOtherPlayerDetails(Player p) throws RemoteException;

}
