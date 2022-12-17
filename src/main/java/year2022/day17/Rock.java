package year2022.day17;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import util.Coordinate;

public class Rock {
	
	public static final Rock HORIZONTAL_LINE = new Rock(new HashSet<>(Arrays.asList(
			new Coordinate(0L, 0L),
			new Coordinate(1L, 0L),
			new Coordinate(2L, 0L),
			new Coordinate(3L, 0L)
			)));

	public static final Rock PLUS = new Rock(new HashSet<>(Arrays.asList(
			new Coordinate(1L, 0L),
			new Coordinate(0L, 1L),
			new Coordinate(1L, 1L),
			new Coordinate(2L, 1L),
			new Coordinate(1L, 2L)
			)));

	public static final Rock L_MIRROR = new Rock(new HashSet<>(Arrays.asList(
			new Coordinate(0L, 0L),
			new Coordinate(1L, 0L),
			new Coordinate(2L, 0L),
			new Coordinate(2L, 1L),
			new Coordinate(2L, 2L)
			)));

	public static final Rock VERTICAL_LINE = new Rock(new HashSet<>(Arrays.asList(
			new Coordinate(0L, 0L),
			new Coordinate(0L, 1L),
			new Coordinate(0L, 2L),
			new Coordinate(0L, 3L)
			)));

	public static final Rock SQUARE = new Rock(new HashSet<>(Arrays.asList(
			new Coordinate(0L, 0L),
			new Coordinate(0L, 1L),
			new Coordinate(1L, 0L),
			new Coordinate(1L, 1L)
			)));

	private Set<Coordinate> coordinates;
	
	public Rock(Set<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	public Rock copy() {
		Set<Coordinate> copyCoordinates = getCoordinates().stream()
				.map(c -> new Coordinate(c.getX(), c.getY()))
				.collect(Collectors.toSet());
		
		return new Rock(copyCoordinates);
	}

	public void applyMovement(RockMovement rockMovement) {
		for(Coordinate coordinate : coordinates) {
			coordinate.setX(coordinate.getX() + rockMovement.getxChange());
			coordinate.setY(coordinate.getY() + rockMovement.getyChange());
		}
	}

	public Long getHeight() {
		return coordinates.stream()
				.map(c -> c.getY() + 1L)
				.max(Comparator.naturalOrder()).orElse(null);
	}

	public void addCoordinate(Coordinate coordinate) {
		getCoordinates().add(coordinate);
	}

	public Set<Coordinate> getCoordinates() {
		if(coordinates == null) {
			coordinates = new HashSet<>();
		}
		return coordinates;
	}

	public void setCoordinates(Set<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

}
