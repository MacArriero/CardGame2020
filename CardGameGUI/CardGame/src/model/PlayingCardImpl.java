package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard {
	private Suit suit;
	private Value value;
	
	/*
	 * Constructs an PlayingCardImpl object and initialises
	 * the suit and value, based on the values passed in the parameter.
	 * The score is also initialised based on the value of the card,
	 * where Ace=11, J, Q, K=10 and the rest are just their face value.
	 */
	public PlayingCardImpl (Suit suit, Value value) {
		this.suit = suit;
		this.value = value;
	}

	/*
	 * Returns this PlayingCardImpl's suit
	 */
	@Override
	public Suit getSuit() {
		return this.suit;
	}

	/*
	 * Returns this PlayingCardImpl's value
	 */
	@Override
	public Value getValue() {
		return this.value;
	}

	/*
	 * Returns this PlayingCardImpl's score
	 */
	@Override
	public int getScore() {
		int score = 0;
		
		if (this.value == Value.ACE) {
			score = 11;
		} else if (this.value == Value.EIGHT) {
			score = 8;
		} else if (this.value == Value.NINE) {
			score = 9;
		} else if (this.value == Value.TEN) {
			score = 10;
		} else if (this.value == Value.JACK) {
			score = 10;
		} else if (this.value == Value.QUEEN) {
			score = 10;
		} else if (this.value == Value.KING) {
			score = 10;
		}
		
		return score;
	}

	/*
	 * Returns a formatted String of this PlayingCardImp's variable values.
	 */
	@Override
	public String toString() {
		String suitString = this.suit.toString().substring(0,1).toUpperCase() + this.suit.toString().substring(1).toLowerCase();
		String valueString = this.value.toString().substring(0,1).toUpperCase() + this.value.toString().substring(1).toLowerCase();
		return String.format("Suit: %s, Value: %s, Score: %d", suitString, valueString, this.getScore());
	}

	/*
	 * Returns true if both the suit and value of this PlayingCardImpl and the other 
	 * PlayingCardImpl passed in the parameter are equal. Otherwise returns false.
	 */
	public boolean equals(PlayingCard card) {
		return (this.suit == card.getSuit() && this.value == card.getValue());
	}

	/*
	 * Returns true if both the suit and value of this PlayingCardImpl and the other 
	 * PlayingCardImpl passed in the parameter are equal. Otherwise returns false.
	 */
	@Override
	public boolean equals(Object card) {
		return (this.suit == ((PlayingCard) card).getSuit() && this.value == ((PlayingCard) card).getValue());
	}

	/*
	 * Returns an integer value based on the sum of this PlayingCardImpl suit's hash code
	 * and this PlayingCardImpl value's hash code.
	 */
	@Override
	public int hashCode() {
		return this.suit.hashCode() + this.value.hashCode();
	}

}
