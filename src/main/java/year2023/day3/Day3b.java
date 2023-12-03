package year2023.day3;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class Day3b {

//	private static final String FILENAME = "src/main/resources/2023/day3InputSample.txt";
//	private static final String FILENAME = "src/main/resources/2023/day3InputSampleRon.txt";
	private static final String FILENAME = "src/main/resources/2023/day3Input.txt";
	
	private static Set<NumberCoordinate> numberCoordinates = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		List<String> lines = readLines();
		
		List<Long> partNumbers = new ArrayList<>();
		List<Long> notPartNumbers = new ArrayList<>();
		
		processLines(lines, partNumbers, notPartNumbers);
		
		Set<SymbolCoordinate> symbolCoordinates = numberCoordinates.stream()
				.flatMap(nc -> nc.getSymbolCoordinates().stream())
				.filter(sc -> sc.getSymbol().equals("*"))
				.collect(Collectors.toSet());
		
		Long result = 0L;
		for(SymbolCoordinate symbolCoordinate : symbolCoordinates) {
			Set<NumberCoordinate> numberCoordinatesBySymbolCoordinate = findNumberCoordinatesBySymbolCoordinate(numberCoordinates, symbolCoordinate);
			
			if(numberCoordinatesBySymbolCoordinate.size() == 2) {
				result += numberCoordinatesBySymbolCoordinate.stream()
						.map(NumberCoordinate::getNumber)
						.reduce(1L, (a, b) -> a * b);
			}
		}

		System.out.println(result);
		
		System.out.println("done");
	}
	
	private static Set<NumberCoordinate> findNumberCoordinatesBySymbolCoordinate(Set<NumberCoordinate> numberCoordinates, SymbolCoordinate symbolCoordinate) {
		return numberCoordinates.stream()
				.filter(nc -> nc.getSymbolCoordinates().contains(symbolCoordinate))
				.collect(Collectors.toSet());
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
			
			NumberCoordinate numberCoordinate = new NumberCoordinate(Long.valueOf(currentNumber), row, currentNumberStart, currentNumberEnd);
			numberCoordinates.add(numberCoordinate);
			
			if(isNonPartNumber(numberCoordinate, row, lines)) {
				notPartNumbers.add(Long.valueOf(currentNumber));
			} else {
				partNumbers.add(Long.valueOf(currentNumber));
			}
			currentNumber = "";
		}
		return currentNumber;
	}

	private static boolean isNonPartNumber(NumberCoordinate numberCoordinate, int row, List<String> lines) {
		int previousRow = row - 1;
		int nextRow = row + 1;
		
		boolean nonPartNumber = true;
		if(previousRow >= 0) {
			nonPartNumber &= isNonPartNumber(numberCoordinate, lines, previousRow);
		}
		
		nonPartNumber &= isNonPartNumber(numberCoordinate, lines, row);

		if(nextRow < lines.size()) {
			nonPartNumber &= isNonPartNumber(numberCoordinate, lines, nextRow);	
		}
		
		
		return nonPartNumber;
	}

	private static boolean isNonPartNumber(NumberCoordinate numberCoordinate, List<String> lines, int row) {
		boolean nonPartNumber = true;
		
		String line = lines.get(row);
		
		int start = numberCoordinate.getStart() - 1;
		if(start < 0) {
			start = 0;
		}
		int end = numberCoordinate.getEnd() + 1;
		if(end >= line.length()) {
			end = line.length();
		}
		
		for(int i = start; i < end; i++) {
			char character = line.charAt(i);
			if( ! Character.isDigit(character)
					&& '.' != character
					) {
				nonPartNumber = false;
				SymbolCoordinate symbolCoordinate = new SymbolCoordinate(String.valueOf(character), row, i);
				numberCoordinate.addSymbolCoordinate(symbolCoordinate);
			}
		}
		
		return nonPartNumber;
	}

	private static List<String> readLines() throws IOException {
		return IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
	}

}
