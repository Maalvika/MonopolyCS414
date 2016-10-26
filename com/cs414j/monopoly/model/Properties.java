package com.cs414j.monopoly.model;

public class Properties extends Squares {

	
	String name;
	int cost;
	int rentInitial;
	int rentFirstHouse;
	int rentSecondHouse;
	int rentThirdHouse;
	int rentFourthHouse;
	int rentHotel;
	int mortgageValue;
	int houseCost;
	int hotelCost;
	String color;
	Player owner;
	public Properties(int i) {
		super(i);
		setProperties(i);
		assignColors(i);
		assignName(i);
		
	}
	

	public void setProperties(int i)
	{
		// populating Vertical First Grid
		if ((i==1)|(i==3)|(i==6)|(i==8)|(i==9))
		{
			//int temp = 10-i;
			int value[]={cost, rentInitial, rentFirstHouse, rentSecondHouse, rentThirdHouse, rentFourthHouse,
				     rentHotel, mortgageValue, houseCost,hotelCost};
				for (int k=0; k<10; k++)
					{
						value[k] = verticalFirstGrid[i][k];
					}
				this.cost = value[0];
				this.rentInitial = value[1];
				this.rentFirstHouse = value[2];
				this.rentSecondHouse = value[3];
				this.rentThirdHouse = value[4];
				this.rentFourthHouse= value[5];
				this.rentHotel = value[6];
				this.mortgageValue = value[7];
				this.houseCost = value[8];
				this.hotelCost = value[9];
				//return value;
		}
		
		// populating vertical second grid
		if ((i==21)|(i==23)|(i==24)|(i==26)|(i==27)|(i==29))
		{
			int temp = i-20;
			int value[]={cost, rentInitial, rentFirstHouse, rentSecondHouse, rentThirdHouse, rentFourthHouse,
				     rentHotel, mortgageValue, houseCost,hotelCost};
				for (int k=0; k<10; k++)
					{
						value[k] = verticalSecondGrid[temp][k];
					}
				this.cost = value[0];
				this.rentInitial = value[1];
				this.rentFirstHouse = value[2];
				this.rentSecondHouse = value[3];
				this.rentThirdHouse = value[4];
				this.rentFourthHouse= value[5];
				this.rentHotel = value[6];
				this.mortgageValue = value[7];
				this.houseCost = value[8];
				this.hotelCost = value[9];
				//return value;
		}
		
		// populating horizontal above grid
		if ((i==11)|(i==13)|(i==14)|(i==16)|(i==18)|(i==19))
		{
			int temp = i-10;
			int value[]={cost, rentInitial, rentFirstHouse, rentSecondHouse, rentThirdHouse, rentFourthHouse,
				     rentHotel, mortgageValue, houseCost,hotelCost};
			System.out.println("value size:"+value.length);
			System.out.println("horizontal size:"+horizontalAboveGrid.length);
			System.out.println("temp:"+temp);
				for (int k=0; k<10; k++)
					{
						value[k] = horizontalAboveGrid[temp][k];
					}
				this.cost = value[0];
				this.rentInitial = value[1];
				this.rentFirstHouse = value[2];
				this.rentSecondHouse = value[3];
				this.rentThirdHouse = value[4];
				this.rentFourthHouse= value[5];
				this.rentHotel = value[6];
				this.mortgageValue = value[7];
				this.houseCost = value[8];
				this.hotelCost = value[9];
				//return value;
		}
		
		// populating horizontal below grid
		if ((i==31)|(i==32)|(i==34)|(i==37)|(i==39))
		{
			int temp = i-30;
			int value[]={cost, rentInitial, rentFirstHouse, rentSecondHouse, rentThirdHouse, rentFourthHouse,
				     rentHotel, mortgageValue, houseCost,hotelCost};
				for (int k=0; k<10; k++)
					{
						value[k] = horizontalBelowGrid[temp][k];
					}
				this.cost = value[0];
				this.rentInitial = value[1];
				this.rentFirstHouse = value[2];
				this.rentSecondHouse = value[3];
				this.rentThirdHouse = value[4];
				this.rentFourthHouse= value[5];
				this.rentHotel = value[6];
				this.mortgageValue = value[7];
				this.houseCost = value[8];
				this.hotelCost = value[9];
				//return value;
		}
		
		
	}
	public void assignColors(int i)
	{
		// Assigning Color to property
				if (i==1 | i==3)
				{
					this.color = "Brown";
				}
				
				if (i==6 | i==8 | i==9)
				{
					this.color = "LightBlue";
				}
				
				if (i==11 | i==13 | i==14)
				{
					this.color = "Pink";
				}
				
				if (i==16 | i==18 | i==19)
				{
					this.color = "Orange";
				}
				
				if (i==21 | i==23 | i==24)
				{
					this.color = "Red";
				}
				
				if (i==26 | i==27 | i==29)
				{
					this.color = "Yellow";
				}
				
				if (i==31 | i==32 | i==34)
				{
					this.color = "Green";
				}
				
				if (i==37 | i==39)
				{
					this.color = "Blue";
				}
	}

	public void assignName(int i)
	{
		this.name = namesList[i];
	}
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * @return the owner of properties
	 */
	public Player getOwner() {
		return this.owner;
	}

	/**
	 * @param sets the owner of property
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
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
	 * @return the rentFirstHouse
	 */
	public int getRentFirstHouse() {
		return this.rentFirstHouse;
	}

	/**
	 * @return the rentSecondHouse
	 */
	public int getRentSecondHouse() {
		return this.rentSecondHouse;
	}

	/**
	 * @return the rentThirdHouse
	 */
	public int getRentThirdHouse() {
		return this.rentThirdHouse;
	}

	/**
	 * @return the rentFourthHouse
	 */
	public int getRentFourthHouse() {
		return this.rentFourthHouse;
	}

	/**
	 * @return the rentHotel
	 */
	public int getRentHotel() {
		return this.rentHotel;
	}

	/**
	 * @return the mortgageValue
	 */
	public int getMortgageValue() {
		return this.mortgageValue;
	}

	/**
	 * @return the houseCost
	 */
	public int getHouseCost() {
		return this.houseCost;
	}

	/**
	 * @return the hotelCost
	 */
	public int getHotelCost() {
		return this.hotelCost;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return this.color;
	}
	

}
