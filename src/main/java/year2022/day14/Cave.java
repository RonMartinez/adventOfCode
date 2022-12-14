package year2022.day14;

import java.util.HashMap;
import java.util.Map;

public class Cave {
	
	public static final String ROCK = "#";
	public static final String SAND = "o";

	private Map<Coordinate, String> coordinateMap;
	private Long maxY;
	
	public Coordinate dropSand(Coordinate sand) {
		Coordinate coordinate = findSettledCoordinate(sand);
		
		if(coordinate != null) {
			coordinateMap.put(coordinate, SAND);
		}
		
		return coordinate;
	}

	public Coordinate findSettledCoordinate(Coordinate sand) {
		Coordinate coordinate = null;

		Long x = sand.getX();
		Long y = sand.getY();
		
		if(y < maxY) {
			Long newY = y+1;
			
			Coordinate down = new Coordinate(x, newY);
			Coordinate downLeft = new Coordinate(x-1, newY);
			Coordinate downRight = new Coordinate(x+1, newY);
			if(coordinateMap.get(down) == null) {
				coordinate = findSettledCoordinate(down);
			} else if(coordinateMap.get(downLeft) == null) {
				coordinate = findSettledCoordinate(downLeft);
			} else if(coordinateMap.get(downRight) == null) {
				coordinate = findSettledCoordinate(downRight);
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

	public Long getMaxY() {
		return maxY;
	}

	public void setMaxY(Long maxY) {
		this.maxY = maxY;
	}

}
