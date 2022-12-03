package year2021.day3;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day3 {

//	private static final String FILENAME = "src/main/resources/2021/day3InputSample.txt";
	private static final String FILENAME = "src/main/resources/2021/day3Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<String> lines = readLines();
		
		lines.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		StringBuilder gammaRateBuilder = new StringBuilder();
		StringBuilder epsilonRateBuilder = new StringBuilder();
		for(int i = 0; i < lines.iterator().next().length(); i++) {
			Long zeroBitCount = 0L;
			Long oneBitCount = 0L;
			for(String line : lines) {
				if(line.charAt(i) == '0') {
					zeroBitCount++;
				} else if(line.charAt(i) == '1') {
					oneBitCount++;
				} else {
					throw new RuntimeException("unknown bit");
				}
			}
			
			if(zeroBitCount > oneBitCount) {
				gammaRateBuilder.append("0");
				epsilonRateBuilder.append("1");
			} else {
				gammaRateBuilder.append("1");
				epsilonRateBuilder.append("0");
			}
		}
		
		String gammaRateBinary = gammaRateBuilder.toString();
		Long gammaRate = Long.parseLong(gammaRateBinary, 2);

		String epsilonRateBinary = epsilonRateBuilder.toString();
		Long epsilonRate = Long.parseLong(epsilonRateBinary, 2);

		System.out.println(gammaRateBinary);
		System.out.println(gammaRate);
		System.out.println(epsilonRateBinary);
		System.out.println(epsilonRate);
		System.out.println(gammaRate * epsilonRate);
	}

	private static List<String> readLines() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.collect(Collectors.toList());
	}

}
