package com.cs414j.monopoly.view.SpecialBlocks;

import java.util.HashMap;
import java.util.Map;

public enum Corner {
	
	GO(10,870),JUST_VISITING(90,70), FREE_PARKING(890,70),GO_JAIL(890,870);
	
	private int xpoint,ypoint;
	
	private Corner(int x, int y) {
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
