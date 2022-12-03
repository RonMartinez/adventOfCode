package year2020.day17;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.time.StopWatch;

public class Day17b {

//	private static final String FILENAME = "src/main/resources/2020/day17InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day17Input.txt";
	
	private static final int PASSES = 6;
	
	private static Map<Coordinate, Set<Cube>> cachedNeighbours = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		Map<Coordinate, Cube> coordinateCubeMap = readCubes();
		
		coordinateCubeMap.keySet().forEach(coordinate -> {
			System.out.println(ReflectionToStringBuilder.toString(coordinate, ToStringStyle.MULTI_LINE_STYLE));
			System.out.println(ReflectionToStringBuilder.toString(coordinateCubeMap.get(coordinate), ToStringStyle.MULTI_LINE_STYLE));
		});

		createNeighbourCubesAroundInitialInput(coordinateCubeMap);
		
		for(int i = 0; i < PASSES; i++) {
			StopWatch stopWatch = StopWatch.createStarted();
			System.out.println("start pass: " + i);
			
			Set<Coordinate> coordinates = coordinateCubeMap.keySet().stream()
					.collect(Collectors.toSet());
			
			coordinates.forEach(coordinate -> calculateNextValue(coordinate, coordinateCubeMap));
			
			coordinateCubeMap.values().forEach(cube -> cube.processChange());
			
			System.out.println("end pass: " + i + " (" + stopWatch.getTime() + ")");
		}
		
		Set<Cube> activeCubes = coordinateCubeMap.values().stream()
				.filter(Cube::isActive)
				.collect(Collectors.toSet());
		
		System.out.println(activeCubes.size());
	}


	private static void createNeighbourCubesAroundInitialInput(Map<Coordinate, Cube> coordinateCubeMap) {
		Set<Coordinate> coordinates = coordinateCubeMap.keySet().stream()
				.collect(Collectors.toSet());
		
		coordinates.forEach(coordinate -> getOrCreateNeighbourCubes(coordinate, coordinateCubeMap));
	}
		

	private static Map<Coordinate, Cube> readCubes() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		Map<Coordinate, Cube> coordinateCubeMap = new HashMap<>();
		for(int y = 0; y < lines.size(); y++) {
			String line = lines.get(y);
			for(int x = 0; x < line.length(); x++) {
				Coordinate coordinate = new Coordinate(x, y, 0, 0);
				
				Cube cube = new Cube(String.valueOf(line.charAt(x)), coordinate);
				coordinateCubeMap.put(coordinate, cube);
			}
		}

		return coordinateCubeMap;
	}

	private static void calculateNextValue(Coordinate coordinate, Map<Coordinate, Cube> coordinateCubeMap) {
		Set<Cube> neighbourCubes = getOrCreateNeighbourCubes(coordinate, coordinateCubeMap);
		
		Set<Cube> activeNeighbourCubes = neighbourCubes.stream()
				.filter(Cube::isActive)
				.collect(Collectors.toSet());
		
		calculateNextValue(coordinateCubeMap.get(coordinate), activeNeighbourCubes);
	}


	private static Set<Cube> getOrCreateNeighbourCubes(Coordinate coordinate, Map<Coordinate, Cube> coordinateCubeMap) {
		Set<Cube> neighbourCubes = cachedNeighbours.get(coordinate);
		if(neighbourCubes == null) {
			Set<Coordinate> neighbourCoordinates = new HashSet<>();
			for(long w = coordinate.getW() - 1; w <= coordinate.getW() + 1; w++) {
				for(long z = coordinate.getZ() - 1; z <= coordinate.getZ() + 1; z++) {
					for(long y = coordinate.getY() - 1; y <= coordinate.getY() + 1; y++) {
						for(long x = coordinate.getX() - 1; x <= coordinate.getX() + 1; x++) {
							Coordinate neighbourCoordinate = new Coordinate(x, y, z, w);
							if( ! coordinateCubeMap.containsKey(neighbourCoordinate)) {
								Cube cube = new Cube(".", neighbourCoordinate);
								coordinateCubeMap.put(neighbourCoordinate, cube);
							}
							if( ! neighbourCoordinate.equals(coordinate)) {
								neighbourCoordinates.add(neighbourCoordinate);	
							}
						}
					}
				}
			}
			
			neighbourCubes = neighbourCoordinates.stream()
					.map(nc -> coordinateCubeMap.get(nc))
					.collect(Collectors.toSet());

			cachedNeighbours.put(coordinate, neighbourCubes);
		}
		
		return neighbourCubes;
	}

	private static void calculateNextValue(Cube cube, Set<Cube> activeNeighbourCubes) {
		if(cube.isActive()) {
			if(activeNeighbourCubes.size() == 2
					|| activeNeighbourCubes.size() == 3
					) {
				cube.setNextValue("#");
			} else {
				cube.setNextValue(".");
			}
		} else {
			if(activeNeighbourCubes.size() == 3
					) {
				cube.setNextValue("#");
			} else {
				cube.setNextValue(".");
			}
		}
	}

}
