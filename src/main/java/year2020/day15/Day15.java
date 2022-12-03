package year2020.day15;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

public class Day15 {
	
//	private static final String FILENAME = "src/main/resources/2020/day15InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day15Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<Long> startingNumbers = readStartingNumbers();
		
		Map<Long, Long> numberTurnMap = new HashMap<>();
		
		startingNumbers.forEach(o -> System.out.println(o));
		for(int i = 0; i < startingNumbers.size(); i++) {
			numberTurnMap.put(startingNumbers.get(i), (long) i+1);
		}
		
		long lastNumber = startingNumbers.get(startingNumbers.size() - 1);
		int i = startingNumbers.size() + 1;
		boolean lastNumberInMap = false; 
		while(i <= 30000000) {
			if(lastNumberInMap) {
				long lastTurn = numberTurnMap.get(lastNumber);
				numberTurnMap.put(lastNumber, (long) i-1);
				
				lastNumber = i - 1 - lastTurn;
				numberTurnMap.putIfAbsent(lastNumber, (long) i);
			} else {
				lastNumber = 0;
			}
			System.out.println("Turn " + i + ": " + lastNumber);
			i++;
			lastNumberInMap = numberTurnMap.containsKey(lastNumber);
		}
		
		System.out.println(lastNumber);
	}

	private static List<Long> readStartingNumbers() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		List<Long> startingNumbers = new ArrayList<>();
		for(String s : lines.get(0).split(",")) {
			startingNumbers.add(Long.parseLong(s));
		}
		return startingNumbers;
	}

}
