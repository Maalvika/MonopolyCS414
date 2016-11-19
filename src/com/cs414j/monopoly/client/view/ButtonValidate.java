package com.cs414j.monopoly.client.view;

import java.rmi.RemoteException;

import com.cs414j.monopoly.client.common.view.SpecialBlocks.Chance;
import com.cs414j.monopoly.client.common.view.SpecialBlocks.CommunityChest;
import com.cs414j.monopoly.client.common.view.SpecialBlocks.Corner;
import com.cs414j.monopoly.client.common.view.SpecialBlocks.Others;
import com.cs414j.monopoly.client.common.view.SpecialBlocks.RailRoad;
import com.cs414j.monopoly.client.common.view.SpecialBlocks.Tax;
import com.cs414j.monopoly.client.controller.MonopolyMain;
import com.cs414j.monopoly.client.controller.MonopolyOptions;
import com.cs414j.monopoly.client.main.ClientMain;
import com.cs414j.monopoly.common.Player;
import com.cs414j.monopoly.common.PlayerColor;
import com.cs414j.monopoly.common.Token;
import com.cs414j.monopoly.server.model.Properties;
import com.cs414j.monopoly.server.model.Squares;

public class ButtonValidate {

	public static boolean buyPropertyEnabled = false;
	public static boolean ischance = false;

	public static void landOnBlock(Token t) throws RemoteException {
		int xPoint = t.getxCoordinate();
		int yPoint = t.getyCoordinate();
		// check for corner
		System.out.println("x:" + xPoint + " y:" + yPoint);
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
		} else {
			defaultSettings();
		}

	}

	private static int validateCorners(int x, int y) throws RemoteException {
		for (Corner c : Corner.values()) {
			if (x == c.getXpoint() && y == c.getYpoint()) {
				MonopolyOptions.rollDice.setEnabled(false);
				MonopolyOptions.rollFixedNum.setEnabled(false);
				MonopolyOptions.buy.setEnabled(false);
				MonopolyOptions.conti.setEnabled(true);
				MonopolyOptions.pay.setEnabled(false);
				MonopolyOptions.build.setEnabled(false);
				enableMortgageButton();
				enableUnMortgageButton();
				MonopolyOptions.tax.setEnabled(false);
				return 1;
			}

		}
		return 0;

	}

	private static int validateTax(int x, int y) throws RemoteException {
		for (Tax t : Tax.values()) {
			if (x == t.getXpoint() && y == t.getYpoint()) {
				MonopolyOptions.rollDice.setEnabled(false);
				MonopolyOptions.rollFixedNum.setEnabled(false);
				MonopolyOptions.buy.setEnabled(false);
				MonopolyOptions.conti.setEnabled(false);
				MonopolyOptions.pay.setEnabled(false);
				MonopolyOptions.build.setEnabled(false);
				MonopolyOptions.tax.setEnabled(true);
				enableMortgageButton();
				enableUnMortgageButton();
				return 1;
			}

		}
		return 0;
	}

	private static int validateCommunityChest(int x, int y) throws RemoteException {
		for (CommunityChest c : CommunityChest.values()) {
			if (x == c.getXpoint() && y == c.getYpoint()) {
				MonopolyOptions.rollDice.setEnabled(false);
				MonopolyOptions.rollFixedNum.setEnabled(false);
				MonopolyOptions.buy.setEnabled(false);
				MonopolyOptions.conti.setEnabled(true);
				MonopolyOptions.pay.setEnabled(false);
				MonopolyOptions.build.setEnabled(false);
				MonopolyOptions.tax.setEnabled(false);
				enableMortgageButton();
				enableUnMortgageButton();
				// add server community chest code over here
				int index = MonopolyMain.cards.generateRandomChest();
				ClientMain.store.callChestCards(index);
				MonopolyOptions.mortgage.setEnabled(false);
				return 1;

			}

		}
		return 0;

	}

	private static int validateRailRoad(int x, int y) throws RemoteException {
		for (RailRoad t : RailRoad.values()) {
			if (x == t.getXpoint() && y == t.getYpoint()) {
				MonopolyOptions.rollDice.setEnabled(false);
				MonopolyOptions.rollFixedNum.setEnabled(false);
				MonopolyOptions.conti.setEnabled(true);
				String currentProperty = MonopolyOptions
						.getPropertyName(ClientMain.store.getCurrentPlayer().getToken());
				if (isOtherOwns(currentProperty) == true) {
					MonopolyOptions.buy.setEnabled(false);
					MonopolyOptions.pay.setEnabled(true);
				} else {
					if (!ClientMain.store.getCurrentPlayer().OwnedSquareName().contains(currentProperty)) {
						MonopolyOptions.pay.setEnabled(false);
						MonopolyOptions.buy.setEnabled(true);
						buyPropertyEnabled = true;
					} else {
						MonopolyOptions.pay.setEnabled(false);
						MonopolyOptions.buy.setEnabled(false);
					}
				}
				MonopolyOptions.build.setEnabled(false);
				MonopolyOptions.tax.setEnabled(false);
				enableMortgageButton();
				enableUnMortgageButton();
				return 1;

			}

		}
		return 0;

	}

	private static int validateChance(int x, int y) throws RemoteException {
		for (Chance c : Chance.values()) {
			if (x == c.getXpoint() && y == c.getYpoint()) {
				MonopolyOptions.rollDice.setEnabled(false);
				MonopolyOptions.rollFixedNum.setEnabled(false);
				MonopolyOptions.buy.setEnabled(false);
				MonopolyOptions.conti.setEnabled(true);
				MonopolyOptions.pay.setEnabled(false);
				MonopolyOptions.build.setEnabled(false);
				MonopolyOptions.tax.setEnabled(false);
				enableMortgageButton();
				enableUnMortgageButton();
				// add server chance code over here
				int index = MonopolyMain.cards.generateRandomChance();
				ClientMain.store.callChanceCards(index);
				MonopolyOptions.mortgage.setEnabled(false);
				return 1;

			}

		}
		return 0;

	}

	private static int validateOthers(int x, int y) throws RemoteException {
		for (Others o : Others.values()) {
			if (x == o.getXpoint() && y == o.getYpoint()) {
				MonopolyOptions.rollDice.setEnabled(false);
				MonopolyOptions.rollFixedNum.setEnabled(false);
				MonopolyOptions.conti.setEnabled(true);
				String currentProperty = MonopolyOptions
						.getPropertyName(ClientMain.store.getCurrentPlayer().getToken());
				if (isOtherOwns(currentProperty) == true) {
					MonopolyOptions.buy.setEnabled(false);
					MonopolyOptions.pay.setEnabled(true);
					MonopolyOptions.conti.setEnabled(false);
				} else {
					if (!ClientMain.store.getCurrentPlayer().OwnedSquareName().contains(currentProperty)) {
						MonopolyOptions.pay.setEnabled(false);
						MonopolyOptions.buy.setEnabled(true);
						buyPropertyEnabled = true;
					} else {
						MonopolyOptions.pay.setEnabled(false);
						MonopolyOptions.buy.setEnabled(false);
					}
				}
				MonopolyOptions.build.setEnabled(false);
				MonopolyOptions.tax.setEnabled(false);
				enableMortgageButton();
				enableUnMortgageButton();
				return 1;

			}

		}
		return 0;

	}

	private static void defaultSettings() throws RemoteException {
		MonopolyOptions.rollDice.setEnabled(false);
		MonopolyOptions.rollFixedNum.setEnabled(false);
		MonopolyOptions.conti.setEnabled(true);

		if (MonopolyOptions.properties.containsKey(getCurrentPropertyUI())) {
			MonopolyOptions.build.setEnabled(true);
			MonopolyOptions.buy.setEnabled(false);
			MonopolyOptions.pay.setEnabled(false);
		} else {

			String currentProperty = MonopolyOptions.getPropertyName(ClientMain.store.getCurrentPlayer().getToken());
			System.out.println("currentProperty");
			if (!ClientMain.store.isOwnedByBank(currentProperty)
					&& !ClientMain.store.getCurrentPlayer().OwnedSquareName().contains(currentProperty)) {
				MonopolyOptions.build.setEnabled(false);
				MonopolyOptions.buy.setEnabled(false);
				MonopolyOptions.pay.setEnabled(true);
			} else {
				if (ClientMain.store.getCurrentPlayer().OwnedSquareName().contains(currentProperty)) {
					MonopolyOptions.build.setEnabled(true);
					MonopolyOptions.buy.setEnabled(false);
				} else {
					MonopolyOptions.build.setEnabled(false);
					MonopolyOptions.buy.setEnabled(true);
					buyPropertyEnabled = true;
				}
				MonopolyOptions.pay.setEnabled(false);
			}
		}
		MonopolyOptions.tax.setEnabled(false);
		enableMortgageButton();
		enableUnMortgageButton();

	}

	private static PropertyUI getCurrentPropertyUI() throws RemoteException {
		Player player = ClientMain.store.getCurrentPlayer();
		Token currentToken = player.getToken();
		PropertyUI p = new PropertyUI(currentToken.getxCoordinate(), currentToken.getyCoordinate(), player.getName(),
				PlayerColor.valueOf(player.getColor()));
		return p;
	}

	private static void enableMortgageButton() throws RemoteException {
		if (ClientMain.store.getCurrentPlayer().OwnedSquareName().isEmpty()) {
			MonopolyOptions.mortgage.setEnabled(false);
		} else {
			MonopolyOptions.mortgage.setEnabled(true);
		}

	}

	private static void enableUnMortgageButton() throws RemoteException {
		if (ClientMain.store.getCurrentPlayer().mortgagedSquareName().isEmpty()) {
			MonopolyOptions.UnMortgage.setEnabled(false);
		} else {
			MonopolyOptions.UnMortgage.setEnabled(true);
		}

	}

	private static boolean isOtherOwns(String propertyName) throws RemoteException {
		for (Player p : ClientMain.store.getPlayers()) {
			if (!p.getName().equals(ClientMain.store.getCurrentPlayer().getName())) {
				if (p.OwnedSquareName().contains(propertyName)) {
					return true;
				}
			}
		}
		return false;
	}

}