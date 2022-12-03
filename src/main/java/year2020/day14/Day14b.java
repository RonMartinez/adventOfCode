package year2020.day14;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class Day14b {
	
//	private static final String FILENAME = "src/main/resources/2020/day14bInputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day14Input.txt";
	
	public static Map<Long, Long> memory = new HashMap<>();
	public static String mask;
	
	public static void main(String[] args) throws IOException {
		processFile();
	}

	private static void processFile() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		lines.forEach(Day14b::processLine);
		
		long sum = memory.values().stream()
				.reduce(0L, Long::sum);
		
		System.out.println(sum);
	}

	private static void processLine(String line) {
		if(line.matches("mask = .*")) {
			processMask(line);
		} else {
			processMemory(line);
		}
	}

	private static void processMask(String line) {
		mask = line.split(" = ")[1];
		System.out.println("mask: " + mask);
	}

	private static void processMemory(String line) {
		String[] lineSplit = line.split(" = ");
		
		String addressString = lineSplit[0];
		addressString = addressString.replaceAll("mem\\[", "");
		addressString = addressString.replaceAll("\\]", "");
		
		Long address = Long.parseLong(addressString);
		Long value = Long.parseLong(lineSplit[1]);
		
		String addressBinary = StringUtils.leftPad(Long.toBinaryString(address), 36, '0');
		
		List<Long> memoryAddresses = applyMask(addressBinary);

		System.out.println("value: " + value);
		for(Long memoryAddress : memoryAddresses) {
			memory.put(memoryAddress, value);
			System.out.println("memoryAddress: " + memoryAddress);	
		}
	}
	
	private static List<Long> applyMask(String addressBinary) {
		List<StringBuilder> memoryAddresses = new ArrayList<>();
		memoryAddresses.add(new StringBuilder());
		for(int i = 0; i < addressBinary.length(); i++) {
			char valueCharacter = addressBinary.charAt(i);
			char maskCharacter = mask.charAt(i);
			
			if(maskCharacter == 'X') {
				List<StringBuilder> newMemoryAddresses = new ArrayList<>();
				for(StringBuilder memoryAddress : memoryAddresses) {
					StringBuilder newMemoryAddress = new StringBuilder(memoryAddress);
					newMemoryAddresses.add(newMemoryAddress);
					
					memoryAddress.append("0");
					newMemoryAddress.append("1");
				}
				
				newMemoryAddresses.forEach(nma -> memoryAddresses.add(nma));
			} if(maskCharacter == '1') {
				memoryAddresses.forEach(sb -> sb.append("1"));
			} if(maskCharacter == '0') {
				memoryAddresses.forEach(sb -> sb.append(valueCharacter));
			}
		}
		
		return memoryAddresses.stream()
				.map(sb -> Long.parseLong(sb.toString(), 2))
				.collect(Collectors.toList());
	}

}
