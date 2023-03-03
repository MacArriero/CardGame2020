package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.interfaces.GameEngine;
import view.CardPanel;
import view.StatusBar;

public class DealHouseController implements PropertyChangeListener {

	private GameEngine engine;
	private int delay;
	private StatusBar statusBar;
	private CardPanel cardPanel;

	public DealHouseController(GameEngine engine, int delay, StatusBar statusBar, CardPanel cardPanel) {
		this.engine = engine;
		this.delay = delay;
		this.statusBar = statusBar;
		this.cardPanel = cardPanel;
	}

	/*
	 * Requests the engine to deal the House and configures the GUI
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String status = evt.getNewValue().toString();
		String dealHousePrep = "Preparing to deal House...";
		
		// Deals the house if the status matches the dealHousePrep string.
		if (dealHousePrep.equals(status)) {
			
			// Clears the House's display and updates the text on the status bar. 
			this.cardPanel.clearHouseDisplay();			
			this.statusBar.setStatus("Dealing cards to House...");
			
			// Requests the engine to deal cards to the House on a new thread.
			new Thread() {
				@Override
				public void run() {
					engine.dealHouse(delay);
				}
			}.start();

		}

	}

}
