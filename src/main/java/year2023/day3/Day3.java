package year2023.day3;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class Day3 {

//	private static final String FILENAME = "src/main/resources/2023/day3InputSample.txt";
	private static final String FILENAME = "src/main/resources/2023/day3Input.txt";

	public static void main(String[] args) throws IOException {
		List<String> lines = readLines();
		
		List<Long> partNumbers = new ArrayList<>();
		List<Long> notPartNumbers = new ArrayList<>();
		
		processLines(lines, partNumbers, notPartNumbers);
		
		Long result = partNumbers.stream()
				.reduce(0L, Long::sum);
		
		System.out.println(result);
		
		System.out.println("done");
	}

	private static void processLines(List<String> lines, List<Long> partNumbers, List<Long> notPartNumbers) {
		for(int row = 0; row < lines.size(); row++) {
			processLine(lines.get(row), partNumbers, notPartNumbers, row, lines);
		}
	}

	private static void processLine(String line, List<Long> partNumbers, List<Long> notPartNumbers, int row, List<String> lines) {
		int currentNumberStart = 0;
		String currentNumber = "";
		
		int column = 0;
		for(column = 0; column < line.length(); column++) {
			char character = line.charAt(column);
			if(Character.isDigit(character)) {
				if(StringUtils.isBlank(currentNumber)) {
					currentNumberStart = column;
				}
				currentNumber += line.charAt(column);
			} else {
				currentNumber = processEndOfCurrentNumber(partNumbers, notPartNumbers, row, currentNumberStart, currentNumber, column, lines);
			}
		}
		currentNumber = processEndOfCurrentNumber(partNumbers, notPartNumbers, row, currentNumberStart, currentNumber, column, lines);
	}

	private static String processEndOfCurrentNumber(List<Long> partNumbers, List<Long> notPartNumbers, int row, int currentNumberStart, String currentNumber, int column, List<String> lines) {
		if( ! StringUtils.isBlank(currentNumber)) {
			int currentNumberEnd = column;
			if(isNonPartNumber(currentNumberStart, currentNumberEnd, row, lines)) {
				notPartNumbers.add(Long.valueOf(currentNumber));
			} else {
				partNumbers.add(Long.valueOf(currentNumber));
			}
			currentNumber = "";
		}
		return currentNumber;
	}

	private static boolean isNonPartNumber(int currentNumberStart, int currentNumberEnd, int row, List<String> lines) {
		int previousRow = row - 1;
		int nextRow = row + 1;
		
		int start = currentNumberStart - 1;
		if(start < 0) {
			start = 0;
		}
		int end = currentNumberEnd + 1;
		
		boolean nonPartNumber = true;
		if(previousRow >= 0) {
			String line = lines.get(previousRow);
			nonPartNumber &= isNonPartNumber(start, end, line);
		}
		
		nonPartNumber &= isNonPartNumber(start, end, lines.get(row));

		if(nextRow < lines.size()) {
			nonPartNumber &= isNonPartNumber(start, end, lines.get(nextRow));	
		}
		
		
		return nonPartNumber;
	}

	private static boolean isNonPartNumber(int start, int end, String line) {
		boolean nonPartNumber = true;
		
		if(end >= line.length()) {
			end = line.length();
		}
		
		for(int i = start; i < end; i++) {
			char character = line.charAt(i);
			if( ! Character.isDigit(character)
					&& '.' != character
					) {
				nonPartNumber = false;
			}
		}
		
		return nonPartNumber;
	}

	private static List<String> readLines() throws IOException {
		return IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
	}

}
