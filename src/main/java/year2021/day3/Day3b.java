package year2021.day3;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day3b {

//	private static final String FILENAME = "src/main/resources/2021/day3InputSample.txt";
	private static final String FILENAME = "src/main/resources/2021/day3Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<String> lines = readLines();
		
		lines.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		String oxygenGeneratorRatingBinary = calculateOxygenGeneratorRating(lines, 0);
		Long oxygenGeneratorRating = Long.parseLong(oxygenGeneratorRatingBinary, 2);

		String co2ScrubberRatingBinary = calculateCo2ScrubberRating(lines, 0);
		Long co2ScrubberRating = Long.parseLong(co2ScrubberRatingBinary, 2);

		System.out.println(oxygenGeneratorRatingBinary);
		System.out.println(oxygenGeneratorRating);

		System.out.println(co2ScrubberRatingBinary);
		System.out.println(co2ScrubberRating);
		
		System.out.println(oxygenGeneratorRating* co2ScrubberRating);
	}
	
	private static String calculateOxygenGeneratorRating(List<String> lines, int bit) {
		if(lines.size() == 1) {
			return lines.iterator().next();
		}
		
		Long zeroBitCount = 0L;
		Long oneBitCount = 0L;
		for(String line : lines) {
			if(line.charAt(bit) == '0') {
				zeroBitCount++;
			} else if(line.charAt(bit) == '1') {
				oneBitCount++;
			} else {
				throw new RuntimeException("unknown bit");
			}
		}

		List<String> remainingLines = null;		 
		if(zeroBitCount > oneBitCount) {
			remainingLines = lines.stream()
					.filter(l -> l.charAt(bit) == '0')
					.collect(Collectors.toList());
		} else {
			remainingLines = lines.stream()
					.filter(l -> l.charAt(bit) == '1')
					.collect(Collectors.toList());
		}
		
		return calculateOxygenGeneratorRating(remainingLines, bit+1);
	}

	private static String calculateCo2ScrubberRating(List<String> lines, int bit) {
		if(lines.size() == 1) {
			return lines.iterator().next();
		}
		
		Long zeroBitCount = 0L;
		Long oneBitCount = 0L;
		for(String line : lines) {
			if(line.charAt(bit) == '0') {
				zeroBitCount++;
			} else if(line.charAt(bit) == '1') {
				oneBitCount++;
			} else {
				throw new RuntimeException("unknown bit");
			}
		}

		List<String> remainingLines = null;		 
		if(oneBitCount < zeroBitCount) {
			remainingLines = lines.stream()
					.filter(l -> l.charAt(bit) == '1')
					.collect(Collectors.toList());
		} else {
			remainingLines = lines.stream()
					.filter(l -> l.charAt(bit) == '0')
					.collect(Collectors.toList());
		}
		
		return calculateCo2ScrubberRating(remainingLines, bit+1);
	}

	private static List<String> readLines() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.collect(Collectors.toList());
	}

}
