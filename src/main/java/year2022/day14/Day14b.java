package year2022.day14;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day14b {

//	private static final String FILENAME = "src/main/resources/2022/day14InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day14Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<CoordinatePath> coordinatePaths = readCoordinatePaths();
		
		coordinatePaths.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		Cave cave = new Cave();
		
		for(CoordinatePath coordinatePath : coordinatePaths) {
			processCoordinatePath(coordinatePath, cave);
		}
		
		Long maxY = cave.getCoordinateMap().keySet().stream()
				.map(c -> c.getY())
				.max(Comparator.naturalOrder()).orElse(null);
		cave.setMaxY(maxY + 2L);

		for(Coordinate coordinate : cave.getCoordinateMap().keySet()) {
			System.out.println(coordinate.getX() + "," + coordinate.getY());
		}
		
		Coordinate entryCoordinate = new Coordinate(500L, 0L);
		Coordinate settledCoordinate = cave.dropSand(entryCoordinate);
		while( ! settledCoordinate.equals(entryCoordinate)) {
			settledCoordinate = cave.dropSand(entryCoordinate);
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
		String[] split = line.split(" -> ");
		
		CoordinatePath coordinatePath = new CoordinatePath();
		for(String element : split) {
			coordinatePath.addCoordinate(createCoordinate(element));
		}
		
		return coordinatePath;
	}

	private static Coordinate createCoordinate(String element) {
		String[] split = element.split(",");
		
		return new Coordinate(Long.valueOf(split[0]), Long.valueOf(split[1]));
	}

}
