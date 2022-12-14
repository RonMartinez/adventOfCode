package year2022.day14;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day14 {

//	private static final String FILENAME = "src/main/resources/2022/day14InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day14Input.txt";
	
	public static final Coordinate ENTRY_COORDINATE = new Coordinate(500L, 0L);
	public static final String COORDINATE_PATH_DELIMITER = " -> ";
	public static final String COMMA = ",";
	
	public static void main(String[] args) throws IOException {
		List<CoordinatePath> coordinatePaths = readCoordinatePaths();
		
		coordinatePaths.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		Cave cave = new Cave();
		
		for(CoordinatePath coordinatePath : coordinatePaths) {
			processCoordinatePath(coordinatePath, cave);
		}
		
//		for(Coordinate coordinate : cave.getCoordinateMap().keySet()) {
//			System.out.println(coordinate.getX() + "," + coordinate.getY());
//		}
		
		Coordinate settledCoordinate = cave.dropSand(ENTRY_COORDINATE);
		while(settledCoordinate != null) {
			settledCoordinate = cave.dropSand(ENTRY_COORDINATE);
		}
		
//		for(Coordinate coordinate : cave.getCoordinateMap().keySet()) {
//			if(cave.getCoordinateMap().get(coordinate).equals("o")) {
//				System.out.println(coordinate.getX() + "," + coordinate.getY());
//			}
//		}
		
		long settledSandCount = cave.getCoordinateMap().values().stream()
				.filter(v -> Cave.SAND.equals(v))
				.count();
		
		System.out.println(settledSandCount);
		
		System.out.println("done");
	}
	
	private static void processCoordinatePath(CoordinatePath coordinatePath, Cave cave) {
		List<Coordinate> coordinates = coordinatePath.getCoordinates();
		
		Coordinate previousCoordinate = null;
		for(int i = 0; i < coordinates.size(); i++) {
			Coordinate coordinate = coordinates.get(i);
			
			if(previousCoordinate != null) {
				Long previousX = previousCoordinate.getX();
				Long previousY = previousCoordinate.getY();
				Long x = coordinate.getX();
				Long y = coordinate.getY();
				if(x.equals(previousX)) {
					int direction = 1;
					if(previousY > y) {
						direction = -1;
					}
					
					//there's probably a better way to parameterize the comparison, and I think I've done it in a past aoc, whatever
					if(direction > 0) {
						for(long newY = previousY; newY <= y; newY += direction) {
							cave.addRock(new Coordinate(x, newY));
						}
					} else {
						for(long newY = previousY; newY >= y; newY += direction) {
							cave.addRock(new Coordinate(x, newY));
						}
					}
				} else if(y.equals(previousY)) {
					int direction = 1;
					if(previousX > x) {
						direction = -1;
					}
					
					//there's probably a better way to parameterize the comparison, and I think I've done it in a past aoc, whatever
					if(direction > 0) {
						for(long newX = previousX; newX <= x; newX += direction) {
							cave.addRock(new Coordinate(newX, y));
						}
					} else {
						for(long newX = previousX; newX >= x; newX += direction) {
							cave.addRock(new Coordinate(newX, y));
						}
					}
				} else {
					throw new RuntimeException("diagonal path found");
				}
			}
			
			previousCoordinate = coordinate;
		}
	}

	private static List<CoordinatePath> readCoordinatePaths() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		return lines.stream()
				.map(line -> createCoordinatePath(line))
				.collect(Collectors.toList());
	}

	private static CoordinatePath createCoordinatePath(String line) {
		String[] split = line.split(COORDINATE_PATH_DELIMITER);
		
		CoordinatePath coordinatePath = new CoordinatePath();
		for(String element : split) {
			coordinatePath.addCoordinate(createCoordinate(element));
		}
		
		return coordinatePath;
	}

	private static Coordinate createCoordinate(String element) {
		String[] split = element.split(COMMA);
		
		return new Coordinate(Long.valueOf(split[0]), Long.valueOf(split[1]));
	}

}
