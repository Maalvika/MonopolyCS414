package com.cs414j.monopoly.server.model;

import java.awt.Color;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import com.cs414j.monopoly.client.main.ClientCallback;
import com.cs414j.monopoly.client.main.InitcardDeck;
import com.cs414j.monopoly.common.Bank;
import com.cs414j.monopoly.common.Board;
import com.cs414j.monopoly.common.Cards;
import com.cs414j.monopoly.common.MonopolyStore;
import com.cs414j.monopoly.common.Player;
import com.cs414j.monopoly.common.PlayerColor;
import com.cs414j.monopoly.common.PlayerImpl;
import com.cs414j.monopoly.common.Token;
import com.cs414j.monopoly.controller.MonopolyMain;
import com.cs414j.monopoly.controller.StartGameTimer;
import com.cs414j.monopoly.view.TokenUrls;

public class MonopolyServerStore extends java.rmi.server.UnicastRemoteObject implements MonopolyStore {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int PLAYER_COUNT = 0;
	public List<Player> players;
	private static MonopolyServerStore serverStore = null;
	private static List<Token> alltokens;
	private static List<String> colors;
	public List<Token> selectedTokens;
	private static Bank bank;
	private static Board board;
	private static Player currentPlayer;
	private static List<ClientCallback> clientObj;
	private static Cards card;
	private static InitcardDeck cardDeck;

	private MonopolyServerStore() throws java.rmi.RemoteException {
		players = new LinkedList<>();
		alltokens = new LinkedList<>();
		selectedTokens = new LinkedList<>();
		clientObj = new LinkedList<>();
		colors = new LinkedList<>();
		prepareTokenList();
		prepareColorList();
	}

	public static MonopolyServerStore getInstance() throws RemoteException {
		if (serverStore == null) {
			serverStore = new MonopolyServerStore();
		}
		return serverStore;
	}

	public Player addNewPlayer(String name) throws RemoteException {
		Player p = new PlayerImpl(name);
		p.setToken(alltokens.get(PLAYER_COUNT));
		p.setColor(colors.get(PLAYER_COUNT));
		Player stub = (Player) UnicastRemoteObject.exportObject(p, 0);
		if (PLAYER_COUNT == 0) {
			new StartGameTimer();
			currentPlayer = stub;
		}
		selectedTokens.add(alltokens.get(PLAYER_COUNT));
		players.add(stub);
		PLAYER_COUNT++;
		return p;

	}

	private void prepareTokenList() {
		alltokens.add(new Token(TokenUrls.CAR, 10, 870));
		alltokens.add(new Token(TokenUrls.SHIP, 70, 870));
		alltokens.add(new Token(TokenUrls.HORSE, 70, 930));
		alltokens.add(new Token(TokenUrls.SHOE, 10, 930));

	}

	private void prepareColorList() {
		for (PlayerColor c : PlayerColor.values()) {
			colors.add(c.name());
		}

	}

	public List<Player> getPlayers() throws RemoteException {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Token> getSelectedTokens() throws RemoteException {
		return selectedTokens;
	}

	public void setSelectedTokens(List<Token> selectedTokens) throws RemoteException {
		this.selectedTokens = selectedTokens;
	}

	public static Bank getBankInstance() {
		if (bank == null) {
			bank = new Bank();
		}
		return bank;
	}

	public static Cards getCardInstance() {
		if (card == null) {
			card = new Cards();
		}
		return card;
	}

	public static Board getBoardInstance() {
		if (board == null) {
			board = new Board();
		}
		return board;
	}

	public static InitcardDeck getDeckInstance() {
		if (cardDeck == null) {
			cardDeck = new InitcardDeck();
		}
		return cardDeck;
	}

	public static int getPLAYER_COUNT() {
		return PLAYER_COUNT;
	}

	public static void setPLAYER_COUNT(int pLAYER_COUNT) {
		PLAYER_COUNT = pLAYER_COUNT;
	}

	public Player getCurrentPlayer() throws RemoteException {
		return currentPlayer;
	}

	@Override
	public void setTokenCoordinates(TokenUrls t, int x, int y) throws RemoteException {
		for (Token token : selectedTokens) {
			if (token.getTokenURL() == t) {
				selectedTokens.remove(token);
				token.setxCoordinate(x);
				token.setyCoordinate(y);
				selectedTokens.add(token);
			}
		}

	}

	@Override
	public void switchToNextTurn(ClientCallback c) throws RemoteException {
		int currentIndex = players.indexOf(currentPlayer);
		if (currentIndex == MonopolyServerStore.getPLAYER_COUNT() - 1) {
			currentPlayer = serverStore.players.get(0);
		} else {
			currentPlayer = serverStore.players.get(currentIndex + 1);
		}
		changeAllPlayerDetails(true);

	}

	@Override
	public void registerClient(ClientCallback c) throws RemoteException {
		clientObj.add(c);

	}

	@Override
	public void changeOtherPlayerBoard(ClientCallback c, int leftDie, int rightDie) throws RemoteException {
		for (ClientCallback client : clientObj) {
			if (!client.getName().equals(c.getName())) {
				client.changeBoardImage(leftDie, rightDie);
			}
		}

	}

	public static List<ClientCallback> getClientObj() {
		return clientObj;
	}

	public static void setClientObj(List<ClientCallback> clientObj) {
		MonopolyServerStore.clientObj = clientObj;
	}

	public static void changeAllPlayerDetails(boolean changeButtons) throws RemoteException {
		for (ClientCallback client : clientObj) {
			if(changeButtons == true)
				client.changeOtherPlayerDetailsAndControlButton(currentPlayer);
			else 
				client.changePlayerDetails();
		}
	}

	public void sendMessageToAll(String otherMessage) throws RemoteException {
		for (ClientCallback c : clientObj) {
			if (!c.getName().equals(currentPlayer.getName())) {
				c.showMsg(otherMessage);
			}
		}
	}

	public void sendMessageToPlayer(String message) throws RemoteException {
		for (ClientCallback c : clientObj) {
			if (c.getName().equals(currentPlayer.getName())) {
				c.showMsg(message);
			}
		}
	}

	@Override
	public boolean isOwnedByBank(String name) throws RemoteException {
		return MonopolyServerStore.getBankInstance().isOwned(name);
	}

	@Override
	public void sendPropertyForAuction(String propertyName) throws RemoteException {
		for (ClientCallback c : clientObj) {
			if (!c.getName().equals(currentPlayer.getName())) {
				c.auctionProperty(propertyName);
			}
		}
		int max = 0;
		ClientCallback client = null;
		for (ClientCallback c : clientObj) {
			if (!c.getName().equals(currentPlayer.getName())) {
				if (max < c.getBidValue()) {
					max = c.getBidValue();
					client = c;
				}
			}
		}

		for (Player p : players) {
			if (client.getName().equals(p.getName())) {
				p.buyProperty(propertyName, max);
			}

		}
		for (ClientCallback c : clientObj) {
			c.buyPropertyAction(client.getName());
		}
		sendMessageToAll(propertyName + " is purchased by Player: " + client.getName());
	}

	@Override
	public void sendRentMessageToOwner(String propertyName) throws RemoteException {
		Player owner = currentPlayer.getOwner(propertyName);
		getClientFromPlayer(owner.getName()).showMsg(currentPlayer.getName() + " paid you rent");
	}

	public static ClientCallback getClientFromPlayer(String pName) throws RemoteException {
		for (ClientCallback c : clientObj) {
			if (c.getName().equals(pName)) {
				return c;
			}
		}
		return null;
	}

	@Override
	public void endGameIfAnyPlayerQuits(String playerName) throws RemoteException {
		ClientCallback clientQuitted = getClientFromPlayer(playerName);
		for (ClientCallback c : clientObj) {
			if (c != clientQuitted) {
				c.endGame();
			}
		}

	}

	@Override
	public void placeBuyPropertyToken() throws RemoteException {
		ClientCallback currentClient = getClientFromPlayer(currentPlayer.getName());
		for (ClientCallback c : clientObj) {
			if (c != currentClient) {
				c.buyPropertyAction(currentPlayer.getName());
			}
		}

	}

	public void callChanceCards(int index) throws RemoteException {
		MonopolyServerStore.getCardInstance().chanceAction(index, currentPlayer);
	}

	public void callChestCards(int index) throws RemoteException {
		MonopolyServerStore.getCardInstance().chestAction(index, currentPlayer);
	}

}
