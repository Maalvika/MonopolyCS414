package com.cs414j.monopoly.view;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

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
public class EndForm extends javax.swing.JFrame {

    /**
     * Creates new form EndForm
     */
    public EndForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        finalResultLabel = new javax.swing.JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        finalResultLabel.setText("END RESULT");
        for(int i=0; i<pResult.length; i++) {
        	pResult[i] = new JLabel();
        	propertyPLabel[i] = new JLabel();
        	propertyPLabel[i].setEnabled(false);
        	propertyList[i] = new javax.swing.JList<>();
        	jScrollPane[i] = new javax.swing.JScrollPane();
        	jScrollPane[i].setEnabled(false);
        	
        }
        
        for(int i=0; i<MonopolyMain.players.size(); i++) {
        	Player p= MonopolyMain.players.get(i);
        	MonopolyMain.currentPlayer = p;
        	pResult[i].setText("<html><b>Player Name<b>: "+p.getName()+"<br/><b> Player Balance<b>: "+p.getBalance()+"<html>");
        	propertyPLabel[i].setText("Properties");
        	propertyPLabel[i].setEnabled(true);
        	if(!p.OwnedSquareName().isEmpty()) {
        	propertyList[i].setModel(new javax.swing.AbstractListModel<String>() {
                List<String> strings = new LinkedList<>(MonopolyMain.currentPlayer.OwnedSquareName());
                public int getSize() { return strings.size(); }
                public String getElementAt(int i) { return strings.get(i); }
            }); } else {
            	
            	propertyList[i].setModel(new javax.swing.AbstractListModel<String>() {
                    String s[] = {"No Properties are Owned"};
                    public int getSize() { return s.length; }
                    public String getElementAt(int i) { return s[i]; }
                }); 
            }
        	jScrollPane[i].setViewportView(propertyList[i]);
        	jScrollPane[i].setSize(100, 100);
        	jScrollPane[i].setEnabled(true);
        } 

       
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(pResult[0])
                        .addGap(100, 100, 100)
                        .addComponent(pResult[1]))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(propertyPLabel[0])
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane[0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(propertyPLabel[1])
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane[1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(propertyPLabel[2])
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane[2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(propertyPLabel[3])
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane[3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(30, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(pResult[2])
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pResult[3])
                        .addGap(56, 56, 56))))
            .addGroup(layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(finalResultLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(finalResultLabel)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pResult[0])
                    .addComponent(pResult[1])
                    .addComponent(pResult[2])
                    .addComponent(pResult[3]))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(propertyPLabel[0])
                    .addComponent(propertyPLabel[2])
                    .addComponent(propertyPLabel[3])
                    .addComponent(jScrollPane[0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane[1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(propertyPLabel[1])
                    .addComponent(jScrollPane[2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane[3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EndForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EndForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EndForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EndForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EndForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel finalResultLabel;
    private JLabel propertyPLabel[] = new JLabel[4];
    private javax.swing.JList<String> propertyList[] = new JList[4];
    private javax.swing.JScrollPane jScrollPane[] = new JScrollPane[4];
    private JLabel pResult[] = new JLabel[4];
    // End of variables declaration                   
}
