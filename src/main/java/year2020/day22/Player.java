package year2020.day22;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class Player {
	
	private Long id;
	private List<Long> cards;
	
	public Long getNextCard() {
		Long nextCard = null;
		if(CollectionUtils.isNotEmpty(cards)) {
			nextCard = cards.remove(0);
		}
		return nextCard;
	}

	public void addCard(Long card) {
		getCards().add(card);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Long> getCards() {
		if(cards == null) {
			cards = new ArrayList<>();
		}
		return cards;
	}

	public void setCards(List<Long> cards) {
		this.cards = cards;
	}

}
