package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeMap;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {
	private TreeMap<String, Player> players;
	private Deque<PlayingCard> deck;
	private Collection<GameEngineCallback> callBacks;

	/*
	 * Constructs a GameEngineImpl object
	 */
	public GameEngineImpl() {
		this.callBacks = new LinkedHashSet<>();
		this.players = new TreeMap<String, Player>();
		this.deck = getShuffledHalfDeck();
	}

	public void dealPlayer(Player player, int delay) throws IllegalArgumentException {

		int pointsCounter = 0;
		PlayingCard cardDrawn = null;

		ensureCanDeal();

		/*
		 * Deals the player cards and sums each card's score values until the player
		 * busts. The player's final result is updated with the sum of the score values
		 * before the bust. Information about each card drawn are recorded on all the
		 * call backs.
		 */
		while (pointsCounter < GameEngine.BUST_LEVEL) {
			try {
				if (delay > 0 && delay < 1000) {
					Thread.sleep(delay);
				}
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}

			cardDrawn = this.deck.pop();
			pointsCounter += cardDrawn.getScore();

			if (pointsCounter < GameEngine.BUST_LEVEL) {
				for (GameEngineCallback cb : this.callBacks) {
					cb.nextCard(player, cardDrawn, this);
				}
			}
		}

		for (GameEngineCallback cb : this.callBacks) {
			cb.bustCard(player, cardDrawn, this);
			player.setResult(pointsCounter - cardDrawn.getScore());
			cb.result(player, player.getResult(), this);
		}
	}

	public void dealHouse(int delay) throws IllegalArgumentException {

		int pointsCounter = 0;
		PlayingCard cardDrawn = null;

		ensureCanDeal();

		/*
		 * Deals the house cards and sums each card's score values until the house
		 * busts. The house's final result is updated with the sum of the score values
		 * before the bust. Information about each card drawn are recorded on all the
		 * call backs.
		 */
		while (pointsCounter < GameEngine.BUST_LEVEL) {
			try {
				if (delay > 0) {
					Thread.sleep(delay);
				}
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}

			cardDrawn = this.deck.pop();

			pointsCounter += cardDrawn.getScore();

			if (pointsCounter < GameEngine.BUST_LEVEL) {
				for (GameEngineCallback cb : this.callBacks) {
					cb.nextHouseCard(cardDrawn, this);
				}
			}
		}

		// Iterates each player and calls the applyWinLoss function
		// to update each player's points.
		for (Player p : this.players.values()) {
			applyWinLoss(p, pointsCounter - cardDrawn.getScore());
		}

		for (GameEngineCallback cb : this.callBacks) {
			cb.houseBustCard(cardDrawn, this);
			cb.houseResult(pointsCounter - cardDrawn.getScore(), this);
		}

		// Iterates each player and resets their bet to 0.
		for (Player p : this.players.values()) {
			p.resetBet();
		}
	}

	/*
	 * Updates player points by calling setPoints method from the Player class. If
	 * the player results and house results are equal, the bet is returned to the
	 * player's points. However if the player's points is greater, the player's
	 * point is added with double the amount of the player's bet value.
	 */
	public void applyWinLoss(Player player, int houseResult) {
		if (player.getResult() == houseResult) {
			// Does nothing, since its a draw, the player will not gain or lose points
		} else if (player.getResult() > houseResult) {
			player.setPoints(player.getPoints() + player.getBet());
		} else if (player.getResult() < houseResult) {
			player.setPoints(player.getPoints() - player.getBet());
		}
	}

	/*
	 * Add's a new player to the array list of players. If an existing player has
	 * the same Id as the new player, the new player replaces the existing one in
	 * the array list.
	 */
	public void addPlayer(Player player) {
		players.put(player.getPlayerId(), player);
	}

	// Returns a player with an equal id from the array list of players.
	public Player getPlayer(String id) {
		return players.get(id);
	}

	/*
	 * Removes a player with a matching player ID from the array list of players.
	 * Returns true if the player was removed from the list or returns false no
	 * player was found with a matching ID.
	 */
	public boolean removePlayer(Player player) {
		boolean playerRemoved = false;
		if (player != null) {
			playerRemoved = players.remove(player.getPlayerId(), player);
		}
		return playerRemoved;
	}

	/*
	 * Returns true if the player's bet was updated to the bet value passed in the
	 * parameter.
	 */
	public boolean placeBet(Player player, int bet) {
		return player.setBet(bet);
	}

	// Adds a gameEngineCallback to the arraylist of call backs.
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		callBacks.add(gameEngineCallback);
	}

	// Returns true if the gameEngineCallback was removed from the array list of
	// call backs.
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		return callBacks.remove(gameEngineCallback);
	}

	// Returns a collection of a shallow copy of the array list of players.
	public Collection<Player> getAllPlayers() {
		ArrayList<Player> copyOfPlayerList = new ArrayList<Player>(players.values());
		return copyOfPlayerList;
	}

	// Returns a new Deque of shuffled cards
	public Deque<PlayingCard> getShuffledHalfDeck() {
		LinkedList<PlayingCard> newHalfDeck = new LinkedList<PlayingCard>();
		for (int i = 0; i < PlayingCard.Suit.values().length; ++i) {
			for (int j = 0; j < PlayingCard.Value.values().length; ++j) {
				newHalfDeck.add(new PlayingCardImpl(PlayingCard.Suit.values()[i], PlayingCard.Value.values()[j]));
			}
		}
		Collections.shuffle(newHalfDeck);
		return newHalfDeck;
	}

	/*
	 * Adds a new deck of shuffled cards to the existing deck, 
	 * there is enough cards to deal the player.
	 */
	private void ensureCanDeal() {
		if (deck.size() < 6) {
			this.deck.addAll(getShuffledHalfDeck());
		}
	}

}
