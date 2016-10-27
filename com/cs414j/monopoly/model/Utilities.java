package com.cs414j.monopoly.model;


public class Utilities extends Squares {
	
	private String name;
	private int cost = 150;
	private int rentFirstUtility;
	private int rentSecondUtility;
	private int mortgageValue = 75;
	private Player uOwner;
	
	public Utilities(int i)
	{
		super(i);
		setUtilitiesValues(i);
		assignName(i);
	}
	
	public void setUtilitiesValues(int i)
	{
		if (i==28 | i== 22)
		{
			this.cost =150;
			this.rentFirstUtility = 20;
			this.rentSecondUtility = 100;
			this.mortgageValue = 75;
		}

	}
	
	public void assignName(int i)
	{
		this.name = namesList[i];
	}

	// return the name 
	public String getName()
	{
		return this.name;
	}
	// return the owner
	
	public Player getOwner()
	{
		return this.uOwner;
	}
	
	public void setOwner(Player p)
	{
		this.uOwner = p;
	}
	/**
	 * @return the cost
	 */
	public int getCost() {
		return this.cost;
	}

	/**
	 * @return the rentFirstUtility
	 */
	public int getRentFirstUtility() {
		return this.rentFirstUtility;
	}

	/**
	 * @return the rentSecondUtility
	 */
	public int getRentSecondUtility() {
		return this.rentSecondUtility;
	}

	/**
	 * @return the mortgageValue
	 */
	public int getMortgageValue() {
		return this.mortgageValue;
	}
}
