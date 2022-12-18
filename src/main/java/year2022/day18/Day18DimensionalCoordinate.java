package year2022.day18;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import util.DimensionalCoordinate;
import util.DimensionalCoordinateHelper;

public class Day18DimensionalCoordinate {

//	private static final String FILENAME = "src/main/resources/2022/day18InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day18Input.txt";
	
	public static void main(String[] args) throws IOException {
		Set<DimensionalCoordinate> dimensionalCoordinates = readDimensionalCoordinates();
		
		dimensionalCoordinates.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		Long count = 0L;
		for(DimensionalCoordinate dimensionalCoordinate : dimensionalCoordinates) {
			Set<DimensionalCoordinate> adjacentDimensionalCoordinates = DimensionalCoordinateHelper.getAdjacentDimensionalCoordinates(dimensionalCoordinate);
			for(DimensionalCoordinate adjacentDimensionalCoordinate : adjacentDimensionalCoordinates) {
				if( ! dimensionalCoordinates.contains(adjacentDimensionalCoordinate)) {
					count++;
				}
			}
		}
		
		System.out.println(count);
		
		System.out.println("done");
	}

	private static Set<DimensionalCoordinate> readDimensionalCoordinates() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(line -> createDimensionalCoordinate(line))
				.collect(Collectors.toSet());
	}

	private static DimensionalCoordinate createDimensionalCoordinate(String line) {
		String[] split = line.split(",");
		
		return new DimensionalCoordinate(Arrays.asList(Long.valueOf(split[0]), Long.valueOf(split[1]), Long.valueOf(split[2])));
	}

}
