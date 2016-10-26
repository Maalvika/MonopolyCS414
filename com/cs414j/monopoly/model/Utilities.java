package com.cs414j.monopoly.model;


public class Utilities extends Squares {
	
	private int cost;
	private int rentFirstUtility;
	private int rentSecondUtility;
	private int mortgageValue;
	private Player uOwner;
	
	public Utilities(int i)
	{
		super(i);
		setUtilitiesValues(i);
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
