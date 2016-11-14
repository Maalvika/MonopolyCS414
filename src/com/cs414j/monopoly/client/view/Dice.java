package com.cs414j.monopoly.client.view;

public class Dice {

	public static int rollDice(){
		int die1;  //The number that comes up on the 1st die
	    int die2;  //The number that comes up on the 2nd die
	    int diceValue;  //The sum of numbers that came up on both the dice
		
		die1 = (int)(Math.random()*6) + 1;
		die2 = (int)(Math.random()*6) + 1;
		
		if (die1 == die2){ 
			// doubles are rolled then dice is rolled again
		diceValue = die1 +die2;//The sum of numbers that came up on both the dice
		System.out.println("oops you rolled doubles: " + die1+", "+die2 +" Rolling again");
		return diceValue;
		}
		else{
			// rolling the dice again
			die1 = (int)(Math.random()*6) + 1;
			die2 = (int)(Math.random()*6) + 1;
			diceValue = die1 +die2;//The sum of numbers that came up on both the dice
			return diceValue;
		}
		
	}
	

}
