package model;

import model.interfaces.Player;

public class SimplePlayer implements Player {
	private String playerId;
	private String name;
	private int points;
	private int bet;
	private int result;

	/*
	 * Constructs a Simple player object and initialises the ID, player name and the player's
	 * initials points based on the values passed in the parameter. Bet is initialised to 0.
	 */
	public SimplePlayer(String id, String playerName, int initialPoints) {
		this.playerId = id;
		this.name = playerName;
		this.points = initialPoints;
		this.bet = 0;
	}

	/*
	 * Returns the Player's name
	 */
	public String getPlayerName() {
		return this.name;
	}

	/*
	 * Replaces the player's name with the name passed in the parameter
	 */
	public void setPlayerName(String playerName) {
		this.name = playerName;
	}

	/*
	 * Returns the Player's points
	 */
	public int getPoints() {
		return this.points;
	}

	/*
	 * Adds the integer value passed in the parameter to the Player's current points. 
	 * If the value passed in the parameter is a negative integer, 
	 * it will subtract this value from the player's current points.
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/*
	 * Returns the Player's ID
	 */
	public String getPlayerId() {
		return this.playerId;
	}

	/*
	 * Returns false if the bet passed in the parameter is less than 0,
	 * or if the player's current points is less than the bet. 
	 * If the player's current points is greater or equal to bet
	 * the bet is subtracted from the player's points, the player's
	 * bet is assigned with the value passed in the parameter and returns true.
	 */
	public boolean setBet(int bet) {
		boolean canBet = false;
		if (bet >= 1 && bet<= this.points) {
			canBet = true;
			this.bet = bet;
		} 
		return canBet;
	}

	/*
	 * Returns the player's current bet.
	 */
	public int getBet() {
		return this.bet;
	}

	/*
	 * Sets the player's bet to 0.
	 */
	public void resetBet() {
		this.bet = 0;
	}

	/*
	 * Returns the Player's current result.
	 */
	public int getResult() {
		return this.result;
	}

	/*
	 * Adds the integer value passed in the parameter to the Player's current result. 
	 * If the value passed in the parameter is a negative integer, 
	 * it will subtract this value from the player's current result.
	 */
	public void setResult(int result) {
		this.result = result;
	}

	/*
	 * Returns true if this player's ID is equal to the other
	 * player's ID. Otherwise returns false.
	 */
	public boolean equals(Player player) {
		return this.playerId.equals(player.getPlayerId());
	}

	/*
	 * Returns true if this player's ID is equal to the other
	 * player's ID. Otherwise returns false.
	 */
	@Override
	public boolean equals(Object player) {
		return this.playerId.equals(((Player) player).getPlayerId());
	}

	/*
	 * Returns an integer based on the hash code of this player's ID.
	 */
	@Override
	public int hashCode() {
		return this.playerId.hashCode();
	}

	/*
	 * Returns 0 if both this player's ID and the other player's ID are lexicographically equal.
	 * Returns a positive integer if this player's ID is lexicographically greater than the other player's ID.
	 * Returns a negative integer if this player's ID is lexicographically lesser than the other player's ID.
	 */
	public int compareTo(Player player) {
		return this.playerId.compareTo(player.getPlayerId());
	}

	/*
	 * Returns a formatted String of the Player's ID, name, bet, points and result.
	 */
	@Override
	public String toString() {
		return String.format("Player: id=%s, name=%s, bet=%d, points=%d, RESULT .. %d", playerId, name, bet, points,
				result);
	}
}
