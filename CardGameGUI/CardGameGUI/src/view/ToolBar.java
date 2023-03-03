package view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar{
	
	private JButton dealButton;
	private JButton betButton;
	private JButton removePlayerButton;
	private PlayerComboBox comboBox;
	
	/*
	 * Creates a tool bar with a deal button, bet button, remove player button and a PlayerCombobox.
	 */
	public ToolBar() {

		this.dealButton = new JButton("Deal");
		this.betButton = new JButton("Bet");
		this.removePlayerButton = new JButton("Remove Player");
		this.comboBox = new PlayerComboBox();
		
		Border border = BorderFactory.createTitledBorder("Tool Bar");
		setBorder(border);
		
		add(dealButton);
		addSeparator();
		add(betButton);
		disableDealButton();
		disableRemovePlayerButton();
		disableBetButton();
		
		add(comboBox);
		
		addSeparator();
		add(removePlayerButton);
	}
	
	// Disables the bet button so it's unclickable.
	public void disableBetButton() {
		this.betButton.setEnabled(false);	
	}
	
	// Enables the bet button so it's clickable
	public void enableBetButton() {
		this.betButton.setEnabled(true);	
	}
	
	// Enables the remove bet button so it's clickable
	public void enableRemoveBetButton() {
		this.betButton.setEnabled(true);
	}
	
	// Disables the remove bet button so it's unclickable
	public void disableRemoveBetButton() {
		this.betButton.setEnabled(false);
	}

	// Enables the deal button so it's clickable
	public void enableDealButton() {
		this.dealButton.setEnabled(true);
	}
	
	// Disable the deal button so it's unclickable
	public void disableDealButton() {
		this.dealButton.setEnabled(false);
	}
	
	// Disables the remove player button so it's unclickable
	public void disableRemovePlayerButton() {
		this.removePlayerButton.setEnabled(false);
	}
	
	// Enables the remove player button so it's clickable
	public void enableRemovePlayerButton() {
		this.removePlayerButton.setEnabled(true);
	}
	
	// Return the bet button
	public JButton getBetButton() {
		return this.betButton;
	}
	
	// Returns the PlayerComboBox on the tool bar
	public PlayerComboBox getPlayerComboBox() {
		return this.comboBox;
	}
	
	// Returns the remove player button
	public JButton getRemovePlayerButton() {
		return this.removePlayerButton;
	}
	
	// Changes the bet button text to "Bet"
	public void setBetButtonText() {
		this.betButton.setText("Bet");
	}
	
	// Changes the bet button text to "Remove Bet"
	public void setRemoveBetButtonText() {
		this.betButton.setText("Remove Bet");
	}
	
	// Returns the Deal Button
	public JButton getDealButton() {
		return this.dealButton;
	}
	
}
