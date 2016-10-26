package com.cs414j.monopoly.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Player {

	private String name;
	private int location;
	private int balance;
	private HashSet<Properties>ownedProperty;
	private int housesOwned=0;
	private int hotelsOwned=0;
	private HashSet<Utilities> ownedUtilities;
	private HashSet<RailRoad> ownedRailRoad ;
	private Token token;



	public Player(String n){
		name = n;
		location = 0;
		balance = 1500;
		ownedProperty = new HashSet<Properties>();
		ownedUtilities = new HashSet<Utilities>();
		ownedRailRoad = new HashSet<RailRoad>();

	}

	public String getName(){
		// to get player's name

		return name;
	}

	public void setName(String name) {
		// to set player's name
		this.name = name;
	}

	public Token getToken(){
		return token;
	}
	
	public Set<String> OwnedSquareName(){
		Iterator<Properties> itr = ownedProperty.iterator();
		HashSet<String> propertyNames = new HashSet<String>();
		while(!(ownedProperty.isEmpty())&& itr.hasNext())
		{
		Properties p = (Properties)itr.next();
		propertyNames.add(p.getName());
	    }
	return propertyNames;
	}
	


	public int getLocation(){
		// returns current location of the player
		return location;
	}

	public void setBalance(int b){
		this.balance = b;
	}

	public Set<Properties> getOwnedProperties(){
		return ownedProperty;
	}

	public Set<Utilities> getOwnedUtilities(){
		return ownedUtilities;
	}

	public void moveForward(int diceValue){
		// would move the player dicevalue squares forward on the board
		location = (location + diceValue) % 40;
	}

	public int getBalance(){
		return balance;
	}

	public void unMortgageProperty(String name, Bank b){

	}

	
	public boolean isPropertyOwned(Properties p){
		// checks if a particular method is owned by a property
		if(ownedProperty.contains(p)){
			return true;
		}
		else return false;
	}

	public void passGo(int diceValue){
		// This is to add $200 every time a player lands on or crosses "GO".
		for(int i = 0; i>40;i++ ){
			if(getLocation() + diceValue >= 40){
				int newBalance = getBalance() + 200;
				setBalance(newBalance);
			}
		}
	}

	public boolean landedOnGoToJail(){
		// to check if a player landed on Go To Jail square
		if(getLocation() == 30){
			return true;
		}
		return false;
	}

	public void sendtoInJail(){
		// if a player lands on go to jail he is sent to in jail square which 
		//is location 10 on board and is asked to pay $50 to get out of jail
		if(landedOnGoToJail() == true){
			location = 10;
			if(getBalance() > 50){
				System.out.println("Would you like to pay $50 to get out of jail? yes/no");
				Scanner scanner = new Scanner(System.in);
				String response = scanner.next();
				if(response.equals("yes")){

					int newBalance = balance-50;
					setBalance(newBalance);
				}
				scanner.close();
			}
		}
	}

	public Properties getPropertyObject(String key, Board board){
		// if the key is contained in the hashMap
		// the property object is returned corresponding to that key
		if(board.stringProperties.containsKey(key)){
			return board.stringProperties.get(key);
		}
		else return null;
	}

	public void buyProperty(String name, Bank b, Board board){
		// to buy property from bank
		if(!(getPropertyObject(name,board)==null))		
		{
			Properties p = getPropertyObject(name,board);
			balance =  balance - p.getCost();
			ownedProperty.add(p);
			b.getBankPropertiesSet().remove(p);
		}
	}

	public Utilities getUtilityObject(String key, Board board){
		if(board.stringUtilities.containsKey(key)){
			return board.stringUtilities.get(key);
		}
		else return null;
	}

	public void buyUtility(String name, Bank b, Board board){
		// to buy utility from bank
		if(!(getUtilityObject(name,board)==null))		
		{
			Utilities u = getUtilityObject(name,board);
			balance =  balance - u.getCost();
			ownedUtilities.add(u);
			b.getBankUtilitySet().remove(u);
		}
	}


	public RailRoad getRailRoadObject(String key, Board board){

		if(board.stringRailRoad.containsKey(key)){
			return board.stringRailRoad.get(key);
		}
		else return null;
	}

	public void buyRailRoad(String name, Bank b, Board board){
		// to buy RailRoad from bank

		if(!(getRailRoadObject(name,board)==null))		
		{
			RailRoad r = getRailRoadObject(name,board);
			balance =  balance - r.getCost();
			ownedRailRoad.add(r);
			b.getBankRailRoad().remove(r);
		}
	}

	
	public void payRent(String name, Board b, int diceValue){
		// pay rent to another player
		// by getting the rent from square.property 
		// subtracting the rent from player's balance
		// adding the rent in owner's balance
		if(b.stringProperties.containsKey(name))
		{
			Properties p = getPropertyObject(name, b );
			int rent = p.getRentInitial();
			balance =  balance - rent;
			Player receiver = p.getOwner();
			receiver.balance = receiver.balance + rent;
		}

		if(b.stringUtilities.containsKey(name))
		{
			Utilities u = this.getUtilityObject(name, b);
			Player receiver = u.getOwner();
			if(receiver.ownedUtilities.size() == 2)
			{
				int rent = 10 * diceValue;
				balance = balance - rent;
			}
			else
			{
				int rent = 4 * diceValue;
				balance = balance - rent;
			}
		}
		if(b.stringRailRoad.containsKey(name))
		{

			RailRoad r = this.getRailRoadObject(name, b);
			Player receiver = r.getOwner();
			if(receiver.ownedRailRoad.size() == 1){
				balance = balance - 25;
			}
			else if(receiver.ownedRailRoad.size() == 2){
				balance = balance - 50;
			}
			else if(receiver.ownedRailRoad.size() == 3){
				balance = balance - 100;
			}
			else if(receiver.ownedRailRoad.size() == 4){
				balance = balance -200;
			}
		}
		MonopolyOptions.changePlayerDetails(this);

	}

	public void payTax(){
		// deducts $100 if player lands on luxury tax
		//deducts $200 from player's balance if player lands on income tax

		if(location == 4){
			// income tax
			balance = balance - 200;
		}
		if(location == 38){
			// luxury tax
			balance = balance - 100;
		}
		MonopolyOptions.changePlayerDetails(this);

	}

	public void mortgageProperty(String name, Bank b){
		// asks bank for loan on a particular property 
		
	}

	public void sellProperty(String name ,Bank b){

//		if(ownedProperty.contains(p))
//		{
//			balance=balance+((1/2)*p.getCost());
//
//			ownedProperty.remove(p);
//			b.getBankPropertiesSet().add(p);
//		}
    }

	public void buyHouse(String name){
		// buy house on owned property
//		if(ownedProperty.contains(p))
//		{
//			if(balance > p.getHouseCost())
//			{
//				balance=balance-p.getHouseCost();
//				housesOwned++;
//			}
//		}
	}

	public void buyHotel(String name){
		// buy hotel on owned property
//		if(ownedProperty.contains(p))
//		{
//			if(housesOwned>=4)
//			{
//				if(balance > p.getHotelCost())
//				{
//					balance=balance-p.getHotelCost();
//					hotelsOwned++;
//					housesOwned=0;
//				}
//
//			}
//		}
	}
}

