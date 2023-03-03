package view;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java
 * logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback {
	public static final Logger logger = Logger.getLogger(GameEngineCallbackImpl.class.getName());
	private boolean roundEnded = false;

	// utility method to set output level of logging handlers
	public static Logger setAllHandlers(Level level, Logger logger, boolean recursive) {
		// end recursion?
		if (logger != null) {
			logger.setLevel(level);
			for (Handler handler : logger.getHandlers())
				handler.setLevel(level);
			// recursion
			setAllHandlers(level, logger.getParent(), recursive);
		}
		return logger;
	}

	public GameEngineCallbackImpl() {
		// NOTE can also set the console to FINE in %JRE_HOME%\lib\logging.properties
		setAllHandlers(Level.FINE, logger, true);
	}

	// Logs the card dealt to the player.
	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		String message = String.format("Card Dealt to %s .. %s", player.getPlayerName(), card.toString());
		logger.log(Level.FINE, message, engine);
	}

	// Logs the final results of the player.
	@Override
	public void result(Player player, int result, GameEngine engine) {
		String message = String.format("%s, final result=%d", player.getPlayerName(), result);
		logger.log(Level.INFO, message, engine);
	}

	// Logs the card dealt to the player that resulted to a bust.
	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		String message = String.format("Card Dealt to %s .. %s ... YOU BUSTED!", player.getPlayerName(),
				card.toString());
		logger.log(Level.INFO, message, engine);

	}

	//Logs the card dealt to the house.
	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		String message = String.format("Card Dealt to House .. %s", card.toString());
		logger.log(Level.INFO, message, engine);

	}

	// Logs the card dealt to the house that resulted to a bust.
	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		String message = String.format("Card Dealt to House .. %s ... HOUSE BUSTED!", card.toString());
		logger.log(Level.INFO, message, engine);

	}

	//Logs the final house results and the all the final player results.
	@Override
	public void houseResult(int result, GameEngine engine) {
		if (roundEnded == false) {
			String message = String.format("House, final result=%d", result);
			logger.log(Level.INFO, message, engine);
			roundEnded = true;
			houseResult(result, engine);
		} else {
			ArrayList<Player> playerList= (ArrayList<Player>) engine.getAllPlayers();
			String playerResults = "";
			for (Player p : playerList) {
				playerResults += String.format("%s\n", p.toString());
			}
			String message = String.format("Final Player Results\n%s", playerResults);
			logger.log(Level.INFO, message, engine);
			
			// Resets to false for the next round.
			this.roundEnded = false;
		}

	}

}
