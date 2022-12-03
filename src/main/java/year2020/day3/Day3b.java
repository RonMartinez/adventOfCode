package year2020.day3;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day3b {
	
//	private static final String FILENAME = "src/main/resources/2020/day3InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day3Input.txt";
	
	public static void main(String[] args) throws IOException {
		Forest forest = read();
		
		traverseForest(forest);
	}
	
	private static Forest read() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		List<ForestRow> forestRows = lines.stream()
				.map(line -> readLine(line))
				.collect(Collectors.toList());
		
		return new Forest(forestRows);
	}

	private static ForestRow readLine(String line) {
		List<ForestCoordinate> forestCoordinates = new ArrayList<>();
		for(int i = 0; i < line.length(); i++) {
			forestCoordinates.add(readCharacter(line.charAt(i)));
		}
		return new ForestRow(forestCoordinates);
	}

	private static ForestCoordinate readCharacter(char character) {
		ForestCoordinate forestCoordinate = null;
		if(character == '.') {
			forestCoordinate = new OpenSquare();
		} else if(character == '#') {
			forestCoordinate = new Tree();
		}
		return forestCoordinate;
	}

	private static void traverseForest(Forest forest) {
		List<Slope> slopes = Arrays.asList(
				new Slope(1, 1),
				new Slope(3, 1),
				new Slope(5, 1),
				new Slope(7, 1),
				new Slope(1, 2)
				);
		
		List<Integer> treeCounts = slopes.stream()
				.map(slope -> traverseForest(forest, slope))
				.collect(Collectors.toList());
		
		int result = 1;
		for(Integer treeCount: treeCounts) {
			System.out.println(treeCount);
			result *= treeCount;
		}
		
		System.out.println(result);
	}
	
	private static Integer traverseForest(Forest forest, Slope slope) {
		int x = 0;
		int y = 0;
		
		int xChange = slope.getxChange();
		int yChange = slope.getyChange();
		
		int treeCount = 0;
		
		x += xChange;
		y += yChange;
		
		while(y < forest.getForestRows().size()) {
			ForestRow forestRow = forest.getForestRows().get(y);
			List<ForestCoordinate> forestCoordinates = forestRow.getForestCoordinates();
			ForestCoordinate forestCoordinate = forestCoordinates.get(x % forestCoordinates.size());
			
			if(forestCoordinate instanceof Tree) {
				treeCount++;
			}
			
			x += xChange;
			y += yChange;
		}

		return treeCount;
	}

}
