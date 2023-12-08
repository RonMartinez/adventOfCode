package year2023.day7;

import java.util.Comparator;

public class HandBid {
	
	public static final Comparator<HandBid> HAND_COMPARATOR = new Comparator<HandBid>() {
		
		@Override
		public int compare(HandBid handBid1, HandBid handBid2) {
			Hand hand1 = handBid1.getHand();
			Hand hand2 = handBid2.getHand();
			
			return Hand.HAND_TYPE_CARD_RANK_COMPARATOR.compare(hand1, hand2);
		}
		
	};

	private Hand hand;
	private Long bid;
	
	public HandBid(Hand hand, Long bid) {
		this.hand = hand;
		this.bid = bid;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}
	
	
}