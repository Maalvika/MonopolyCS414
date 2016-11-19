package com.cs414j.monopoly.client.main;

import java.rmi.RemoteException;

import com.cs414j.monopoly.common.Player;

public interface ClientCallback extends java.rmi.Remote{
	
	public String getName() throws RemoteException;	
	
	public void startMonopolyGame(int END_TIME_MIN) throws RemoteException;
	
	public void showErrorStartGame(String message) throws RemoteException;
	
	public void changeBoardImage(int leftDie, int rightDie) throws RemoteException;
	
	public void changeOtherPlayerDetailsAndControlButton(Player p) throws RemoteException;
	
	public void changePlayerDetails() throws RemoteException;
	
	public void showMsg(String otherMessage) throws RemoteException;
	
	public void auctionProperty(String name) throws RemoteException;
	
	public int getBidValue() throws RemoteException;
	
	public void endGame() throws RemoteException;
	
	public void moveChance(String propertyName) throws RemoteException;
	
	public void buyPropertyAction(String pName)throws RemoteException;
	
	public void buildPropertyAction() throws RemoteException;
	
}
