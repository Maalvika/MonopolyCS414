package com.cs414j.monopoly.view;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class PlayerTest {

	

	@Test
	public final void testBuyProperty() {
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
		
		
	}
    @Test
	public final void testMortgageProperty() {
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
		assertEquals(true,p.getOwnedProperties().isEmpty());
		assertEquals(1437,p.getBalance());
		
		// Unmortgaging the Utility
		
		assertEquals(2,bank.getBankUtilitySet().size());
		p.buyProperty("Electric Co", bank, board);
		ownedUtilities = p.getOwnedUtilities();
		Iterator<Utilities> iter2 = ownedUtilities.iterator();
		Utilities ui = iter2.next();
		assertEquals("Electric Co",ui.getName());
		assertEquals(1,bank.getBankUtilitySet().size());
		
		p.mortgageProperty("Electric Co", bank, board);
		//System.out.println(p.getBalance());
		assertEquals(1,p.getMortgageUtilities().size());
		assertEquals(true,p.getOwnedUtilities().isEmpty());
		assertEquals(1362,p.getBalance());	
		assertEquals(1,bank.getBankUtilitySet().size());
		
		p.unMortgageProperty("Electric Co", bank, board);
		assertEquals(true,p.getMortgageUtilities().isEmpty());
		assertEquals(true,p.getOwnedUtilities().isEmpty());
		assertEquals(1280,p.getBalance());
		
		// unmortgaging the rail road
		assertEquals(4,bank.getBankRailRoad().size());
		p.buyProperty("Pennsylvania Railroad", bank, board);
		ownedRailRoad = p.getOwnedRailRoad();
		Iterator<RailRoad> iter3 = ownedRailRoad.iterator();
		RailRoad r1 = iter3.next();
		assertEquals("Pennsylvania Railroad",r1.getName());
		assertEquals(3,bank.getBankRailRoad().size());
		
		p.mortgageProperty("Pennsylvania Railroad", bank, board);
		//System.out.println(p.getBalance());
		assertEquals(1,p.getMortgageRailRoad().size());
		assertEquals(true,p.getOwnedRailRoad().isEmpty());
		assertEquals(1180,p.getBalance());	
		assertEquals(3,bank.getBankRailRoad().size());
		
		p.unMortgageProperty("Pennsylvania Railroad", bank, board);
		assertEquals(true,p.getMortgageUtilities().isEmpty());
		assertEquals(true,p.getOwnedUtilities().isEmpty());
		assertEquals(1070,p.getBalance());
    	
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
		
		p.buyProperty("Short Line", bank, board);
		p2.payRent("B O Railroad", board, 3);
		assertEquals(777,p.getBalance());
		assertEquals(1063,p2.getBalance());
		
		
    	
    }
	
}


