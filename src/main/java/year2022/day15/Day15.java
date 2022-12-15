package year2022.day15;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day15 {

//	private static final String FILENAME = "src/main/resources/2022/day15InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day15Input.txt";
	
	public static final String COMMA = ",";
//	public static final Long ROW = 10L;
	public static final Long ROW = 2000000L;
	
	public static void main(String[] args) throws IOException {
		TunnelNetwork tunnelNetwork = readTunnelNetwork();
		
		System.out.println(ReflectionToStringBuilder.toString(tunnelNetwork, ToStringStyle.MULTI_LINE_STYLE));
		
		Long coveredCount = tunnelNetwork.getCoveredCoordinateCount(ROW);
		
		System.out.println(coveredCount);
		
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
