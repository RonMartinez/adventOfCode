package year2020.day9;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day9b {
	
//	private static final String FILENAME = "src/main/resources/2020/day9InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day9Input.txt";
	
	public static final int PREAMBLE = 25;
	
	public static void main(String[] args) throws IOException {
		List<Long> numbers = readNumbers();
		
		int i = PREAMBLE;
		while(i < numbers.size()) {
			long number = numbers.get(i);
			List<Long> preambleList = numbers.subList(i-PREAMBLE, i);
			if( ! findSum(number, preambleList) ) {
				break;
			}
			
			i++;
		}
		
		long invalidNumber = numbers.get(i);
		System.out.println(numbers.get(i));
		
		List<Long> contiguousList = findContiguousList(invalidNumber, numbers);
		
		contiguousList.forEach(n -> System.out.println(n));
		
		Collections.sort(contiguousList);
		
		contiguousList.forEach(n -> System.out.println(n));
		
		long smallestNumber = contiguousList.get(0);
		long largestNumber = contiguousList.get(contiguousList.size() - 1);
		
		System.out.println(smallestNumber);
		System.out.println(largestNumber);
		System.out.println(smallestNumber + largestNumber);
	}
	
	private static List<Long> findContiguousList(long invalidNumber, List<Long> numbers) {
		boolean found = false;
		int i = 0;
		int j = 0;
		while(i < numbers.size()
				&& !found) {
			j = i + 1;
			
			long sum = numbers.get(i) + numbers.get(j);
			while(sum < invalidNumber) {
				j++;
				sum += numbers.get(j);
			}
			
			if(sum == invalidNumber) {
				found = true;
				break;
			}
			
			i++;
		}
		
		return numbers.subList(i, j + 1);
	}

	private static boolean findSum(long number, List<Long> preambleList) {
		boolean found = false;
		for(int i = 0; i < preambleList.size() && !found; i++) {
			for(int j = i+1; j < preambleList.size() && !found; j++) {
				if(preambleList.get(i) + preambleList.get(j) == number) {
					found = true;
				}
			}
		}
		
		return found;
	}

	private static List<Long> readNumbers() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(Long::parseLong)
				.collect(Collectors.toList());
	}

}
