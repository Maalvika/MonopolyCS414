package com.cs414j.monopoly.common;

import java.rmi.RemoteException;
import java.util.List;

import com.cs414j.monopoly.client.main.ClientCallback;
import com.cs414j.monopoly.common.Player;
import com.cs414j.monopoly.controller.MonopolyMain;
import com.cs414j.monopoly.server.model.MonopolyServerStore;
import com.cs414j.monopoly.view.TokenUrls;

public interface MonopolyStore extends java.rmi.Remote{
	
	public void registerClient(ClientCallback c) throws RemoteException;
	
	public void changeOtherPlayerBoard(ClientCallback c, int leftDie, int rightDie) throws RemoteException;
	
	public Player addNewPlayer(String name) throws RemoteException;
	
	public List<Player> getPlayers() throws RemoteException;

	public List<Token> getSelectedTokens() throws RemoteException;
	
	public void setSelectedTokens(List<Token> selectedTokens) throws RemoteException;
	
	public void setTokenCoordinates(TokenUrls t ,int x,int y) throws RemoteException;

	public Player getCurrentPlayer() throws RemoteException;
	
	public void switchToNextTurn(ClientCallback c) throws RemoteException;
	
	public boolean isOwnedByBank(String name) throws RemoteException;
	
	public void sendMessageToAll(String otherMessage) throws RemoteException;
	
	public void sendPropertyForAuction(String propertyName)throws RemoteException ;
	
	public void sendRentMessageToOwner(String propertyName) throws RemoteException;
	
	public void endGameIfAnyPlayerQuits(String playerName) throws RemoteException;
	
	public void landonChance() throws RemoteException;
	
	public void landonChest() throws RemoteException;

}
