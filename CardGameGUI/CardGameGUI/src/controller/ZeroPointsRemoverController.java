package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.CardPanel;
import view.SummaryPanel;
import view.ToolBar;

public class ZeroPointsRemoverController implements PropertyChangeListener {

	private GameEngine engine;
	private SummaryPanel sumPanel;
	private CardPanel cardPanel;
	private ToolBar toolBar;

	/*
	 * Listens for any property change in the status and checks if each player in the summary panel has zero points.
	 * If a player is found to have zero points, then the player is deleted from the engine, summary panel, card panel
	 * and the player combo box.
	 */
	public ZeroPointsRemoverController(GameEngine engine, SummaryPanel sumPanel, CardPanel cardPanel, ToolBar toolBar) {
		this.engine = engine;
		this.sumPanel = sumPanel;
		this.cardPanel = cardPanel;
		this.toolBar = toolBar;

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String status = evt.getNewValue().toString();

		String roundEndMessage = "Round Complete!";
		if (status.startsWith(roundEndMessage)) {
			for (int row = 0; row < this.sumPanel.getTableNumRows(); ++row) {
				int points = Integer.valueOf((String) this.sumPanel.getAllPlayersInfo().getValueAt(row,
						this.sumPanel.getPointsColumnIndex()));
				if (points <= 0) {
					Player zeroPointsPlayer = (Player) engine.getAllPlayers().toArray()[row];
					engine.removePlayer(zeroPointsPlayer);
					this.toolBar.getPlayerComboBox().removePlayer(row);
					this.cardPanel.deletePlayerDisplay(zeroPointsPlayer.getPlayerId());
					this.sumPanel.removePlayerInfo(row);
					JOptionPane.showMessageDialog(null,
							String.format("Player %s reached %d points and was removed from the game.",
									zeroPointsPlayer.getPlayerName(), zeroPointsPlayer.getPoints()));
				}
			}
		}
	}
}
