public class Dice {

	public static int rollDice(){
		int die1;  //The number that comes up on the 1st die
	    int die2;  //The number that comes up on the 2nd die
		  //The sum of numbers that came up on both the dice
		
		die1 = (int)(Math.random()*6) + 1;
		die2 = (int)(Math.random()*6) + 1;
		int diceValue = die1 +die2;//The sum of numbers that came up on both the dice
		
		return diceValue;
		
	}
	

}
