package year2021.day6;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day6 {

//	private static final String FILENAME = "src/main/resources/2021/day6InputSample.txt";
	private static final String FILENAME = "src/main/resources/2021/day6Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<Lanternfish> lanternfishes = readLines();
		
		lanternfishes.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		for(int i = 0; i < 80; i++) {
			List<Lanternfish> spawnedLanternfishes = new ArrayList<>();
			for(Lanternfish lanternfish : lanternfishes) {
				Lanternfish spawnedLanternfish = lanternfish.decrement();
				if(spawnedLanternfish != null) {
					spawnedLanternfishes.add(spawnedLanternfish);
				}
			}
			lanternfishes.addAll(spawnedLanternfishes);
		}
		
		System.out.println(lanternfishes.size());
	}
	
	private static List<Lanternfish> readLines() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		List<String> timerStrings = new ArrayList<>(Arrays.asList(StringUtils.split(lines.iterator().next(), ",")));
		
		return timerStrings.stream()
				.map(Day6::createLanternfish)
				.collect(Collectors.toList());
	}

	private static Lanternfish createLanternfish(String timerString) {
		return new Lanternfish(Long.valueOf(timerString));
	}

}
