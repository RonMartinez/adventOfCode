package year2021.day1;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day1b {

//	private static final String FILENAME = "src/main/resources/2021/day1InputSample.txt";
	private static final String FILENAME = "src/main/resources/2021/day1Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<Measurement> measurements = readMeasurements();
		
		measurements.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		int depthIncreases = 0;
		Long lastMeasurmentSum = null;
		Measurement lastMeasurement = null;
		for(int i = 0; i < measurements.size()-2; i++) {
			Long measurmentSum = measurements.get(i).getDepth()
					+ measurements.get(i+1).getDepth()
					+ measurements.get(i+2).getDepth();
						
			if(lastMeasurmentSum != null
					&& lastMeasurmentSum < measurmentSum) {
				depthIncreases++;	
			}
			
			lastMeasurmentSum = measurmentSum;
		}
		
		System.out.println(depthIncreases);
	}

	private static List<Measurement> readMeasurements() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(line -> createMeasurement(line))
				.collect(Collectors.toList());
	}

	private static Measurement createMeasurement(String line) {
		Measurement measurement = new Measurement();
		measurement.setDepth(Long.valueOf(line));
		return measurement;
	}

}
