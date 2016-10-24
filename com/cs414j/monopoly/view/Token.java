package com.cs414j.monopoly.view;

public class Token {
	
	private TokenUrls token;
	private int xCoordinate;
	private int yCoordinate;
	// delta value is the difference between different block wrt token
	public Token(TokenUrls token,int xPos, int yPos) {
		this.token = token;
		this.xCoordinate = xPos;
		this.yCoordinate = yPos;
		
	}

	public TokenUrls getToken() {
		return token;
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	
	@Override
	public boolean equals(Object o) {
		Token tempToken = (Token)o;
		if(this.token == tempToken.token)
			return true;
		return false;
		
	}
	
	
	
	

}
