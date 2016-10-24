package com.cs414j.monopoly.view;

import com.cs414j.monopoly.view.SpecialBlocks.Chance;
import com.cs414j.monopoly.view.SpecialBlocks.CommunityChest;
import com.cs414j.monopoly.view.SpecialBlocks.Corner;
import com.cs414j.monopoly.view.SpecialBlocks.Others;
import com.cs414j.monopoly.view.SpecialBlocks.RailRoad;
import com.cs414j.monopoly.view.SpecialBlocks.Tax;

public class ButtonValidate {

	public static void landOnBloack(Token t) {
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
			if (x == c.getXpoint() && y == c.getYpoint()) {
				MonopolyOptions.rollDice.setEnabled(true);
				MonopolyOptions.buy.setEnabled(false);
				MonopolyOptions.conti.setEnabled(true);
				MonopolyOptions.pay.setEnabled(false);
				MonopolyOptions.build.setEnabled(false);
				MonopolyOptions.mortgage.setEnabled(false);
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
				// TODO: check player balance before enabling the button
				MonopolyOptions.mortgage.setEnabled(false);
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
				// TODO: check player balance before enabling the button
				MonopolyOptions.mortgage.setEnabled(false);
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
				// TODO: check player balance before enabling the button
				MonopolyOptions.mortgage.setEnabled(false);
				return 1;

			}

		}
		return 0;

	}

	private static int validateChance(int x, int y) {
		System.out.println("chance1");
		for (Chance c : Chance.values()) {
			if (x == c.getXpoint() && y == c.getYpoint()) {
				MonopolyOptions.rollDice.setEnabled(false);
				MonopolyOptions.buy.setEnabled(false);
				MonopolyOptions.conti.setEnabled(true);
				MonopolyOptions.pay.setEnabled(false);
				MonopolyOptions.build.setEnabled(false);
				MonopolyOptions.tax.setEnabled(false);
				// TODO: check player balance before enabling the button
				MonopolyOptions.mortgage.setEnabled(false);
				return 1;

			}

		}
		return 0;

	}

	private static int validateOthers(int x, int y) {
		for (Others o : Others.values()) {
			if (x == o.getXpoint() && y == o.getYpoint()) {
				MonopolyOptions.rollDice.setEnabled(false);
				MonopolyOptions.buy.setEnabled(false);
				MonopolyOptions.conti.setEnabled(true);
				MonopolyOptions.pay.setEnabled(true);
				MonopolyOptions.build.setEnabled(false);
				MonopolyOptions.tax.setEnabled(true);
				// TODO: check player balance before enabling the button
				MonopolyOptions.mortgage.setEnabled(true);
				return 1;

			}

		}
		return 0;

	}
	
	private static void defaultSettings() {
		MonopolyOptions.rollDice.setEnabled(false);
		MonopolyOptions.buy.setEnabled(true);
		MonopolyOptions.conti.setEnabled(true);
		MonopolyOptions.pay.setEnabled(false);
		MonopolyOptions.build.setEnabled(false);
		MonopolyOptions.tax.setEnabled(false);
		// TODO: check player balance before enabling the button
		MonopolyOptions.mortgage.setEnabled(false);

	}


}
