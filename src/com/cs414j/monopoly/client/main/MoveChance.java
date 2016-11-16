package com.cs414j.monopoly.client.main;

public enum MoveChance {
	
	St_Charles_Place(170,70),Illinois_Avenue(890,390),JUST_VISITING(90,70),Water_Works(890,710),
	Electric_Co(250,70), Reading_Railroad(10,470), Pennsylvania_Railroad(490,70),B_O_Railroad(890,470),
	Short_Line(490,870),GO(10,870),Boardwalk(170,870);
	
	private int xpoint, ypoint;

	private MoveChance(int x, int y) {
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
