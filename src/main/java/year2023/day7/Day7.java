package year2023.day7;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day7 {

//	private static final String FILENAME = "src/main/resources/2023/day7InputSample.txt";
	private static final String FILENAME = "src/main/resources/2023/day7Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<String> lines = readLines();
		
		List<HandBid> handBids = lines.stream()
				.map(Day7::processLine)
				.collect(Collectors.toList());
		
		List<HandBid> sortedHandBids = handBids.stream()
				.sorted(HandBid.HAND_COMPARATOR)
				.collect(Collectors.toList());
		
		sortedHandBids.forEach(shb -> System.out.println(shb.getHand().getCardsString()));

		Long totalWinnings = 0L;
		for(HandBid handBid : sortedHandBids) {
			long rank = sortedHandBids.indexOf(handBid) + 1;
			totalWinnings += handBid.getBid() * rank;
		}
		
		System.out.println(totalWinnings);
		
		System.out.println("done");
	}

	private static HandBid processLine(String line) {
		String[] lineSplit = line.split("\\s+");
		
		List<Card> cards = new ArrayList<>();
		for(char c : lineSplit[0].toCharArray()) {
			String value = String.valueOf(c);
			cards.add(findCard(value));
		}
		
		Hand hand = new Hand(cards);
		
		return new HandBid(hand, Long.valueOf(lineSplit[1]));
	}

	private static Card findCard(String value) {
		Card card = Card.ACE;
		if(Card.TWO.getValue().equals(value)) {
			card = Card.TWO;
		} else if(Card.THREE.getValue().equals(value)) {
			card = Card.THREE;
		} else if(Card.FOUR.getValue().equals(value)) {
			card = Card.FOUR;
		} else if(Card.FIVE.getValue().equals(value)) {
			card = Card.FIVE;
		} else if(Card.SIX.getValue().equals(value)) {
			card = Card.SIX;
		} else if(Card.SEVEN.getValue().equals(value)) {
			card = Card.SEVEN;
		} else if(Card.EIGHT.getValue().equals(value)) {
			card = Card.EIGHT;
		} else if(Card.NINE.getValue().equals(value)) {
			card = Card.NINE;
		} else if(Card.TEN.getValue().equals(value)) {
			card = Card.TEN;
		} else if(Card.JACK.getValue().equals(value)) {
			card = Card.JACK;
		} else if(Card.QUEEN.getValue().equals(value)) {
			card = Card.QUEEN;
		} else if(Card.KING.getValue().equals(value)) {
			card = Card.KING;
		}
		return card;
	}

	private static List<String> readLines() throws IOException {
		return IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
	}

}
