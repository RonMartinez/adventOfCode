package year2020.day3;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day3 {
	
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
		int x = 0;
		int y = 0;
		
		int xChange = 3;
		int yChange = 1;
		
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
		
		System.out.println(treeCount);
	}

}
