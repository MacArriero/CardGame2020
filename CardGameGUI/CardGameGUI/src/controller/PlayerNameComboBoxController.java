package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.CardPanel;
import view.PlayerComboBox;
import view.StatusBar;
import view.SummaryPanel;
import view.ToolBar;

public class PlayerNameComboBoxController implements ActionListener {

	private GameEngine engine;
	private ToolBar toolBar;
	private String currentSelectedPlayerName;
	private StatusBar statusBar;
	private CardPanel cardPanel;
	private SummaryPanel sumPanel;

	public PlayerNameComboBoxController(GameEngine engine, ToolBar toolBar, StatusBar statusBar, CardPanel cardPanel,
			SummaryPanel sumPanel) {
		this.engine = engine;
		this.toolBar = toolBar;
		this.statusBar = statusBar;
		this.cardPanel = cardPanel;
		this.sumPanel = sumPanel;
	}

	// Determines which player is currently selected and adjusts the GUI components accordingly.
	@Override
	public void actionPerformed(ActionEvent e) {
		PlayerComboBox comboBox = (PlayerComboBox) e.getSource();
		setCurrentSelectedPlayerName((String) comboBox.getSelectedItem());

		/*
		 *  Changes card panel to display the House's display and disables all buttons
		 *  and changes the status to state that the House currently selected.
		 */
		if (currentSelectedPlayerName == "House") {
			changeToHouseCardPanel();
			disableRemovePlayerButton();
			disableBetButton();
			disableDealButton();
			updateStatusBar(String.format("%s player selected.", currentSelectedPlayerName));
			
		} else if (currentSelectedPlayerName != null) {
			
			/*
			 *  Changes card panel to display the player's display and enables the buttons
			 *  according to the bet and current result of the player
			 *  and changes the status to state that the player currently selected.
			 */
			updateStatusBar(String.format("%s player selected.", currentSelectedPlayerName));
			enableRemovePlayerButton();

			Player currentPlayer = (Player) engine.getAllPlayers().toArray()[getCurrentPositionIndex()];
			changeToPlayerCardPanel(currentPlayer.getPlayerId());

			if (Integer.parseInt(this.sumPanel.getPlayerResult(getCurrentPositionIndex())) <= 0) { 
				
				if(currentPlayer.getBet() <= 0) {
					
					// Enables the player to make a bet, by enabling the bet button and disabling the deal button.
					setBetButtonText();
					enableBetButton();
					disableDealButton();
				} else {
					
					//  Enables the player to remove their bet and make a deal, by enabling the remove bet and deal buttons.
					setBetRemoveButtonText();
					enableRemoveBetButton();
					enableDealButton();
				}
				
			} else { 
				// Disables the bet button and deal button as the player has been dealt according to their result.
				disableBetButton();
				disableDealButton();
			}
	
		}
	}

	// Updates the status text on the status bar to the String passed in.
	public void updateStatusBar(String status) {
		this.statusBar.setStatus(status);
	}

	// Disables the remove player button to make it unclickable.
	public void disableRemovePlayerButton() {
		this.toolBar.disableRemovePlayerButton();
	}

	// Enables the remove player button to make it clickable.
	public void enableRemovePlayerButton() {
		this.toolBar.enableRemovePlayerButton();
	}

	// Disables the deal button to make it unclickable.
	public void disableDealButton() {
		this.toolBar.disableDealButton();
	}

	// Enables the deal button to make it clickable.
	public void enableDealButton() {
		this.toolBar.enableDealButton();
	}

	// Disables the bet button, to make it unclickable.
	public void disableBetButton() {
		this.toolBar.disableBetButton();
	}

	// Enables the bet button to make it clickable.
	public void enableBetButton() {
		this.toolBar.enableBetButton();
	}

	// Enables the remove bet button to make it clickable.
	public void enableRemoveBetButton() {
		this.toolBar.enableBetButton();
	}

	// Disables the remove bet button to make it clickable. 
	public void disableRemoveBetButton() {
		this.toolBar.disableRemoveBetButton();
	}

	// Sets the current selected player name to the name passed in.
	public void setCurrentSelectedPlayerName(String name) {
		this.currentSelectedPlayerName = name;
	}

	// Returns the index position of the player currently selected in player combobox.
	public int getCurrentPositionIndex() {
		return this.toolBar.getPlayerComboBox().getSelectedIndex();
	}

	// Sets the bet button text to "Remove Bet".
	public void setBetRemoveButtonText() {
		this.toolBar.setRemoveBetButtonText();
	}

	// Sets the bet button text to "Bet".
	public void setBetButtonText() {
		this.toolBar.setBetButtonText();
	}

	// Requests the card panel to change the players display, based on their id.
	public void changeToPlayerCardPanel(String id) {
		this.cardPanel.changeToPlayerDisplay(id);
	}

	// Requests the card panel to change to the House's display.
	public void changeToHouseCardPanel() {
		this.cardPanel.changeToHouseDisplay();
	}

}
