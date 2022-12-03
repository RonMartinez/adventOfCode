package year2022.day3;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day3 {

//	private static final String FILENAME = "src/main/resources/2022/day3InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day3Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<Rucksack> rucksacks = readRucksacks();
		
		rucksacks.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		Integer totalPriority = rucksacks.stream()
				.map(r -> getPriority(r.getCommonItem()))
				.reduce(0, Integer::sum);
		
		System.out.println(totalPriority);
		
		System.out.println("done");
	}
	
	private static int getPriority(Character item) {
		int offset = -9;
		if(Character.isUpperCase(item)) {
			offset += 26;
		}
		
		return Character.getNumericValue(item) + offset;
	}

	private static List<Rucksack> readRucksacks() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(line -> createRucksack(line))
				.collect(Collectors.toList());
	}

	private static Rucksack createRucksack(String line) {
		String firstHalf = line.substring(0, line.length()/2);
		String secondHalf = line.substring(line.length()/2, line.length());
		
		return new Rucksack(firstHalf, secondHalf);
	}

}
