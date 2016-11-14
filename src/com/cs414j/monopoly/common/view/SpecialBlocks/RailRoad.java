package com.cs414j.monopoly.common.view.SpecialBlocks;

public enum RailRoad {
	RRailroad(10, 470), PRailroad(490, 70), SRailroad(490, 870), BRailRoad(890, 470);

	private int xpoint, ypoint;

	private RailRoad(int x, int y) {
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
