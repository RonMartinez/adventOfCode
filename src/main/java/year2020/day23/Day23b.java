package year2020.day23;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.StopWatch;

public class Day23b {

//	private static final String FILENAME = "src/main/resources/2020/day23InputSample.txt";
//	private static final String FILENAME = "src/main/resources/2020/day23InputIllia.txt";
	private static final String FILENAME = "src/main/resources/2020/day23Input.txt";

	private static final long NUMBER_OF_CUPS = 1_000_000L;
	private static final long MOVES = 10_000_000L;
	private static final int NUMBER_OF_CUPS_TO_PICK_UP_PER_MOVE = 3;
	
	private static Map<Long, Cup> CUP_MAP = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		StopWatch stopWatch = StopWatch.createStarted();
		
		//TODO don't really need the full list of cups anymore, just need to keep track of the first currentCup and the lastCup when filling up to one million
		List<Cup> cups = readCups();
		
		cups.forEach(c -> System.out.print(c.getLabel()));
		
		System.out.println();
		
		addUntilOneMillion(cups);
		
//		cups.forEach(c -> System.out.println(ReflectionToStringBuilder.toString(c, ToStringStyle.MULTI_LINE_STYLE)));
		
		Cup currentCup = cups.get(0);
		for(long i = 0; i < MOVES; i++) {
//			printMoveNumber(i);
			
//			printCups(cups, currentCup);
			
			List<Cup> pickedUpCups = pickUpCups(currentCup);

//			printPickedUpCups(pickedUpCups);
			
			Cup destinationCup = selectDestinationCup(currentCup, pickedUpCups);

//			printDestinationCup(destinationCup);

			placePickedUpCups(destinationCup, pickedUpCups);
			
			currentCup = selectCurrentCup(currentCup);
			
//			System.out.println();
		}

//		System.out.println("-- final --");

//		printCups(cups, currentCup);
		
		List<Cup> resultCups = selectResultCups();
		
		resultCups.forEach(c -> System.out.println(c.getLabel()));
		
		long result = 1L;
		for(Cup resultCup: resultCups) {
			result *= resultCup.getLabel();
		}
		
		System.out.println(result);
		
		System.out.println(stopWatch.getTime() + "ms");
	}

	private static void addUntilOneMillion(List<Cup> cups) {
		Cup highestCup = cups.stream()
				.sorted(Comparator.comparing(Cup::getLabel).reversed())
				.findFirst().orElse(null);
		
		Cup previousCup = cups.get(cups.size() - 1);
		for(long i = highestCup.getLabel() + 1; i <= NUMBER_OF_CUPS; i++) {
			Cup cup = new Cup();
			cup.setLabel(i);
			CUP_MAP.put(cup.getLabel(), cup);
			cups.add(cup);
			
			cup.setPreviousCup(previousCup);
			previousCup.setNextCup(cup);
			
			previousCup = cup;
		}
		
		Cup firstCup = cups.get(0);
		
		firstCup.setPreviousCup(previousCup);
		previousCup.setNextCup(firstCup);
	}

	private static List<Cup> selectResultCups() {
		Cup cup1 = CUP_MAP.get(1L);
		Cup nextCup = cup1.getNextCup();
		Cup nextNextCup = nextCup.getNextCup();
		
		List<Cup> resultCups = new ArrayList<>();
		resultCups.add(nextCup);
		resultCups.add(nextNextCup);

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

	private static void printMoveNumber(long i) {
		System.out.println("-- move " + (i + 1) + " --");
	}

	private static Cup selectCurrentCup(Cup currentCup) {
		return currentCup.getNextCup();
	}

	private static void placePickedUpCups(Cup destinationCup, List<Cup> pickedUpCups) {
		Cup endCup = destinationCup.getNextCup();
		
		Cup firstPickedUpCup = pickedUpCups.get(0);
		Cup lastPickedUpCup = pickedUpCups.get(pickedUpCups.size() - 1);
		
		destinationCup.setNextCup(firstPickedUpCup);
		firstPickedUpCup.setPreviousCup(destinationCup);
		
		lastPickedUpCup.setNextCup(endCup);
		endCup.setPreviousCup(lastPickedUpCup);
	}

	private static Cup selectDestinationCup(Cup currentCup, List<Cup> pickedUpCups) {
		Long currentLabel = currentCup.getLabel();
		
		Set<Long> pickedUpCupLabels = pickedUpCups.stream()
				.map(c -> c.getLabel())
				.collect(Collectors.toSet());
		
		Long destinationLabel = currentLabel - 1;
		if(destinationLabel < 1) {
			destinationLabel = NUMBER_OF_CUPS;
		}
		while(pickedUpCupLabels.contains(destinationLabel)) {
			destinationLabel--;
			if(destinationLabel < 1) {
				destinationLabel = NUMBER_OF_CUPS;
			}
		}
		
		Cup destinationCup = CUP_MAP.get(destinationLabel);

		return destinationCup;
	}

	private static List<Cup> pickUpCups(Cup currentCup) {
		List<Cup> pickedUpCups = new ArrayList<>();
		
		Cup currentlyPickedUpCup = currentCup.getNextCup();
		for(int i = 0; i < NUMBER_OF_CUPS_TO_PICK_UP_PER_MOVE; i++) {
			pickedUpCups.add(currentlyPickedUpCup);
			
			currentlyPickedUpCup = currentlyPickedUpCup.getNextCup();
		}

		currentlyPickedUpCup.setPreviousCup(currentCup);
		currentCup.setNextCup(currentlyPickedUpCup);
		
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
		
		Cup previousCup = null;
		for(char character : line.toCharArray()) {
			Cup cup = processCharacter(String.valueOf(character), previousCup);
			cups.add(cup);
			previousCup = cup;
		}
		
		return cups;
	}

	private static Cup processCharacter(String character, Cup previousCup) {
		Cup cup = new Cup();
		cup.setLabel(Long.parseLong(character));
		CUP_MAP.put(cup.getLabel(), cup);
		
		if(previousCup != null) {
			cup.setPreviousCup(previousCup);
			previousCup.setNextCup(cup);
		}
		
		return cup;
	}

}
