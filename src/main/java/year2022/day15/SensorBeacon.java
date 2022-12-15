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

	public Long getMinX(SensorBeacon sensorBeacon, long y) {
		Long minX = null;
		
		Coordinate sensor = sensorBeacon.getSensor();
		Long sensorX = sensor.getX();
		Long sensorY = sensor.getY();
		
		Long yDiff = Math.abs(sensorY - y);
		long manhattanDistance = sensorBeacon.getManhattanDistance();
		if(yDiff <= manhattanDistance) {
			minX = sensorX - (manhattanDistance - Math.abs(sensorY - y));
		}
		
		return minX;
	}

	public Long getMaxX(SensorBeacon sensorBeacon, long y) {
		Long maxX = null;
		
		Coordinate sensor = sensorBeacon.getSensor();
		Long sensorX = sensor.getX();
		Long sensorY = sensor.getY();
		
		Long yDiff = Math.abs(sensorY - y);
		long manhattanDistance = sensorBeacon.getManhattanDistance();
		if(yDiff <= manhattanDistance) {
			maxX = sensorX + (manhattanDistance - Math.abs(sensorY - y));
		}
		
		return maxX;
	}

	public long getMinX(SensorBeacon sensorBeacon) {
		Coordinate sensor = sensorBeacon.getSensor();
		Coordinate beacon = sensorBeacon.getBeacon();
		Long sensorX = sensor.getX();
		Long sensorY = sensor.getY();
		Long beaconY = beacon.getY();
		long manhattanDistance = sensorBeacon.getManhattanDistance();
		
		return manhattanDistance - Math.abs(sensorY - beaconY) - sensorX;
	}

	public long getMaxX(SensorBeacon sensorBeacon) {
		Coordinate sensor = sensorBeacon.getSensor();
		Coordinate beacon = sensorBeacon.getBeacon();
		Long sensorX = sensor.getX();
		Long sensorY = sensor.getY();
		Long beaconY = beacon.getY();
		long manhattanDistance = sensorBeacon.getManhattanDistance();
		
		return manhattanDistance - Math.abs(sensorY - beaconY) + sensorX;
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
