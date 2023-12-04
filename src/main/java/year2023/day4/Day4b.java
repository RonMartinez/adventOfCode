package year2023.day4;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day4b {

//	private static final String FILENAME = "src/main/resources/2023/day4InputSample.txt";
	private static final String FILENAME = "src/main/resources/2023/day4Input.txt";

	public static void main(String[] args) throws IOException {
		List<String> lines = readLines();
		
		List<Card> cards = lines.stream()
				.map(Day4b::processLine)
				.sorted(Card.CARD_NUMBER_COMPARATOR)
				.collect(Collectors.toList());
		
		CardStack cardStack = new CardStack(cards);
		
		Map<Long, Long> cardNumberCount = new HashMap<>();
		
		for(Card card : cards) {
			addCardNumber(cardNumberCount, card.getCardNumber(), 1L);
		}
		
		//there's an assumption the cards start at 1 and there are no gaps
		for(long cardNumber = 1; cardNumber <= cards.size(); cardNumber++) {
			Long count = cardNumberCount.get(cardNumber);
			
			Card card = cardStack.getCardByCardNumber(cardNumber);
			
			List<Long> matchingNumbers =card.getMatchingNumbers();
			
			if( ! matchingNumbers.isEmpty()) {
				for(long nextCardNumber = cardNumber + 1; nextCardNumber < cardNumber + 1 + matchingNumbers.size(); nextCardNumber++) {
					addCardNumber(cardNumberCount, nextCardNumber, count);
				}
			}
		}
		
		Long result = cardNumberCount.values().stream()
				.reduce(0L, Long::sum);
		
		System.out.println(result);
		
		System.out.println("done");
	}

	private static void addCardNumber(Map<Long, Long> cardNumberCount, Long cardNumber, Long count) {
		Long existingCount = cardNumberCount.getOrDefault(cardNumber, 0L);
		cardNumberCount.put(cardNumber, existingCount + (1 * count));
	}

	private static Card processLine(String line) {
		String[] lineSplit = line.split(":\\s+");
		Long cardNumber = Long.valueOf(lineSplit[0].replaceAll("Card\\s+",  ""));
		
		lineSplit = lineSplit[1].split("\\s+\\|\\s+");
		String[] winningNumberArray = lineSplit[0].split("\\s+");
		
		List<Long> winningNumbers = Arrays.stream(winningNumberArray)
				.map(Long::valueOf)
				.collect(Collectors.toList());
		
		String[] numberArray = lineSplit[1].split("\\s+");
		List<Long> numbers = Arrays.stream(numberArray)
				.map(Long::valueOf)
				.collect(Collectors.toList());
		
		return new Card(cardNumber, winningNumbers, numbers);
	}

	private static List<String> readLines() throws IOException {
		return IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
	}

}
;