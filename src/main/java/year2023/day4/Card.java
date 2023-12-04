package year2023.day4;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Card {
	
	public static final Comparator<Card> CARD_NUMBER_COMPARATOR = Comparator.comparing(Card::getCardNumber);

	private Long cardNumber;
	private List<Long> winningNumbers;
	private List<Long> numbers;

	public Card(Long cardNumber, List<Long> winningNumbers, List<Long> numbers) {
		this.cardNumber = cardNumber;
		this.winningNumbers = winningNumbers;
		this.numbers = numbers;
	}

	public Long getPoints() {
		Long points = 0L;
		List<Long> matchingNumbers = getMatchingNumbers();
		
		if( ! matchingNumbers.isEmpty()) {
			points = (long) Math.pow(2D, Double.valueOf(matchingNumbers.size() - 1));
		}
		
		return points;
	}

	public List<Long> getMatchingNumbers() {
		return numbers.stream()
				.filter(n -> winningNumbers.contains(n))
				.collect(Collectors.toList());
	}

	public List<Long> getWinningNumbers() {
		return winningNumbers;
	}

	public void setWinningNumbers(List<Long> winningNumbers) {
		this.winningNumbers = winningNumbers;
	}

	public List<Long> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Long> numbers) {
		this.numbers = numbers;
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

}
