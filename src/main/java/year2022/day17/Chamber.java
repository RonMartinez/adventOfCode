package year2022.day17;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import util.Coordinate;

public class Chamber {

	public static final Long DROP_ROCK_Y_OFFSET = 3L;
	public static final Long DROP_ROCK_X_OFFSET = 2L;
	public static final Long NUMBER_OF_TOP_TOWER_ROWS_TO_INSPECT = 30L;

	private Set<Coordinate> coordinates;
	private Long height;
	private Long width;
	private Map<ChamberDropRockState, Long> chamberDropRockStateMap;
	
	public Chamber(Long height, Long width) {
		this.height = height;
		this.width = width;
		for(long x = 0; x < width; x++) {
			addCoordinate(new Coordinate(x, 0L));
		}
	}
	
	public Set<Coordinate> getTowerState() {
		Set<Coordinate> towerCoordinates = new HashSet<>();
		
		for(long y = getHeight()-1; y >= getHeight()-NUMBER_OF_TOP_TOWER_ROWS_TO_INSPECT; y--) {
			for(long x = 0; x < getWidth(); x++) {
				Coordinate coordinateToFind = new Coordinate(x, y);
				if(getCoordinates().contains(coordinateToFind)) {
					towerCoordinates.add(coordinateToFind);
				}
			}
		}
		
		Long minY = towerCoordinates.stream()
				.map(Coordinate::getY)
				.min(Comparator.naturalOrder()).orElse(null);
		
		return towerCoordinates.stream()
				.map(pc -> new Coordinate(pc.getX(), pc.getY()-minY))
				.collect(Collectors.toSet());
	}

	public void print() {
		for(long y = height-1; y >= 0; y--) {
			for(long x = 0; x < width; x++) {
				Coordinate coordinate = new Coordinate(x, y);
				String character = ".";
				if(getCoordinates().contains(coordinate)) {
					character = "#";
				}
				System.out.print(character);
			}
			System.out.println();
		}
	}

	public void print(Rock rock) {
		long height = Math.max(rock.getHeight(), getHeight());
		
		for(long y = height-1; y >= 0; y--) {
			for(long x = 0; x < width; x++) {
				Coordinate coordinate = new Coordinate(x, y);
				String character = ".";
				if(getCoordinates().contains(coordinate)) {
					character = "#";
				} else if(rock.getCoordinates().stream()
						.filter(c -> c.equals(coordinate))
						.findFirst().orElse(null) != null) {
					character = "@";
				}
				System.out.print(character);
			}
			System.out.println();
		}
	}

	public DropRockResult dropRock(DropRockInput dropRockInput, long iteration) {
		Set<Coordinate> towerState = getTowerState();
		
		Rock rock = dropRockInput.getRock().copy();
		setInitialRockX(rock);
		setInitialRockY(rock);
		List<RockMovement> rockMovements = dropRockInput.getRockMovements();
		int rockMovementsIndex = dropRockInput.getRockMovementsIndex();
		
		boolean collision = false;
		while ( ! collision) {
//			print(rock);
			
			RockMovement rockMovement = rockMovements.get(rockMovementsIndex);
			rockMovementsIndex++;
			if(rockMovementsIndex == rockMovements.size()) {
				rockMovementsIndex = 0;
			}
			
//			System.out.println(rockMovement.getName());
			
			rock.applyMovement(rockMovement);
			if(isOutOfBounds(rock)
					|| isCollision(rock)
					) {
				rock.applyMovement(rockMovement.getOppositeRockMovement());
			}
//			print(rock);
			
			RockMovement downRockMovement = RockMovement.DOWN;
			rock.applyMovement(downRockMovement);
			if(isCollision(rock)) {
				rock.applyMovement(downRockMovement.getOppositeRockMovement());
				collision = true;
			}
//			print(rock);
		}
		
		getCoordinates().addAll(rock.getCoordinates());
		
		
		Long previousHeight = height;
		Long rockHeight = rock.getHeight();
		if(rockHeight > height) {
			height = rockHeight;
		}
		Long heightChange = height - previousHeight;
		
		ChamberDropRockState chamberDropRockState = new ChamberDropRockState(dropRockInput, towerState);
		Long previousIteration = getChamberDropRockStateMap().get(chamberDropRockState);
		if(previousIteration == null) {
			getChamberDropRockStateMap().put(chamberDropRockState, iteration);
		}
		
		return new DropRockResult(rockMovementsIndex, previousIteration);
	}
	
	private void setInitialRockY(Rock rock) {
		Long chamberHeight = getHeight();
		for(long i = 0; i < chamberHeight + DROP_ROCK_Y_OFFSET; i++) {
			rock.applyMovement(RockMovement.UP);
		}
	}

	private void setInitialRockX(Rock rock) {
		for(long i = 0; i < DROP_ROCK_X_OFFSET; i++) {
			rock.applyMovement(RockMovement.RIGHT);
		}
	}
	
	private boolean isCollision(Rock rock) {
		return rock.getCoordinates().stream()
				.anyMatch(c -> getCoordinates().contains(c));
	}

	private boolean isOutOfBounds(Rock rock) {
		return rock.getCoordinates().stream()
				.anyMatch(c -> c.getX() < 0L || c.getX() >= width);
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

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public Long getWidth() {
		return width;
	}

	public void setWidth(Long width) {
		this.width = width;
	}

	public Map<ChamberDropRockState, Long> getChamberDropRockStateMap() {
		if(chamberDropRockStateMap == null) {
			chamberDropRockStateMap = new HashMap<>();
		}
		return chamberDropRockStateMap;
	}

	public void setChamberDropRockStateMap(Map<ChamberDropRockState, Long> chamberDropRockStateMap) {
		this.chamberDropRockStateMap = chamberDropRockStateMap;
	}


}
