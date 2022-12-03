package year2020.day24;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day24 {

	private static final String FILENAME = "src/main/resources/2020/day24InputSample.txt";
//	private static final String FILENAME = "src/main/resources/2020/day24InputSampleRon.txt";
//	private static final String FILENAME = "src/main/resources/2020/day24Input.txt";
	
	private static final long MAX_DEPTH = 3L;
	
	public static void main(String[] args) throws IOException {
		List<Instruction> instructions = readInstructions();
		
//		instructions.forEach(i -> System.out.println(ReflectionToStringBuilder.toString(i, ToStringStyle.MULTI_LINE_STYLE)));
		
		instructions.forEach(i -> {
				i.getDirections().forEach(d -> System.out.print(d.getCode() + " "));
				System.out.println();
		});
		
		List<Instruction> reducedInstructions = instructions.stream()
				.map(Day24::createReducedInstruction)
				.collect(Collectors.toList());

		reducedInstructions.forEach(i -> {
				i.getDirections().forEach(d -> System.out.print(d.getCode() + " "));
				System.out.println();
		});

		Tile centerTile = new Tile();
		createSurroundingTiles(centerTile);
		Tile.TILES.add(centerTile);
		
		System.out.println(Tile.TILES.size());
		
//		instructions.forEach(i -> processInstruction(i, centerTile));
		reducedInstructions.forEach(i -> processInstruction(i, centerTile));
		
		System.out.println(Tile.TILES.size());
		
		Set<Tile> blackTiles = Tile.TILES.stream()
				.filter(t -> Colour.BLACK.equals(t.getColour()))
				.collect(Collectors.toSet());

		System.out.println(blackTiles.size());
	}

	private static void createSurroundingTiles(Tile tile) {
		createSurroundingTiles(tile, 1L);
	}

	private static void createSurroundingTiles(Tile tile, long depth) {
		Tile eastTile = tile.getOrCreateEast();
		Tile southEastTile = tile.getOrCreateSouthEast();
		Tile southWestTile = tile.getOrCreateSouthWest();
		Tile westTile = tile.getOrCreateWest();
		Tile northWestTile = tile.getOrCreateNorthWest();
		Tile northEastTile = tile.getOrCreateNorthEast();
		
		eastTile.linkSouthWest(southEastTile);
		southEastTile.linkWest(southWestTile);
		southWestTile.linkNorthWest(westTile);
		westTile.linkNorthEast(northWestTile);
		northWestTile.linkEast(northEastTile);
		northEastTile.linkSouthEast(eastTile);
		
		if(depth < MAX_DEPTH) {
			createSurroundingTiles(eastTile, depth + 1);
			createSurroundingTiles(southEastTile, depth + 1);
			createSurroundingTiles(southWestTile, depth + 1);
			createSurroundingTiles(westTile, depth + 1);
			createSurroundingTiles(northWestTile, depth + 1);
			createSurroundingTiles(northEastTile, depth + 1);
		}
	}

	private static Instruction createReducedInstruction(Instruction instruction) {
		List<Direction> reducedDirections = instruction.getDirections().stream()
				.collect(Collectors.toList());
		
		removeRedundantEastWestDirections(instruction, reducedDirections);
		removeRedundantSouthEastNorthWestDirections(instruction, reducedDirections);
		removeRedundantSouthWestNorthEastDirections(instruction, reducedDirections);
		
		Instruction reducedInstruction = new Instruction();
		reducedInstruction.setDirections(reducedDirections);
		
		return reducedInstruction;
	}

	private static void removeRedundantSouthWestNorthEastDirections(Instruction instruction, List<Direction> directions) {
		long forwardCount = directions.stream()
				.filter(d -> Direction.SOUTH_WEST.getCode().equals(d.getCode()))
				.count();

		long backwardCount =directions.stream()
				.filter(d -> Direction.NORTH_EAST.getCode().equals(d.getCode()))
				.count();
		
		long count = Long.min(forwardCount, backwardCount);
		
		removeDirection(directions, count, Direction.SOUTH_WEST);
		removeDirection(directions, count, Direction.NORTH_EAST);
	}

	private static void removeRedundantSouthEastNorthWestDirections(Instruction instruction, List<Direction> directions) {
		long forwardCount = directions.stream()
				.filter(d -> Direction.SOUTH_EAST.getCode().equals(d.getCode()))
				.count();

		long backwardCount =directions.stream()
				.filter(d -> Direction.NORTH_WEST.getCode().equals(d.getCode()))
				.count();
		
		long count = Long.min(forwardCount, backwardCount);
		
		removeDirection(directions, count, Direction.SOUTH_EAST);
		removeDirection(directions, count, Direction.NORTH_WEST);
	}

	private static void removeRedundantEastWestDirections(Instruction instruction, List<Direction> directions) {
		long forwardCount = directions.stream()
				.filter(d -> Direction.EAST.getCode().equals(d.getCode()))
				.count();

		long backwardCount =directions.stream()
				.filter(d -> Direction.WEST.getCode().equals(d.getCode()))
				.count();
		
		long count = Long.min(forwardCount, backwardCount);
		
		removeDirection(directions, count, Direction.EAST);
		removeDirection(directions, count, Direction.WEST);
	}

	private static void removeDirection(List<Direction> directions, long count, Direction directionToRemove) {
		int removeCount = 0;
		
		List<Direction> directionsToRemove = new ArrayList<>();
		for(int i = 0; i < directions.size() && removeCount < count; i++) {
			Direction direction = directions.get(i);
			if(directionToRemove.getCode().equals(direction.getCode())) {
				directionsToRemove.add(direction);
				removeCount++;
			}
		}
		
		directions.removeAll(directionsToRemove);
	}

	private static void processInstruction(Instruction instruction, Tile currentTile) {
		instruction.getDirections().forEach(d -> System.out.print(d.getCode() + " "));
		System.out.println();
		
		for(Direction direction : instruction.getDirections()) {
			currentTile = processDirection(direction, currentTile);
		}
		
		flipTile(currentTile);
	}

	private static void flipTile(Tile currentTile) {
		if(Colour.WHITE.equals(currentTile.getColour())) {
			currentTile.setColour(Colour.BLACK);
			System.out.println("flip to black");
		} else if(Colour.BLACK.equals(currentTile.getColour())) {
			currentTile.setColour(Colour.WHITE);
			System.out.println("flip to white");
		}
	}

	private static Tile processDirection(Direction direction, Tile currentTile) {
		Tile nextTile = null;
		if(Direction.NORTH_EAST.getCode().equals(direction.getCode())) {
			nextTile = currentTile.getOrCreateNorthEast();
		} else if(Direction.NORTH_WEST.getCode().equals(direction.getCode())) {
			nextTile = currentTile.getOrCreateNorthWest();
		} else if(Direction.SOUTH_EAST.getCode().equals(direction.getCode())) {
			nextTile = currentTile.getOrCreateSouthEast();
		} else if(Direction.SOUTH_WEST.getCode().equals(direction.getCode())) {
			nextTile = currentTile.getOrCreateSouthWest();
		} else if(Direction.EAST.getCode().equals(direction.getCode())) {
			nextTile = currentTile.getOrCreateEast();
		} else if(Direction.WEST.getCode().equals(direction.getCode())) {
			nextTile = currentTile.getOrCreateWest();
		}
		createSurroundingTiles(nextTile);
		
		return nextTile;
	}

	private static List<Instruction> readInstructions() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		return lines.stream()
				.map(Day24::createInstruction)
				.collect(Collectors.toList());
	}

	private static Instruction createInstruction(String line) {
		List<Direction> directions = new ArrayList<>();
		
		int i = 0;
		while(i < line.length()) {
			String twoCharacterDirection = null;
			if(i + 2 <= line.length() ) {
				twoCharacterDirection = line.substring(i, i+2);	
			}
			String oneCharacterDirection = line.substring(i, i+1);
			if(Direction.NORTH_EAST.getCode().equals(twoCharacterDirection)) {
				directions.add(new Direction(Direction.NORTH_EAST.getCode()));
				i += 2;
			} else if(Direction.NORTH_WEST.getCode().equals(twoCharacterDirection)) {
				directions.add(new Direction(Direction.NORTH_WEST.getCode()));
				i += 2;
			} else if(Direction.SOUTH_EAST.getCode().equals(twoCharacterDirection)) {
				directions.add(new Direction(Direction.SOUTH_EAST.getCode()));
				i += 2;
			} else if(Direction.SOUTH_WEST.getCode().equals(twoCharacterDirection)) {
				directions.add(new Direction(Direction.SOUTH_WEST.getCode()));
				i += 2;
			} else if(Direction.EAST.getCode().equals(oneCharacterDirection)) {
				directions.add(new Direction(Direction.EAST.getCode()));
				i += 1;
			} else if(Direction.WEST.getCode().equals(oneCharacterDirection)) {
				directions.add(new Direction(Direction.WEST.getCode()));
				i += 1;
			}
		}
		
		Instruction instruction = new Instruction();
		instruction.setDirections(directions);
		
		return instruction;
	}

}
