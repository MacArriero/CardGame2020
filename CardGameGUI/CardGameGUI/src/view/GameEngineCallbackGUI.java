package view;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {

	private SummaryPanel sumPanel;
	private CardPanel cardPanel;
	private StatusBar statusBar;
	private ToolBar toolBar;

	// Creates a GameEngineCallbackGUI object
	public GameEngineCallbackGUI(SummaryPanel sumPanel, CardPanel cardPanel, StatusBar statusBar, ToolBar toolBar) {
		this.sumPanel = sumPanel;
		this.cardPanel = cardPanel;
		this.statusBar = statusBar;
		this.toolBar = toolBar;
	}

	// Calls the displayPlayerCard method, passing in the player and the card.
	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		displayPlayerCard(player, card);
	}

	// Calls the displayPlayerCard method, passing in the player and the card.
	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		displayPlayerCard(player, card);
	}

	// Updates the player results on the Summary Panel.
	@Override
	public void result(Player player, int result, GameEngine engine) {

		// Finds the index of the player on the game engine.
		int playerIndex = 0;
		for (int i = 0; i < engine.getAllPlayers().toArray().length; ++i) {
			if (player.equals(engine.getAllPlayers().toArray()[i])) {
				playerIndex = i;
			}
		}
		
		// Requests the summary panel to update the result, based on the player index.
		this.sumPanel.updateResult(playerIndex, String.valueOf(player.getResult()));

		// Changes the status to "Preparing to deal House..." if all players are dealt.
		if (this.sumPanel.getNumOfPlayersDealt() == engine.getAllPlayers().size()) {
			this.statusBar.setStatus("Preparing to deal House...");
		}
	}

	// Calls the displayHouseCard method, passing in the card.
	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		displayHouseCard(card);
	}

	// Calls the displayHouseCard method, passing in the card.
	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		displayHouseCard(card);
	}

	/*
	 * Displays the House's final result on a JOptionPane indicating
	 * the round has ended and triggers round end GUI update.
	 */
	@Override
	public void houseResult(int result, GameEngine engine) {
		
		String roundEndMessage = String.format("Round Complete! House final result = %d.", result);
		JOptionPane.showMessageDialog(null, roundEndMessage);
		
		/*
		 * Triggers round end GUI update and displays the round 
		 * end message on the status bar
		 */
		displayRoundEnd(engine);
		this.statusBar.setStatus(roundEndMessage);
	}
	
	/*
	 * Updates the Summary Panel and configures the bet button for the next round.
	 */
	private void displayRoundEnd(GameEngine engine) {
		
		// Resets all the bets on the Summary Panel to zero.
		this.sumPanel.resetAllBets();

		/*
		 *  Updates the player history by subtracting current player points
		 *  with the points on the summary panel. Then the player points on the
		 *  summary panel is updated with the current player points on the engine.
		 */
		for (int playerIndex = 0; playerIndex < engine.getAllPlayers().size(); ++playerIndex) {
			Player copyOfPlayer = (Player) engine.getAllPlayers().toArray()[playerIndex];
			int currentPoints = copyOfPlayer.getPoints();
			int currentResult = copyOfPlayer.getResult();

			String outcome = "";
			int winLossAmount = currentPoints - Integer.valueOf(this.sumPanel.getPlayerPoints(playerIndex));
			if (winLossAmount == 0) {
				outcome = String.format("Draw R: %s", currentResult);
			} else if (winLossAmount < 0) {
				outcome = String.format("Loss %sP R: %s", winLossAmount, currentResult);
			} else if (winLossAmount > 0) {
				outcome = String.format("Win +%sP R: %s", winLossAmount, currentResult);
			}

			this.sumPanel.updateHistory(playerIndex, outcome);
			this.sumPanel.updatePlayerPoints(playerIndex, String.valueOf(currentPoints));
		}
		
		// Resets all the player results on the Summary Panel to zero.
		this.sumPanel.resetAllResults();
		
		// Configures the bet button so that it is ready for the next round.
		this.toolBar.setBetButtonText();
		String selectedPlayer = this.toolBar.getPlayerComboBox().getSelectedItem().toString();
		if (selectedPlayer != "House") {
			this.toolBar.enableBetButton();
		}
	}

	// Requests the card panel to display the card passed in, on the player's display.
	private void displayPlayerCard(Player player, PlayingCard card) {
		cardPanel.addPlayerCard(player.getPlayerId(), card.getSuit().toString(), card.getValue().toString());
	}

	// Requests the card panel to display the card passed in, on the House's display.
	private void displayHouseCard(PlayingCard card) {
		this.cardPanel.getHouseDisplay().addCard(card.getSuit().toString(), card.getValue().toString());
	}
}
