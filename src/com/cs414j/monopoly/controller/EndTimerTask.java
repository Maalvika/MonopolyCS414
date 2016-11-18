package com.cs414j.monopoly.controller;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import com.cs414j.monopoly.client.view.EndForm;

public class EndTimerTask {
	
	Timer endTask;
	
	public EndTimerTask(int minutes) {
		int timeinMilliseconds = minutes*1000*60;
		endTask = new Timer();
		endTask.schedule(new EndGame(), timeinMilliseconds);
		endTask.schedule(new CountdownTimer(minutes, endTask), 1000, 1000);
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
	
	class CountdownTimer extends TimerTask {

		int remTimeinSec;
		Timer myTimer;
		
		public CountdownTimer(int timeinMin, Timer endTimer) {
			remTimeinSec = timeinMin*60;
			myTimer = endTimer;
		}
		@Override
		public void run() {
			int displayTime = remTimeinSec/60;
			int  sec = remTimeinSec % 60;
			MonopolyOptions.remTime.setText("REMAINING TIME: "+displayTime+":"+sec);
			System.out.println("disp:"+displayTime+"sec:"+sec);
			remTimeinSec--;
			System.out.println("remTime:"+remTimeinSec);
			if(remTimeinSec == 0) {
				myTimer.cancel();
			}
			
		}
		
	}

}
