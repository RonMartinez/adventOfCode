package year2023.day1;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import util.ListHelper;

public class Day1 {

//	private static final String FILENAME = "src/main/resources/2023/day1InputSample.txt";
	private static final String FILENAME = "src/main/resources/2023/day1Input.txt";

	public static void main(String[] args) throws IOException {
		List<String> lines = readLines();
		
		List<Long> calibrationValues = lines.stream()
				.map(Day1::findCalibrationValue)
				.collect(Collectors.toList());
		
		Long result = calibrationValues.stream()
				.reduce(0L, Long::sum);
		
		System.out.println(result);
		
		System.out.println("done");
	}

	private static List<String> readLines() throws IOException {
		return IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
	}

	private static Long findCalibrationValue(String line) {
		List<String> digits = line.codePoints()
				.mapToObj(c -> String.valueOf((char) c))
				.filter(StringUtils::isNumeric)
				.collect(Collectors.toList());

		return Long.valueOf(digits.get(0) + ListHelper.getLast(digits)); 
	}
	
}
