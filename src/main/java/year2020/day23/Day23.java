package year2020.day23;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day23 {

//	private static final String FILENAME = "src/main/resources/2020/day23InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day23Input.txt";

	private static final int MOVES = 100;
	private static final int NUMBER_OF_CUPS_TO_PICK_UP_PER_MOVE = 3;
	
	public static void main(String[] args) throws IOException {
		List<Cup> cups = readCups();
		
		cups.forEach(c -> System.out.println(ReflectionToStringBuilder.toString(c, ToStringStyle.MULTI_LINE_STYLE)));
		
		Cup currentCup = cups.get(0);
		for(int i = 0; i < MOVES; i++) {
			printMoveNumber(i);
			
			printCups(cups, currentCup);
			
			List<Cup> pickedUpCups = pickUpCups(currentCup, cups);

			printPickedUpCups(pickedUpCups);
			
			Cup destinationCup = selectDestinationCup(currentCup, cups);

			printDestinationCup(destinationCup);

			placePickedUpCups(destinationCup, pickedUpCups, cups);
			
			currentCup = selectCurrentCup(currentCup, cups);
			
			System.out.println();
		}

		System.out.println("-- final --");

		printCups(cups, currentCup);
		
		List<Cup> resultCups = selectResultCups(cups);
		
		resultCups.forEach(c -> System.out.print(c.getLabel()));
	}

	private static List<Cup> selectResultCups(List<Cup> cups) {
		Cup cup1 = cups.stream()
				.filter(c -> 1L == c.getLabel())
				.findFirst().orElse(null);
		
		int cup1Index = cups.indexOf(cup1);
		int nextIndex = cup1Index + 1;
		
		List<Cup> resultCups = new ArrayList<>();
		if(nextIndex < cups.size()) {
			resultCups.addAll(cups.subList(nextIndex, cups.size()));
		} 
		resultCups.addAll(cups.subList(0, cup1Index));

		return resultCups;
	}

	private static void printDestinationCup(Cup destinationCup) {
		System.out.println("destination: " + destinationCup.getLabel());
	}

	private static void printPickedUpCups(List<Cup> pickedUpCups) {
		System.out.println("pick up: " + pickedUpCups.stream()
				.map(c -> c.getLabel().toString())
				.collect(Collectors.joining(", ")));
	}

	private static void printCups(List<Cup> cups, Cup currentCup) {
		System.out.println("cups: " + cups.stream()
				.map(c -> c.equals(currentCup) ? "(" + c.getLabel().toString() + ")" : c.getLabel().toString())
				.collect(Collectors.joining(" ")));
	}

	private static void printMoveNumber(int i) {
		System.out.println("-- move " + (i + 1) + " --");
	}

	private static Cup selectCurrentCup(Cup currentCup, List<Cup> cups) {
		int nextCurrentCupIndex = cups.indexOf(currentCup) + 1;
		if(nextCurrentCupIndex >= cups.size()) {
			nextCurrentCupIndex = 0;
		}
		return cups.get(nextCurrentCupIndex);
	}

	private static void placePickedUpCups(Cup destinationCup, List<Cup> pickedUpCups, List<Cup> cups) {
		int nextDestinationCupIndex = cups.indexOf(destinationCup) + 1;
		if(nextDestinationCupIndex >= cups.size()) {
			nextDestinationCupIndex = 0;
		}
		cups.addAll(nextDestinationCupIndex, pickedUpCups);
	}

	private static Cup selectDestinationCup(Cup currentCup, List<Cup> cups) {
		Long currentLabel = currentCup.getLabel();

		List<Cup> sortedCups = cups.stream()
				.sorted(Comparator.comparing(Cup::getLabel).reversed())
				.collect(Collectors.toList());

		Cup destinationCup = sortedCups.stream()
				.filter(c -> c.getLabel() < currentCup.getLabel())
				.findFirst().orElse(null);

		if(destinationCup == null) {
			destinationCup = sortedCups.stream()
					.findFirst().orElse(null);
		}

		return destinationCup;
	}

	private static List<Cup> pickUpCups(Cup currentCup, List<Cup> cups) {
		int currentCupIndex = cups.indexOf(currentCup);
		
		List<Cup> pickedUpCups = new ArrayList<Cup>();
		
		int pickUpIndex = currentCupIndex + 1;
		for(int i = 0; i < NUMBER_OF_CUPS_TO_PICK_UP_PER_MOVE; i++) {
			if(pickUpIndex >= cups.size()) {
				pickUpIndex = 0;
			}

			pickedUpCups.add(cups.remove(pickUpIndex));
		}
		
		return pickedUpCups;
	}

	private static List<Cup> readCups() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		List<Cup> cups = new ArrayList<>();
		for(String line : lines) {
			cups.addAll(processLine(line));
		}
		
		return cups;
	}

	private static List<Cup> processLine(String line) {
		List<Cup> cups = new ArrayList<>();
		
		for(char character : line.toCharArray()) {
			cups.add(processCharacter(String.valueOf(character)));
		}
		
		return cups;
	}

	private static Cup processCharacter(String character) {
		Cup cup = new Cup();
		cup.setLabel(Long.parseLong(character));
		return cup;
	}

}
