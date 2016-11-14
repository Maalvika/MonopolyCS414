package com.cs414j.monopoly.common.view.SpecialBlocks;

public enum Chance {

	ChanceYNeg(10, 310), ChanceYPos(890, 230), ChanceXNeg(410, 870);
	
	private int xpoint, ypoint;

	private Chance(int x, int y) {
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
