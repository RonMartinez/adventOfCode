package year2022.day15;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day15b {

//	private static final String FILENAME = "src/main/resources/2022/day15InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day15Input.txt";
	
	public static final String COMMA = ",";
	public static final Long MINUMUM_COORDINATE = 0L;
//	public static final Long MAXIMUM_COORDINATE = 20L;
	public static final Long MAXIMUM_COORDINATE = 4000000L;
	public static final Long MULTIPLIER_X = 4000000L;
	
	public static void main(String[] args) throws IOException {
		TunnelNetwork tunnelNetwork = readTunnelNetwork();
		
		System.out.println(ReflectionToStringBuilder.toString(tunnelNetwork, ToStringStyle.MULTI_LINE_STYLE));

		Long targetX = null;
		Long targetY = null;
		for(long y = MINUMUM_COORDINATE; y <= MAXIMUM_COORDINATE; y++) {
			Set<Range<Long>> ranges = new HashSet<>();
			for(SensorBeacon sensorBeacon : tunnelNetwork.getSensorBeacons()) {
				Long minX = sensorBeacon.getMinX(y);
				Long maxX = sensorBeacon.getMaxX(y);
				if(minX != null
						&& maxX != null
						) {
					if(minX < MINUMUM_COORDINATE) {
						minX = MINUMUM_COORDINATE;
					}
					
					if(maxX > MAXIMUM_COORDINATE) {
						maxX = MAXIMUM_COORDINATE;
					}
					ranges.add(Range.between(minX, maxX));
				}
			}
			List<Range<Long>> sortedRanges = ranges.stream()
					.sorted(Comparator.comparing(Range::getMinimum))
					.collect(Collectors.toList());
			
			Long previousMaximum = null;
			for(Range<Long> range : sortedRanges) {
				Long minimum = range.getMinimum();
				Long maximum = range.getMaximum();
				if(previousMaximum != null
						&& minimum > previousMaximum + 1) {
					targetX = minimum-1;
					break;
				}
				
				if(previousMaximum == null
						|| maximum > previousMaximum) {
					previousMaximum = maximum;
				}
			}
			if(targetX != null) {
				targetY = y;
				break;
			}
		}

		System.out.println(targetX);
		System.out.println(targetY);
		System.out.println(targetX * MULTIPLIER_X + targetY);
		
		System.out.println("done");
	}
	
	private static TunnelNetwork readTunnelNetwork() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		TunnelNetwork tunnelNetwork = new TunnelNetwork();
		
		lines.forEach(line -> createAndAddSensorBeacon(line, tunnelNetwork));
		
		return tunnelNetwork;
	}

	private static void createAndAddSensorBeacon(String line, TunnelNetwork tunnelNetwork) {
		SensorBeacon sensorBeacon = createSensorBeacon(line);
		tunnelNetwork.addSensorBeacon(sensorBeacon);
	}

	private static SensorBeacon createSensorBeacon(String line) {
		line = line.replaceAll("Sensor at x=", "");
		line = line.replaceAll(", y=", ",");
		line = line.replaceAll(": closest beacon is at x=", ",");
		
		String[] split = line.split(COMMA);
		
		return new SensorBeacon(
				new Coordinate(Long.valueOf(split[0]), Long.valueOf(split[1])),
				new Coordinate(Long.valueOf(split[2]), Long.valueOf(split[3]))
				);
	}

}
