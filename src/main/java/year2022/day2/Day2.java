package year2022.day2;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day2 {

//	private static final String FILENAME = "src/main/resources/2022/day2InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day2Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<RpsMatch> rpsMatches = readRpsMatches();
		
		rpsMatches.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		Long totalOverallScore = rpsMatches.stream()
				.map(RpsMatch::getTotalScore)
				.reduce(0L, Long::sum);
		
		System.out.println(totalOverallScore);
		
		System.out.println("done");
	}

	private static List<RpsMatch> readRpsMatches() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(line -> createRpsMatch(line))
				.collect(Collectors.toList());
	}

	private static RpsMatch createRpsMatch(String line) {
		List<String> encodedShapes = Arrays.asList(line.split(" "));
		
		String encodedShape1 = encodedShapes.get(0);
		String encodedShape2 = encodedShapes.get(1);
		RpsShape rpsShape1 = calculateRpsShape1(encodedShape1);
		RpsShape rpsShape2 = calculateRpsShape2(encodedShape2);
		
		RpsMatch rpsMatch = new RpsMatch(rpsShape1, rpsShape2);
		
		return rpsMatch;
	}

	private static RpsShape calculateRpsShape1(String encodedShape) {
		RpsShape rpsShape = RpsShape.SCISSORS;
		if("A".equals(encodedShape)) {
			rpsShape = RpsShape.ROCK;
		} else if("B".equals(encodedShape)) {
			rpsShape = RpsShape.PAPER;
		}
		return rpsShape;
	}

	private static RpsShape calculateRpsShape2(String encodedShape) {
		RpsShape rpsShape = RpsShape.SCISSORS;
		if("X".equals(encodedShape)) {
			rpsShape = RpsShape.ROCK;
		} else if("Y".equals(encodedShape)) {
			rpsShape = RpsShape.PAPER;
		}
		return rpsShape;
	}

}
