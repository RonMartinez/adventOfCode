package year2021.day5;

public class Line {

	private Coordinate startCoordinate;
	private Coordinate endCoordinate;
	
	public Line(Coordinate startCoordinate, Coordinate endCoordinate) {
		this.startCoordinate = startCoordinate;
		this.endCoordinate = endCoordinate;
	}
	
	public boolean isVertical() {
		return startCoordinate.getX().equals(endCoordinate.getX());
	}

	public boolean isHorizontal() {
		return startCoordinate.getY().equals(endCoordinate.getY());
	}

	public boolean isDiagonal() {
		long xDiff = Math.abs(startCoordinate.getX() - endCoordinate.getX());
		long yDiff = Math.abs(startCoordinate.getY() - endCoordinate.getY());
		
		return xDiff == yDiff;
	}

	public Coordinate getStartCoordinate() {
		return startCoordinate;
	}

	public void setStartCoordinate(Coordinate startCoordinate) {
		this.startCoordinate = startCoordinate;
	}

	public Coordinate getEndCoordinate() {
		return endCoordinate;
	}

	public void setEndCoordinate(Coordinate endCoordinate) {
		this.endCoordinate = endCoordinate;
	}
}
