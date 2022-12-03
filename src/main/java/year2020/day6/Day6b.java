package year2020.day6;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class Day6b {
	
//	private static final String FILENAME = "src/main/resources/2020/day6InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day6Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<AnswerGroup> answerGroups = readAnswerGroups();

//		answerGroups.stream()
//				.forEach(ag -> System.out.println(ReflectionToStringBuilder.toString(ag, ToStringStyle.MULTI_LINE_STYLE)));

		long sum = answerGroups.stream()
				.mapToLong(ag -> countEveryoneAnswered(ag))
				.sum();
		
		System.out.println(sum);
	}
	
	private static long countEveryoneAnswered(AnswerGroup answerGroup) {
		return answerGroup.getAnswerMap().keySet().stream()
				.filter(k -> answerGroup.getAnswerMap().get(k) == answerGroup.getGroupSize())
				.count();
	}

	private static List<AnswerGroup> readAnswerGroups() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		List<AnswerGroup> answerGroups = new ArrayList<>();
		
		AnswerGroup answerGroup = null;
		
		for(String line : lines) {
			if(StringUtils.isBlank(line)) {
				answerGroup = addAnswerGroup(answerGroups, answerGroup);
			} else {
				if(answerGroup == null) {
					answerGroup = new AnswerGroup();
					answerGroup.setGroupSize(0);
				}
				answerGroup.setGroupSize(answerGroup.getGroupSize()+1);
				processLine(answerGroup, line);
			}
		}
		answerGroup = addAnswerGroup(answerGroups, answerGroup);
		
		return answerGroups;
	}

	private static AnswerGroup addAnswerGroup(List<AnswerGroup> answerGroups, AnswerGroup answerGroup) {
		if(answerGroup != null) {
			answerGroups.add(answerGroup);
			answerGroup = null;
		}
		return answerGroup;
	}

	private static void processLine(AnswerGroup answerGroup, String line) {
		for(Character character : line.toCharArray()) {
			processCharacter(answerGroup, character);
		}
	}

	private static void processCharacter(AnswerGroup answerGroup, Character character) {
		Map<Character, Integer> answerMap = answerGroup.getAnswerMap();
		int previousCount = 0;
		if(answerMap.containsKey(character)) {
			previousCount = answerMap.get(character); 
		}
		answerMap.put(character, previousCount+1);
	}


}
