package year2023.day1;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

import util.ListHelper;

public class Day1b {

//	private static final String FILENAME = "src/main/resources/2023/day1bInputSample.txt";
	private static final String FILENAME = "src/main/resources/2023/day1Input.txt";

	public static void main(String[] args) throws IOException {
		List<String> lines = readLines();
		
		List<Long> calibrationValues = lines.stream()
				.map(Day1b::findCalibrationValue)
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
		String regex = "(?=(one|two|three|four|five|six|seven|eight|nine|[0-9]))";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(line);
		
		List<String> digitStrings = new ArrayList<>();
		while(matcher.find()) {
			digitStrings.add(matcher.group(1));
		}
		
		List<String> digits = digitStrings.stream()
				.map(Day1b::convertDigitStringToDigit)
				.collect(Collectors.toList());

		return Long.valueOf(digits.get(0) + ListHelper.getLast(digits)); 
	}

	private static String convertDigitStringToDigit(String digitString) {
		String digit = digitString;
		
		if("one".equals(digitString)) {
			digit = "1";
		} else if("two".equals(digitString)) {
			digit = "2";
		} else if("three".equals(digitString)) {
			digit = "3";
		} else if("four".equals(digitString)) {
			digit = "4";
		} else if("five".equals(digitString)) {
			digit = "5";
		} else if("six".equals(digitString)) {
			digit = "6";
		} else if("seven".equals(digitString)) {
			digit = "7";
		} else if("eight".equals(digitString)) {
			digit = "8";
		} else if("nine".equals(digitString)) {
			digit = "9";
		}
		
		return digit; 
	}

}
