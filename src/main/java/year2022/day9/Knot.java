package year2022.day9;

import java.util.HashMap;
import java.util.Map;

public class Knot {

	private Coordinate coordinate;
	private Map<Coordinate, Long> visitedMap;

	public Knot(Coordinate coordinate) {
		this.coordinate = coordinate;
		this.visitedMap = new HashMap<>();
		updateVisitedMap();
	}

	public void processMovement(Movement movement) {
		coordinate.setX(coordinate.getX() + movement.getMoveX());
		coordinate.setY(coordinate.getY() + movement.getMoveY());
		if( ! movement.isNoChangeMovement()) {
			updateVisitedMap();	
		}
	}

	private void updateVisitedMap() {
		Coordinate copyCoordinate = new Coordinate(coordinate.getX(), coordinate.getY());
		Long visited = visitedMap.getOrDefault(copyCoordinate, 0L);
		visitedMap.put(copyCoordinate, visited+1);
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Map<Coordinate, Long> getVisitedMap() {
		return visitedMap;
	}

	public void setVisitedMap(Map<Coordinate, Long> visitedMap) {
		this.visitedMap = visitedMap;
	}

}
