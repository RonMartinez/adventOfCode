package year2020.day6;

import java.util.HashMap;
import java.util.Map;

public class AnswerGroup {
	private int groupSize = 0;
	private Map<Character, Integer> answerMap = new HashMap<>();

	public int getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	public Map<Character, Integer> getAnswerMap() {
		return answerMap;
	}

	public void setAnswerMap(Map<Character, Integer> answerMap) {
		this.answerMap = answerMap;
	}

}
