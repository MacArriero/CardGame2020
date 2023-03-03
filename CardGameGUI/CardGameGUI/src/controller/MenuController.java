package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.SummaryPanel;
import view.ToolBar;
import view.CardPanel;

public class MenuController implements ActionListener {
	private GameEngine engine;
	private SummaryPanel summary;
	private ToolBar toolBar;
	private CardPanel cardPanel;

	public MenuController(GameEngine engine, SummaryPanel summary, ToolBar toolBar, CardPanel cardPanel) {
		this.engine = engine;
		this.summary = summary;
		this.toolBar = toolBar;
		this.cardPanel = cardPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem menuItem = (JMenuItem) e.getSource();
		if (menuItem.getText() == "Add Player") {
			addPlayer(displayNewPlayerInputDialog());
		} else if (menuItem.getText() == "Exit") {
			System.exit(0);
		}

	}

	/*
	 *  Adds a player to a game engine, only if the player passed does not have a
	 *  null id and name and points is not -1.
	 */
	public void addPlayer(Player player) {
		if (player.getPlayerId() != null && player.getPlayerName() != null && player.getPoints() > -1) {
			
			// Deletes existing player information from the summary panel with a matching id as the new player.
			for (int i = 0; i < this.engine.getAllPlayers().size(); ++i) {
				if (player.equals(this.engine.getAllPlayers().toArray()[i])) {
					summary.removePlayerInfo(i);

				}
			}
			
			// Adds the player to the game engine.
			this.engine.addPlayer(player);
			
			String[] newPlayerInfo = { player.getPlayerName(), String.valueOf(player.getPoints()),
					String.valueOf(player.getBet()), String.valueOf(player.getResult()),
					String.valueOf(player.getResult())};

			// Adds the new player information to the summary panel.
			for (int i = 0; i < this.engine.getAllPlayers().size(); ++i) {
				if (player.equals(this.engine.getAllPlayers().toArray()[i])) {
					summary.addPlayerInfo(i, newPlayerInfo);
				}
			}

			// Creates a card display for the new player
			this.cardPanel.addPlayerDisplay(player.getPlayerId());
			
			updateComboBox();
		}
	}

	/*
	 *  Deletes all the names on the combobox and then repopulate the combobox with player names
	 *  in same order the players are in the game engine.
	 */
	public void updateComboBox() {
		this.toolBar.getPlayerComboBox().deleteAllNames();
		for (Player p : engine.getAllPlayers()) {
			this.toolBar.getPlayerComboBox().addPlayer(p.getPlayerName());
		}
		
		// Adds the pseudo player "House" to the end of the combobox
		this.toolBar.getPlayerComboBox().addPlayer("House");
	}

	
	/*
	 * Creates a simple player object based on the values passed in by the user.
	 */
	public Player displayNewPlayerInputDialog() {

		String id = requestIdInput();
		String playerName = null;
		int initialBalance = -1;

		if (id != null) {
			playerName = requestPlayerNameInput();
		}

		if (playerName != null) {
			initialBalance = requestInitialBalance();
		}

		return new SimplePlayer(id, playerName, initialBalance);
	}

	// Opens a JOptionPane where the user can enter a player ID.
	public String requestIdInput() {

		String id;
		try {
			id = JOptionPane.showInputDialog(null, "Enter the player ID: ");
			while (!id.matches("[a-zA-Z0-9]+")) {
				id = JOptionPane.showInputDialog(null,
						"Enter a valid player ID. Must contain letters and/or numbers only.");
			}
		} catch (NullPointerException e) {
			id = null;
		}

		return id;
	}

	// Opens a JOptionPane where the user can enter a player name.
	public String requestPlayerNameInput() {

		String playerName;
		try {
			playerName = JOptionPane.showInputDialog(null, "Enter the player name: ");

			while (!playerName.matches("^[a-zA-Z]+(\\s+[a-zA-Z]+)*[a-zA-Z]+$") || playerName.matches("House")) {
				if(playerName.matches("House")) {
					playerName = JOptionPane.showInputDialog(null, "House is a reserved name. Please enter another name.");
				} else {
					playerName = JOptionPane.showInputDialog(null, "Enter a valid player name.");
				}
				
			}

		} catch (NullPointerException e) {
			playerName = null;
		}

		return playerName;
	}

	// Opens a JOptionPane where the user can enter a player name.
	public int requestInitialBalance() {
		int initialBalance;
		String initialBalanceString = null;

		try {
			initialBalanceString = JOptionPane.showInputDialog(null, "Enter initial balance: ");

			while (!initialBalanceString.matches("[0-9]+") || Integer.parseInt(initialBalanceString) <= 0) {
				initialBalanceString = JOptionPane.showInputDialog(null,
						"Enter a valid initial balance. Must be an integer greater or equal to 0.");
			}

			initialBalance = Integer.parseInt(initialBalanceString);

		} catch (NullPointerException e) {
			initialBalance = -1;
		}

		return initialBalance;
	}
}
