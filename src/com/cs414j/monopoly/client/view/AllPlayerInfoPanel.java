package com.cs414j.monopoly.client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;

import com.cs414j.monopoly.client.main.ClientMain;
import com.cs414j.monopoly.common.Player;

public class AllPlayerInfoPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AllPlayerInfoPanel() throws RemoteException {
		initComponents();
	}
	
	private void initComponents() throws RemoteException {
		String[] columnNames = {"Player Name", "Token","Color", "Balance", "Property Cost","Total"};
		List<Player> players= ClientMain.store.getPlayers();
		Object[][] data = new Object[players.size()][6];
		for(int i=0; i<players.size(); i++) {
			data[i][0] = players.get(i).getName();
			data[i][1] = players.get(i).getToken().getTokenURL().toString();
			data[i][2] = players.get(i).getColor();
			data[i][3] = players.get(i).getBalance();
			data[i][4] = players.get(i).getpropertyCost();
			data[i][5] = players.get(i).getBalance() + players.get(i).getpropertyCost();
			
		}
		JTable table = new JTable(data,columnNames);
		add(table);
		setVisible(true);
		
		setLayout(new BorderLayout());
		add(table.getTableHeader(), BorderLayout.PAGE_START);
		add(table, BorderLayout.CENTER);
		
		Dimension d = getPreferredSize();
		d.width = 500;
		setPreferredSize(d); 
	}
	

}
