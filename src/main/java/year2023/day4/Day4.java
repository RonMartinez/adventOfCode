package year2023.day4;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day4 {

//	private static final String FILENAME = "src/main/resources/2023/day4InputSample.txt";
	private static final String FILENAME = "src/main/resources/2023/day4Input.txt";

	public static void main(String[] args) throws IOException {
		List<String> lines = readLines();
		
		List<Card> cards = lines.stream()
				.map(Day4::processLine)
				.collect(Collectors.toList());
		
		Long result = cards.stream()
				.map(Card::getPoints)
				.reduce(0L, Long::sum);
		
		System.out.println(result);
		
		System.out.println("done");
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
