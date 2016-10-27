package com.cs414j.monopoly.view;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class MortgageTest {

	@Test
	public final void testMortgage() {
		Mortgage m = new Mortgage();
		
	}

	@Test
	public final void testGetInstance() {
		Mortgage m;
		m = Mortgage.getInstance();
		assertEquals(true,m.getMortgageProperties().isEmpty());
	}

	@Test
	public final void testMortgageProperty() {
		Properties p = new Properties(1);
		Mortgage m = new Mortgage();
		assertEquals(true,m.getMortgageProperties().isEmpty());
		m.mortgageProperty(p);
		assertEquals(1,m.getMortgageProperties().size());
		
		
	}

	@Test
	public final void testMortgageUtilities() {
		Utilities u = new Utilities(12);
		Mortgage m = new Mortgage();
		assertEquals(true,m.getMortgageUtilities().isEmpty());
		m.mortgageUtilities(u);
		assertEquals(1,m.getMortgageUtilities().size());
	}

	@Test
	public final void testMortgageRailRoad() {
		
		RailRoad r = new RailRoad(5);
		Mortgage m = new Mortgage();
		assertEquals(true,m.getMortgageRailRoad().isEmpty());
		m.mortgageRailRoad(r);
		assertEquals(1,m.getMortgageRailRoad().size());
	}

	@Test
	public final void testUnMortgageProperty() {
		Properties p = new Properties(1);
		Mortgage m = new Mortgage();
		assertEquals(true,m.getMortgageProperties().isEmpty());
		m.mortgageProperty(p);
		assertEquals(1,m.getMortgageProperties().size());
		m.unMortgageProperty(p);
		assertEquals(true,m.getMortgageProperties().isEmpty());
		
	}

	@Test
	public final void testUnMortgageUtilities() {
		Utilities u = new Utilities(12);
		Mortgage m = new Mortgage();
		assertEquals(true,m.getMortgageUtilities().isEmpty());
		m.mortgageUtilities(u);
		assertEquals(1,m.getMortgageUtilities().size());
		m.unMortgageUtilities(u);
		assertEquals(true,m.getMortgageUtilities().isEmpty());
	}

	@Test
	public final void testUnMortgageRailRoad() {
		RailRoad r = new RailRoad(5);
		Mortgage m = new Mortgage();
		assertEquals(true,m.getMortgageRailRoad().isEmpty());
		m.mortgageRailRoad(r);
		assertEquals(1,m.getMortgageRailRoad().size());
		m.unMortgageRailRoad(r);
		assertEquals(true,m.getMortgageUtilities().isEmpty());
	}

	@Test
	public final void testGetMortgageProperties() {
		Properties p = new Properties(1);
		Set<Properties> temp = new HashSet<Properties>();
		temp.add(p);
		Mortgage m = new Mortgage();
		assertEquals(true,m.getMortgageProperties().isEmpty());
		m.setMortgageProperties(temp);
		assertEquals(false,m.getMortgageProperties().isEmpty());
	}

	@Test
	public final void testGetMortgageUtilities() {
		Utilities u = new Utilities(12);
		Set<Utilities> temp = new HashSet<Utilities>();
		temp.add(u);
		Mortgage m = new Mortgage();
		assertEquals(true,m.getMortgageUtilities().isEmpty());
		m.setMortgageUtilities(temp);
		assertEquals(false,m.getMortgageUtilities().isEmpty());
	}

	@Test
	public final void testGetMortgageRailRoad() {
		RailRoad r = new RailRoad(5);
		Set<RailRoad> temp = new HashSet<RailRoad>();
		temp.add(r);
		Mortgage m = new Mortgage();
		assertEquals(true,m.getMortgageRailRoad().isEmpty());
		m.setMortgageRailRoad(temp);
		assertEquals(false,m.getMortgageRailRoad().isEmpty());
	}

	@Test
	public final void testSetMortgageProperties() {
		Properties p = new Properties(1);
		Set<Properties> temp = new HashSet<Properties>();
		temp.add(p);
		Mortgage m = new Mortgage();
		assertEquals(true,m.getMortgageProperties().isEmpty());
		m.setMortgageProperties(temp);
		assertEquals(false,m.getMortgageProperties().isEmpty());
	}

	@Test
	public final void testSetMortgageUtilities() {
		Utilities u = new Utilities(12);
		Set<Utilities> temp = new HashSet<Utilities>();
		temp.add(u);
		Mortgage m = new Mortgage();
		assertEquals(true,m.getMortgageUtilities().isEmpty());
		m.setMortgageUtilities(temp);
		assertEquals(false,m.getMortgageUtilities().isEmpty());
	}

	@Test
	public final void testSetMortgageRailRoad() {
		RailRoad r = new RailRoad(5);
		Set<RailRoad> temp = new HashSet<RailRoad>();
		temp.add(r);
		Mortgage m = new Mortgage();
		assertEquals(true,m.getMortgageRailRoad().isEmpty());
		m.setMortgageRailRoad(temp);
		assertEquals(false,m.getMortgageRailRoad().isEmpty());
	}

}
