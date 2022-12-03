package year2021.day5;

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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day5b {

//	private static final String FILENAME = "src/main/resources/2021/day5InputSample.txt";
	private static final String FILENAME = "src/main/resources/2021/day5Input.txt";
	
	private static Map<Coordinate, Set<Line>> coordinateLineMap = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		List<Line> lines = readLines();
		
		lines.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		lines.stream()
				.filter(Line::isHorizontal)
				.forEach(l -> processHorizontal(l));
		
		lines.stream()
				.filter(Line::isVertical)
				.forEach(l -> processVertical(l));

		lines.stream()
				.filter(Line::isDiagonal)
				.forEach(l -> processDiagonal(l));

		long count = 0L;
		for(Coordinate coordinate : coordinateLineMap.keySet()) {
			if(coordinateLineMap.get(coordinate).size() > 1) {
				count++;
			}
		}
		
		System.out.println(count);
	}

	private static void processDiagonal(Line line) {
		long xIncrement = 1;
		if(line.getStartCoordinate().getX().compareTo(line.getEndCoordinate().getX()) > 0) {
			xIncrement = -1;
		}

		long yIncrement = 1;
		if(line.getStartCoordinate().getY().compareTo(line.getEndCoordinate().getY()) > 0) {
			yIncrement = -1;
		}
		
		for(long x = line.getStartCoordinate().getX(), y = line.getStartCoordinate().getY();
				xIncrement == 1 ? x <= line.getEndCoordinate().getX() : x >= line.getEndCoordinate().getX();
				x += xIncrement, y += yIncrement) {
			Coordinate coordinate = new Coordinate(x, y);
			Set<Line> mapLines = coordinateLineMap.getOrDefault(coordinate, new HashSet<>());
			mapLines.add(line);
			coordinateLineMap.put(coordinate, mapLines);
		}
	}

	private static void processHorizontal(Line line) {
		long y = line.getStartCoordinate().getY();
		
		long startX = line.getStartCoordinate().getX();
		long endX = line.getEndCoordinate().getX();
		if(line.getStartCoordinate().getX().compareTo(line.getEndCoordinate().getX()) > 0) {
			startX = line.getEndCoordinate().getX();
			endX = line.getStartCoordinate().getX();
		}
		
		for(long x = startX; x <= endX; x++) {
			Coordinate coordinate = new Coordinate(x, y);
			Set<Line> mapLines = coordinateLineMap.getOrDefault(coordinate, new HashSet<>());
			mapLines.add(line);
			coordinateLineMap.put(coordinate, mapLines);
		}
	}

	private static void processVertical(Line line) {
		long x = line.getStartCoordinate().getX();
		
		long startY = line.getStartCoordinate().getY();
		long endY = line.getEndCoordinate().getY();
		if(line.getStartCoordinate().getY().compareTo(line.getEndCoordinate().getY()) > 0) {
			startY = line.getEndCoordinate().getY();
			endY = line.getStartCoordinate().getY();
		}
		
		for(long y = startY; y <= endY; y++) {
			Coordinate coordinate = new Coordinate(x, y);
			Set<Line> mapLines = coordinateLineMap.getOrDefault(coordinate, new HashSet<>());
			mapLines.add(line);
			coordinateLineMap.put(coordinate, mapLines);
		}
	}

	private static List<Line> readLines() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(l -> createLine(l))
				.collect(Collectors.toList());
	}

	private static Line createLine(String line) {
		String[] coordinateStrings = StringUtils.split(line, " -> ");
		String startCoordinatePair = coordinateStrings[0];
		String endCoordinatePair = coordinateStrings[1];
		
		String[] startCoordinates = StringUtils.split(startCoordinatePair, ",");
		Long startX = Long.valueOf(startCoordinates[0]);
		Long startY = Long.valueOf(startCoordinates[1]);

		String[] endCoordinates = StringUtils.split(endCoordinatePair, ",");
		Long endX = Long.valueOf(endCoordinates[0]);
		Long endY = Long.valueOf(endCoordinates[1]);
		
		Coordinate startCoordinate = new Coordinate(startX, startY);
		Coordinate endCoordinate = new Coordinate(endX, endY);

		return new Line(startCoordinate, endCoordinate);
	}

}
