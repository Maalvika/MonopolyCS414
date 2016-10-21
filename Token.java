
public class Token {
	int position =0;
	String name;// newly added
	
	public String landedOn(int position, int diceValue){
		int newposition = (position + diceValue) % 40;
		
		String s = String.format("you landed on:"+ Board.board[newposition].toString());
		return s;
	}
	
	public int getLocation(int position, int diceValue){
	     for (int i=0; i<40; i++)
	     {
	    	 if (Board.board[i].toString().equals(landedOn(position, diceValue))) {
	    		 return i;
	    	 }
	     }
	     
	     return 0;
	
		
	}
	
	
	
	

	
	
	
	

	

	
}
