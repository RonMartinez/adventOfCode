package year2022.day18;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import util.Cube;
import util.CubeHelper;

public class Day18 {

//	private static final String FILENAME = "src/main/resources/2022/day18InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day18Input.txt";
	
	public static void main(String[] args) throws IOException {
		Set<Cube> cubes = readCubes();
		
		cubes.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		Long count = 0L;
		for(Cube cube : cubes) {
			Set<Cube> adjacentCubes = CubeHelper.getAdjacentCubes(cube);
			for(Cube adjacentCube : adjacentCubes) {
				if( ! cubes.contains(adjacentCube)) {
					count++;
				}
			}
		}
		
		System.out.println(count);
		
		System.out.println("done");
	}

	private static Set<Cube> readCubes() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(line -> createCube(line))
				.collect(Collectors.toSet());
	}

	private static Cube createCube(String line) {
		String[] split = line.split(",");
		
		return new Cube(Long.valueOf(split[0]), Long.valueOf(split[1]), Long.valueOf(split[2]));
	}

}
