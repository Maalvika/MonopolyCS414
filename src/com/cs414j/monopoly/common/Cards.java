package com.cs414j.monopoly.model;

import java.util.HashMap;
import java.util.Random;

import com.cs414j.monopoly.view.PlayerDetails;

public class Cards {
	public String[] chance;
	public String[] chest;
	Type t;

	public Cards(){
		chance = new String[16];
		chest = new String[17];
		initializeChance();
		initializeChest();
	}

	public Type getCardType(){
		return t;
	}

	public void initializeChance(){
		chance[0] = ("Advance to Go");
		chance[1] = ("Advance to Illinois Avenue");
		chance[2] = ("Advance to St. Charles Place");
		chance[3] = ("Advance to nearest Utility");
		chance[4] = ("Advance to nearest RailRoad");
		chance[5] = ("Take a walk on Boardwalk");
		chance[6] = ("Take a trip to Reading RailRoad");
		chance[7] = ("Go to Jail directly");
		chance[8] = ("Go back 3 spaces");
		chance[9] = ("Bank pays you dividend of $50");
		chance[10] = ("Get out of Jail free");
		chance[11] = ("Make general repairs");
		chance[12] = ("Pay Poor Tax - $15");
		chance[13] = ("You have been elected Chairman - Pay $50 to each player");
		chance[14] = ("Your loan matures - Receive $150");
		chance[15] = ("You have won a Crossword Competition - You get $100");
	}

	public void initializeChest(){
		chest[0]=("Advance to Go");
		chest[1] = ("Bank Error in you FAVOUR!! - You get $200");
		chest[2] = ("Pay $50 Doctor's Fee");
		chest[3] = ("Sale of Stock - Receive $50");
		chest[4] = ("Grand Opera Night - Collect $50 from all players");
		chest[5] = ("Receive Holiday Fund of $50");
		chest[6] = ("Get out of Jail free");
		chest[7] = ("Income Tax Refund - Receive $20");
		chest[8] = ("Go to Jail directly");
		chest[9] = ("Happy Birthday!! You Get $10 from all");
		chest[10] = ("Life Insurance matures - Receive $100");
		chest[11] = ("Pay $100 Hospital Fees");
		chest[12] = ("Pay School Fees - Pay $150");
		chest[13] = ("Receive $25 Consultant Fee");
		chest[14] = ("Street Repairs- Pay $40 per house");
		chest[15] = ("You have won SECOND PRIZE $10 in a BEAUTY CONTEST!!");
		chest[16] = ("You inherit $100");			
	}

	public String chanceMove(int index,Player p){
		// the method is called if random index for chance >= 8
		String s="";
		switch(index){
		case 0:
			this.advanceToGo(p);
			s = "Go";
			break;

		case 1:
			this.advancetoIllinois(p);
			s = "Illinois Avenue";

			break;

		case 2:
			this.advancetoCharles(p);
			s = "St Charles Place"	;	

			break;

		case 3:
			s = this.advancetoUtility(p);

			break;
			
		case 4:
			s = this.advancetoRailRoad(p);
			break;
			
		case 5:
			this.advancetoBoardwalk(p);
			s = "Boardwalk";
			break;
			
		case 6 :
			this.advancetoRR(p);
			s = "Reading Railroad";
			break;
			
		case 7:
			this.advanceToJail(p);
			s = "Visiting Jail";
			break;
			
		case 8:
			s = this.advanceBack(p);
			
			break;
		}

		return s;
	}
	
	public int chanceBalanceUpdate(int index,Player p){
		int balance = 0;
		switch(index){
		case 9:
			balance = this.chancedividend(p);
			break;
			
		case 11:
			balance = this.chanceRepairs(p);
			break;
			
		case 12:
			balance = this.chancePoorTax(p);
			break;
			
		case 13:
			
		}
		return balance;
	}

	public String generateRandomChance(Player p){
		Random r = new Random();
		int index = r.nextInt((15 - 0)+1);
		String s = chance[index];
		if(index <= 8){
			return this.chanceMove(index, p);
		}
		else{
			this.chanceBalanceUpdate(index,p);
		return null;
		}
	}

	public int generateRandomChest(){
		Random r = new Random();
		return r.nextInt((16 - 0)+1); 
	}

	public String chestCard(){
		int index = generateRandomChest();
		String s = chest[index];
		return s;
	}



	public void advanceToGo(Player p){
		t = Type.MOVE;
		p.setLocation(0);
		String s = "Congratulations!!! You landed on GO and receive 200$";

	}

	public void advancetoIllinois(Player p){
		t = Type.MOVE;
		if(p.getLocation() > 24){
			int currBalance = p.getBalance();
			p.setBalance(currBalance + 200);
			p.setLocation(24);
			String s = "You receive $200";
		}
		else{
			p.setLocation(24);
		}
	}

	public  void advancetoCharles(Player p){
		// in case of the card "advance to St Charles"
		t = Type.MOVE;
		String s = "";
		if(p.getLocation() > 11){
			int currBalance = p.getBalance();
			p.setBalance(currBalance + 200);
			p.setLocation(11);
			String display = "You receive $200";

		}
		else{
			p.setLocation(11);

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
	
	public String advancetoRailRoad(Player p){
		t = Type.MOVE;
		String name = "";
		if(p.getLocation() <= 5){
			p.setLocation(5);
			name = "Reading Railroad";
			return name;
		}
		if(p.getLocation() > 5 || p.getLocation() <= 15){
			p.setLocation(15);
			name = "Pennsylvania Railroad";
			return name;
		}
		if(p.getLocation() > 15 || p.getLocation() <= 25){
			p.setLocation(25);
			name = "B O Railroad";
			return name;
		}
		
		if(p.getLocation() > 25 || p.getLocation() <= 35)
		{
			p.setLocation(35);
			name = "Short Line";
			return name;
		}
		if(p.getLocation() > 35){
			p.setLocation(5);
			int currbalance = p.getBalance();
			p.setBalance(currbalance + 200);
			name = "Reading Railroad";
			return name;
		}
		return null;
	}
	
	public void advancetoBoardwalk(Player p){
		t = Type.MOVE;
		p.setLocation(39);
				
	}
	
	public void advancetoRR(Player p){
		t = Type.MOVE;
		p.setLocation(5);
	}
	
	public void advanceToJail(Player p){
		t = Type.MOVE;
		p.setLocation(10);
	}
	
	public String advanceBack(Player p){
		t = Type.MOVEX;
		String s = "";
		if(p.getLocation() == 7){
			p.setLocation(4);
			s = "Income Tax";
			return s;
		}
		if(p.getLocation() == 22){
			p.setLocation(19);
			s = "New York Avenue";
			return s;
		}
		
		if(p.getLocation() == 36){
			p.setLocation(33);
			s = "CC";
			return s;
		}
		return s;
	}
	
	public int chancedividend(Player p){
		t = Type.ADD;
		int balance = p.getBalance() + 50;
		p.setBalance(balance);
		return balance;
	}
	
	public int chanceRepairs(Player p){
		int x = p.getHousesOwned();
		int balance = (p.getBalance() - (x*25));
		p.setBalance(balance);
		return balance;
	}
	
	public int chancePoorTax(Player p){
		int balance = p.getBalance() - 15;
		p.setBalance(balance);
		return balance;
	}
	
	public int chanceChairman(Player p){
		int balance = p.getBalance();
		PlayerDetails pd = new PlayerDetails();
		int n = pd.noOfPlayers;
		balance = balance - (n*50);
		return balance;
	}
	
	
	
	







}
