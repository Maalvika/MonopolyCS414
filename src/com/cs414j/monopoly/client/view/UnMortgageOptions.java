package com.cs414j.monopoly.client.view;


import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.cs414j.monopoly.client.controller.MonopolyMain;
import com.cs414j.monopoly.client.controller.MonopolyOptions;
import com.cs414j.monopoly.client.main.ClientMain;

public class UnMortgageOptions {

	public UnMortgageOptions(Set<String> properties) throws RemoteException {

		JPanel panel = new JPanel();
		panel.add(new JLabel("Please make a selection:"));
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		for (String string : properties) {
			model.addElement(string);
		}
		JComboBox comboBox = new JComboBox(model);
		panel.add(comboBox);

		int result = JOptionPane.showConfirmDialog(null, panel, "Flavor", JOptionPane.CLOSED_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		switch (result) {
		case JOptionPane.OK_OPTION:
			ClientMain.store.getCurrentPlayer().unMortgageProperty(comboBox.getSelectedItem().toString());
			MonopolyOptions.displayPopUp("Your property has been un-morgaged. Amount is deducted from your account");
			break;
		}

	}
}

