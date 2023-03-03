package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.CardPanel;
import view.StatusBar;
import view.ToolBar;

public class DealPlayerController implements ActionListener {

	private GameEngine engine;
	private int delay;
	private ToolBar toolBar;
	private StatusBar statusBar;
	private CardPanel cardPanel;

	public DealPlayerController(GameEngine engine, int delay, ToolBar toolBar, StatusBar statusBar, CardPanel cardPanel) {
		this.engine = engine;
		this.delay = delay;
		this.toolBar = toolBar;
		this.statusBar = statusBar;
		this.cardPanel = cardPanel;
	}

	// Requests the engine to deal the player and configures the GUI
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Disabling the deal and bet buttons
		this.toolBar.disableBetButton();
		this.toolBar.disableDealButton();

		
		Player temp = getCurrentPlayer();
		for (Player p : engine.getAllPlayers()) {
			if (temp.equals(p)) {

				// Clears the player display and change the status to state the player is being dealt.
				this.cardPanel.clearPlayerDisplay(temp.getPlayerId());
				this.statusBar.setStatus(String.format("Dealing cards to %s...", temp.getPlayerName()));
				
				// Creates a new thread to deal the player
				new Thread() {
					@Override
					public void run() {
						engine.dealPlayer(p, delay);
					}
				}.start();
			}
		}

	}

	// Returns the index of the player currently selected in the player combo box.
	public int getCurrentPlayerPosition() {
		return this.toolBar.getPlayerComboBox().getSelectedIndex();
	}

	// Returns a player from the engine that is currently selected in the player combo box.
	public Player getCurrentPlayer() {
		return (Player) this.engine.getAllPlayers().toArray()[getCurrentPlayerPosition()];
	}


}
