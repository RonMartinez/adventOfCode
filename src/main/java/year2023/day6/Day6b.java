package year2023.day6;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day6b {

//	private static final String FILENAME = "src/main/resources/2023/day6InputSample.txt";
	private static final String FILENAME = "src/main/resources/2023/day6Input.txt";
	
	private static List<Race> races = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		readLines();
		
		List<List<Long>> listOfWinningHoldTimes = races.stream()
				.map(Race::findWinningHoldTimes)
				.collect(Collectors.toList());
		
		int result = listOfWinningHoldTimes.stream()
				.map(lofwt -> lofwt.size())
				.reduce(1, (a,b) -> a * b);
		
		System.out.println(result);
		
		System.out.println("done");
	}

	private static void readLines() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		String timesLine = lines.get(0);
		timesLine = timesLine.replaceAll("Time\\:\\s+", "");
		timesLine = timesLine.replaceAll("\\s+", "");

		String distancesLine = lines.get(1);
		distancesLine = distancesLine.replaceAll("Distance\\:\\s+", "");
		distancesLine = distancesLine.replaceAll("\\s+", "");

		Race race = new Race(Long.valueOf(timesLine), Long.valueOf(distancesLine));
		races.add(race);
	}

}