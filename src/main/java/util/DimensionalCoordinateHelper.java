package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DimensionalCoordinateHelper {

	public static long getManhattanDistance(DimensionalCoordinate dimensionalCoordinate1, DimensionalCoordinate dimensionalCoordinate2) {
		//assumes both have the same size
		
		long manhattanDistance = 0L;
		for(int i = 0; i < dimensionalCoordinate1.getDimensions().size(); i++) {
			manhattanDistance += Math.abs(dimensionalCoordinate1.getDimensions().get(i) - dimensionalCoordinate2.getDimensions().get(i));
		}
		
		return manhattanDistance;
	}
	
	public static Set<DimensionalCoordinate> getAdjacentDimensionalCoordinates(DimensionalCoordinate dimensionalCoordinate) {
		Set<DimensionalCoordinate> adjacentDimensionalCoordinates = new HashSet<>();
		for(int i = 0; i < dimensionalCoordinate.getDimensions().size(); i++) {
			Long dimension = dimensionalCoordinate.getDimensions().get(i);
			List<Long> newDimensionsLeft = dimensionalCoordinate.getDimensions().stream()
				.collect(Collectors.toList());
			newDimensionsLeft.set(i, dimension - 1);
			adjacentDimensionalCoordinates.add(new DimensionalCoordinate(newDimensionsLeft));

			List<Long> newDimensionsRight = dimensionalCoordinate.getDimensions().stream()
					.collect(Collectors.toList());
			newDimensionsRight.set(i, dimension + 1);
			adjacentDimensionalCoordinates.add(new DimensionalCoordinate(newDimensionsRight));
		}
		
		return adjacentDimensionalCoordinates;
	}

	
	//this doesn't feel completely efficient, but it works
	public static Set<DimensionalCoordinate> getSurroundingDimensionalCoordinates(DimensionalCoordinate dimensionalCoordinate) {
		Set<DimensionalCoordinate> surroundingDimensionalCoordinates = new HashSet<>();
		
		List<Long> dimensionChanges = Arrays.asList(-1L, 0L, 1L);
		
		List<Long> dimensions = dimensionalCoordinate.getDimensions();
		for(Long dimensionChange : dimensionChanges) {
			List<Long> newDimensions = new ArrayList<>();
			createDimensionalCoordinate(0, newDimensions, dimensions, dimensionChange, dimensionChanges, surroundingDimensionalCoordinates);
		}
		surroundingDimensionalCoordinates.remove(dimensionalCoordinate);
		
		return surroundingDimensionalCoordinates;
	}
	
	static void createDimensionalCoordinate(int index, List<Long> newDimensions, List<Long> dimensions, Long dimensionChange, List<Long> dimensionChanges, Set<DimensionalCoordinate> surroundingDimensionalCoordinates) {
		newDimensions.add(dimensions.get(index) + dimensionChange);
		
		if(newDimensions.size() == dimensions.size()) {
			surroundingDimensionalCoordinates.add(new DimensionalCoordinate(newDimensions));
		} 
		
		if(index < dimensions.size() - 1) {
			for(Long nextDimensionChange : dimensionChanges) {
				createDimensionalCoordinate(index+1, new ArrayList<>(newDimensions), dimensions, nextDimensionChange, dimensionChanges, surroundingDimensionalCoordinates);
			}
		}
	}


}
