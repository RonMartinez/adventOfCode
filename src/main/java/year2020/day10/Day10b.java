package year2020.day10;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day10b {
	
//	private static final String FILENAME = "src/main/resources/2020/day10InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day10Input.txt";
	
	public static final long TOLERANCE = 3;
	
	public static long leafNodeCount = 0;
	
	public static void main(String[] args) throws IOException {
		List<Long> numbers = readNumbers();

		numbers.add(0L);
		
		numbers.sort(Comparator.naturalOrder());
		
		long deviceJoltage = numbers.get(numbers.size() - 1) + TOLERANCE;
		
		numbers.add(deviceJoltage);
		
		Node rootNode = createNode(numbers);
		
		System.out.println(leafNodeCount);
	}

	private static Node createNode(List<Long> numbers) {
		if(numbers.size() == 1) {
			leafNodeCount++; //coward Ron!  Traverse your Node object graph afterwards :)
			Node rootNode = new Node();
			rootNode.setNumber(numbers.get(0));
			return rootNode;
		}
		
		Node rootNode = new Node();
		rootNode.setNumber(numbers.get(0));
		
		int i = 1;
		while(i < numbers.size()
				&& numbers.get(i) - numbers.get(0) <= TOLERANCE) {
			i++;
		}
		for(int j = 1; j < i; j++) {
			rootNode.addChildNode(createNode(numbers.subList(j, numbers.size())));
		}
		
		return rootNode;
	}

	private static List<Long> readNumbers() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(Long::parseLong)
				.collect(Collectors.toList());
	}

}
