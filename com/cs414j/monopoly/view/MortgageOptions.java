package com.cs414j.monopoly.view;

import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MortgageOptions {

	public MortgageOptions(Set<String> properties) {

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
			MonopolyMain.currentPlayer.mortgageProperty(comboBox.getSelectedItem().toString(), 
															MonopolyMain.bank,MonopolyMain.board);
			break;
		}

	}

}
