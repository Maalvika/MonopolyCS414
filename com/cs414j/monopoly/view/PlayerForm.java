package com.cs414j.monopoly.view;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.cs414j.monopoly.controller.MonopolyMain;
import com.cs414j.monopoly.model.Player;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maalv
 */
public class PlayerForm extends javax.swing.JFrame {
	
	

    private int playerCount;
    private JTextField[] playerNames = new JTextField[4]; 
    private JLabel[] playerLabels = new JLabel[4]; 
    public static List<Token> tokens = new LinkedList<>();
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel pInfo;
    private javax.swing.JButton startMonopoly;
    
    
    public PlayerForm(int pCount) {
        this.playerCount = pCount;
        prepareTokenList();
        initComponents();
        hideComponents(pCount);
        
    }
    
    public void hideComponents(int pCount) {
        
        for(int i=playerCount; i<playerNames.length; i++) {
        	playerNames[i].setVisible(false);
        	playerLabels[i].setVisible(false);
        }
        errorLabel.setVisible(false);
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        pInfo = new javax.swing.JLabel();
        playerLabels[0] = new javax.swing.JLabel();
        playerLabels[1] = new javax.swing.JLabel();
        playerLabels[2] = new javax.swing.JLabel();
        playerLabels[3] = new javax.swing.JLabel();
        playerNames[0] = new javax.swing.JTextField();
        playerNames[1] = new javax.swing.JTextField();
        playerNames[2] = new javax.swing.JTextField();
        playerNames[3] = new javax.swing.JTextField();
        startMonopoly = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pInfo.setText("Enter Player Names");

        playerLabels[0].setText("Player 1");

        playerLabels[1].setText("Player 2");

        playerLabels[2].setText("Player 3");

        playerLabels[3].setText("Player 4");

        startMonopoly.setText("Start Game");
        startMonopoly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startMonopolyActionPerformed(evt);
            }
        });

        errorLabel.setText("<html><font color=\"red\">Information is incomplete</html>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(playerLabels[1])
                            .addComponent(playerLabels[0])
                            .addComponent(playerLabels[2])
                            .addComponent(playerLabels[3]))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(playerNames[1], javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(playerNames[2])))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pInfo)
                                .addGap(42, 42, 42))
                            .addComponent(playerNames[0], javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playerNames[3], javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(startMonopoly)
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pInfo)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerLabels[0])
                    .addComponent(playerNames[0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerLabels[1])
                    .addComponent(playerNames[1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerLabels[2])
                    .addComponent(playerNames[2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerLabels[3])
                    .addComponent(playerNames[3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startMonopoly)
                    .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        
        pack();
    }
    
    private void startMonopolyActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:

        int flag=0;
    	for(int i=0; i<playerCount; i++) {
    		if(playerNames[i].getText().isEmpty())
    			flag = -1;
    	}
    	
		for (int i = 0; i < playerCount; i++) {
			for (int j = i + 1; j < playerCount; j++) {
				if (playerNames[i].getText().toString().equals(playerNames[j].getText().toString())) {
					System.out.println("entered");
					flag = 1;
					break;
				}
			}
		}
        
        if(flag == -1) {
            errorLabel.setVisible(true);
        } else if(flag == 1){
        	errorLabel.setText("Duplicate Name!!!! Enter unique names");
        	errorLabel.setVisible(true);
        }else {
        
        	this.setVisible(false);
        	List<Player> players = new LinkedList<>();
        	for(int i=0; i<playerCount;i++) {
        		Player p = new Player(this.playerNames[i].getText());
        		p.setToken(tokens.get(i));
        		players.add(p);
        	}
        	MonopolyMain.setPlayers(players);
        	MonopolyMain.init();
        	//MonopolyMain.setPlayerDetails(this.playerNames[1].getText(), 50);
        }
    }  
    
    private void prepareTokenList() {
		tokens.add(new Token(TokenUrls.CAR, 10, 870));
		tokens.add(new Token(TokenUrls.SHIP, 70, 870));
		switch (playerCount) {
		case 3:
			tokens.add(new Token(TokenUrls.HORSE, 70, 930));
			break;
		case 4:
			tokens.add(new Token(TokenUrls.HORSE, 70, 930));
			tokens.add(new Token(TokenUrls.SHOE, 10, 930));
			break;
		}
	}
                 
}
