package com.cs414j.monopoly.common;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.cs414j.monopoly.client.main.ClientCallback;
import com.cs414j.monopoly.client.main.ClientMain;
import com.cs414j.monopoly.controller.MonopolyOptions;
import com.cs414j.monopoly.server.model.MonopolyServerStore;
import com.cs414j.monopoly.server.model.Properties;
import com.cs414j.monopoly.server.model.RailRoad;
import com.cs414j.monopoly.server.model.Utilities;

public class PlayerImpl implements Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int location;
	private int balance;
	private HashSet<Properties> ownedProperty;
	private int housesOwned = 0;
	private int hotelsOwned = 0;
	private HashSet<Utilities> ownedUtilities;
	private HashSet<RailRoad> ownedRailRoad;
	private Token token;
	private HashSet<Properties> mortgageProperties;
	private HashSet<Utilities> mortgageUtilities;
	private HashSet<RailRoad> mortgageRailRoad;
	private int propertyCost;

	public PlayerImpl(String n) {
		name = n;
		location = 0;
		balance = 1500;
		ownedProperty = new HashSet<Properties>();
		ownedUtilities = new HashSet<Utilities>();
		ownedRailRoad = new HashSet<RailRoad>();
		mortgageProperties = new HashSet<>();
		mortgageRailRoad = new HashSet<>();
		mortgageUtilities = new HashSet<>();
		propertyCost = 0;
	}

	public int getpropertyCost() {
		// to get player's name

		return propertyCost;
	}

	public String getName() {
		// to get player's name
		return name;
	}

	public void setName(String name) {
		// to set player's name
		this.name = name;
	}

	public Token getToken() {
		return token;
	}

	public int getLocation() {
		// returns current location of the player
		return location;
	}

	public void setLocation(int l) {
		location = l;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int b) {
		this.balance = b;
	}

	public Set<Properties> getOwnedProperties() {
		return ownedProperty;
	}

	public Set<Utilities> getOwnedUtilities() {
		return ownedUtilities;
	}

	public void moveForward(int diceValue) {
		// would move the player dicevalue squares forward on the board
		location = (location + diceValue) % 40;
	}

	public void setToken(Token t) {
		this.token = t;
	}

	public int getHousesOwned() {
		return this.housesOwned;
	}

	public int getHotelsOwned() {
		return this.hotelsOwned;
	}

	public Set<RailRoad> getOwnedRailRoad() {
		return ownedRailRoad;
	}

	public Set<Utilities> getMortgageUtilities() {
		return this.mortgageUtilities;
	}

	public Set<RailRoad> getMortgageRailRoad() {
		return this.mortgageRailRoad;
	}

	public Set<Properties> getMortgageProperties() {
		return mortgageProperties;
	}
	public Player getOwner(String name){
		Player owner = null;
		if (MonopolyServerStore.getBoardInstance().stringProperties.containsKey(name)) {
			Properties p = getPropertyObject(name);
			owner = p.owner;
			return owner;
		}
		if (MonopolyServerStore.getBoardInstance().stringUtilities.containsKey(name)){
			Utilities u = this.getUtilityObject(name);
			owner = u.getOwner();
			return owner;			
		}
		
		if(MonopolyServerStore.getBoardInstance().stringRailRoad.containsKey(name)){
			RailRoad r = this.getRailRoadObject(name);
			owner = r.getOwner();
			return owner;
		}
		return owner		
	}
	
	public int getRent(String name) throws RemoteException{
		int rent = 0;
		if (MonopolyServerStore.getBoardInstance().stringProperties.containsKey(name)) {
			Properties p = getPropertyObject(name);
			rent = p.rentInitial;
			return rent;
		}
		if (MonopolyServerStore.getBoardInstance().stringUtilities.containsKey(name)){
			Utilities u = this.getUtilityObject(name);
			rent = u.getRentFirstUtility();
			return rent;
		}
		if(MonopolyServerStore.getBoardInstance().stringRailRoad.containsKey(name)){
			RailRoad r = this.getRailRoadObject(name);
			rent = r.getRentInitial();
			return rent
		}
		
		return rent;
	}
	

	public Set<String> OwnedSquareName() {
		// Iterates through the three hashSets of owned property/
		// utility/railroad and populates their names in the propertyNames
		// hashSet

		HashSet<String> propertyNames = new HashSet<String>();
		if (!ownedProperty.isEmpty()) {
			Iterator<Properties> itr = ownedProperty.iterator();

			while (itr.hasNext()) {
				Properties p = (Properties) itr.next();
				propertyNames.add(p.getName());
			}
		}

		if (!ownedUtilities.isEmpty()) {
			Iterator<Utilities> itrU = ownedUtilities.iterator();
			while (itrU.hasNext()) {

				Utilities u = (Utilities) itrU.next();
				propertyNames.add(u.getName());
			}
		}

		if (!ownedRailRoad.isEmpty()) {
			Iterator<RailRoad> itrR = ownedRailRoad.iterator();
			while (itrR.hasNext()) {

				RailRoad r = (RailRoad) itrR.next();
				propertyNames.add(r.getName());
			}
		}
		return propertyNames;
	}

	public Set<String> mortgagedSquareName() {
		// gives names of the mortgaged properties
		// Iterates through the three hashSets of mortgaged property/
		// utility/railroad and populates their names in the propertyNames
		// hashSet

		HashSet<String> mortgagedProperties = new HashSet<String>();
		if (!(mortgageProperties.isEmpty())) {
			Iterator<Properties> itr = this.mortgageProperties.iterator();

			while (itr.hasNext()) {
				Properties p = (Properties) itr.next();
				mortgagedProperties.add(p.getName());
			}
		}

		if (!(this.mortgageUtilities.isEmpty())) {

			Iterator<Utilities> itrU = this.mortgageUtilities.iterator();
			while (itrU.hasNext()) {

				Utilities u = (Utilities) itrU.next();
				mortgagedProperties.add(u.getName());
			}
		}

		if (!(this.mortgageRailRoad.isEmpty())) {
			Iterator<RailRoad> itrR = ownedRailRoad.iterator();
			while (itrR.hasNext()) {

				RailRoad r = (RailRoad) itrR.next();
				mortgagedProperties.add(r.getName());
			}
		}

		return mortgagedProperties;
	}

	public void unMortgageProperty(String name) throws RemoteException {

		// asks bank for unmortgaging a particular property
		if (Board.stringProperties.containsKey(name)) {
			Properties p = getPropertyObject(name);
			if (mortgageProperties.contains(p)) {
				MonopolyServerStore.getBankInstance().unMortgageProperty(this, p);
				this.mortgageProperties.remove(p);
				this.ownedProperty.add(p);
			}
		}
		if (MonopolyServerStore.getBoardInstance().stringUtilities.containsKey(name)) {
			Utilities u = this.getUtilityObject(name);
			if (this.mortgageUtilities.contains(u)) {
				MonopolyServerStore.getBankInstance().unMortgageUtility(this, u);
				this.mortgageUtilities.remove(u);
				this.ownedUtilities.add(u);
			}
		}

		if (MonopolyServerStore.getBoardInstance().stringRailRoad.containsKey(name)) {
			RailRoad r = this.getRailRoadObject(name);
			if (this.mortgageRailRoad.contains(r)) {
				MonopolyServerStore.getBankInstance().unMortgageRailRoad(this, r);
				this.mortgageRailRoad.remove(r);
				this.ownedRailRoad.add(r);
			}
		}

		MonopolyOptions.changePlayerDetails(this);
	}

	public boolean isPropertyOwned(Properties p) {
		// checks if a particular method is owned by a property
		if (ownedProperty.contains(p)) {
			return true;
		} else
			return false;
	}

	public void passGo(int diceValue) throws RemoteException {
		// This is to add $200 every time a player lands on or crosses "GO".

		if (getLocation() + diceValue >= 40) {
			int newBalance = getBalance() + 200;
			this.setBalance(newBalance);
			String input = "$200 has been added to your balance";
			MonopolyOptions.displayPopUp(input);
			MonopolyOptions.changePlayerDetails(this);
		}
	}

	public void landedOnGoToJail(int l) {
		// to check if a player landed on Go To Jail square
		// if it does send him to "in jail" location = 10

		if (l == 30) {
			this.setLocation(10);

		}
	}

	public void paidJailPenalty() throws RemoteException {
		this.setBalance(balance - 50);
		MonopolyOptions.changePlayerDetails(this);
	}

	public Properties getPropertyObject(String key) {
		// if the key is contained in the hashMap
		// the property object is returned corresponding to that key
		if (MonopolyServerStore.getBoardInstance().stringProperties.containsKey(key)) {
			return MonopolyServerStore.getBoardInstance().stringProperties.get(key);
		} else
			return null;
	}

	public Utilities getUtilityObject(String key) {

		if (MonopolyServerStore.getBoardInstance().stringUtilities.containsKey(key)) {
			return MonopolyServerStore.getBoardInstance().stringUtilities.get(key);
		} else
			return null;
	}

	public RailRoad getRailRoadObject(String key) {

		if (MonopolyServerStore.getBoardInstance().stringRailRoad.containsKey(key)) {
			return MonopolyServerStore.getBoardInstance().stringRailRoad.get(key);
		} else
			return null;
	}

	public void buyProperty(String name) throws RemoteException {
		// takes square name from UI and
		// checks if it is a utility or a property or a rairoad
		// and accordingly gets it cost, subtracts the cost from balance,
		// adds the square into player's ownedproperties/utilities/railroad

		if (MonopolyServerStore.getBoardInstance().stringProperties.containsKey(name)) {
			Properties p = getPropertyObject(name);

			System.out.println("my prop name:" + p.getName());
			System.out.println(
					"my prop name:" + MonopolyServerStore.getBankInstance().getBankPropertiesSet().contains(p));
			int cost = p.getCost();
			if (MonopolyServerStore.getBankInstance().getBankPropertiesSet().contains(p)) {
				if (balance > cost) {
					balance = balance - cost;
					ownedProperty.add(p);
					p.setOwner(this);
					MonopolyServerStore.getBankInstance().getBankPropertiesSet().remove(p);
					this.propertyCost = propertyCost + cost;
				} else {
					MonopolyOptions
							.displayPopUp("Your balance is insufficient!!!! " + "You cant purchase the property");
				}
			}

		}

		if (MonopolyServerStore.getBoardInstance().stringUtilities.containsKey(name)) {
			Utilities u = this.getUtilityObject(name);
			int cost = u.getCost();
			if (MonopolyServerStore.getBankInstance().getBankUtilitySet().contains(u)) {
				if (balance > cost) {

					balance = balance - cost;
					ownedUtilities.add(u);
					u.setOwner(this);
					MonopolyServerStore.getBankInstance().getBankUtilitySet().remove(u);
					this.propertyCost = propertyCost + cost;
				} else {
					MonopolyOptions
							.displayPopUp("Your balance is insufficient!!!! " + "You cant purchase the property");
				}
			}

		}

		if (MonopolyServerStore.getBoardInstance().stringRailRoad.containsKey(name))

		{

			RailRoad r = this.getRailRoadObject(name);
			int cost = r.getCost();
			if (MonopolyServerStore.getBankInstance().getBankRailRoad().contains(r)) {
				if (balance > cost) {
					balance = balance - cost;
					ownedRailRoad.add(r);
					r.setOwner(this);
					MonopolyServerStore.getBankInstance().getBankRailRoad().remove(r);
					this.propertyCost = propertyCost + cost;
				} else {
					MonopolyOptions
							.displayPopUp("Your balance is insufficient!!!! " + "You cant purchase the property");
				}
			}

		}
		
		MonopolyServerStore.changeAllPlayerDetails();
		String myMessage = "Congrats!!!! "+name+" is yours. \n Your new balance is: $"+balance;
		String otherMessage = "Property: "+ name+" is sold to Player: "+this.getName();
		MonopolyServerStore.sendMessageToAll(myMessage, otherMessage);
		
	}
	
	public void buyProperty(String name, int bid) throws RemoteException {
		// takes square name from UI and
		// checks if it is a utility or a property or a rairoad
		// and accordingly gets it cost, subtracts the cost from balance,
		// adds the square into player's ownedproperties/utilities/railroad

		if (MonopolyServerStore.getBoardInstance().stringProperties.containsKey(name)) {
			Properties p = getPropertyObject(name);

			System.out.println("my prop name:" + p.getName());
			System.out.println(
					"my prop name:" + MonopolyServerStore.getBankInstance().getBankPropertiesSet().contains(p));
			int cost = p.getCost();
			if (MonopolyServerStore.getBankInstance().getBankPropertiesSet().contains(p)) {
				if (balance > bid) {
					balance = balance - bid;
					ownedProperty.add(p);
					p.setOwner(this);
					MonopolyServerStore.getBankInstance().getBankPropertiesSet().remove(p);
					this.propertyCost = propertyCost + cost;
				} else {
					MonopolyOptions
							.displayPopUp("Your balance is insufficient!!!! " + "You cant purchase the property");
				}
			}

		}

		if (MonopolyServerStore.getBoardInstance().stringUtilities.containsKey(name)) {
			Utilities u = this.getUtilityObject(name);
			int cost = u.getCost();
			if (MonopolyServerStore.getBankInstance().getBankUtilitySet().contains(u)) {
				if (balance > bid) {

					balance = balance - bid;
					ownedUtilities.add(u);
					u.setOwner(this);
					MonopolyServerStore.getBankInstance().getBankUtilitySet().remove(u);
					this.propertyCost = propertyCost + cost;
				} else {
					MonopolyOptions
							.displayPopUp("Your balance is insufficient!!!! " + "You cant purchase the property");
				}
			}

		}

		if (MonopolyServerStore.getBoardInstance().stringRailRoad.containsKey(name))

		{

			RailRoad r = this.getRailRoadObject(name);
			int cost = r.getCost();
			if (MonopolyServerStore.getBankInstance().getBankRailRoad().contains(r)) {
				if (balance > bid) {
					balance = balance - bid;
					ownedRailRoad.add(r);
					r.setOwner(this);
					MonopolyServerStore.getBankInstance().getBankRailRoad().remove(r);
					this.propertyCost = propertyCost + cost;
				} else {
					MonopolyOptions
							.displayPopUp("Your balance is insufficient!!!! " + "You cant purchase the property");
				}
			}

		}
		
		MonopolyServerStore.changeAllPlayerDetails();
		String myMessage = "Congrats!!!! "+name+" is yours. \n Your new balance is: $"+balance;
		String otherMessage = "Property: "+ name+" is sold to Player: "+this.getName();
		MonopolyServerStore.sendMessageToAll(myMessage, otherMessage);
		
	}

	public void payRent(String name, int diceValue) throws RemoteException {
		// pay rent to another player
		// by getting the rent from square.property
		// subtracting the rent from player's balance
		// adding the rent in owner's balance
		if (MonopolyServerStore.getBoardInstance().stringProperties.containsKey(name)) {
			Properties p = getPropertyObject(name);
			int rent = p.getRentInitial();
			balance = balance - rent;
			Player receiver = p.getOwner();
			receiver.setBalance(receiver.getBalance() + rent);
		}

		if (MonopolyServerStore.getBoardInstance().stringUtilities.containsKey(name)) {
			Utilities u = this.getUtilityObject(name);
			Player receiver = u.getOwner();
			if (receiver.getOwnedRailRoad().size() == 2) {
				int rent = 10 * diceValue;
				balance = balance - rent;
				receiver.setBalance(receiver.getBalance() + rent);
			} else {
				int rent = 4 * diceValue;
				balance = balance - rent;
				receiver.setBalance(receiver.getBalance() + rent);
			}
		}
		if (MonopolyServerStore.getBoardInstance().stringRailRoad.containsKey(name)) {

			RailRoad r = this.getRailRoadObject(name);
			Player receiver = r.getOwner();
			if (receiver.getOwnedRailRoad().size() == 1) {
				balance = balance - 25;
				receiver.setBalance(receiver.getBalance() + 25);
			} else if (receiver.getOwnedRailRoad().size() == 2) {
				balance = balance - 50;
				receiver.setBalance(receiver.getBalance() + 50);
			} else if (receiver.getOwnedRailRoad().size() == 3) {
				balance = balance - 100;
				receiver.setBalance(receiver.getBalance() + 100);
			} else if (receiver.getOwnedRailRoad().size() == 4) {
				balance = balance - 200;
				receiver.setBalance(receiver.getBalance() + 200);
			}
		}
		MonopolyServerStore.changeAllPlayerDetails();
	}

	public void payTax() throws RemoteException {
		// deducts $100 if player lands on luxury tax
		// deducts $200 from player's balance if player lands on income tax

		if (location == 4) {
			// income tax
			balance = balance - 200;
		}
		if (location == 38) {
			// luxury tax
			balance = balance - 100;
		}
		MonopolyServerStore.changeAllPlayerDetails();
	}

	public void mortgageProperty(String name) throws RemoteException {
		// asks bank for loan on a particular property
		if (MonopolyServerStore.getBoardInstance().stringProperties.containsKey(name)) {
			Properties p = getPropertyObject(name);
			if (isPropertyOwned(p) == true) {
				MonopolyServerStore.getBankInstance().giveLoanProperty(this, p);
				this.mortgageProperties.add(p);
				this.ownedProperty.remove(p);
				int cost = p.getCost();
				propertyCost = propertyCost - cost;

			}
		}
		if (MonopolyServerStore.getBoardInstance().stringUtilities.containsKey(name)) {
			Utilities u = this.getUtilityObject(name);
			if (ownedUtilities.contains(u)) {
				MonopolyServerStore.getBankInstance().giveLoanUtility(this, u);
				this.mortgageUtilities.add(u);
				this.ownedUtilities.remove(u);
				int cost = u.getCost();
				propertyCost = propertyCost - cost;
			}
		}

		if (MonopolyServerStore.getBoardInstance().stringRailRoad.containsKey(name)) {
			RailRoad r = this.getRailRoadObject(name);
			if (ownedRailRoad.contains(r)) {
				MonopolyServerStore.getBankInstance().giveLoanRailRoad(this, r);
				this.mortgageRailRoad.add(r);
				this.ownedRailRoad.remove(r);
				int cost = r.getCost();
				propertyCost = propertyCost - cost;
			}

		}
		MonopolyServerStore.changeAllPlayerDetails();
	}

	public void buyHouse(String name) throws RemoteException {
		// buy house on owned property
		if (MonopolyServerStore.getBoardInstance().stringProperties.containsKey(name)) {
			Properties p = getPropertyObject(name);

			if (ownedProperty.contains(p)) {
				if (balance > p.getHouseCost()) {
					balance = balance - p.getHouseCost();
					housesOwned++;
					p.houses++;
				}
			}
		}

		MonopolyServerStore.changeAllPlayerDetails();
	}

	public void buyHotel(String name) throws RemoteException {
		// buy hotel on owned property
		if (MonopolyServerStore.getBoardInstance().stringProperties.containsKey(name)) {
			Properties p = getPropertyObject(name);

			if (ownedProperty.contains(p)) {
				if (p.houses >= 4) {
					if (balance > p.getHotelCost()) {
						balance = balance - p.getHotelCost();
						hotelsOwned++;
						p.hotel = true;
					}

				}
			}
		}

		MonopolyServerStore.changeAllPlayerDetails();
	}

}
