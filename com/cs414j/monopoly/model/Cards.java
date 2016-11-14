package com.cs414j.monopoly.model;

import java.util.HashMap;

public class Cards {
	public HashMap<String, Integer> chance = new HashMap<String, Integer>();
	public HashMap<String, Integer> chest = new HashMap<String, Integer>();
	Type t;
	
	public Cards(){
		initializeChance();
		initializeChest();
	}
	
	public Type getCardType(){
		return t;
	}

	public void initializeChance(){
		chance.put("Advance to Go", 200);
		chance.put("Advance to Illinois Avenue", 200);
		chance.put("Advance to St. Charles Place", 200);
		chance.put("Advance to nearest Utility", 0);
		chance.put("Advance to nearest RailRoad",0);
		chance.put("Bank pays you dividend of $50", 50);
		chance.put("Get out of Jail free", 0);
		chance.put("Go back 3 spaces", -3);
		chance.put("Go to Jail directly", 0);
		chance.put("Make general repairs",-25);
		chance.put("Pay Poor Tax - $15", -15);
		chance.put("Take a trip to Reading RailRoad", 200);
		chance.put("Take a walk on Boardwalk", 0);
		chance.put("You have been elected Chairman - Pay $50 to each player", 50);
		chance.put("Your loan matures - Receive $150", 150);
		chance.put("You have won a Crossword Competition - You get $100", 100);
	}

	public void initializeChest(){
		chest.put("Advance to Go", 200);
		chest.put("Bank Error in you FAVOUR!! - You get $200", 200);
		chest.put("Doctor's Fee", -50);
		chest.put("Sale of Stock - Receive $50", 50);
		chest.put("Grand Opera Night - Collect $50 from all players", 50);
		chest.put("Receive Holiday Fund of $50", 50);
		chest.put("Get out of Jail free", 0);
		chest.put("Income Tax Refund - Receive $20", 20);
		chest.put("Go to Jail directly", 0);
		chest.put("Happy Birthday!! You Get $10 from all", 10);
		chest.put("Life Insurance matures - Receive $100", 100);
		chest.put("Pay Hospital Fees - Pay $100", -100);
		chest.put("Pay School Fees - Pay $150", -150);
		chest.put("Receive $25 Consultant Fee", 25);
		chest.put("Street Repairs- Pay $40 per house", 40);
		chest.put("You have won SECOND PRIZE in a BEAUTY CONTEST!!", 10);
		chest.put("You inherit $100", 100);			
	}
	
	public String advanceToGo(Player p){
		t = Type.MOVE;
		p.setLocation(0);
		String s = "Congratulations!!! You landed on GO and receive 200$";
		return s;
	}
	
	public String advancetoIllinois(Player p){
		t = Type.MOVE;
		if(p.getLocation() > 24){
			int currBalance = p.getBalance();
			p.setBalance(currBalance + 200);
			p.setLocation(24);
			String s = "You receive $200";
			return s;
		}
		else{
			p.setLocation(24);
			String s = "You landed on Illinois Avenue";
			return s;
		}
	}
	
	public  String advancetoCharles(Player p){
		// in case of the card "advance to St Charles"
		t = Type.MOVE;
		if(p.getLocation() > 11){
			int currBalance = p.getBalance();
			p.setBalance(currBalance + 200);
			p.setLocation(11);
			String display = "You receive $200";
			String s = "St Charles Place";
			return s;
		}
		else{
			p.setLocation(11);
			String s = "St Charles Place";
			return s;
		}
	}
	
	public String advancetoUtility(Player p){
		t = Type.MOVE;
		String name = "";
		if(p.getLocation() <= 12){
			p.setLocation(12);
			name = "Electric Co";
			return name;
		}
		if(p.getLocation() > 12 || p.getLocation() <= 28){
			p.setLocation(28);
			name = "Water Works";
			return name;
		}
		if(p.getLocation() > 28){
			p.setLocation(12);
			int currbalance = p.getBalance();
			p.setBalance(currbalance + 200);
			name = "Electric Co";
			return name;
		}
		return null;
	}
	
	public int payUtilityRent(Player p, int diceValue, Board b, String name){
		// if the player lands on a sold utility
		// the player rollss dice again and pays the amount on dice * 10
		
		int rent = diceValue * 10;
		int balance = p.getBalance();
		p.setBalance(balance - rent);
		Utilities u = p.getUtilityObject(name, b);
		Player receiver = u.getOwner();
		int ownerBalance = receiver.getBalance();
		receiver.setBalance(ownerBalance + rent);
		return rent;
	
	}
	
	
	
	
	
}
