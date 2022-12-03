package year2020.day14;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class Day14 {
	
//	private static final String FILENAME = "src/main/resources/2020/day14InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day14Input.txt";
	
	public static Map<Long, Long> memory = new HashMap<>();
	public static String mask;
	
	public static void main(String[] args) throws IOException {
		processFile();
	}

	private static void processFile() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		lines.forEach(Day14::processLine);
		
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
		
		String valueBinary = StringUtils.leftPad(Long.toBinaryString(value), 36, '0');
		
		long masked = applyMask(valueBinary);
		
		memory.put(address, masked);

		System.out.println("address: " + address);
		System.out.println("value: " + value);
		System.out.println("masked: " + masked);
	}
	
	private static long applyMask(String valueBinary) {
		StringBuilder resultBinary = new StringBuilder();
		for(int i = 0; i < valueBinary.length(); i++) {
			char valueCharacter = valueBinary.charAt(i);
			char maskCharacter = mask.charAt(i);
			
			if(maskCharacter == 'X') {
				resultBinary.append(valueCharacter);
			} if(maskCharacter == '1') {
				resultBinary.append('1');
			} if(maskCharacter == '0') {
				resultBinary.append('0');
			}
		}
		
		return Long.parseLong(resultBinary.toString(), 2);		
	}

}
