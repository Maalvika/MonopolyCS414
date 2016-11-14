package com.cs414j.monopoly.view;

public class PropertyUI {
	
	int xPoint;
	int yPoint;
	String player;
	
	public PropertyUI(int x, int y, String pName) {
		this.xPoint = x;
		this.yPoint = y;
		this.player = pName;
	}
	
	public int getxPoint() {
		return xPoint;
	}
	public void setxPoint(int xPoint) {
		this.xPoint = xPoint;
	}
	public int getyPoint() {
		return yPoint;
	}
	public void setyPoint(int yPoint) {
		this.yPoint = yPoint;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	
	

}
