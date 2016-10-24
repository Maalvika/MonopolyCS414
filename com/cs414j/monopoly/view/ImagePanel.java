package com.cs414j.monopoly.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

public class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;
	private List<Token> tokens;

	  public ImagePanel(Image img, List<Token> tokens) {
	    this.img = img;
	    this.tokens = tokens;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	    //setOpaque(true);
	  }

	  public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	    for(Token t: tokens) {
	    	g.drawImage(getCarTokenImg(t.getToken()), t.getxCoordinate(), t.getyCoordinate(), 50,50,null);
	    }
	    repaint();
	  }
	  
	  public void changeTokenPosition(List<Token> tokens){
		  this.tokens = tokens;
		  repaint();
	  }
	  
	  private Image getCarTokenImg(TokenUrls token) {
		  final BufferedImage img = new ImageUtility().scaleImage(1000, 1000, token.getURLValue());
		  Image tokenImg = new ImageIcon(img).getImage();
		  return tokenImg;
		  
	  }

	}
