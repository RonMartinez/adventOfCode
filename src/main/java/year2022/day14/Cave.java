package year2022.day14;

import java.util.HashMap;
import java.util.Map;

public class Cave {
	
	public static final String ROCK = "#";
	public static final String SAND = "o";
	public static final Long MAX_ITERATIONS = 2_500L;

	private Map<Coordinate, String> coordinateMap;
	
	public Coordinate dropSand(Coordinate sand) {
		Coordinate coordinate = findSettledCoordinate(sand, 0L);
		
		if(coordinate != null) {
			coordinateMap.put(coordinate, SAND);
		}
		
		return coordinate;
	}

	public Coordinate findSettledCoordinate(Coordinate sand, Long iterations) {
		Coordinate coordinate = null;
		
		if(iterations < MAX_ITERATIONS) {
			Long x = sand.getX();
			Long y = sand.getY();
			
			Long newY = y+1;
			
			Coordinate down = new Coordinate(x, newY);
			Coordinate downLeft = new Coordinate(x-1, newY);
			Coordinate downRight = new Coordinate(x+1, newY);
			if(coordinateMap.get(down) == null) {
				coordinate = findSettledCoordinate(down, iterations+1L);
			} else if(coordinateMap.get(downLeft) == null) {
				coordinate = findSettledCoordinate(downLeft, iterations+1L);
			} else if(coordinateMap.get(downRight) == null) {
				coordinate = findSettledCoordinate(downRight, iterations+1L);
			} else {
				coordinate = sand;
			}
		}
	
		return coordinate;
	}

	public void addRock(Coordinate coordinate) {
		getCoordinateMap().put(coordinate, ROCK);
	}

	public Map<Coordinate, String> getCoordinateMap() {
		if(coordinateMap == null) {
			coordinateMap = new HashMap<>();
		}
		return coordinateMap;
	}

	public void setCoordinateMap(Map<Coordinate, String> coordinateMap) {
		this.coordinateMap = coordinateMap;
	}

}
