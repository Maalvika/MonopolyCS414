package com.cs414j.monopoly.controller;

import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

import com.cs414j.monopoly.client.view.EndForm;

public class EndTimerTask {
	
	Timer endTask;
	
	public EndTimerTask(int minutes) {
		int timeinMilliseconds = minutes*1000*60;
		endTask = new Timer();
		endTask.schedule(new EndGame(), timeinMilliseconds);
	}
	
	class EndGame extends TimerTask{
		
		public void run() {
			MonopolyMain.frame.dispose();
			EndForm f;
			try {
				f = new EndForm();
				f.setVisible(true);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
