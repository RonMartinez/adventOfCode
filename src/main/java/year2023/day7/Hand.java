package year2023.day7;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Hand {
	
	public static final Comparator<Hand> HAND_TYPE_CARD_RANK_COMPARATOR = new Comparator<Hand>() {
		
		@Override
		public int compare(Hand hand1, Hand hand2) {
			Long handTypeRank1 = hand1.getHandType().getRank();
			Long handTypeRank2 = hand2.getHandType().getRank();
			
			if(handTypeRank1 < handTypeRank2) {
				return -1;
			} else if(handTypeRank1 > handTypeRank2) {
				return 1;
			} else {
				for(int i = 0; i < hand1.getCards().size(); i++) {
					Card card1 = hand1.getCards().get(i);
					Card card2 = hand2.getCards().get(i);
					if(card1.getRank() < card2.getRank()) {
						return -1;
					} else if(card1.getRank() > card2.getRank()) {
						return 1;
					}
				}
			}
			
			return 0;
		}
		
	};

	private List<Card> cards;
	private HandType handType;
	
	public Hand(List<Card> cards) {
		this.cards = cards;
		this.handType = calculateHandType();
	}
	
	public String getCardsString() {
		StringBuilder sb = new StringBuilder();
		for(Card card : cards) {
			sb.append(card.getValue());
		}
		sb.append(" " + handType.getName());
		return sb.toString();
	}

	private HandType calculateHandType() {
		Map<Card, Long> cardCountMap = cards.stream()
				.collect(Collectors.groupingBy(c -> c, Collectors.counting()));
		
		Long fiveOfAKindCount = getOfAKindCount(cardCountMap, 5L);
		Long fourOfAKindCount = getOfAKindCount(cardCountMap, 4L);
		Long threeOfAKindCount = getOfAKindCount(cardCountMap, 3L);
		Long twoOfAKindCount = getOfAKindCount(cardCountMap, 2L);
		
		Long jokerCount = cardCountMap.getOrDefault(Card.JOKER, 0L);
		
		if(fiveOfAKindCount == 1
				|| (
						fourOfAKindCount == 1
						&& jokerCount == 1
						)
				|| (
						threeOfAKindCount == 1
						&& jokerCount == 2
						)
				|| (
						twoOfAKindCount == 1
						&& jokerCount == 3
						)
				|| (
						jokerCount == 4
						)
				|| (
						jokerCount == 5
						)
				) {
			handType = HandType.FIVE_OF_A_KIND;
		} else if(fourOfAKindCount == 1
				|| (
						threeOfAKindCount == 1
						&& jokerCount == 1
						)
				|| (
						twoOfAKindCount == 1
						&& jokerCount == 2
						)
				|| (
						jokerCount == 3
						)
				) {
			handType = HandType.FOUR_OF_A_KIND;
		} else if((
					threeOfAKindCount == 1
					&& twoOfAKindCount == 1
					)
				|| (
						threeOfAKindCount == 1
						&& jokerCount == 1
						)
				|| (
						twoOfAKindCount == 1
						&& jokerCount == 2
						)
				|| (
						twoOfAKindCount == 2
						&& jokerCount == 1
						)
				) {
			handType = HandType.FULL_HOUSE;
		} else if(threeOfAKindCount == 1
				|| (
						twoOfAKindCount == 1
						&& jokerCount == 1
						)
				|| (
						jokerCount == 2
						)
				) {
			handType = HandType.THREE_OF_A_KIND;
		} else if(twoOfAKindCount == 2
				|| (
						twoOfAKindCount == 1
						&& jokerCount == 1
						)
				) {
			handType = HandType.TWO_PAIR;
		} else if(twoOfAKindCount == 1
				|| (
						jokerCount == 1
						)
				
				) {
			handType = HandType.ONE_PAIR;
		} else {
			handType = HandType.HIGH_CARD;
		}
			
		return handType;
	}

	private Long getOfAKindCount(Map<Card, Long> cardCountMap, Long count) {
		Long ofAKindCount = 0L;
		for(Card card : cardCountMap.keySet()) {
			if( ! Card.JOKER.equals(card)
					&& count.equals(cardCountMap.get(card))
					) {
				ofAKindCount++;	
			}
		}
		return ofAKindCount;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public HandType getHandType() {
		return handType;
	}

	public void setHandType(HandType handType) {
		this.handType = handType;
	}
	
}