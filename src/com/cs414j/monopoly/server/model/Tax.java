package com.cs414j.monopoly.server.model;

import java.io.Serializable;

public class Tax extends Squares implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int incomeTax;
	private int luxuryTax; 
	
	public Tax()
	{
		super(0);
		setTaxValue(0);
	}
	
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
