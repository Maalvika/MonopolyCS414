package com.cs414j.monopoly.view;

import java.awt.Color;

import com.cs414j.monopoly.common.PlayerColor;

public class PropertyUI {
	
	int xPoint;
	int yPoint;
	PlayerColor c;
	String player;
	
	public PropertyUI(int x, int y, String pName,PlayerColor c) {
		this.xPoint = x;
		this.yPoint = y;
		this.player = pName;
		this.c = c;
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

	public PlayerColor getColor() {
		return c;
	}

	public void setColor(PlayerColor c) {
		this.c = c;
	}
	
	

}
