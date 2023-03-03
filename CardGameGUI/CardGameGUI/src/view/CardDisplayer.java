package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CardDisplayer extends JPanel{
	
	private ArrayList<CardGraphics> cardsDisplayed;
	private final static int maxCards = 6;
	
	// Creates a card display with a maximum 6 slots
	public CardDisplayer() {
		
		setLayout(new GridLayout(0, maxCards));
		setVisible(true);
		this.cardsDisplayed = new ArrayList<CardGraphics>();
		
	}
	
	// Creates a new card graphic and adds it to one of the 6 slots.
	public void addCard(String cardSuit, String cardValue) {
		CardGraphics card = new CardGraphics(cardSuit, cardValue);
		add(card, BorderLayout.CENTER);
		this.cardsDisplayed.add(card);
		revalidate();
	}
	
	// Deletes all items on display.
	public void removeAllCards() {
		removeAll();
		revalidate();
	}
	
}
