package com.cs414j.monopoly.model.junitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cs414j.monopoly.model.Player;
import com.cs414j.monopoly.model.Properties;

public class PropertiesTest {

	@Test
	public final void testProperties() {
		Properties p1 = new Properties(1);
		Properties p2 = new Properties(21);
		Properties p3 = new Properties(32);
		String name = p1.getName();
		assertEquals("Mediterranean Avenue",name);
		name = p2.getName();
		assertEquals("Kentucky Avenue",name);
		name = p3.getName();
		assertEquals("North Carolina Avenue",name);
		
	}

	@Test
	public final void testSetProperties() {
		Properties p1 = new Properties(1);
		p1.setProperties(1);
		String name = p1.getName();
		assertEquals("Mediterranean Avenue",name);
		int cost = p1.getCost();
		assertEquals(60,cost);		
	}

	@Test
	public final void testAssignColors() {
		Properties p1 = new Properties(1);
		Properties p2 = new Properties(21);
		Properties p3 = new Properties(32);
		p1.assignColors(1);
		p2.assignColors(21);
		p3.assignColors(32);
		String color = p1.getColor();
		assertEquals("Brown",color);
		color = p2.getColor();
		assertEquals("Red",color);
		color = p3.getColor();
		assertEquals("Green",color);
	}

	@Test
	public final void testAssignName() {
		Properties p1 = new Properties(18);
		Properties p2 = new Properties(23);
		Properties p3 = new Properties(26);
		p1.assignName(18);
		p2.assignName(23);
		p3.assignName(26);
		String name = p1.getName();
		assertEquals("Tenesse Avenue",name);
		name = p2.getName();
		assertEquals("Indiana Avenue",name);
		name = p3.getName();
		assertEquals("Atlantic Avenue",name);
		
		
	}

	@Test
	public final void testGetName() {
		Properties p1 = new Properties(18);
		Properties p2 = new Properties(23);
		Properties p3 = new Properties(26);
		p1.assignName(18);
		p2.assignName(23);
		p3.assignName(26);
		String name = p1.getName();
		assertEquals("Tenesse Avenue",name);
		name = p2.getName();
		assertEquals("Indiana Avenue",name);
		name = p3.getName();
		assertEquals("Atlantic Avenue",name);
	}

	@Test
	public final void testGetOwner() {
		Properties p1 = new Properties(18);
		Properties p2 = new Properties(23);
		Player pop1 = new Player("Joe");
		Player pop2 = new Player("Mark");
		p1.setOwner(pop1);
		p2.setOwner(pop2);
		assertEquals(pop1, p1.getOwner());
		assertEquals(pop2, p2.getOwner());
	}

	@Test
	public final void testSetOwner() {
		Properties p1 = new Properties(18);
		Properties p2 = new Properties(23);
		Player pop1 = new Player("Ali");
		Player pop2 = new Player("Jim");
		p1.setOwner(pop1);
		p2.setOwner(pop2);
		assertEquals(pop1, p1.getOwner());
		assertEquals(pop2, p2.getOwner());
	}

	@Test
	public final void testGetCost() {
		Properties p1 = new Properties(19);
		Properties p2 = new Properties(39);
		int cost = p1.getCost();
		assertEquals(200,cost);
		assertEquals(400,p2.getCost());
	}

	@Test
	public final void testGetRentInitial() {
		Properties p1 = new Properties(19);
		Properties p2 = new Properties(39);
		assertEquals(16,p1.getRentInitial());
		assertEquals(50,p2.getRentInitial());
		
	}

	@Test
	public final void testGetRentFirstHouse() {
		Properties p1 = new Properties(9);
		Properties p2 = new Properties(11);
		assertEquals(40,p1.getRentFirstHouse());
		assertEquals(50,p2.getRentFirstHouse());
	}

	@Test
	public final void testGetRentSecondHouse() {
		Properties p1 = new Properties(3);
		Properties p2 = new Properties(19);
		assertEquals(60,p1.getRentSecondHouse());
		assertEquals(220,p2.getRentSecondHouse());
	}

	@Test
	public final void testGetRentThirdHouse() {
		Properties p1 = new Properties(3);
		Properties p2 = new Properties(19);
		assertEquals(180,p1.getRentThirdHouse());
		assertEquals(600,p2.getRentThirdHouse());
	}

	@Test
	public final void testGetRentFourthHouse() {
		Properties p1 = new Properties(3);
		Properties p2 = new Properties(19);
		assertEquals(320,p1.getRentFourthHouse());
		assertEquals(800,p2.getRentFourthHouse());
	}

	@Test
	public final void testGetRentHotel() {
		Properties p1 = new Properties(19);
		Properties p2 = new Properties(23);
		assertEquals(1000,p1.getRentHotel());
		assertEquals(1050,p2.getRentHotel());
	}

	@Test
	public final void testGetMortgageValue() {
		Properties p1 = new Properties(19);
		Properties p2 = new Properties(23);
		assertEquals(100,p1.getMortgageValue());
		assertEquals(110,p2.getMortgageValue());
	}

	@Test
	public final void testGetHouseCost() {
		Properties p1 = new Properties(14);
		Properties p2 = new Properties(16);
		assertEquals(100,p1.getHouseCost());
		assertEquals(100,p2.getHouseCost());
	}

	@Test
	public final void testGetHotelCost() {
		Properties p1 = new Properties(37);
		Properties p2 = new Properties(34);
		assertEquals(200,p1.getHotelCost());
		assertEquals(200,p2.getHotelCost());
	}

	@Test
	public final void testGetColor() {
		Properties p1 = new Properties(19);
		Properties p2 = new Properties(23);
		p1.assignColors(19);
		p2.assignColors(23);
		assertEquals("Orange",p1.getColor());
		assertEquals("Red",p2.getColor());
	}

	
}
