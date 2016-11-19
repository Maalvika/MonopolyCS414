package com.cs414j.monopoly.client.common.view.SpecialBlocks;

public enum Others {

	ElectricComp(250, 70), WaterWorks(890, 710);

	private int xpoint, ypoint;

	private Others(int x, int y) {
		xpoint = x;
		ypoint = y;
	}

	public int getXpoint() {
		return xpoint;
	}

	public int getYpoint() {
		return ypoint;
	}

}