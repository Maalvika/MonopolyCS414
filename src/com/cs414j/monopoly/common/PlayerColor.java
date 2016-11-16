package com.cs414j.monopoly.common;

public enum PlayerColor {
	
	RED("images/red.png"),BLUE("images/blue.jpg"),GREEN("images/green.jpg"),YELLOW("images/yellow.png");

	String url;
	
	private PlayerColor(String s) {
		url = s;
	}

	public String getUrl() {
		return url;
	}
	
	
}
