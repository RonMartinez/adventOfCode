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

public class Day10bNoCreate {
	
//	private static final String FILENAME = "src/main/resources/2020/day10InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day10Input.txt";
	
	public static final long TOLERANCE = 3;
	
	public static Map<List<Long>, Long> numbersCountMap = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		List<Long> numbers = readNumbers();

		numbers.add(0L);
		
		numbers.sort(Comparator.naturalOrder());
		
		long deviceJoltage = numbers.get(numbers.size() - 1) + TOLERANCE;
		
		numbers.add(deviceJoltage);
		
		long count = createNode(numbers);
		
		System.out.println(count);
	}

	private static long createNode(List<Long> numbers) {
		if(numbersCountMap.containsKey(numbers)) {
			return numbersCountMap.get(numbers);
		}
		
		long count = 0;
		if(numbers.size() == 1) {
			count = 1;
		} else {
			int i = 1;
			while(i < numbers.size()
					&& numbers.get(i) - numbers.get(0) <= TOLERANCE) {
				i++;
			}
			
			for(int j = 1; j < i; j++) {
				count += createNode(numbers.subList(j, numbers.size()));
			}
		}
		
		numbersCountMap.putIfAbsent(numbers, count);
		
		return count;
	}

	private static List<Long> readNumbers() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(Long::parseLong)
				.collect(Collectors.toList());
	}

}
