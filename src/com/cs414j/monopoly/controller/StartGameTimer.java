package com.cs414j.monopoly.controller;

import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

import com.cs414j.monopoly.client.main.ClientCallback;
import com.cs414j.monopoly.server.model.MonopolyServerStore;

public class StartGameTimer {

	Timer startTask;
	private static final int WAIT_TIME_SEC = 30;

	public StartGameTimer() {
		int timeinMilliseconds = WAIT_TIME_SEC * 1000;
		startTask = new Timer();
		startTask.schedule(new StartGame(), timeinMilliseconds);
	}

	class StartGame extends TimerTask {


		public void run() {
			
			try {
				if(MonopolyServerStore.getInstance().getPlayers().size() >1) {
					for(ClientCallback c: MonopolyServerStore.getInstance().getClientObj()) {
						c.startMonopolyGame(MonopolyServerStore.getEndTime());
					}
				} else {
					 MonopolyServerStore.getInstance().getClientObj().get(0).
					 	showErrorStartGame("Sorry!!! No other Players are available. Unable to start the game");
						
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
		}

	}

}
