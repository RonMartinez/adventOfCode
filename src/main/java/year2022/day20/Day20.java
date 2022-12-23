package year2022.day20;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day20 {

	private static final String FILENAME = "src/main/resources/2022/day20InputSample.txt";
//	private static final String FILENAME = "src/main/resources/2022/day20Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<Number> numbers = readNumbers();
		System.out.println(numbers.size());
		System.out.println(Integer.toString(numbers.stream().map(Number::getValue).max(Comparator.naturalOrder()).orElse(null)));
		System.out.println(Integer.toString(numbers.stream().map(Number::getValue).min(Comparator.naturalOrder()).orElse(null)));
		
		numbers.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		List<Number> originalNumbers = new ArrayList<>(numbers);
		
		mixNumbers(numbers, originalNumbers);
//		mixNumbers2(numbers, originalNumbers);
		
		Number zeroNumber = numbers.stream()
				.filter(n -> n.getValue() == 0)
				.findFirst().orElse(null);


		Number _1000thNumber = null;
		Number _2000thNumber = null;
		Number _3000thNumber = null;
		int count = 0;
		int i = numbers.indexOf(zeroNumber);
		while(count <= 3000) {
			Number number = numbers.get(i);
			if(count == 1000) {
				_1000thNumber = number;
			}
			if(count == 2000) {
				_2000thNumber = number;
			}
			if(count == 3000) {
				_3000thNumber = number;
			}
			count++;
			i++;
			if(i == numbers.size()) {
				i = 0;
			}
		}
		
		System.out.println(_1000thNumber.getValue());
		System.out.println(_2000thNumber.getValue());
		System.out.println(_3000thNumber.getValue());
		
		System.out.println(_1000thNumber.getValue() + _2000thNumber.getValue() + _3000thNumber.getValue());
		
		System.out.println("done");
	}

	private static void mixNumbers(List<Number> numbers, List<Number> originalNumbers) {
		int size = originalNumbers.size();
		for(Number originalNumber : originalNumbers) {
			int value = originalNumber.getValue();
			int currentIndex = numbers.indexOf(originalNumber);
			
			int sum = currentIndex + value;
			if(sum < 0) {
				sum--;
			}
			int newIndex = Math.floorMod(sum, size) + currentIndex;

			numbers.remove(originalNumber);
			numbers.add(newIndex, originalNumber);

			System.out.println(numbers.stream().map(n -> Integer.toString(n.getValue())).collect(Collectors.joining(", ")));
		}
	}
	
	private static List<Number> readNumbers() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(line -> createNumber(line))
				.collect(Collectors.toList());
	}
	
	private static Number createNumber(String line) {
		return new Number(Integer.valueOf(line));
	}

}
