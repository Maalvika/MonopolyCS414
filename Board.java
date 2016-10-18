import java.util.Arrays;


public class Board {

	private String[][] board;
	
	public Board(){
		board = new String[11][11];
		initializeBoard();
	}
	
	public void initializeBoard(){
		board[10][0] = "Go";
		board[9][0] = "Mediterranean Avenue";
		board[8][0] = "CC";
		board[7][0] = "Baltic Avenue";
		board[6][0] = "Income Tax";
		board[5][0] = "Reading Railroad";
		board[4][0] = "Oriental Avenue";
		board[3][0] = "Chance";
		board[2][0] = "Vermont Avenue";
		board[1][0] = "Connecticut Avenue";
		board[0][0] = "Visiting Jail";
		board[0][1] = "St.Charles Place";
		board[0][2] = "Electric Co";
		board[0][3] = "State Avenue";
		board[0][4] = "Virginia Avenue";
		board[0][5] = "Pennsylvania Railroad";
		board[0][6] = "St.James Place";
		board[0][7] = "CC";
		board[0][8] = "Tenesse Avenue";
		board[0][9] = "New York Avenue";
		board[0][10] = "Free Parking";
		board[1][10] = "Kentucky Avenue";
		board[2][10] = "Chance";
		board[3][10] = "Indiana Avenue";
		board[4][10] = "Illinois Avenue";
		board[5][10] = "B & O Railroad";
		board[6][10] = "Atlantic Avenue";
		board[7][10] = "Ventnor Avenue";
		board[8][10] = "Water Works";
		board[9][10] = "Marvin Gardens";
		board[10][10] = "Go To Jail";
		board[10][9] = "Pacific Avenue";
		board[10][8] = "North Carolina Avenue";
		board[10][7] = "CC";
		board[10][6] = "Pennsylvania Avenue";
		board[10][5] = "Short Line";
		board[10][4]= "Chance";
		board[10][3] = "Park Place";
		board[10][2] = "Luxury Tax";
		board[10][1] = "Boardwalk";
			
	}
	
	
	public static void main(String[] args) {
		Board game = new Board();
			for (int i = 0; i < game.board.length; i++) {
		    for (int j = 0; j < game.board.length; j++) {
		        System.out.print(game.board[i][j] + " ");
		    }
		    System.out.println();
		}
	

	}

}
