package year2023.day9;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

import util.ListHelper;

public class Day9 {

//	private static final String FILENAME = "src/main/resources/2023/day9InputSample.txt";
	private static final String FILENAME = "src/main/resources/2023/day9Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<String> lines = readLines();
		
		List<Sequence> sequences = lines.stream()
				.map(Day9::processLine)
				.collect(Collectors.toList());
		
		Long result = sequences.stream()
				.map(s -> ListHelper.getLast(s.getValues()))
				.reduce(0L, Long::sum);
		
		System.out.println(result);
		
		System.out.println("done");
	}

	private static Sequence processLine(String line) {
		String[] lineSplit = line.split("\\s+");

		List<Long> values = new ArrayList<>();
		values = Arrays.stream(lineSplit)
				.map(Long::valueOf)
				.collect(Collectors.toList());
		
		return new Sequence(values);
	}

	private static List<String> readLines() throws IOException {
		return IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
	}

}
