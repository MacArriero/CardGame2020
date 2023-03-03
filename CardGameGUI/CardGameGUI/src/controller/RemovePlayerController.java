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
import view.CardPanel;

public class RemovePlayerController implements ActionListener {

	private GameEngine engine;
	private ToolBar toolBar;
	private SummaryPanel summary;
	private CardPanel cardPanel;

	public RemovePlayerController(GameEngine engine, ToolBar toolBar, SummaryPanel summary, CardPanel cardPanel) {
		this.engine = engine;
		this.toolBar = toolBar;
		this.summary = summary;
		this.cardPanel = cardPanel;
	}

	/*
	 * Since the GameEngine only removes players by passing in a Player, this method
	 * creates a temporary Player and requests the engine to remove the player if it
	 * has the same ID as the temporary Player.
	 */
	public void removePlayer() {
		Player temp = (SimplePlayer) engine.getAllPlayers().toArray()[getCurrentPlayerPosition()];
		for (int i = 0; i < engine.getAllPlayers().size(); ++i) {
			if (temp.equals(engine.getAllPlayers().toArray()[i])) {
				int choice = confirmRemovePlayer(temp);
				if (choice == JOptionPane.OK_OPTION) {
					this.engine.removePlayer(temp);
					this.summary.removePlayerInfo(i);
					updateComboBox();
					this.cardPanel.deletePlayerDisplay(temp.getPlayerId());
				}
			}
		}
	}

	/*
	 * Deletes all the player names in the player combo box and repopulate it with
	 * all the player names currently in the engine.
	 */
	public void updateComboBox() {
		this.toolBar.getPlayerComboBox().deleteAllNames();
		for (Player p : engine.getAllPlayers()) {
			this.toolBar.getPlayerComboBox().addPlayer(p.getPlayerName());
		}
		this.toolBar.getPlayerComboBox().addPlayer("House");
	}

	// Returns the currently selected player in the player combo box
	public String getCurrentPlayerName() {
		return (String) this.toolBar.getPlayerComboBox().getSelectedItem();
	}

	// Returns the index of the player on the player combo box that is currently
	// selected
	public int getCurrentPlayerPosition() {
		return this.toolBar.getPlayerComboBox().getSelectedIndex();
	}

	// Opens up a JOptionPane to confirm if the user would like to remove the player.
	public int confirmRemovePlayer(Player player) {
		int choice = JOptionPane.showConfirmDialog(null,
				String.format("Are you sure you want to delete player \"%s (ID: %s)\".",
						player.getPlayerName(), player.getPlayerId()),
						"Remove Player", JOptionPane.YES_NO_OPTION);

		return choice;
	}

	// If triggered and the button text matches "Remove Player", then it calls the removePlayer() function
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.getText() == "Remove Player") {
			removePlayer();
		}
	}
}
