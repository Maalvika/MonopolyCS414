package com.cs414j.monopoly.model;

import java.util.HashSet;
import java.util.Set;

public class Mortgage {
	
	
	private Set<Properties> mortgageProperties = new HashSet<Properties>();
	private Set<Utilities> mortgageUtilities = new HashSet<Utilities>();
	private Set<RailRoad> mortgageRailRoad = new HashSet<RailRoad>();
	private static Mortgage mortgageInstance;
	
	
	public Mortgage() {
	
	}

	public static Mortgage getInstance() {
		if(mortgageInstance == null) {
			mortgageInstance = new Mortgage();
		}
		return mortgageInstance;
	}
	
	public void mortgageProperty(Properties p)
	{
		// code for mortgaging property
		mortgageProperties.add(p);
		
	}
	
	public void mortgageUtilities(Utilities u)
	{
		// code for mortgaging utilities
		mortgageUtilities.add(u);
		
	}
	
	public void mortgageRailRoad(RailRoad r)
	{
		// code for mortgaging railroad
		mortgageRailRoad.add(r);
		
	}
	

	public void unMortgageProperty(Properties p)
	{
		// code for unmortgaging property
		mortgageProperties.remove(p);
	}
	

	public void unMortgageUtilities(Utilities u)
	{
		// code for unmortgaging utilities
		mortgageUtilities.remove(u);
		
	}
	
	public void unMortgageRailRoad(RailRoad r)
	{
		// code for unmortgaging railroad
		mortgageRailRoad.remove(r);
		
	}
	
	/**
	 * @return the mortgageProperties
	 */
	public Set<Properties> getMortgageProperties() {
		return this.mortgageProperties;
	}

	/**
	 * @return the mortgageUtilities
	 */
	public Set<Utilities> getMortgageUtilities() {
		return this.mortgageUtilities;
	}

	/**
	 * @return the mortgageRailRoad
	 */
	public Set<RailRoad> getMortgageRailRoad() {
		return this.mortgageRailRoad;
	}

	/**
	 * @param mortgageProperties the mortgageProperties to set
	 */
	public void setMortgageProperties(Set<Properties> mortgageProperties) {
		this.mortgageProperties = mortgageProperties;
	}

	/**
	 * @param mortgageUtilities the mortgageUtilities to set
	 */
	public void setMortgageUtilities(Set<Utilities> mortgageUtilities) {
		this.mortgageUtilities = mortgageUtilities;
	}

	/**
	 * @param mortgageRailRoad the mortgageRailRoad to set
	 */
	public void setMortgageRailRoad(Set<RailRoad> mortgageRailRoad) {
		this.mortgageRailRoad = mortgageRailRoad;
	}
	
	

}
