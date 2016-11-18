package com.cs414j.monopoly.server.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.server.RemoteServer;

import com.cs414j.monopoly.common.ConnectionDetails;
import com.cs414j.monopoly.common.MonopolyStore;
import com.cs414j.monopoly.server.model.MonopolyServerStore;


public class ServerMain {
	
	private String url;
	private int endTime;
	
	public ServerMain(String url,int endTime) {
		this.url = url;
		this.endTime=endTime;
		try {
			MonopolyStore store = MonopolyServerStore.getInstance();
			Naming.rebind(url, store);
			System.out.println("server started....");
		} catch (Exception e) {
			System.out.println("Trouble: " + e);
		}
	}
	
	public int getEndTime()
	{
		return endTime;
	}
	
	public void setEndTime(int endTime)
	{
		this.endTime=endTime;
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		String url = new String("rmi://" + ConnectionDetails.HOSTNAME+ ":" 
					+ ConnectionDetails.PORT + ConnectionDetails.SERVICE_NAME);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Input Total Game Duration(in minutes):");
		int endTime = Integer.parseInt(br.readLine());
		new ServerMain(url,endTime);
	}

}
