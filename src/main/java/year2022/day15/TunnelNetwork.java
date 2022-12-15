package year2022.day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TunnelNetwork {
	
	public static final String SENSOR = "S";
	public static final String BEACON = "B";
	
	private Map<Coordinate, String> coordinateMap;
	private List<SensorBeacon> sensorBeacons;
	private Set<Coordinate> sensors;
	private Set<Coordinate> beacons;
	
	public Set<Coordinate> getCoveredCoordinates() {
		return sensorBeacons.stream()
				.flatMap(sb -> getCoveredCoordinates(sb).stream())
				.collect(Collectors.toSet());
	}
	
	private Set<Coordinate> getCoveredCoordinates(SensorBeacon sensorBeacon) {
		Set<Coordinate> coordinates = new HashSet<>();
		
		Coordinate sensor = sensorBeacon.getSensor();
		long manhattanDistance = sensorBeacon.getManhattanDistance();
		
		for(long x = sensor.getX() - manhattanDistance; x <= sensor.getX() + manhattanDistance; x++) {
			for(long y = sensor.getY() - manhattanDistance; y <= sensor.getY() + manhattanDistance; y++) {
				Coordinate coordinate = new Coordinate(x, y);
				if(CoordinateHelper.getManhattanDistance(sensor, coordinate) <= manhattanDistance) {
					coordinates.add(coordinate);
				}
			}
		}
		
		return coordinates;
	}
	
	public Long getCoveredCoordinateCount(long y) {
		Long minX = Long.MAX_VALUE;
		Long maxX = Long.MIN_VALUE;
		for(SensorBeacon sensorBeacon : sensorBeacons) {
			Long newMinX = sensorBeacon.getMinX(sensorBeacon);
			if(newMinX < minX) {
				minX = newMinX;
			}

			Long newMaxX = sensorBeacon.getMaxX(sensorBeacon);
			if(newMaxX > maxX) {
				maxX = newMaxX;
			}
		}
		
		return getCoveredCoordinateCount(y,  minX, maxX, false);
	}

	public Long getCoveredCoordinateCount(long y, Long minX, Long maxX, boolean includeBeacons) {
		Long count = 0L;
		
		Set<Coordinate> beacons = getBeacons();
		for(long x = minX; x <= maxX; x++) {
			Coordinate coordinate = new Coordinate(x, y);
			if(sensorBeacons.stream()
					.anyMatch(sb -> CoordinateHelper.getManhattanDistance(sb.getSensor(), coordinate) <= sb.getManhattanDistance())
					&& (
							includeBeacons
							||
							! beacons.contains(coordinate)
							)
					) {
				count++;
			}
		}
		
		return count;
	}

	public void addSensorBeacon(SensorBeacon sensorBeacon) {
		getSensorBeacons().add(sensorBeacon);
		getSensors().add(sensorBeacon.getSensor());
		getBeacons().add(sensorBeacon.getBeacon());
		getCoordinateMap().put(sensorBeacon.getSensor(), SENSOR);
		getCoordinateMap().put(sensorBeacon.getBeacon(), SENSOR);
		
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

	public List<SensorBeacon> getSensorBeacons() {
		if(sensorBeacons == null) {
			sensorBeacons = new ArrayList<>();
		}
		return sensorBeacons;
	}

	public void setSensorBeacons(List<SensorBeacon> sensorBeacons) {
		this.sensorBeacons = sensorBeacons;
	}

	public Set<Coordinate> getSensors() {
		if(sensors == null) {
			sensors = new HashSet<>();
		}
		return sensors;
	}

	public void setSensors(Set<Coordinate> sensors) {
		this.sensors = sensors;
	}

	public Set<Coordinate> getBeacons() {
		if(beacons == null) {
			beacons = new HashSet<>();
		}
		return beacons;
	}

	public void setBeacons(Set<Coordinate> beacons) {
		this.beacons = beacons;
	}

}
