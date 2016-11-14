package com.cs414j.monopoly.common;

import java.io.Serializable;

import com.cs414j.monopoly.view.TokenUrls;

public class Token implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TokenUrls tokenUrl;
	private int xCoordinate;
	private int yCoordinate;
	// delta value is the difference between different block wrt token
	public Token(TokenUrls token,int xPos, int yPos) {
		this.tokenUrl = token;
		this.xCoordinate = xPos;
		this.yCoordinate = yPos;
		
	}

	public TokenUrls getTokenURL() {
		return tokenUrl;
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
		if(this.tokenUrl == tempToken.tokenUrl)
			return true;
		return false;
		
	}
	
	
	
	

}
