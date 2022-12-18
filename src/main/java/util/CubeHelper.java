package util;

import java.util.HashSet;
import java.util.Set;

public class CubeHelper {

	public static long getManhattanDistance(Cube cube1, Cube cube2) {
		return Math.abs(cube1.getX() - cube2.getX())
				+ Math.abs(cube1.getY() - cube2.getY())
				+ Math.abs(cube1.getZ() - cube2.getZ())
				;
	}
	
	public static Set<Cube> getAdjacentCubes(Cube cube) {
		Long x = cube.getX();
		Long y = cube.getY();
		Long z = cube.getZ();
		
		Set<Cube> adjacentCubes = new HashSet<>();
		adjacentCubes.add(new Cube(x + 1, y, z));
		adjacentCubes.add(new Cube(x - 1, y, z));
		adjacentCubes.add(new Cube(x, y + 1, z));
		adjacentCubes.add(new Cube(x, y - 1, z));
		adjacentCubes.add(new Cube(x, y, z + 1));
		adjacentCubes.add(new Cube(x, y, z - 1));
		
		return adjacentCubes;
	}

	public static Set<Cube> getSurroundingCubes(Cube cube) {
		Long x = cube.getX();
		Long y = cube.getY();
		Long z = cube.getZ();
		
		Set<Cube> surroundingCubes = new HashSet<>();
		for(long xChange = -1; xChange <= 1; xChange++) {
			for(long yChange = -1; yChange <= 1; yChange++) {
				for(long zChange = -1; zChange <= 1; zChange++) {
					surroundingCubes.add(new Cube(x + xChange, y + yChange, z + zChange));
				}
			}
		}
		surroundingCubes.remove(cube);
		
		return surroundingCubes;
	}


}
