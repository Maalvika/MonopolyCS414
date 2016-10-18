
public class RollDice {
	
	public int rollDice(){
		int die1;  //The number that comes up on the 1st die
	    int die2;  //The number that comes up on the 2nd die
		int currentValue;  //The sum of numbers that came up on both the dice
		
		die1 = (int)(Math.random()*6) + 1;
		die2 = (int)(Math.random()*6) + 1;
		currentValue = die1 +die2;
		
		return currentValue;
		
	}
	
	public static void main(String[] args) {
		
		RollDice rd = new RollDice();
		System.out.println("You rolled:"+ rd.rollDice());
		

	}

}
