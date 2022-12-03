package year2020.day9;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day9 {
	
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
		
		System.out.println(numbers.get(i));
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
