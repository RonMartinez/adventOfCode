package util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class DimensionalCoordinateHelperTest {
	
	@Test
	public void test2dAdjacent() {
		DimensionalCoordinate dimensionalCoordinate2d = new DimensionalCoordinate(Arrays.asList(0L, 0L));
		Set<DimensionalCoordinate> actual = DimensionalCoordinateHelper.getAdjacentDimensionalCoordinates(dimensionalCoordinate2d);
		Set<DimensionalCoordinate> expected = new HashSet<>(Arrays.asList(
				new DimensionalCoordinate(Arrays.asList(-1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, -1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 1L))
				));
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void test2dSurrounding() {
		DimensionalCoordinate dimensionalCoordinate2d = new DimensionalCoordinate(Arrays.asList(0L, 0L));
		Set<DimensionalCoordinate> actual = DimensionalCoordinateHelper.getSurroundingDimensionalCoordinates(dimensionalCoordinate2d);
		Set<DimensionalCoordinate> expected = new HashSet<>(Arrays.asList(
				new DimensionalCoordinate(Arrays.asList(-1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(0L, -1L)),
				new DimensionalCoordinate(Arrays.asList(1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 0L)),
				//new DimensionalCoordinate(Arrays.asList(0L, 0L)),
				new DimensionalCoordinate(Arrays.asList(1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 1L)),
				new DimensionalCoordinate(Arrays.asList(1L, 1L))
				));
		assertEquals(expected, actual);
	}

	@Test
	public void test3dAdjacent() {
		DimensionalCoordinate dimensionalCoordinate2d = new DimensionalCoordinate(Arrays.asList(0L, 0L, 0L));
		Set<DimensionalCoordinate> actual = DimensionalCoordinateHelper.getAdjacentDimensionalCoordinates(dimensionalCoordinate2d);
		Set<DimensionalCoordinate> expected = new HashSet<>(Arrays.asList(
				new DimensionalCoordinate(Arrays.asList(-1L, 0L, 0L)),
				new DimensionalCoordinate(Arrays.asList(1L, 0L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, -1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, 1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, 0L, -1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 0L, 1L))
				));
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void test3dSurrounding() {
		DimensionalCoordinate dimensionalCoordinate2d = new DimensionalCoordinate(Arrays.asList(0L, 0L, 0L));
		Set<DimensionalCoordinate> actual = DimensionalCoordinateHelper.getSurroundingDimensionalCoordinates(dimensionalCoordinate2d);
		Set<DimensionalCoordinate> expected = new HashSet<>(Arrays.asList(
				new DimensionalCoordinate(Arrays.asList(-1L, -1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(0L, -1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(1L, -1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 0L, -1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 0L, -1L)),
				new DimensionalCoordinate(Arrays.asList(1L, 0L, -1L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(1L, 1L, -1L)),
				
				new DimensionalCoordinate(Arrays.asList(-1L, -1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, -1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(1L, -1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 0L, 0L)),
				//new DimensionalCoordinate(Arrays.asList(0L, 0L, 0L)),
				new DimensionalCoordinate(Arrays.asList(1L, 0L, 0L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, 1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(1L, 1L, 0L)),

				new DimensionalCoordinate(Arrays.asList(-1L, -1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(0L, -1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(1L, -1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 0L, 1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 0L, 1L)),
				new DimensionalCoordinate(Arrays.asList(1L, 0L, 1L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(1L, 1L, 1L))
				));
		assertEquals(expected, actual);
	}

	@Test
	public void test4dAdjacent() {
		DimensionalCoordinate dimensionalCoordinate2d = new DimensionalCoordinate(Arrays.asList(0L, 0L, 0L, 0L));
		Set<DimensionalCoordinate> actual = DimensionalCoordinateHelper.getAdjacentDimensionalCoordinates(dimensionalCoordinate2d);
		Set<DimensionalCoordinate> expected = new HashSet<>(Arrays.asList(
				new DimensionalCoordinate(Arrays.asList(-1L, 0L, 0L, 0L)),
				new DimensionalCoordinate(Arrays.asList(1L, 0L, 0L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, -1L, 0L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, 1L, 0L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, 0L, -1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, 0L, 1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, 0L, 0L, -1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 0L, 0L, 1L))
				));
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void test4dSurrounding() {
		DimensionalCoordinate dimensionalCoordinate2d = new DimensionalCoordinate(Arrays.asList(0L, 0L, 0L, 0L));
		Set<DimensionalCoordinate> actual = DimensionalCoordinateHelper.getSurroundingDimensionalCoordinates(dimensionalCoordinate2d);
		Set<DimensionalCoordinate> expected = new HashSet<>(Arrays.asList(
				new DimensionalCoordinate(Arrays.asList(-1L, -1L, -1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(0L, -1L, -1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(1L, -1L, -1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 0L, -1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 0L, -1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(1L, 0L, -1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 1L, -1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 1L, -1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(1L, 1L, -1L, -1L)),
				
				new DimensionalCoordinate(Arrays.asList(-1L, -1L, 0L, -1L)),
				new DimensionalCoordinate(Arrays.asList(0L, -1L, 0L, -1L)),
				new DimensionalCoordinate(Arrays.asList(1L, -1L, 0L, -1L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 0L, 0L, -1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 0L, 0L, -1L)),
				new DimensionalCoordinate(Arrays.asList(1L, 0L, 0L, -1L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 1L, 0L, -1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 1L, 0L, -1L)),
				new DimensionalCoordinate(Arrays.asList(1L, 1L, 0L, -1L)),

				new DimensionalCoordinate(Arrays.asList(-1L, -1L, 1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(0L, -1L, 1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(1L, -1L, 1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 0L, 1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 0L, 1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(1L, 0L, 1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 1L, 1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 1L, 1L, -1L)),
				new DimensionalCoordinate(Arrays.asList(1L, 1L, 1L, -1L)),

				new DimensionalCoordinate(Arrays.asList(-1L, -1L, -1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, -1L, -1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(1L, -1L, -1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 0L, -1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, 0L, -1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(1L, 0L, -1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 1L, -1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, 1L, -1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(1L, 1L, -1L, 0L)),
				
				new DimensionalCoordinate(Arrays.asList(-1L, -1L, 0L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, -1L, 0L, 0L)),
				new DimensionalCoordinate(Arrays.asList(1L, -1L, 0L, 0L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 0L, 0L, 0L)),
				//new DimensionalCoordinate(Arrays.asList(0L, 0L, 0L, 0L)),
				new DimensionalCoordinate(Arrays.asList(1L, 0L, 0L, 0L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 1L, 0L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, 1L, 0L, 0L)),
				new DimensionalCoordinate(Arrays.asList(1L, 1L, 0L, 0L)),

				new DimensionalCoordinate(Arrays.asList(-1L, -1L, 1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, -1L, 1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(1L, -1L, 1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 0L, 1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, 0L, 1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(1L, 0L, 1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 1L, 1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(0L, 1L, 1L, 0L)),
				new DimensionalCoordinate(Arrays.asList(1L, 1L, 1L, 0L)),

				new DimensionalCoordinate(Arrays.asList(-1L, -1L, -1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(0L, -1L, -1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(1L, -1L, -1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 0L, -1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 0L, -1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(1L, 0L, -1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 1L, -1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 1L, -1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(1L, 1L, -1L, 1L)),
				
				new DimensionalCoordinate(Arrays.asList(-1L, -1L, 0L, 1L)),
				new DimensionalCoordinate(Arrays.asList(0L, -1L, 0L, 1L)),
				new DimensionalCoordinate(Arrays.asList(1L, -1L, 0L, 1L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 0L, 0L, 1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 0L, 0L, 1L)),
				new DimensionalCoordinate(Arrays.asList(1L, 0L, 0L, 1L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 1L, 0L, 1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 1L, 0L, 1L)),
				new DimensionalCoordinate(Arrays.asList(1L, 1L, 0L, 1L)),

				new DimensionalCoordinate(Arrays.asList(-1L, -1L, 1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(0L, -1L, 1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(1L, -1L, 1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 0L, 1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 0L, 1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(1L, 0L, 1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(-1L, 1L, 1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(0L, 1L, 1L, 1L)),
				new DimensionalCoordinate(Arrays.asList(1L, 1L, 1L, 1L))
				));
		assertEquals(expected, actual);
	}

}
