package com.cs414j.monopoly.view;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



import org.junit.Test;

import com.cs414j.monopoly.view.Token;

public class PlayerTest {
	Player p = new Player("Tom");

	@Test
	public void testPlayer() {
		Player p = new Player("Paul");
		Player c = new Player("Carl");
		String name  = p.getName();
		assertEquals("Paul",name);
		name = c.getName();
		assertEquals("Carl",name);
		name = "Tara";
		p.setName(name);
		assertEquals("Tara", name);
	}

	@Test
	public void testGetLocation() {
		Player p = new Player("Busy");
		p.setLocation(5);
		int l = p.getLocation();
		assertEquals(5,l);

	}
	@Test
	public void testBuyProperty() {
		Board board = new Board();
		Bank bank = new Bank();		
		Player p = new Player("Trump");
		Set<Utilities> ownedUtilities= new HashSet<Utilities>();
		Set<RailRoad> ownedRailRoad = new HashSet<RailRoad>();
		Set<Properties>ownedProperty = new HashSet<Properties>();

		assertEquals(22,bank.getBankPropertiesSet().size());
		p.buyProperty("Mediterranean Avenue", bank, board);
		ownedProperty = p.getOwnedProperties();
		Iterator<Properties> iter = ownedProperty.iterator();
		Properties prop = iter.next();
		assertEquals("Mediterranean Avenue",prop.getName());
		assertEquals(21,bank.getBankPropertiesSet().size());
		//System.out.println(p.getBalance());


		assertEquals(2,bank.getBankUtilitySet().size());
		p.buyProperty("Electric Co", bank, board);
		ownedUtilities = p.getOwnedUtilities();
		Iterator<Utilities> iter2 = ownedUtilities.iterator();
		Utilities ui = iter2.next();
		assertEquals("Electric Co",ui.getName());
		assertEquals(1,bank.getBankUtilitySet().size());
		//System.out.println(p.getBalance());

		assertEquals(4,bank.getBankRailRoad().size());
		p.buyProperty("Pennsylvania Railroad", bank, board);
		ownedRailRoad = p.getOwnedRailRoad();
		Iterator<RailRoad> iter3 = ownedRailRoad.iterator();
		RailRoad r1 = iter3.next();
		assertEquals("Pennsylvania Railroad",r1.getName());
		assertEquals(3,bank.getBankRailRoad().size());
		//System.out.println(p.getBalance());

	}




	@Test
	public void testMortgageProperty() {

		Board board = new Board();
		Bank bank = new Bank();		
		Player p = new Player("Trump");
		Set<Utilities> ownedUtilities= new HashSet<Utilities>();
		Set<RailRoad> ownedRailRoad = new HashSet<RailRoad>();
		Set<Properties>ownedProperty = new HashSet<Properties>();
		p.buyProperty("Mediterranean Avenue", bank, board);
		ownedProperty = p.getOwnedProperties();
		Iterator<Properties> iter = ownedProperty.iterator();
		Properties prop = iter.next();
		assertEquals("Mediterranean Avenue",prop.getName());
		assertEquals(21,bank.getBankPropertiesSet().size());
		assertEquals(2,bank.getBankUtilitySet().size());
		p.buyProperty("Electric Co", bank, board);
		ownedUtilities = p.getOwnedUtilities();
		Iterator<Utilities> iter2 = ownedUtilities.iterator();
		Utilities ui = iter2.next();
		assertEquals("Electric Co",ui.getName());

		assertEquals(1,bank.getBankUtilitySet().size());


		assertEquals(4,bank.getBankRailRoad().size());
		p.buyProperty("Pennsylvania Railroad", bank, board);

		ownedRailRoad = p.getOwnedRailRoad();
		Iterator<RailRoad> iter3 = ownedRailRoad.iterator();
		RailRoad r1 = iter3.next();

		assertEquals("Pennsylvania Railroad",r1.getName());
		assertEquals(3,bank.getBankRailRoad().size());

		// Mortgaging test
		//System.out.println(p.getBalance());

		p.mortgageProperty("Mediterranean Avenue", bank, board);
		//System.out.println(p.getBalance());
		assertEquals(1,p.getMortgageProperties().size());
		assertEquals(true,p.getOwnedProperties().isEmpty());

		//System.out.println();
		assertEquals(1120,p.getBalance());

		p.mortgageProperty("Electric Co", bank, board);
		//System.out.println(p.getBalance());
		assertEquals(1,p.getMortgageUtilities().size());
		assertEquals(true,p.getOwnedUtilities().isEmpty());
		assertEquals(1195,p.getBalance());	

		p.mortgageProperty("Pennsylvania Railroad", bank, board);
		//System.out.println(p.getBalance());
		assertEquals(1,p.getMortgageRailRoad().size());
		assertEquals(true,p.getOwnedRailRoad().isEmpty());
		assertEquals(1295,p.getBalance());	
	}


	@Test
	public void testUnMortgageProperty()
	{
		Board board = new Board();
		Bank bank = new Bank();		
		Player p = new Player("Trump");
		Set<Utilities> ownedUtilities= new HashSet<Utilities>();
		Set<RailRoad> ownedRailRoad = new HashSet<RailRoad>();
		Set<Properties>ownedProperty = new HashSet<Properties>();

		// Un mortgaging the property
		assertEquals(22,bank.getBankPropertiesSet().size());
		p.buyProperty("Mediterranean Avenue", bank, board);
		ownedProperty = p.getOwnedProperties();
		Iterator<Properties> iter = ownedProperty.iterator();
		Properties prop = iter.next();
		assertEquals("Mediterranean Avenue",prop.getName());
		assertEquals(21,bank.getBankPropertiesSet().size());

		p.mortgageProperty("Mediterranean Avenue", bank, board);
		//System.out.println(p.getBalance());
		assertEquals(1,p.getMortgageProperties().size());
		assertEquals(true,p.getOwnedProperties().isEmpty());
		assertEquals(1470,p.getBalance());
		assertEquals(21,bank.getBankPropertiesSet().size());


		p.unMortgageProperty("Mediterranean Avenue", bank, board);
		assertEquals(true,p.getMortgageProperties().isEmpty());
		assertEquals(false,p.getOwnedProperties().isEmpty());
		assertEquals(1437,p.getBalance());

		//Unmortgaging the Utility

		assertEquals(2,bank.getBankUtilitySet().size());
		p.buyProperty("Electric Co", bank, board);
		ownedUtilities = p.getOwnedUtilities();
		Iterator<Utilities> iter2 = ownedUtilities.iterator();
		Utilities ui = iter2.next();
		assertEquals("Electric Co",ui.getName());
		assertEquals(1,bank.getBankUtilitySet().size());


		p.mortgageProperty("Electric Co", bank, board);

		assertEquals(1,p.getMortgageUtilities().size());
		assertEquals(true,p.getOwnedUtilities().isEmpty());
		assertEquals(1362,p.getBalance());	
		assertEquals(1,bank.getBankUtilitySet().size());

		p.unMortgageProperty("Electric Co", bank, board);
		assertEquals(true,p.getMortgageUtilities().isEmpty());

	}

	@Test
	public void testPayRent()
	{
		Board board = new Board();
		Bank bank = new Bank();		
		Player p = new Player("Trump");
		Set<Utilities> ownedUtilities= new HashSet<Utilities>();
		Set<RailRoad> ownedRailRoad = new HashSet<RailRoad>();
		Set<Properties>ownedProperty = new HashSet<Properties>();

		assertEquals(22,bank.getBankPropertiesSet().size());
		p.buyProperty("Mediterranean Avenue", bank, board);
		ownedProperty = p.getOwnedProperties();
		Iterator<Properties> iter = ownedProperty.iterator();
		Properties prop = iter.next();
		assertEquals("Mediterranean Avenue",prop.getName());
		assertEquals(21,bank.getBankPropertiesSet().size());

		Player p2 = new Player("Joe");
		p2.payRent("Mediterranean Avenue", board, 1);
		assertEquals(1442,p.getBalance());
		assertEquals(1498,p2.getBalance());

		//Checking the rent for utility
		p.buyProperty("Electric Co", bank, board);
		ownedUtilities = p.getOwnedUtilities();
		Iterator<Utilities> iter2 = ownedUtilities.iterator();
		Utilities ui = iter2.next();
		assertEquals("Electric Co",ui.getName());
		assertEquals(1,bank.getBankUtilitySet().size());
		p.buyProperty("Water Works", bank, board);

		p2.payRent("Electric Co", board, 6);
		assertEquals(1202,p.getBalance());
		assertEquals(1438,p2.getBalance());

		// pay rent for the railroad
		p.buyProperty("Pennsylvania Railroad", bank, board);
		p2.payRent("Pennsylvania Railroad", board, 3);
		assertEquals(1027,p.getBalance());
		assertEquals(1413,p2.getBalance());

		p.buyProperty("Reading Railroad", bank, board);
		p2.payRent("Pennsylvania Railroad", board, 3);
		assertEquals(877,p.getBalance());
		assertEquals(1363,p2.getBalance());

		p.buyProperty("B O Railroad", bank, board);
		p2.payRent("Reading Railroad", board, 3);
		assertEquals(777,p.getBalance());
		assertEquals(1263,p2.getBalance());
		System.out.println("The balance of p: "+ p.getBalance());

		p.buyProperty("Short Line", bank, board);
		System.out.println("The balance of p after short: " + p.getBalance());
		p2.payRent("B O Railroad", board, 3);
		System.out.println("The balance of p after rent: " + p.getBalance());
		assertEquals(777,p.getBalance());
		assertEquals(1063,p2.getBalance());

	}


	@Test
	public void testBuyHouse() {
		// test to buy house
		// a player should own a property already 

		Board board = new Board();
		Bank bank = new Bank();		
		Player p = new Player("Trump");
		Set<Utilities> ownedUtilities= new HashSet<Utilities>();
		Set<RailRoad> ownedRailRoad = new HashSet<RailRoad>();
		Set<Properties>ownedProperty = new HashSet<Properties>();

		assertEquals(22,bank.getBankPropertiesSet().size());
		p.buyProperty("Mediterranean Avenue", bank, board);

		p.buyHouse("Mediterranean Avenue", board);
		// each house in mediterranean ave costs $50.
		assertEquals(1390,p.getBalance());
		assertEquals(1,p.getHousesOwned());

	}

	@Test
	public void testBuyHotel() {
		// test to build a 
		Board board = new Board();
		Bank bank = new Bank();		
		Player p = new Player("Trump");
		Set<Utilities> ownedUtilities= new HashSet<Utilities>();
		Set<RailRoad> ownedRailRoad = new HashSet<RailRoad>();
		Set<Properties>ownedProperty = new HashSet<Properties>();

		assertEquals(22,bank.getBankPropertiesSet().size());
		p.buyProperty("Mediterranean Avenue", bank, board);

		p.buyHouse("Mediterranean Avenue", board);
		p.buyHouse("Mediterranean Avenue", board);
		p.buyHouse("Mediterranean Avenue", board);
		p.buyHouse("Mediterranean Avenue", board);
		
		// each house in mediterranean ave costs $50.
		assertEquals(1240,p.getBalance());
		System.out.println("balance after buying 4 houses" + p.getBalance());
    	p.buyHotel("Mediterranean Avenue", board);
    	System.out.println("No. of houses:" + p.getHousesOwned());
		assertEquals(4,p.getHousesOwned());
		assertEquals(1,p.getHotelsOwned());
	}
	
	@Test
	public void testMoveForward() {
		Player p = new Player("Beyonce");
		p.moveForward(5);
		assertEquals(5, p.getLocation());
	}
	
	@Test
	public void testPassGo() {
		Player p = new Player("About to get lucky");
		p.setLocation(38);
		assertEquals(38,p.getLocation());
		p.passGo(6);
		assertEquals(1700, p.getBalance());
	}
	
	@Test
	public void testLandedOnGoToJail() {
		Player p = new Player("About to get lucky, not so much");
		p.setLocation(30);
		p.landedOnGoToJail(30);
		assertEquals(10,p.getLocation());
	}
	
	@Test
	public void testPaidJailPenalty() {
		Player p = new Player("About to get lucky, not so much");
		p.setLocation(30);
		p.landedOnGoToJail(30);
		assertEquals(10,p.getLocation());
		p.paidJailPenalty();
		assertEquals(1450, p.getBalance());
	}


}



