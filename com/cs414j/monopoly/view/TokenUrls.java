package com.cs414j.monopoly.view;

public enum TokenUrls {

	CAR("images/car.jpg"), SHIP("images/ship.jpg"),HORSE("images/horse.jpg"),SHOE("images/shoe.jpg");

	private final String name;

	TokenUrls(String imgURL) {
		name = imgURL;
	}

	public String getURLValue() {
		return name;
	}
}
