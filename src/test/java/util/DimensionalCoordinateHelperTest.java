package util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DimensionalCoordinateHelperTest {
	
	@Test
	public void test() {
		DimensionalCoordinate dimensionalCoordinate2d = new DimensionalCoordinate(Arrays.asList(0L, 0L));
		Set<DimensionalCoordinate> adjacent = DimensionalCoordinateHelper.getAdjacentDimensionalCoordinates(dimensionalCoordinate2d);
		Assertions.assertEquals(new HashSet<>(), adjacent);
		
//		System.out.println("Adjacent");
//		adjacent.forEach(dc -> System.out.println(dc));
//		System.out.println();
//		Set<DimensionalCoordinate> surrounding = DimensionalCoordinateHelper.getSurroundingDimensionalCoordinates(dimensionalCoordinate2d);
//		System.out.println("Surrounding");
//		surrounding.forEach(dc -> System.out.println(dc));
//		System.out.println();
//
//		DimensionalCoordinate dimensionalCoordinate3d = new DimensionalCoordinate(Arrays.asList(0L, 0L));
//		System.out.println(dimensionalCoordinate3d);
//		Set<DimensionalCoordinate> adjacent3d = DimensionalCoordinateHelper.getAdjacentDimensionalCoordinates(dimensionalCoordinate3d);
//		System.out.println("Adjacent");
//		adjacent3d.forEach(dc -> System.out.println(dc));
//		System.out.println();
//		Set<DimensionalCoordinate> surrounding3d = DimensionalCoordinateHelper.getSurroundingDimensionalCoordinates(dimensionalCoordinate3d);
//		System.out.println("Surrounding");
//		
//		surrounding3d.forEach(dc -> System.out.println(dc));
//		System.out.println();
	}

}
