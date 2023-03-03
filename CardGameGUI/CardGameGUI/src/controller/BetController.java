package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.SummaryPanel;
import view.ToolBar;

public class BetController implements ActionListener {

	private GameEngine engine;
	private ToolBar toolBar;
	private SummaryPanel sumPanel;

	public BetController(GameEngine engine, ToolBar toolBar, SummaryPanel sumPanel) {
		this.engine = engine;
		this.toolBar = toolBar;
		this.sumPanel = sumPanel;
	}

	/*
	 * Checks if the button text matches "Bet" or "Remove Bet" and calls the place
	 * bet or remove bet function accordingly.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.getText() == "Bet") {
			placeBet();
		} else if (button.getText() == "Remove Bet") {
			removeBet();
		}
	}

	// Requests the engine to reset the bet on the player currently selected on
	// player combo box.
	private void removeBet() {
		boolean resetBetSuccessful = false;

		/*
		 * Creates a temporary player and requests the engine to reset the bet on the
		 * same player with a matching id.
		 */
		Player temp = (SimplePlayer) getCurrentPlayer();
		for (Player p : engine.getAllPlayers()) {
			if (temp.equals(p)) {
				p.resetBet();
				resetBetSuccessful = true;
			}
		}

		// Creates and displays a message to display if the bet was successfully reset.
		String message = "";
		if (resetBetSuccessful) {
			message = String.format("%s has cancelled bet.", temp.getPlayerName());
			setBetButtonText();
			toolBar.disableDealButton();
			updateSummary();
		} else {
			message = String.format("%s was unsuccessful in cancelling bet.", temp.getPlayerName());
		}
		JOptionPane.showMessageDialog(null, message);

	}

	// Requests the engine to place a bet on the player currently selected on the
	// player combo box.
	public void placeBet() {
		boolean betSuccessful = false;

		/*
		 * Creates a temporary player and requests the engine to place the bet on the
		 * same player with a matching id.
		 */
		Player temp = (SimplePlayer) getCurrentPlayer();
		String betString = JOptionPane.showInputDialog(null, "Enter Bet Amount: ");

		try {
			// Requests the user to enter a bet amount on a JOptionPane
			while (!betString.matches("[0-9]+") || Integer.parseInt(betString) < 0
					|| Integer.parseInt(betString) > Integer.MAX_VALUE
					|| Integer.parseInt(betString) > temp.getPoints()) {
				betString = JOptionPane.showInputDialog(null, "Invalid bet amount. Please enter a valid bet amount.");
			}

			int bet = Integer.parseInt(betString);
			for (Player p : engine.getAllPlayers()) {
				if (temp.equals(p)) {
					betSuccessful = this.engine.placeBet(p, bet);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, String.format("%s unsuccessfully placed a bet", temp.getPlayerName()));
		}

		/*
		 * Changes the bet text to remove bet, enables the deal button, updates the summary
		 * and displays a message dialog will appear to state if the bet was successfully
		 * placed.
		 */
		if (betSuccessful) {
			this.toolBar.setRemoveBetButtonText();
			toolBar.enableDealButton();
			updateSummary();
			JOptionPane.showMessageDialog(null, String.format("%s successfully placed a bet", temp.getPlayerName()));
		}

	}

	// Returns the current index position on player combo box
	public int getCurrentPlayerPosition() {
		return this.toolBar.getPlayerComboBox().getSelectedIndex();
	}

	// Changes the bet button text to "Remove bet".
	public void setBetRemoveButtonText() {
		this.toolBar.setRemoveBetButtonText();
	}

	// Changes the bet button text to "Bet".
	public void setBetButtonText() {
		this.toolBar.setBetButtonText();
	}

	// Updates the bet on summary panel based on the current player selected.
	public void updateSummary() {
		String bet = String.valueOf(getCurrentPlayer().getBet());
		sumPanel.updateBet(getCurrentPlayerPosition(), bet);
	}

	// Returns a Player from the engine that is currently selected on the player combo box.
	public Player getCurrentPlayer() {
		return (Player) this.engine.getAllPlayers().toArray()[getCurrentPlayerPosition()];
	}

}
