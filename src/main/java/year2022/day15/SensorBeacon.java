package year2022.day15;

public class SensorBeacon {

	private Coordinate sensor;
	private Coordinate beacon;
	private long manhattanDistance;

	public SensorBeacon(Coordinate sensor, Coordinate beacon) {
		this.sensor = sensor;
		this.beacon = beacon;
		this.manhattanDistance = CoordinateHelper.getManhattanDistance(sensor, beacon);
	}

	public Long getMinX(long y) {
		Long minX = null;
		
		Coordinate sensor = getSensor();
		Long sensorX = sensor.getX();
		Long sensorY = sensor.getY();
		
		Long yDiff = Math.abs(sensorY - y);
		long manhattanDistance = getManhattanDistance();
		if(yDiff <= manhattanDistance) {
			minX = sensorX - (manhattanDistance - yDiff);
		}
		
		return minX;
	}

	public Long getMaxX(long y) {
		Long maxX = null;
		
		Coordinate sensor = getSensor();
		Long sensorX = sensor.getX();
		Long sensorY = sensor.getY();
		
		Long yDiff = Math.abs(sensorY - y);
		long manhattanDistance = getManhattanDistance();
		if(yDiff <= manhattanDistance) {
			maxX = sensorX + (manhattanDistance - yDiff);
		}
		
		return maxX;
	}

	public Coordinate getSensor() {
		return sensor;
	}

	public void setSensor(Coordinate sensor) {
		this.sensor = sensor;
	}

	public Coordinate getBeacon() {
		return beacon;
	}

	public void setBeacon(Coordinate beacon) {
		this.beacon = beacon;
	}

	public long getManhattanDistance() {
		return manhattanDistance;
	}

	public void setManhattanDistance(long manhattanDistance) {
		this.manhattanDistance = manhattanDistance;
	}

}
