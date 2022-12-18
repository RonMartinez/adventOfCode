package util;

import java.util.HashSet;
import java.util.Set;

public class CoordinateHelper {

	public static long getManhattanDistance(Coordinate coordinate1, Coordinate coordinate2) {
		return Math.abs(coordinate1.getX() - coordinate2.getX()) + Math.abs(coordinate1.getY() - coordinate2.getY());
	}
	
	public static Set<Coordinate> getAdjacentCoordinates(Coordinate coordinate) {
		Long x = coordinate.getX();
		Long y = coordinate.getY();
		
		Set<Coordinate> adjacentCoordinates = new HashSet<>();
		adjacentCoordinates.add(new Coordinate(x + 1, y));
		adjacentCoordinates.add(new Coordinate(x - 1, y));
		adjacentCoordinates.add(new Coordinate(x, y + 1));
		adjacentCoordinates.add(new Coordinate(x, y - 1));
		
		return adjacentCoordinates;
	}

	public static Set<Coordinate> getSurroundingCoordinates(Coordinate coordinate) {
		Long x = coordinate.getX();
		Long y = coordinate.getY();
		
		Set<Coordinate> surroundingCoordinates = new HashSet<>();
		for(long xChange = -1; xChange <= 1; xChange++) {
			for(long yChange = -1; yChange <= 1; yChange++) {
				surroundingCoordinates.add(new Coordinate(x + xChange, y + yChange));
			}
		}
		surroundingCoordinates.remove(coordinate);
		
		return surroundingCoordinates;
	}


}
