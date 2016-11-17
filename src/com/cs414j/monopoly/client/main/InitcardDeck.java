package com.cs414j.monopoly.client.main;

import java.rmi.RemoteException;
import java.util.Random;

import com.cs414j.monopoly.common.Player;
import com.cs414j.monopoly.controller.MonopolyOptions;
import com.cs414j.monopoly.server.model.MonopolyServerStore;

public class InitcardDeck {
	public String[] chance;
	public String[] chest;
	
	public InitcardDeck(){
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
	
	public int generateRandomChance() throws RemoteException{
		Random r = new Random();
		int index = r.nextInt((15 - 0)+1);
		String s = chance[index];
		MonopolyOptions.displayPopUp(s);
		return index;

		}
		
		public int generateRandomChest() throws RemoteException{
			Random r = new Random();
			int index = r.nextInt((16-0)+1);
			String s = chest[index];
			MonopolyOptions.displayPopUp(s);
			return index;
}



}
