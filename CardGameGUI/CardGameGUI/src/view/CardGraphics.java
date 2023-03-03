package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class CardGraphics extends JPanel {

	private BufferedImage cardImage;
	private static final double cardHeightScaler = 1.5;

	/*
	 * Draws the image of the card by grabbing the card image from the images directory.
	 */
	public CardGraphics(String cardSuit, String cardValue) {
		
		try {
			this.cardImage = ImageIO.read(new File(String.format("CardGameGUI/images/cards/%s_%s.PNG", cardSuit, cardValue)));
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Can't find the card images directory.", "IOException", JOptionPane.ERROR_MESSAGE);
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int padding = 10;
		g.drawImage(cardImage, 0, 0, getWidth() - padding, (int) (getWidth() * cardHeightScaler) - padding, null);
	}

}
