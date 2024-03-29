package year2023.day8;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

public class Day8 {

//	private static final String FILENAME = "src/main/resources/2023/day8InputSample.txt";
//	private static final String FILENAME = "src/main/resources/2023/day8InputSample2.txt";
	private static final String FILENAME = "src/main/resources/2023/day8Input.txt";
	
	public static List<String> instructions;
	public static Map<String, Element> elementMap = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		List<String> lines = readLines();
		
		processLines(lines);
		
		Element currentElement = elementMap.get("AAA");
		
		long steps = 0L;
		int i = 0;
		while( ! "ZZZ".equals(currentElement.getName())) {
			String instruction = instructions.get(i);
			
			if("L".equals(instruction)) {
				currentElement = currentElement.getLeftElement();
			} else {
				currentElement = currentElement.getRightElement();
			}
			
			steps++;
			
			i++;
			if(i >= instructions.size()) {
				i = 0;
			}
		}
		
		System.out.println(steps);

		System.out.println("done");
	}

	private static void processLines(List<String> lines) {
		String instructionsString = lines.get(0);
		
		instructions = new ArrayList<>();
		for(char c : instructionsString.toCharArray()) {
			String instruction = String.valueOf(c);
			instructions.add(instruction);
		}
		
		for(int i = 2; i < lines.size(); i++) {
			String line = lines.get(i);
			
			String[] lineSplit = line.split(" = ");
			
			String name = lineSplit[0];
			
			Element existingElement = elementMap.computeIfAbsent(name, k -> new Element(k));
			
			String[] nextElementsSplit = lineSplit[1].replace("(", "").replace(")", "").split(", ");
			
			String leftElementName = nextElementsSplit[0];
			Element leftElement = elementMap.computeIfAbsent(leftElementName, k -> new Element(k));
			
			String rightElementName = nextElementsSplit[1];
			Element rightElement = elementMap.computeIfAbsent(rightElementName, k -> new Element(k));

			existingElement.setLeftElement(leftElement);
			existingElement.setRightElement(rightElement);
		}
	}

	private static List<String> readLines() throws IOException {
		return IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
	}

}
