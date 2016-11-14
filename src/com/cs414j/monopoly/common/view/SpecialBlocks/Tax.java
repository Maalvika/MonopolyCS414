package com.cs414j.monopoly.common.view.SpecialBlocks;

public enum Tax {
	IncomeTax(10, 550), LuxaryTax(250, 870);
	private int xpoint, ypoint;

	private Tax(int x, int y) {
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
