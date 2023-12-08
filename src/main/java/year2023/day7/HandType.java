package year2023.day7;

public class HandType {

	public static final HandType HIGH_CARD = new HandType("High card", 1L);
	public static final HandType ONE_PAIR = new HandType("One pair", 2L);
	public static final HandType TWO_PAIR = new HandType("Two pair", 3L);
	public static final HandType THREE_OF_A_KIND = new HandType("Three of a kind", 4L);
	public static final HandType FULL_HOUSE = new HandType("Full house", 5L);
	public static final HandType FOUR_OF_A_KIND = new HandType("Four of a kind", 6L);
	public static final HandType FIVE_OF_A_KIND = new HandType("Five of a kind", 7L);
	
	private String name;
	private Long rank;
	
	public HandType(String name, Long rank) {
		this.name = name;
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}
	
}