package com.cs414j.monopoly.view;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.IntStream;

import org.junit.Test;

public class BankTest {

	@Test
	public final void testBank() {
		Bank newBank = new Bank();
		assertEquals(22,newBank.getBankPropertiesSet().size());
		assertEquals(2,newBank.getBankUtilitySet().size());
		assertEquals(4,newBank.getBankRailRoad().size());
		
	}

	@Test
	public final void testGetInstance() {
		Bank b1;
		b1 = Bank.getInstance();
		assertEquals(22,b1.getBankPropertiesSet().size());
		assertEquals(2,b1.getBankUtilitySet().size());
		assertEquals(4,b1.getBankRailRoad().size());
		
	}

	@Test
	public final void testIsOwned() {
		Board boardObject = new Board();
		Bank bankObject = new Bank();
		boolean temp = bankObject.isOwned("Mediterranean Avenue");
		assertEquals(true,temp);
		boolean temp2 = bankObject.isOwned("Electric Co");
		assertEquals(true,temp2);
		Properties p;
		Utilities u;
		u = boardObject.stringUtilities.get("Electric Co");
		Set<Utilities>  set = new HashSet<Utilities>();
		set = bankObject.getBankUtilitySet();
		boolean temp4 = set.remove(u);
		assertEquals(true,temp4);
		bankObject.setBankUtilitySet(set);
		boolean temp3 = bankObject.isOwned("Electric Co");
		assertEquals(false,temp3);
		
	}

	@Test
	public final void testSetBankUtilitySet() {
		Board b;
		b = Board.getInstance();
		Bank newBank = new Bank();
		Set<Utilities> temp = new HashSet<Utilities>();
		String[] board = b.getBoard();
		temp = newBank.getBankUtilitySet();
		Iterator<Utilities> iterator = temp.iterator();
		while(iterator.hasNext()) 
		{
	        Utilities u = iterator.next();
	        boolean flag = false;
	        int counter =0;
	        for (int i=0; i<40;i++)
	        {
	        	if (u.getName() == board[i])
	        	{
	        		counter = 1;
	        	}
	        }
	        if (counter ==1)
	        {
	        	flag = true;
	        }
	        assertEquals(true,flag);
	        
	        	
		}

	}

	@Test
	public final void testSetBankPropertiesSet() {
		Board b;
		b = Board.getInstance();
		Bank newBank = new Bank();
		Set<Properties> temp = new HashSet<Properties>();
		String[] board = b.getBoard();
		temp = newBank.getBankPropertiesSet();
		Iterator<Properties> iterator = temp.iterator();
		while(iterator.hasNext()) 
		{
	        Properties p = iterator.next();
	        boolean flag = false;
	        int counter =0;
	        for (int i=0; i<40;i++)
	        {
	        	if (p.getName() == board[i])
	        	{
	        		counter = 1;
	        	}
	        }
	        if (counter ==1)
	        {
	        	flag = true;
	        }
	        assertEquals(true,flag);
	        
	        	
		}

	}

	@Test
	public final void testSetBankRailRoad() {
		Board b;
		b = Board.getInstance();
		Bank newBank = new Bank();
		Set<RailRoad> temp = new HashSet<RailRoad>();
		String[] board = b.getBoard();
		temp = newBank.getBankRailRoad();
		Iterator<RailRoad> iterator = temp.iterator();
		while(iterator.hasNext()) 
		{
	        RailRoad r = iterator.next();
	        System.out.println(r.getName());
	        boolean flag = false;
	        int counter =0;
	        for (int i=0; i<40;i++)
	        {
	        	if (r.getName() == board[i])
	        	{
	        		counter = 1;
	        	}
	        }
	        if (counter ==1)
	        {
	        	flag = true;
	        }
	        assertEquals(true,flag);
		}
		
		
	}

	@Test
	public final void testUnMortgageProperty() {
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
			
	}

	@Test
	public final void testUnMortgageUtility() {
		Board board = new Board();
		Bank bank = new Bank();		
		Player p = new Player("Trump");
		Set<Utilities> ownedUtilities= new HashSet<Utilities>();
		Set<RailRoad> ownedRailRoad = new HashSet<RailRoad>();
		Set<Properties>ownedProperty = new HashSet<Properties>();
		
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
		assertEquals(1425,p.getBalance());	
		assertEquals(1,bank.getBankUtilitySet().size());
		
		p.unMortgageProperty("Electric Co", bank, board);
		assertEquals(true,p.getMortgageUtilities().isEmpty());
		assertEquals(true,p.getOwnedUtilities().isEmpty());
		assertEquals(1343,p.getBalance());
	}

	@Test
	public final void testUnMortgageRailRoad() {
		Board board = new Board();
		Bank bank = new Bank();		
		Player p = new Player("Trump");
		Set<Utilities> ownedUtilities= new HashSet<Utilities>();
		Set<RailRoad> ownedRailRoad = new HashSet<RailRoad>();
		Set<Properties>ownedProperty = new HashSet<Properties>();
		
		
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
		assertEquals(1400,p.getBalance());	
		assertEquals(3,bank.getBankRailRoad().size());
		
		p.unMortgageProperty("Pennsylvania Railroad", bank, board);
		assertEquals(true,p.getMortgageUtilities().isEmpty());
		assertEquals(true,p.getOwnedUtilities().isEmpty());
		assertEquals(1290,p.getBalance());
	}

	@Test
	public final void testGiveLoanProperty() {
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
		
		p.mortgageProperty("Mediterranean Avenue", bank, board);
		//System.out.println(p.getBalance());
		assertEquals(1,p.getMortgageProperties().size());
		assertEquals(true,p.getOwnedProperties().isEmpty());
		assertEquals(1470,p.getBalance());
		assertEquals(21,bank.getBankPropertiesSet().size());
	}

	@Test
	public final void testGiveLoanUtility() {
		Board board = new Board();
		Bank bank = new Bank();		
		Player p = new Player("Trump");
		Set<Utilities> ownedUtilities= new HashSet<Utilities>();
		Set<RailRoad> ownedRailRoad = new HashSet<RailRoad>();
		Set<Properties>ownedProperty = new HashSet<Properties>();
		
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
		assertEquals(1425,p.getBalance());	
		assertEquals(1,bank.getBankUtilitySet().size());
		
	}

	@Test
	public final void testGiveLoanRailRoad() {
		Board board = new Board();
		Bank bank = new Bank();		
		Player p = new Player("Trump");
		Set<Utilities> ownedUtilities= new HashSet<Utilities>();
		Set<RailRoad> ownedRailRoad = new HashSet<RailRoad>();
		Set<Properties>ownedProperty = new HashSet<Properties>();
		
		
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
		assertEquals(1400,p.getBalance());	
		assertEquals(3,bank.getBankRailRoad().size());
	}

	@Test
	public final void testGetBankHouse() {
		Bank newBank = new Bank();
		newBank.setBankHouse(2);
		assertEquals(2,newBank.getBankHouse());
	}

	@Test
	public final void testSetBankHouse() {
		Bank newBank = new Bank();
		newBank.setBankHouse(2);
		assertEquals(2,newBank.getBankHouse());
	}

	@Test
	public final void testGetBankHotel() {
		Bank newBank = new Bank();
		newBank.setBankHotel(2);
		assertEquals(2,newBank.getBankHotel());
	}

	@Test
	public final void testSetBankHotel() {
		Bank newBank = new Bank();
		newBank.setBankHotel(2);
		assertEquals(2,newBank.getBankHotel());
	}

	@Test
	public final void testGetBankUtilitySet() {
		Bank newBank = new Bank();
		assertEquals(2,newBank.getBankUtilitySet().size());
	}

	@Test
	public final void testGetBankPropertiesSet() {
		Bank newBank = new Bank();
		assertEquals(22,newBank.getBankPropertiesSet().size());
	}

	@Test
	public final void testGetBankRailRoad() {
		Bank newBank = new Bank();
		assertEquals(4,newBank.getBankRailRoad().size());
	}


}
