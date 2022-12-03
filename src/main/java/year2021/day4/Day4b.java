package year2021.day4;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day4b {

//	private static final String FILENAME = "src/main/resources/2021/day4InputSample.txt";
	private static final String FILENAME = "src/main/resources/2021/day4Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<String> lines = readLines();
		
		List<Long> numbers = new ArrayList<>();
		for(String number : StringUtils.split(lines.get(0), ",")) {
			numbers.add(Long.valueOf(number));
		}
		
		List<BingoBoard> bingoBoards = readBingoBoards(lines.subList(2, lines.size()));
		
		bingoBoards.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		List<BingoBoard> remainingBoards = new ArrayList<>(bingoBoards);
		
		Long lastCalledNumber = null;
		for(Long number : numbers) {
			lastCalledNumber = number;
			for(BingoBoard bingoBoard : remainingBoards) {
				bingoBoard.markNumber(number);
			}

			if( remainingBoards.size() == 1
					&& remainingBoards.iterator().next().isWinning()
					) {
				break;
			}

			remainingBoards = remainingBoards.stream()
					.filter(rb -> ! rb.isWinning())
					.collect(Collectors.toList());
		}
		
		Long unmarkedSum = remainingBoards.iterator().next().getUnmarkedSum();
		
		System.out.println(lastCalledNumber);
		System.out.println(unmarkedSum * lastCalledNumber);
	}

	private static List<BingoBoard> readBingoBoards(List<String> lines) {
		List<BingoBoard> bingoBoards = new ArrayList<>();
		
		BingoBoard bingoBoard = new BingoBoard();
		bingoBoards.add(bingoBoard);
		for(String line : lines) {
			if(StringUtils.isBlank(line)) {
				bingoBoard = new BingoBoard();
				bingoBoards.add(bingoBoard);
			} else {
				BingoRow bingoRow = new BingoRow();
				bingoBoard.addBingoRow(bingoRow);
				for(String number : StringUtils.split(line, " ")) {
					BingoNumber bingoNumber = new BingoNumber(Long.valueOf(number));
					bingoRow.addBingoNumber(bingoNumber);
				}
				
			}
		}
		
		return bingoBoards;
	}

	private static List<String> readLines() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.collect(Collectors.toList());
	}

}
