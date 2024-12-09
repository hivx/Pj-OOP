package game.menu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundGame extends JPanel { 
    
	public Image img;

	public BackgroundGame(Image img) {
	this.img = img;
	Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	setPreferredSize(size);
	setMinimumSize(size);
	setMaximumSize(size);
	setSize(size);
	setLayout(null);
	}

	public void paintComponent(Graphics g) {
	g.drawImage(img, 0, 0, null);
	}

}

