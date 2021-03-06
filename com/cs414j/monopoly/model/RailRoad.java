package com.cs414j.monopoly.model;

public class RailRoad extends Squares {

	private String name;
	private int cost;
	private int rentInitial;
	private int rent2RROwned;
	private int rent3RROwned;
	private int rent4RROwned;
	private int mortgageValue;
	private Player rOwner;
	
	public RailRoad(int i)
	{
		super(i);
		setRRValue(i);
		assignName(i);
	}
	
	public void setRRValue(int i)
	{
		if(i==5 | i==15 | i ==25 | i==35)
		{
			//int temp = 10-1;
			int value[] = {cost,rentInitial,0,rent2RROwned,rent3RROwned, rent4RROwned, mortgageValue};
			for (int k =0 ;k<7;k++)
			{
				value[k]= verticalFirstGrid[5][k];	
			}
			this.cost = value[0];
			this.rentInitial = value[1];
			this.rent2RROwned = value[3];
			this.rent3RROwned = value[4];
			this.rent4RROwned = value[5];
			this.mortgageValue = value[6];	
			//return value;
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
		return this.rOwner;
	}
	
	public void setOwner(Player p)
	{
		this.rOwner = p;
	}
	/**
	 * @return the cost
	 */
	public int getCost() {
		return this.cost;
	}

	/**
	 * @return the rentInitial
	 */
	public int getRentInitial() {
		return this.rentInitial;
	}

	/**
	 * @return the rent2RROwned
	 */
	public int getRent2RROwned() {
		return this.rent2RROwned;
	}

	/**
	 * @return the rent3RROwned
	 */
	public int getRent3RROwned() {
		return this.rent3RROwned;
	}

	/**
	 * @return the rent4RROwned
	 */
	public int getRent4RROwned() {
		return this.rent4RROwned;
	}

	/**
	 * @return the mortgageValue
	 */
	public int getMortgageValue() {
		return this.mortgageValue;
	}
}
