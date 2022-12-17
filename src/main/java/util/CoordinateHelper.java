package util;

public class CoordinateHelper {

	public static long getManhattanDistance(Coordinate coordinate1, Coordinate coordinate2) {
		return Math.abs(coordinate1.getX() - coordinate2.getX()) + Math.abs(coordinate1.getY() - coordinate2.getY());
	}

}
