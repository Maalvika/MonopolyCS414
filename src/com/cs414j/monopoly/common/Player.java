package com.cs414j.monopoly.common;

import java.rmi.RemoteException;
import java.util.Set;

import com.cs414j.monopoly.server.model.Properties;
import com.cs414j.monopoly.server.model.RailRoad;
import com.cs414j.monopoly.server.model.Utilities;

public interface Player extends java.rmi.Remote {
	
	public int getpropertyCost() throws RemoteException;
	
	public String getName()throws RemoteException;

	public void setName(String name)throws RemoteException;

	public Token getToken()throws RemoteException;
	
	public int getLocation()throws RemoteException;

	public void setLocation(int l)throws RemoteException;
	public int getBalance()throws RemoteException;

	public void setBalance(int b)throws RemoteException;

	public Set<Properties> getOwnedProperties()throws RemoteException;
	
	public Set<Utilities> getOwnedUtilities()throws RemoteException;

	public void moveForward(int diceValue)throws RemoteException;

	public void setToken(Token t)throws RemoteException;
	
	public int getHousesOwned()throws RemoteException;

	public int getHotelsOwned()throws RemoteException;
	
	public Set<RailRoad> getOwnedRailRoad()throws RemoteException;
	
	public Set<Utilities> getMortgageUtilities()throws RemoteException;
	
	public Set<RailRoad> getMortgageRailRoad()throws RemoteException;
	
	public Set<Properties> getMortgageProperties()throws RemoteException;

	public Set<String> OwnedSquareName()throws RemoteException;
	
	public void unMortgageProperty(String name)throws RemoteException;
	
	public boolean isPropertyOwned(Properties p)throws RemoteException;

	public void passGo(int diceValue)throws RemoteException;

	public void landedOnGoToJail(int l)throws RemoteException;

	public void paidJailPenalty()throws RemoteException;

	public Properties getPropertyObject(String key)throws RemoteException;

	public Utilities getUtilityObject(String key)throws RemoteException;
	
	public void buyProperty(String name)throws RemoteException;
	
	public void payRent(String name, int diceValue)throws RemoteException;

	public void payTax()throws RemoteException;

	public void mortgageProperty(String name)throws RemoteException;

	public void buyHouse(String name)throws RemoteException;

	public void buyHotel(String name)throws RemoteException;
	
	public void buyProperty(String name, Bank b, int bid) throws RemoteException;

}
