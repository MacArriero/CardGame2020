package view;

import java.awt.CardLayout;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class CardPanel extends JPanel{
	
	private TreeMap<String, CardDisplayer> playerCardDisplays;
	private CardDisplayer houseCardDisplay;
	private CardLayout display;
	
	/*
	 *  Creates a card panel with a card layout, a TreeMap of all the Card displays
	 *  for the players and one for the House.
	 */
	public CardPanel() {		
		Border border = BorderFactory.createTitledBorder("Card Panel");
		setBorder(border);
		
		this.display = new CardLayout();
		setLayout(display);
		
		this.playerCardDisplays = new TreeMap<String, CardDisplayer>();
		this.houseCardDisplay = new CardDisplayer();
		
		add(houseCardDisplay, "");
		display.show(this, "");
			
	}
	
	// Adds a player display, replacing an existing display if ID matches.
	public void addPlayerDisplay(String id) {
		this.playerCardDisplays.put(id, new CardDisplayer());
		add(this.playerCardDisplays.get(id), id);
		display.show(this, id);
		
	}
	
	// Deletes a player display based on the ID passed in.
	public void deletePlayerDisplay(String id) {
		this.playerCardDisplays.remove(id);
	}
	
	// Changes the display currently being displayed, based on the ID.
	public void changeToPlayerDisplay(String id) {
		this.display.show(this, id);
	}
	
	// Changes the display currently being displayed to the House display.
	public void changeToHouseDisplay() {
		this.display.show(this, "");
	}
	
	// Returns the House's display.
	public CardDisplayer getHouseDisplay() {
		return this.houseCardDisplay;
	}
	
	// Returns a player display based on the id passed in.
	public CardDisplayer getPlayerDisplay(String playerID) {
		return this.playerCardDisplays.get(playerID);
	}
	
	// Adds a card to a player's display
	public void addPlayerCard(String playerId, String cardSuit, String cardValue) {
		getPlayerDisplay(playerId).addCard(cardSuit, cardValue);
		changeToPlayerDisplay(playerId);
	}
	
	// Removes all the cards on a player's display, based on the ID passed.
	public void clearPlayerDisplay(String playerID) {
		this.playerCardDisplays.put(playerID, new CardDisplayer());
		add(this.playerCardDisplays.get(playerID), playerID);
		display.show(this, playerID);
	}

	// Removes all the cards on the House's display
	public void clearHouseDisplay() {
		this.houseCardDisplay = new CardDisplayer();
		add(this.houseCardDisplay);
	}

	
	
}
