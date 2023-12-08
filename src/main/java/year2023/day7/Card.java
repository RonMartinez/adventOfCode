package year2023.day7;

public class Card {
	
	public static final Card TWO = new Card("2", 1L);
	public static final Card THREE = new Card("3", 2L);
	public static final Card FOUR = new Card("4", 3L);
	public static final Card FIVE = new Card("5", 4L);
	public static final Card SIX = new Card("6", 5L);
	public static final Card SEVEN = new Card("7", 6L);
	public static final Card EIGHT = new Card("8", 7L);
	public static final Card NINE = new Card("9", 8L);
	public static final Card TEN = new Card("T", 9L);
	public static final Card JACK = new Card("JACK", 10L);
	public static final Card QUEEN = new Card("Q", 11L);
	public static final Card KING = new Card("K", 12L);
	public static final Card ACE = new Card("A", 13L);
	public static final Card JOKER = new Card("J", 0L);
	
	private String value;
	private Long rank;
	
	public Card(String value, Long rank) {
		this.value = value;
		this.rank = rank;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}
	
}