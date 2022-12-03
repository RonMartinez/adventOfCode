package year2021.day6;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day6b {

//	private static final String FILENAME = "src/main/resources/2021/day6InputSample.txt";
	private static final String FILENAME = "src/main/resources/2021/day6Input.txt";
	
	private static List<LanternfishGroup> lanternfishGroups = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		readLines();
		
		lanternfishGroups.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		for(int i = 0; i < 256; i++) {
			long spawnedCount = 0L;
			for(LanternfishGroup lanternfishGroup : lanternfishGroups) {
				spawnedCount += lanternfishGroup.decrement();
			}
			LanternfishGroup lanternfishGroup = lanternfishGroups.stream()
					.filter(lfg -> lfg.getTimer() == 8L)
					.findFirst().orElse(null);
			
			if(lanternfishGroup != null) {
				lanternfishGroup.incrementCount(spawnedCount);
			} else {
				lanternfishGroups.add(new LanternfishGroup(Long.valueOf(8L), spawnedCount));
			}
		}
		
		long count = lanternfishGroups.stream()
				.map(lfg -> lfg.getCount())
				.reduce(0L, Long::sum);
		
		System.out.println(count);
	}
	
	private static void readLines() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		List<String> timerStrings = new ArrayList<>(Arrays.asList(StringUtils.split(lines.iterator().next(), ",")));
		
		timerStrings.forEach(Day6b::createLanternfishGroup);
	}

	private static void createLanternfishGroup(String timerString) {
		long timer = Long.valueOf(timerString);
		
		LanternfishGroup lanternfishGroup = lanternfishGroups.stream()
				.filter(lfg -> lfg.getTimer() == timer)
				.findFirst().orElse(null);
		
		if(lanternfishGroup != null) {
			lanternfishGroup.incrementCount(1L);
		} else {
			lanternfishGroups.add(new LanternfishGroup(Long.valueOf(timerString), 1L));
		}
	}

}
