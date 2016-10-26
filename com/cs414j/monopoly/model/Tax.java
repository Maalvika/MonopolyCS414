package com.cs414j.monopoly.model;


public class Tax extends Sqaures {
	
	private int incomeTax;
	private int luxuryTax; 
	
	public Tax(int i)
	{
		super(i);
		setTaxValue(i);
	}

	public void setTaxValue(int i)
	{
		if (i==4)
		{
			this.incomeTax = 200;
		}
		else if (i== 38)
		{
			this.luxuryTax = 100;
		}
	}

	public int getIncomeTax() {
		return this.incomeTax;
	}
	
	public int getLuxuryTax(){
		return this.luxuryTax;
	}

	
}
