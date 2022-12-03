package year2020.day10;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day10 {
	
//	private static final String FILENAME = "src/main/resources/2020/day10InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day10Input.txt";
	
	public static final long ADAPTER_TOLERANCE = -3;
	public static final long DEVICE_TOLERANCE = 3;
	
	public static void main(String[] args) throws IOException {
		List<Long> numbers = readNumbers();

		numbers.add(0L);
		
		numbers.sort(Comparator.naturalOrder());
		
		long deviceJoltage = numbers.get(numbers.size() - 1) + DEVICE_TOLERANCE;
		
		numbers.add(deviceJoltage);

		Map<Long, Long> differencesMap = new HashMap<>();
		for(int i = 1; i < numbers.size(); i++) {
			Long difference = numbers.get(i) - numbers.get(i-1);
			if( ! differencesMap.containsKey(difference)) {
				differencesMap.put(difference, 1L);
			} else {
				Long previousCount = differencesMap.get(difference);
				differencesMap.put(difference, previousCount+1);
			}
		}
		
		for(Long key : differencesMap.keySet()) {
			System.out.println("key: " + key);
			System.out.println("count: " + differencesMap.get(key));
		}
		
		System.out.println(differencesMap.get(1L) * differencesMap.get(3L));
	}
	
	private static List<Long> readNumbers() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(Long::parseLong)
				.collect(Collectors.toList());
	}

}
