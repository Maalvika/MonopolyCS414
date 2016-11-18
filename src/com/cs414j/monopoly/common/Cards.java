package com.cs414j.monopoly.common;

import java.rmi.RemoteException;
import java.util.Random;

import com.cs414j.monopoly.client.main.ClientCallback;
import com.cs414j.monopoly.controller.MonopolyOptions;
import com.cs414j.monopoly.server.model.MonopolyServerStore;
import com.cs414j.monopoly.server.model.Utilities;

public class Cards {

	public void chanceAction(int index, Player p) throws RemoteException{
		if(index <= 8){
			String propertyName = this.chanceMove(index, p);
			MonopolyServerStore.getClientFromPlayer
			(MonopolyServerStore.getInstance().getCurrentPlayer().getName()).moveChance(propertyName);
		}

		else{
			System.out.println("a");
			this.chanceBalanceUpdate(index, p);
		}
		MonopolyServerStore.changeAllPlayerDetails(false);
	}

	public void chestAction(int index, Player p) throws RemoteException{
		if(index <= 1){

			String propertyName = this.chestMove(index, p);
			MonopolyServerStore.getClientFromPlayer
			(MonopolyServerStore.getInstance().getCurrentPlayer().getName()).moveChance(propertyName);;
		}
		else{
			this.chestBalanceUpdate(index,p);
		}
		MonopolyServerStore.changeAllPlayerDetails(false);
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
		int balance = p.getBalance();
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
			balance = this.chanceChairman(p);
			break;

		case 14:
			balance = this.chanceLoanMatures(p);
			break;

		case 15:
			balance = this.chanceInherit(p);
			break;

		}
		return balance;
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
		case 5:
			balance = this.chestOpera(p);
		case 6:
			balance = this.chestHolidayFund(p);
			break;
		case 8:
			balance = this.chestIncomeTax(p);
			break;
		case 9:
			balance = this.chestBirthday(p);
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
		int balance = p.getBalance();
		p.setBalance(balance + 200);
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
		if(p.getLocation() > 12 && p.getLocation() < 28){
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
		// the player rolls dice again and pays the amount on dice * 10

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
		System.out.println("loc: "+p.getLocation());
		if(p.getLocation() <= 5){
			p.setLocation(5);
			name = "Reading Railroad";
			return name;
		}

		if(p.getLocation() > 5 && p.getLocation() < 15){
			p.setLocation(15);
			name = "Pennsylvania Railroad";
			return name;
		}

		if(p.getLocation() > 15 && p.getLocation() < 25){
			p.setLocation(25);
			name = "B O Railroad";
			return name;
		}


		if(p.getLocation() > 25 && p.getLocation() < 35)
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
		p.setBalance(balance + 25);
		return balance;
	}

	public int chestSchoolFee(Player p) throws RemoteException{

		int balance = p.getBalance();
		p.setBalance(balance - 150);
		return balance;
	}

	public int chanceChairman(Player p) throws RemoteException{
		int currbalance = p.getBalance();
		int listsize = MonopolyServerStore.getInstance().players.size();
		for(int i = 0; i < listsize; i++){
			Player temp = MonopolyServerStore.getInstance().getPlayers().get(i);
			if(!temp.getName().equals(MonopolyServerStore.getInstance().getCurrentPlayer().getName())){
			int balance = MonopolyServerStore.getInstance().players.get(i).getBalance();
			MonopolyServerStore.getInstance().players.get(i).setBalance(balance + 50);
			}
		}
		currbalance = currbalance - ((listsize - 1) * 50);
		p.setBalance(currbalance);
		return currbalance;
		
	}

	public int chestBirthday(Player p) throws RemoteException{
		int currbalance = p.getBalance();
		int listsize = MonopolyServerStore.getInstance().players.size();
		for(int i = 0; i < listsize; i++){
			Player temp = MonopolyServerStore.getInstance().getPlayers().get(i);
			if(!temp.getName().equals(MonopolyServerStore.getInstance().getCurrentPlayer().getName())) {
				int balance = temp.getBalance();
				MonopolyServerStore.getInstance().players.get(i).setBalance(balance - 10);
			}
		}
		currbalance = currbalance + ((listsize -1) * 10);
		p.setBalance(currbalance);
		return currbalance;
		
	}

	public int chestOpera(Player p) throws RemoteException{
		int currbalance = p.getBalance();
		int listsize = MonopolyServerStore.getInstance().players.size();
		for(int i = 0; i < listsize; i++){
			Player temp = MonopolyServerStore.getInstance().getPlayers().get(i);
			if(!temp.getName().equals(MonopolyServerStore.getInstance().getCurrentPlayer().getName())){
				int balance = MonopolyServerStore.getInstance().players.get(i).getBalance();
				MonopolyServerStore.getInstance().players.get(i).setBalance(balance - 50);
			}
		}
		currbalance = currbalance + ((listsize - 1) * 50);
		p.setBalance(currbalance);
		return currbalance;
		
	}


}
