package monopoly.cs414;

import static org.junit.Assert.*;

import org.junit.Test;

public class RailRoadTest {

	@Test
	public final void testRailRoad() {
		RailRoad r1 = new RailRoad(15);
		RailRoad r2 = new RailRoad(25);
		int cost = r1.getCost();
		assertEquals(200,cost);
		int cost2 = r2.getCost();
		assertEquals(200,cost2);
	}

	@Test
	public final void testSetRRValue() {
		RailRoad r1 = new RailRoad(15);
		RailRoad r2 = new RailRoad(25);
		int cost = r1.getCost();
		assertEquals(200,cost);
		int cost2 = r2.getCost();
		assertEquals(200,cost2);
		
	}

	@Test
	public final void testAssignName() {
		RailRoad r1 = new RailRoad(15);
		RailRoad r2 = new RailRoad(25);
		r1.assignName(15);
		r2.assignName(25);
		String name = r1.getName();
		assertEquals("Pennsylvania Railroad",name);
		String name2 = r2.getName();
		assertEquals("B O Railroad",name2);		
	}

	@Test
	public final void testGetName() {
		RailRoad r1 = new RailRoad(5);
		RailRoad r2 = new RailRoad(35);
		RailRoad r3 = new RailRoad(15);
		RailRoad r4 = new RailRoad(25);
		String name = r1.getName();
		assertEquals("Reading Railroad",name);
		String name2 = r2.getName();
		assertEquals("Short Line",name2);
		name = r3.getName();
		assertEquals("Pennsylvania Railroad",name);
		name = r4.getName();
		assertEquals("B O Railroad",name);
	}

	@Test
	public final void testGetOwner() {
		RailRoad r1 = new RailRoad(5);
		Player po = new Player();
		r1.setOwner(po);
		Player temp = r1.getOwner();
		assertEquals(po, temp);
	}

	@Test
	public final void testSetOwner() {
		RailRoad r1 = new RailRoad(5);
		Player po = new Player();
		r1.setOwner(po);
		Player temp = r1.getOwner();
		assertEquals(po, temp);
	}

	@Test
	public final void testGetCost() {
		RailRoad r1 = new RailRoad(5);
		int cost = r1.getCost();
		assertEquals(200,cost);
		RailRoad r2 = new RailRoad(15);
		cost = r2.getCost();
		assertEquals(200,cost);
		RailRoad r3 = new RailRoad(25);
		cost = r3.getCost();
		assertEquals(200,cost);
		RailRoad r4 = new RailRoad(35);
		cost = r4.getCost();
		assertEquals(200,cost);
	}

	@Test
	public final void testGetRentInitial() {
		RailRoad r1 = new RailRoad(5);
		int rent = r1.getRentInitial();
		assertEquals(25,rent);
		RailRoad r2 = new RailRoad(15);
		rent = r2.getRentInitial();
		assertEquals(25,rent);
		RailRoad r3 = new RailRoad(25);
		rent = r3.getRentInitial();
		assertEquals(25,rent);
		RailRoad r4 = new RailRoad(35);
		rent = r4.getRentInitial();
		assertEquals(25,rent);
	}

	@Test
	public final void testGetRent2RROwned() {
		RailRoad r1 = new RailRoad(5);
		int rent = r1.getRent2RROwned();
		assertEquals(50,rent);
		RailRoad r2 = new RailRoad(5);
		rent = r2.getRent2RROwned();
		assertEquals(50,rent);
		RailRoad r3 = new RailRoad(5);
		rent = r3.getRent2RROwned();
		assertEquals(50,rent);
		RailRoad r4 = new RailRoad(5);
		rent = r4.getRent2RROwned();
		assertEquals(50,rent);
	}

	@Test
	public final void testGetRent3RROwned() {
		RailRoad r1 = new RailRoad(5);
		int rent = r1.getRent3RROwned();
		assertEquals(100,rent);
		RailRoad r2 = new RailRoad(15);
		rent = r2.getRent3RROwned();
		assertEquals(100,rent);
		RailRoad r3 = new RailRoad(25);
		rent = r3.getRent3RROwned();
		assertEquals(100,rent);
		RailRoad r4 = new RailRoad(35);
		rent = r4.getRent3RROwned();
		assertEquals(100,rent);
	}

	@Test
	public final void testGetRent4RROwned() {
		RailRoad r1 = new RailRoad(5);
		int rent = r1.getRent4RROwned();
		assertEquals(200,rent);
		RailRoad r2 = new RailRoad(15);
		rent = r2.getRent4RROwned();
		assertEquals(200,rent);
		RailRoad r3 = new RailRoad(25);
		rent = r3.getRent4RROwned();
		assertEquals(200,rent);
		RailRoad r4 = new RailRoad(35);
		rent = r4.getRent4RROwned();
		assertEquals(200,rent);
	}

	@Test
	public final void testGetMortgageValue() {
		RailRoad r1 = new RailRoad(5);
		int value = r1.getMortgageValue();
		assertEquals(100,value);
		RailRoad r2 = new RailRoad(15);
		value = r2.getMortgageValue();
		assertEquals(100,value);
		RailRoad r3 = new RailRoad(25);
		value = r3.getMortgageValue();
		assertEquals(100,value);
		RailRoad r4 = new RailRoad(35);
		value = r4.getMortgageValue();
		assertEquals(100,value);
	}
	

}
