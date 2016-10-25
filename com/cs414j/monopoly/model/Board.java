package com.cs414j.monopoly.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {

	public static String[] board;

	HashMap<Squares, Player> map = new HashMap<Squares, Player>();

	public Board() {
		board = new String[40];
		initializeBoard();
	}

	public void initializeBoard() {
		board[0] = "Go";
		board[1] = "Mediterranean Avenue";
		board[2] = "CC";
		board[3] = "Baltic Avenue";
		board[4] = "Income Tax";
		board[5] = "Reading Railroad";
		board[6] = "Oriental Avenue";
		board[7] = "Chance";
		board[8] = "Vermont Avenue";
		board[9] = "Connecticut Avenue";
		board[10] = "Visiting Jail";
		board[11] = "St.Charles Place";
		board[12] = "Electric Co";
		board[13] = "State Avenue";
		board[14] = "Virginia Avenue";
		board[15] = "Pennsylvania Railroad";
		board[16] = "St.James Place";
		board[17] = "CC";
		board[18] = "Tenesse Avenue";
		board[19] = "New York Avenue";
		board[20] = "Free Parking";
		board[21] = "Kentucky Avenue";
		board[22] = "Chance";
		board[23] = "Indiana Avenue";
		board[24] = "Illinois Avenue";
		board[25] = "B & O Railroad";
		board[26] = "Atlantic Avenue";
		board[27] = "Ventnor Avenue";
		board[28] = "Water Works";
		board[29] = "Marvin Gardens";
		board[30] = "Go To Jail";
		board[31] = "Pacific Avenue";
		board[32] = "North Carolina Avenue";
		board[33] = "CC";
		board[34] = "Pennsylvania Avenue";
		board[35] = "Short Line";
		board[36] = "Chance";
		board[37] = "Park Place";
		board[38] = "Luxury Tax";
		board[39] = "Boardwalk";

	}

	public boolean landedOnGoToJail(Player p) {
		if (p.getLocation() == 30) {
			return true;
		}
		return false;
	}
}