package com.cs414j.monopoly.view.SpecialBlocks;

public enum CommunityChest {
	CCYNeg(10, 710), CCXPos(650, 70), CCXNeg(650, 870);

	private int xpoint, ypoint;

	private CommunityChest(int x, int y) {
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
