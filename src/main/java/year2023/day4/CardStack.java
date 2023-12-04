package year2023.day4;

import java.util.List;

public class CardStack {
	
	private List<Card> cards;

	public CardStack(List<Card> cards) {
		this.cards = cards;
	}
	
	public Card getCardByCardNumber(Long cardNumber) {
		return getCards().stream()
				.filter(c -> c.getCardNumber().equals(cardNumber))
				.findFirst().orElse(null);
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
}
