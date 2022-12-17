package year2022.day17;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day17 {

//	private static final String FILENAME = "src/main/resources/2022/day17InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day17Input.txt";
	
//	public static final Long ITERATIONS = 2022L;
	public static final Long ITERATIONS = 1_000_000_000_000L;
	public static final Long CHAMBER_WIDTH = 7L;
	public static final Long INITIAL_CHAMBER_HEIGHT = 1L;
	public static final Character ROCK_MOVEMENT_LEFT_CHARACTER = '<';
	public static final Character ROCK_MOVEMENT_RIGHT_CHARACTER = '>';
	
	public static void main(String[] args) throws IOException {
		List<RockMovement> rockMovements = readRockMovements();
		
		rockMovements.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		Chamber chamber = new Chamber(INITIAL_CHAMBER_HEIGHT, CHAMBER_WIDTH);
		
		List<Rock> rocks = Arrays.asList(
				Rock.HORIZONTAL_LINE,
				Rock.PLUS,
				Rock.L_MIRROR,
				Rock.VERTICAL_LINE,
				Rock.SQUARE
				);
		
		int rockIndex = 0;
		int rockMovementsIndex = 0;
		
		Long chamberHeight = chamber.getHeight();
		List<Long> chamberHeightChanges = new ArrayList<>();

		chamber.print();
		DropRockResult dropRockResult = null;
		Long currentI = null;
		for(long i = 0; i < ITERATIONS; i++) {
			System.out.println("iteration: " + i);
			
			Rock rock = rocks.get(rockIndex);
			rockIndex++;
			if(rockIndex == rocks.size()) {
				rockIndex = 0;
			}

			DropRockInput dropRockInput = new DropRockInput(rock, rockMovementsIndex, rockMovements);

			dropRockResult = chamber.dropRock(dropRockInput, i);
			
			rockMovementsIndex = dropRockResult.getRockMovementsIndex();
			Long previousIteration = dropRockResult.getPreviousIteration();
			if(previousIteration != null) {
				System.out.println("iteration " + i + " is the same as " + previousIteration);
				currentI = i;
				break;
			}
			
			Long newChamberHeight = chamber.getHeight();
			chamberHeightChanges.add(newChamberHeight - chamberHeight);
			chamberHeight = newChamberHeight;
					
//			System.out.println("iteration: " + i);
//			chamber.print();
		}
		
		Long previousIteration = dropRockResult.getPreviousIteration();
		
		List<Long> chamberHeightChangesSublist = chamberHeightChanges.subList(previousIteration.intValue(), chamberHeightChanges.size());
		
		Long heightChangePerCycle = chamberHeightChangesSublist.stream()
				.reduce(0L, Long::sum);
		
		int cycleSize = chamberHeightChangesSublist.size();
		
		Long fullCyclesRemaining = (ITERATIONS-currentI) / cycleSize;
		Long iterationsOfFinalCycle = (ITERATIONS-currentI) % cycleSize;
		chamberHeight = chamberHeight + (fullCyclesRemaining * heightChangePerCycle);
		
		for(int i = 0; i < iterationsOfFinalCycle; i++) {
			Long chamberHeightChange = chamberHeightChangesSublist.get(i);
			chamberHeight += chamberHeightChange;
		}
		
		System.out.println(chamberHeight-1);
		
		System.out.println("done");
	}

	private static List<RockMovement> readRockMovements() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.flatMap(line -> createRockMovements(line).stream())
				.collect(Collectors.toList());
	}

	private static List<RockMovement> createRockMovements(String line) {
		List<RockMovement> rockMovements = new ArrayList<>();
		for(char c : line.toCharArray()) {
			if(ROCK_MOVEMENT_LEFT_CHARACTER == c) {
				rockMovements.add(RockMovement.LEFT);
			} else if(ROCK_MOVEMENT_RIGHT_CHARACTER == c) {
				rockMovements.add(RockMovement.RIGHT);
			} else {
				throw new RuntimeException("unknown rock movement: " + c);
			}
		}
		
		return rockMovements;
	}

}
