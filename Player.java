package Monopoly;

import java.util.HashSet;
import java.util.Scanner;


public class Player {

	private String name;
	private int location;
	private int balance;
	private int housesOwned=0;
	private int hotelsOwned=0;
	private HashSet<Properties> ownedProperty;
	


	public Player(){
		name = "";
		location = 0;
		balance = 1500;

	}

	public Player(String n, int l){
		name = n;
		location = l;
	}

	public String getName(){

		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLocation(){
		// returns current location of the player
		return location;
	}

	public void setBalance(int b){
		this.balance = b;
	}

	public void moveForward(int diceValue){
		// would move the player dicevalue squares forward on the board
		location = (location + diceValue) % 40;
	}


	public int getBalance(){
		return balance;
	}


	public boolean askToBuy(Properties p){
			if(p.owner == null){
			if(balance > p.getCost()){
				System.out.println("You can buy this! Do you want to? yes/no");
				Scanner scanner = new Scanner(System.in);
				String response = scanner.next();
				if(response.equals("yes")){
					return true;
				}
				else return false;
			}
			return false;
		}
		return false;
	}


	public void buy(Properties p){
		// buy property from bank
		
		if(askToBuy(p) == true){
			balance =  balance - p.getCost();
			ownedProperty.add(p);
		}

	}

	public void payRent(Properties p){
		// pay rent to another player
		// by getting the rent from square.property 
		// subtracting the rent from player's balance
		// adding the rent in owner's balance
			
			int rent = p.getRentInitial();
			balance =  balance - rent;
			Player receiver = p.getOwner();
			receiver.balance = receiver.balance + rent;
	}
	
	public void payTax(int location){

		if(location == 4){
			// income tax
			balance = balance - 200;
		}
		if(location == 38){
			// luxury tax
			balance = balance - 100;
		}
			
	}
	
	public void sellProperty(Properties p){
		
		if(ownedProperty.contains(p))
		{
			balance=balance-((1/2)*p.getCost());
			ownedProperty.remove(p);
		}
	}

	public void buyHouse(Properties p){
		// buy house on owned property
		if(ownedProperty.contains(p))
		{
		if(balance > p.getHouseCost())
		{
			balance=balance-p.getHouseCost();
			housesOwned++;
	}
		}
	}
	public void buyHotel(Properties p){
		// buy hotel on owned property
		if(ownedProperty.contains(p))
		{
			if(housesOwned>=4)
			{
		if(balance > p.getHotelCost())
		{
			balance=balance-p.getHotelCost();
			hotelsOwned++;
			housesOwned=0;
	}
		
		}
	}
	}
	
	


}

