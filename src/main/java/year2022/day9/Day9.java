package year2022.day9;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day9 {

//	private static final String FILENAME = "src/main/resources/2022/day9InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day9Input.txt";

	public static void main(String[] args) throws IOException {
		List<Motion> motions = readMotions();
		
		motions.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));

		Knot head = new Knot(new Coordinate(0L, 0L));
		Knot tail = new Knot(new Coordinate(0L, 0L));
		
		Rope rope = new Rope(head, tail);
		motions.forEach(rope::processMotion);
		
		int tailPositionsVisited = tail.getVisitedMap().keySet().size();
		
		System.out.println(tailPositionsVisited);
		
		System.out.println("done");
	}

	private static List<Motion> readMotions() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(line -> createMotion(line))
				.collect(Collectors.toList());
	}
	
	private static Motion createMotion(String line) {
		String[] split = line.split(" ");
		
		String directionCode = split[0];
		Direction direction = Direction.UP;
		if(Direction.DOWN.getCode().equals(directionCode)) {
			direction = Direction.DOWN;
		} else if(Direction.LEFT.getCode().equals(directionCode)) {
			direction = Direction.LEFT;
		} else if(Direction.RIGHT.getCode().equals(directionCode)) {
			direction = Direction.RIGHT;
		}
		
		Long steps = Long.valueOf(split[1]);
		
		return new Motion(direction, steps);
	}

}
