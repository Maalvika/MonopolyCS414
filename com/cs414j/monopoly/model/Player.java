package com.cs414j.monopoly.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.cs414j.monopoly.view.MonopolyMain;
import com.cs414j.monopoly.view.MonopolyOptions;
import com.cs414j.monopoly.view.Token;

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
	private HashSet<Properties>mortgageProperties;
	private HashSet<Utilities>mortgageUtilities;
	private HashSet<RailRoad>mortgageRailRoad;


	public Player(String n){
		name = n;
		location = 0;
		balance = 1500;
		ownedProperty = new HashSet<Properties>();
		ownedUtilities = new HashSet<Utilities>();
		ownedRailRoad = new HashSet<RailRoad>();
		mortgageProperties = new HashSet<>();
		mortgageRailRoad = new HashSet<>();
		mortgageUtilities = new HashSet<>();
		

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

	public int getLocation(){
		// returns current location of the player
		return location;
	}

	public void setLocation(int l){
		location = l;
	}

	public int getBalance(){
		return balance;
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

	public void setToken(Token t){
		this.token = t;
	}
	
	public int getHousesOwned(){
		return this.housesOwned;
	}

	public int getHotelsOwned(){
		return this.hotelsOwned;
	}
	
	public Set<RailRoad> getOwnedRailRoad(){
		return ownedRailRoad;
	}
	
	public Set<Utilities> getMortgageUtilities(){
		return this.mortgageUtilities;
	}
	
	public Set<RailRoad> getMortgageRailRoad(){
		return this.mortgageRailRoad;
	}
	
	public Set<Properties> getMortgageProperties(){
		return mortgageProperties;
	}


	public Set<String> OwnedSquareName(){
		// Iterates through the three hashSets of owned property/
		// utility/railroad and populates their names in the propertyNames
		// hashSet

		HashSet<String> propertyNames = new HashSet<String>();
		if(!ownedProperty.isEmpty()){
			Iterator<Properties> itr = ownedProperty.iterator();

			while(itr.hasNext())
			{
				Properties p = (Properties)itr.next();
				propertyNames.add(p.getName());
			}
		}

		if(!ownedUtilities.isEmpty()){
			Iterator<Utilities> itrU = ownedUtilities.iterator();
			while(itrU.hasNext()){

				Utilities u = (Utilities)itrU.next();
				propertyNames.add(u.getName());
			}
		}

		if(!ownedRailRoad.isEmpty()){
			Iterator<RailRoad> itrR = ownedRailRoad.iterator();
			while(itrR.hasNext())
			{

				RailRoad r = (RailRoad)itrR.next();
				propertyNames.add(r.getName());
			}
		}
		return propertyNames;
	}

	public Set<String> mortgagedSquareName(){
		//gives names of the mortgaged properties
		// Iterates through the three hashSets of mortgaged property/
		// utility/railroad and populates their names in the propertyNames
		// hashSet
		
		HashSet<String> mortgagedProperties = new HashSet<String>();
		if(!(mortgageProperties.isEmpty())){
			Iterator<Properties> itr = this.mortgageProperties.iterator();

			while(itr.hasNext())
			{
				Properties p = (Properties)itr.next();
				mortgagedProperties.add(p.getName());
			}
		}

		if(!(this.mortgageUtilities.isEmpty())){

			Iterator<Utilities> itrU = this.mortgageUtilities.iterator();
			while(itrU.hasNext()){

				Utilities u = (Utilities)itrU.next();
				mortgagedProperties.add(u.getName());
			}
		}


		if(!(this.mortgageRailRoad.isEmpty())){
			Iterator<RailRoad> itrR = ownedRailRoad.iterator();
			while(itrR.hasNext())
			{

				RailRoad r = (RailRoad)itrR.next();
				mortgagedProperties.add(r.getName());
			}
		}

		return mortgagedProperties;
	}
        public void unMortgageProperty(String name, Bank b, Board board){

		// asks bank for unmortgaging a particular property 
		if(board.stringProperties.containsKey(name))
		{
			Properties p = getPropertyObject(name, board );
			if(mortgageProperties.contains(p))
			{
				b.unMortgageProperty(this, p);
				this.mortgageProperties.remove(p);
				this.ownedProperty.add(p);
			}
		}
		if(board.stringUtilities.containsKey(name)){
			Utilities u = this.getUtilityObject(name, board);
			if(this.mortgageUtilities.contains(u)){
				b.unMortgageUtility(this, u);
				this.mortgageUtilities.remove(u);
				this.ownedUtilities.add(u);
			}
		}

		if(board.stringRailRoad.containsKey(name)){
			RailRoad r = this.getRailRoadObject(name, board);
			if(this.mortgageRailRoad.contains(r)){
				b.unMortgageRailRoad(this, r);
				this.mortgageRailRoad.remove(r);
				this.ownedRailRoad.add(r);
			}
		}

		MonopolyOptions.changePlayerDetails(this);
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

		if(getLocation() + diceValue >= 40){
			int newBalance = getBalance() + 200;
			this.setBalance(newBalance);
			String input = "$200 has been added to your balance";
			com.cs414j.monopoly.view.MonopolyOptions.displayPopUp(input);	
			MonopolyOptions.changePlayerDetails(this);
		}
	}

	public void landedOnGoToJail(int l){
		// to check if a player landed on Go To Jail square
		// if it does send him to "in jail" location = 10

		if(l == 30){
			this.setLocation(10);

		}
	}

	public void paidJailPenalty(){
		this.setBalance(balance - 50);
		MonopolyOptions.changePlayerDetails(this);
	}

	public Properties getPropertyObject(String key, Board board){
		// if the key is contained in the hashMap
		// the property object is returned corresponding to that key
		if(board.stringProperties.containsKey(key)){
			return board.stringProperties.get(key);
		}
		else return null;
	}

	public Utilities getUtilityObject(String key, Board board){

		if(board.stringUtilities.containsKey(key)){
			return board.stringUtilities.get(key);
		}
		else return null;
	}

	public RailRoad getRailRoadObject(String key, Board board){

		if(board.stringRailRoad.containsKey(key)){
			return board.stringRailRoad.get(key);
		}
		else return null;
	}


	public void buyProperty(String name, Bank b, Board board){
		// takes square name from UI and 
		// checks if it is a utility or a property or a rairoad
		// and accordingly gets it cost, subtracts the cost from balance,
		// adds the square into player's ownedproperties/utilities/railroad

		if(board.stringProperties.containsKey(name))
		{
			Properties p = getPropertyObject(name, board );

			int cost = p.getCost();
			if(b.getBankPropertiesSet().contains(p) && balance > cost){
				balance =  balance - cost;
				ownedProperty.add(p);
				p.setOwner(this);
				b.getBankPropertiesSet().remove(p);
			} else {
				MonopolyOptions.displayPopUp("Your balance is insufficient!!!! "
						+ "You cant purchase the property");
			}
		}

		if(board.stringUtilities.containsKey(name))
		{
			Utilities u = this.getUtilityObject(name, board);
			int cost = u.getCost();
			if(b.getBankUtilitySet().contains(u) && balance > cost){

				balance = balance -cost;
				ownedUtilities.add(u);
				u.setOwner(this);
				b.getBankUtilitySet().remove(u);
			}
			
			else {
				MonopolyOptions.displayPopUp("Your balance is insufficient!!!! "
						+ "You cant purchase the property");
			}
		}

		if(board.stringRailRoad.containsKey(name))

		{

			RailRoad r = this.getRailRoadObject(name, board);
			int cost = r.getCost();
			if(b.getBankRailRoad().contains(r) && balance > cost ){

				balance = balance - cost;
				ownedRailRoad.add(r);
				r.setOwner(this);
				b.getBankRailRoad().remove(r);
		}
			
		else {
				MonopolyOptions.displayPopUp("Your balance is insufficient!!!! "
						+ "You cant purchase the property");
		     }

		}
		MonopolyOptions.changePlayerDetails(this);
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
				receiver.balance = receiver.balance + rent;
			}
			else
			{
				int rent = 4 * diceValue;
				balance = balance - rent;
				receiver.balance = receiver.balance + rent;
			}
		}
		if(b.stringRailRoad.containsKey(name))
		{

			RailRoad r = this.getRailRoadObject(name, b);
			Player receiver = r.getOwner();
			if(receiver.ownedRailRoad.size() == 1){
				balance = balance - 25;
				receiver.balance = receiver.balance + 25;
			}
			else if(receiver.ownedRailRoad.size() == 2){
				balance = balance - 50;
				receiver.balance = receiver.balance + 50;
			}
			else if(receiver.ownedRailRoad.size() == 3){
				balance = balance - 100;
				receiver.balance = receiver.balance + 100;
			}
			else if(receiver.ownedRailRoad.size() == 4){
				balance = balance -200;
				receiver.balance = receiver.balance + 200;
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

	public void mortgageProperty(String name, Bank b, Board board){
		// asks bank for loan on a particular property 
		if(board.stringProperties.containsKey(name))
		{
			Properties p = getPropertyObject(name, board );
			if(isPropertyOwned(p) == true)
			{
				b.giveLoanProperty(this, p);
				this.mortgageProperties.add(p);
				this.ownedProperty.remove(p);
				
			}
		}
		if(board.stringUtilities.containsKey(name)){
			Utilities u = this.getUtilityObject(name, board);
			if(ownedUtilities.contains(u)){
				b.giveLoanUtility(this, u);
				this.mortgageUtilities.add(u);
				this.ownedUtilities.remove(u);
			}
		}

		if(board.stringRailRoad.containsKey(name)){
			RailRoad r = this.getRailRoadObject(name, board);
			if(ownedRailRoad.contains(r)){
				b.giveLoanRailRoad(this, r);
				this.mortgageRailRoad.add(r);
				this.ownedRailRoad.remove(r);
			}

		}
		MonopolyOptions.changePlayerDetails(this);
	}

	public void buyHouse(String name, Board board){
		// buy house on owned property
		if(board.stringProperties.containsKey(name))
		{
			Properties p = getPropertyObject(name, board );

			if(ownedProperty.contains(p))
			{
				if(balance > p.getHouseCost())
				{
					balance=balance-p.getHouseCost();
					housesOwned++;
					p.houses++;
				}
			}
		}
		
		MonopolyOptions.changePlayerDetails(this);
	}

	public void buyHotel(String name, Board board){
		// buy hotel on owned property
		if(board.stringProperties.containsKey(name))
		{
			Properties p = getPropertyObject(name, board );			

			if(ownedProperty.contains(p))
			{
				if(p.houses >= 4)
				{
					if(balance > p.getHotelCost())
					{
						balance=balance-p.getHotelCost();
						hotelsOwned++;
						p.hotel = true;
					}

				}
			}
		}
		
		MonopolyOptions.changePlayerDetails(this);
	}
}
