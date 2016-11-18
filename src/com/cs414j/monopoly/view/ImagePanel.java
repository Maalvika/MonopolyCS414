package com.cs414j.monopoly.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

import com.cs414j.monopoly.common.PlayerColor;
import com.cs414j.monopoly.common.Token;

public class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;
	private List<Token> tokens;
	private Map<PropertyUI,Integer> properties = new HashMap<>();

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
		for (Token t : tokens) {
			g.drawImage(getTokenImg(t.getTokenURL()), t.getxCoordinate(), t.getyCoordinate(), 50, 50, null);
		}
		if (!properties.isEmpty()) {
			for (Entry<PropertyUI, Integer> prop : properties.entrySet()) {
				if (prop.getValue() >= 1 && prop.getValue()!=4) {
					PropertyUI temp = prop.getKey();
					for (int i = 1; i <= prop.getValue(); i++)
						g.drawImage(getTokenImg(TokenUrls.HOUSE), temp.getxPoint()
								- i * 20, temp.getyPoint() - i * 20,40, 40, null);

				} else if(prop.getValue()==4) {
					g.drawImage(getTokenImg(TokenUrls.HOTEL), prop.getKey().getxPoint(), 
												prop.getKey().getyPoint(),40, 40, null);
				} 
				g.drawImage(getPlayerColorImg(prop.getKey().getColor()), prop.getKey().getxPoint(),
									prop.getKey().getyPoint(), null);
			}
		}
		repaint();
	}

	  public void changeTokenPosition(List<Token> tokens){
		  this.tokens = tokens;
		  repaint();
	  }
	  
	  public void addPlayerComponents(Map<PropertyUI,Integer> properties){
		  this.properties = properties;
		  repaint();
	  }
	  
	  
	  
	  private Image getTokenImg(TokenUrls token) {
		  final BufferedImage img = new ImageUtility().scaleImage(1000, 1000, token.getURLValue());
		  Image tokenImg = new ImageIcon(img).getImage();
		  return tokenImg;
		  
	  }
	  
	  private Image getPlayerColorImg(PlayerColor color) {
		  final BufferedImage img = new ImageUtility().scaleImage(20, 20, color.getUrl());
		  Image tokenImg = new ImageIcon(img).getImage();
		  return tokenImg;
		  
	  }
	}
