package com.cs414j.monopoly.common;

import java.rmi.RemoteException;
import java.util.Random;

import com.cs414j.monopoly.client.main.ClientCallback;
import com.cs414j.monopoly.controller.MonopolyOptions;
import com.cs414j.monopoly.server.model.MonopolyServerStore;
import com.cs414j.monopoly.server.model.Utilities;

public class Cards {
	public String[] chance;
	public String[] chest;
	

	public Cards(){
		chance = new String[16];
		chest = new String[17];
		initializeChance();
		initializeChest();
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
		chest[1] = ("Go to Jail directly");
		chest[2] = ("Bank Error in you FAVOUR!! - You get $200");
		chest[3] = ("Pay $50 Doctor's Fee");
		chest[4] = ("Sale of Stock - Receive $50");
		chest[5] = ("Grand Opera Night - Collect $50 from all players");
		chest[6] = ("Receive Holiday Fund of $50");
		chest[7] = ("Get out of Jail free");
		chest[8] = ("Income Tax Refund - Receive $20");
		chest[9] = ("Happy Birthday!! You Get $10 from all");
		chest[10] = ("Life Insurance matures - Receive $100");
		chest[11] = ("Pay $100 Hospital Fees");
		chest[12] = ("Pay School Fees - Pay $150");
		chest[13] = ("Receive $25 Consultant Fee");
		chest[14] = ("Street Repairs- Pay $40 per house");
		chest[15] = ("You have won SECOND PRIZE $10 in a BEAUTY CONTEST!!");
		chest[16] = ("You inherit $100");			
	}

	public String chanceMove(int index,Player p) throws RemoteException{
		// the method is called if random index for chance >= 8 for chance
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

	public int chanceBalanceUpdate(int index,Player p) throws RemoteException{
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

			//		case 13:
			//			balance = this.chanceChairman(p);
			//			break;

		case 14:
			balance = this.chanceLoanMatures(p);
			break;

		case 15:
			balance = this.chanceInherit(p);
			break;

		}
		return balance;
	}

	public void generateRandomChance(Player p) throws RemoteException{
		Random r = new Random();
		int index = r.nextInt((15 - 0)+1);
		String s = chance[index];
		//MonopolyServerStore.getInstance().getCurrentPlayer().s
		//Monopoly Store call this method
		MonopolyServerStore.getClientFromPlayer(p.getName()).showMsg(s);
		if(index <= 8){
			this.chanceMove(index, p);
		}
		else{
			this.chanceBalanceUpdate(index,p);
		}
	}
	


	public void generateRandomChest(Player p) throws RemoteException{
		Random r = new Random();
		int index = r.nextInt((16-0)+1);
		String s = chest[index];
		MonopolyServerStore.getClientFromPlayer(p.getName()).showMsg(s);
		if(index <= 1){
			this.chestMove(index, p);
		}
		else{
			this.chestBalanceUpdate(index,p);
		}

	}

	public String chestMove(int index, Player p) throws RemoteException{
		String s = "";
		switch(index){
		case 0:
			
			this.advanceToGo(p);
			s = "Go";
			break;
		case 1:
			this.advanceToJail(p);
			s = "Visiting Jail";
			break;
		}
		return s;
	}

	public int chestBalanceUpdate(int index, Player p) throws RemoteException{
		int balance = p.getBalance();
		switch(index){
		case 2:
			balance = this.chestbankError(p);
			break;
		case 3:
			balance = this.chestDocFee(p);
			break;
		case 4:
			balance = this.chestSaleStock(p);
			break;
		//case 5:
		case 6:
			balance = this.chestHolidayFund(p);
			break;
		case 8:
			balance = this.chestIncomeTax(p);
			break;
		case 10:
			balance = this.chestLifeInsurance(p);
			break;
		case 11:
			balance = this.chestHospitalFee(p);
			break;
		case 12:
			balance = this.chestSchoolFee(p);
			break;
		case 13:
			balance = this.chestConsultFee(p);
			break;
		case 14:
			balance = this.chanceRepairs(p);
			break;
		case 15:
			balance = this.chanceBeautyCont(p);
			break;
		case 16:
			balance = this.chanceInherit(p);
			break;			
		}
		
		return balance;
	
	}


	public void advanceToGo(Player p) throws RemoteException{
	
		p.setLocation(0);
		String s = "Congratulations!!! You landed on GO and receive 200$";

	}

	public void advancetoIllinois(Player p) throws RemoteException{
	
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

	public  void advancetoCharles(Player p) throws RemoteException{
		// in case of the card "advance to St Charles"
	
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

	public String advancetoUtility(Player p) throws RemoteException{
	
		String name = "";
		if(p.getLocation() < 12){
			p.setLocation(12);
			name = "Electric Co";
			return name;
		}
		if(p.getLocation() > 12 || p.getLocation() < 28){
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

	public int payUtilityRent(Player p, int diceValue, String name) throws RemoteException{
		// if the player lands on a sold utility
		// the player rollss dice again and pays the amount on dice * 10

		int rent = diceValue * 10;
		int balance = p.getBalance();
		p.setBalance(balance - rent);
		if (MonopolyServerStore.getBoardInstance().stringUtilities.containsKey(name)){
			Utilities u = p.getUtilityObject(name);
			Player receiver = u.getOwner();
			int ownerBalance = receiver.getBalance();
			receiver.setBalance(ownerBalance + rent);
			return rent;
		}

		return rent;
	}

	public String advancetoRailRoad(Player p) throws RemoteException{
	
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

	public void advancetoBoardwalk(Player p) throws RemoteException{
	
		p.setLocation(39);

	}

	public void advancetoRR(Player p) throws RemoteException{
	
		p.setLocation(5);
	}

	public void advanceToJail(Player p) throws RemoteException{
	
		p.setLocation(10);
	}

	public String advanceBack(Player p) throws RemoteException{
	
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


	public int chancedividend(Player p) throws RemoteException{
	
		int balance = p.getBalance() + 50;
		p.setBalance(balance);
		return balance;
	}

	public int chanceRepairs(Player p) throws RemoteException{
	
		int x = p.getHousesOwned();
		int balance = (p.getBalance() - (x*25));
		p.setBalance(balance);
		return balance;
	}

	public int chancePoorTax(Player p) throws RemoteException{
		int balance = p.getBalance() - 15;
		p.setBalance(balance);
		return balance;
	}

	//	public int chanceChairman(Player p) throws RemoteException{
	//		// added to other players subtracted from this player
	//		int balance = p.getBalance();
	//		int n = MonopolyServerStore.g
	//		
	//		
	//		return balance;
	//	}

	public int chanceLoanMatures(Player p) throws RemoteException{
	
		int balance = p.getBalance();
		p.setBalance(balance + 150);
		return balance;
	}

	public int chanceBeautyCont(Player p) throws RemoteException{
	
		int balance = p.getBalance();
		p.setBalance(balance + 10);

		return balance;
	}

	public int chanceInherit(Player p) throws RemoteException{
	
		int balance = p.getBalance();
		p.setBalance(balance+ 100);
		return balance;
	}
	
	public int chestbankError(Player p) throws RemoteException{

		int balance = p.getBalance();
		p.setBalance(balance + 200);
		return balance;
	}
	
	public int chestDocFee(Player p) throws RemoteException{
		
		int balance = p.getBalance();
		p.setBalance(balance - 50);
		return balance;
	}
	
	public int chestSaleStock(Player p) throws RemoteException{
		
		int balance = p.getBalance();
		p.setBalance(balance + 50);
		return balance;
	}
	
//	public int chestGrandOpera(Player p){
//		int balance = p.getBalance();
//		
//	}
	
	public int chestHolidayFund(Player p) throws RemoteException{
	
		int balance = p.getBalance();
		p.setBalance(balance + 50);
		return balance;
	}
	
	public int chestIncomeTax(Player p) throws RemoteException{

		int balance = p.getBalance();
		p.setBalance(balance + 20);
		return balance;
	}
	
	public int chestLifeInsurance(Player p) throws RemoteException{
		
		int balance = p.getBalance();
		p.setBalance(balance + 100);
		return balance;
	}
	
	public int chestHospitalFee(Player p) throws RemoteException{
		
		int balance = p.getBalance();
		p.setBalance(balance - 100);
		return balance;
	}
	
	public int chestConsultFee(Player p) throws RemoteException{
	
		int balance = p.getBalance();
		p.setBalance(balance - 25);
		return balance;
	}
	
	public int chestSchoolFee(Player p) throws RemoteException{
	
		int balance = p.getBalance();
		p.setBalance(balance - 150);
		return balance;
	}
}
