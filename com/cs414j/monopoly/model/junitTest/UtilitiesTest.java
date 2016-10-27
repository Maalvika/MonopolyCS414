package com.cs414j.monopoly.model.junitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cs414j.monopoly.model.Player;
import com.cs414j.monopoly.model.Utilities;

public class UtilitiesTest {

	@Test
	public final void testUtilities() {
		Utilities u1 = new Utilities(12);
		Utilities u2 = new Utilities(28);
		String name  = u1.getName();
		assertEquals("Electric Co",name);
		String name2 = u2.getName();
		assertEquals("Water Works",name2);		
	}

	@Test
	public final void testSetUtilitiesValues() {
		Utilities u1 = new Utilities(12);
		Utilities u2 = new Utilities(28);
		int cost  = u1.getCost();
		assertEquals(150,cost);
		int cost2 = u2.getCost();
		assertEquals(150,cost2);
		int rent = u1.getMortgageValue();
		assertEquals(75,rent);		
	}

	@Test
	public final void testAssignName() {
		Utilities u1 = new Utilities(12);
		Utilities u2 = new Utilities(28);
		String name  = u1.getName();
		assertEquals("Electric Co",name);
		String name2 = u2.getName();
		assertEquals("Water Works",name2);
		int rent = u1.getMortgageValue();
		assertEquals(75,rent);	
	}

	@Test
	public final void testGetName() {
		Utilities u1 = new Utilities(12);
		Utilities u2 = new Utilities(28);
		String name  = u1.getName();
		assertEquals("Electric Co",name);
		String name2 = u2.getName();
		assertEquals("Water Works",name2);
		int rent = u1.getMortgageValue();
		assertEquals(75,rent);
	}

	@Test
	public final void testGetOwner() {
		Utilities u1 = new Utilities(12);
		Player p = new Player("Danny");
		u1.setOwner(p);
		Player temp = u1.getOwner();
		assertEquals(p,temp);
		
	}

	@Test
	public final void testSetOwner() {
		Utilities u1 = new Utilities(12);
		Player p = new Player("Danny");
		u1.setOwner(p);
		Player temp = u1.getOwner();
		assertEquals(p,temp);
		
	}

	@Test
	public final void testGetCost() {
		Utilities u1 = new Utilities(12);
		int cost = u1.getCost();
		assertEquals(150,cost);
	
	}

	@Test
	public final void testGetRentFirstUtility() {
		Utilities u1 = new Utilities(12);
		int rent = u1.getRentFirstUtility();
		assertEquals(20,rent);
	}

	@Test
	public final void testGetRentSecondUtility() {
		Utilities u1 = new Utilities(12);
		int rent = u1.getRentSecondUtility();
		assertEquals(100,rent);
	}

	@Test
	public final void testGetMortgageValue() {
		Utilities u1 = new Utilities(12);
		int rent = u1.getMortgageValue();
		assertEquals(75,rent);
	}
	

}
