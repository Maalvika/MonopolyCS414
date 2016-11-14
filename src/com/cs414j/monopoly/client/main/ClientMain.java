package com.cs414j.monopoly.client.main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.cs414j.monopoly.client.view.PlayerDetailForm;
import com.cs414j.monopoly.common.ConnectionDetails;
import com.cs414j.monopoly.common.MonopolyStore;

public class ClientMain {

	public static MonopolyStore store;
	public static PlayerDetailForm detail;

	public static void main(String[] args) {
		try {
			store = (MonopolyStore) Naming.lookup("rmi://" + ConnectionDetails.HOSTNAME + ":" + ConnectionDetails.PORT
					+ ConnectionDetails.SERVICE_NAME);
			System.out.println("Client Registered");
			detail = new PlayerDetailForm();
			detail.setVisible(true);
		}

		catch (MalformedURLException murle) {
			System.out.println("MalformedURLException");
			System.out.println(murle);
		} catch (RemoteException re) {
			System.out.println("RemoteException");
			System.out.println(re);
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
