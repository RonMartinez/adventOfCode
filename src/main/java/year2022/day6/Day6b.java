package year2022.day6;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day6b {

//	private static final String FILENAME = "src/main/resources/2022/day6InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day6Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<Datastream> datastreams = readDatastreams();
		
		datastreams.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		datastreams.forEach(d -> System.out.println(d.getMessageMarkers().iterator().next()));
		
		System.out.println("done");
	}

	private static List<Datastream> readDatastreams() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(line -> createDatastream(line))
				.collect(Collectors.toList());
	}
	
	private static Datastream createDatastream(String line) {
		return new Datastream(line);
	}

}
