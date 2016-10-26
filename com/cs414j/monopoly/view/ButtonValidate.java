package com.cs414j.monopoly.view;

import com.cs414j.monopoly.model.Player;
import com.cs414j.monopoly.model.Properties;
import com.cs414j.monopoly.model.Squares;
import com.cs414j.monopoly.view.SpecialBlocks.Chance;
import com.cs414j.monopoly.view.SpecialBlocks.CommunityChest;
import com.cs414j.monopoly.view.SpecialBlocks.Corner;
import com.cs414j.monopoly.view.SpecialBlocks.Others;
import com.cs414j.monopoly.view.SpecialBlocks.RailRoad;
import com.cs414j.monopoly.view.SpecialBlocks.Tax;

public class ButtonValidate {

	public static void landOnBlock(Token t) {
		int xPoint = t.getxCoordinate();
		int yPoint = t.getyCoordinate();
		// check for corner
		if (validateCorners(xPoint, yPoint) == 1) {
			return;
		} else if (validateTax(xPoint, yPoint) == 1) {
			return;
		} else if (validateCommunityChest(xPoint, yPoint) == 1) {
			return;
		} else if (validateChance(xPoint, yPoint) == 1) {
			return;
		} else if (validateRailRoad(xPoint, yPoint) == 1) {
			return;
		} else if (validateOthers(xPoint, yPoint) == 1) {
			return;
		}  else {
			defaultSettings();
		}

	}

	private static int validateCorners(int x, int y) {
		for (Corner c : Corner.values()) {
			System.out.println("a1");
			if (x == c.getXpoint() && y == c.getYpoint()) {
				MonopolyOptions.rollDice.setEnabled(true);
				MonopolyOptions.buy.setEnabled(false);
				MonopolyOptions.conti.setEnabled(true);
				MonopolyOptions.pay.setEnabled(false);
				MonopolyOptions.build.setEnabled(false);
				enableMortgageButton();
				MonopolyOptions.tax.setEnabled(false);
				return 1;
			}

		}
		return 0;

	}

	private static int validateTax(int x, int y) {
		for (Tax t : Tax.values()) {
			if (x == t.getXpoint() && y == t.getYpoint()) {
				MonopolyOptions.rollDice.setEnabled(false);
				MonopolyOptions.buy.setEnabled(false);
				MonopolyOptions.conti.setEnabled(true);
				MonopolyOptions.pay.setEnabled(false);
				MonopolyOptions.build.setEnabled(false);
				MonopolyOptions.tax.setEnabled(true);
				enableMortgageButton();
				return 1;
			}

		}
		return 0;
	}

	private static int validateCommunityChest(int x, int y) {
		for (CommunityChest c : CommunityChest.values()) {
			if (x == c.getXpoint() && y == c.getYpoint()) {
				MonopolyOptions.rollDice.setEnabled(false);
				MonopolyOptions.buy.setEnabled(false);
				MonopolyOptions.conti.setEnabled(true);
				MonopolyOptions.pay.setEnabled(false);
				MonopolyOptions.build.setEnabled(false);
				MonopolyOptions.tax.setEnabled(false);
				enableMortgageButton();
				return 1;

			}

		}
		return 0;

	}

	private static int validateRailRoad(int x, int y) {
		for (RailRoad t : RailRoad.values()) {
			if (x == t.getXpoint() && y == t.getYpoint()) {
				MonopolyOptions.rollDice.setEnabled(false);
				MonopolyOptions.buy.setEnabled(true);
				MonopolyOptions.conti.setEnabled(true);
				MonopolyOptions.pay.setEnabled(false);
				MonopolyOptions.build.setEnabled(false);
				MonopolyOptions.tax.setEnabled(false);
				enableMortgageButton();
				return 1;

			}

		}
		return 0;

	}

	private static int validateChance(int x, int y) {
		for (Chance c : Chance.values()) {
			if (x == c.getXpoint() && y == c.getYpoint()) {
				System.out.println("a2");
				MonopolyOptions.rollDice.setEnabled(false);
				MonopolyOptions.buy.setEnabled(false);
				MonopolyOptions.conti.setEnabled(true);
				MonopolyOptions.pay.setEnabled(false);
				MonopolyOptions.build.setEnabled(false);
				MonopolyOptions.tax.setEnabled(false);
				enableMortgageButton();
				return 1;

			}

		}
		return 0;

	}

	private static int validateOthers(int x, int y) {
		for (Others o : Others.values()) {
			if (x == o.getXpoint() && y == o.getYpoint()) {
				MonopolyOptions.rollDice.setEnabled(false);
				MonopolyOptions.buy.setEnabled(true);
				MonopolyOptions.conti.setEnabled(true);
				String currentProperty = MonopolyOptions.getPropertyName(MonopolyMain.currentPlayer.getToken());
				if(!MonopolyMain.bank.isOwned(currentProperty) && 
						!MonopolyMain.currentPlayer.OwnedSquareName().contains(currentProperty)) {
				
					MonopolyOptions.pay.setEnabled(true);
				} else {
					MonopolyOptions.pay.setEnabled(false);
				}
				MonopolyOptions.build.setEnabled(false);
				MonopolyOptions.tax.setEnabled(true);
				enableMortgageButton();
				return 1;

			}

		}
		return 0;

	}
	
	private static void defaultSettings() {
		MonopolyOptions.rollDice.setEnabled(false);
		MonopolyOptions.conti.setEnabled(true);

		if(MonopolyOptions.properties.containsKey(getCurrentPropertyUI())) {
			MonopolyOptions.build.setEnabled(true);
			MonopolyOptions.buy.setEnabled(false);
			MonopolyOptions.pay.setEnabled(false);
		}else {
		
			String currentProperty = MonopolyOptions.getPropertyName(MonopolyMain.currentPlayer.getToken());
			System.out.println("current Property "+currentProperty);
			if(!MonopolyMain.bank.isOwned(currentProperty) && 
					!MonopolyMain.currentPlayer.OwnedSquareName().contains(currentProperty)) {
				System.out.println("is owned:"+MonopolyMain.bank.isOwned(currentProperty));
				MonopolyOptions.build.setEnabled(false);
				MonopolyOptions.buy.setEnabled(false);
				MonopolyOptions.pay.setEnabled(true);
			} else {
				System.out.println("b");
				MonopolyOptions.build.setEnabled(false);
				MonopolyOptions.buy.setEnabled(true);
				MonopolyOptions.pay.setEnabled(false);
			}
		}
		MonopolyOptions.tax.setEnabled(false);
		enableMortgageButton();

	}
	
	
	private static PropertyUI getCurrentPropertyUI() {
		Player player = MonopolyMain.currentPlayer;
		Token currentToken = player.getToken();
		PropertyUI p = new PropertyUI(currentToken.getxCoordinate(), currentToken.getyCoordinate(), player.getName());
		return p;
	}
	
	private static void enableMortgageButton() {
		if(MonopolyMain.currentPlayer.OwnedSquareName().isEmpty()) {
			MonopolyOptions.mortgage.setEnabled(false);
		}  else {
			MonopolyOptions.mortgage.setEnabled(true);
		}
		
	}


}
