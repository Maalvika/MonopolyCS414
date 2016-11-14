package com.cs414j.monopoly.server.main;

import java.rmi.Naming;
import java.rmi.server.RemoteServer;

import com.cs414j.monopoly.common.ConnectionDetails;
import com.cs414j.monopoly.common.MonopolyStore;
import com.cs414j.monopoly.server.model.MonopolyServerStore;


public class ServerMain {
	
	private String url;
	
	public ServerMain(String url) {
		this.url = url;
		try {
			MonopolyStore store = MonopolyServerStore.getInstance();
			Naming.rebind(url, store);
			System.out.println("server started....");
		} catch (Exception e) {
			System.out.println("Trouble: " + e);
		}
	}
	
	public static void main(String args[]) {
		String url = new String("rmi://" + ConnectionDetails.HOSTNAME+ ":" 
					+ ConnectionDetails.PORT + ConnectionDetails.SERVICE_NAME);
		new ServerMain(url);
	}

}
