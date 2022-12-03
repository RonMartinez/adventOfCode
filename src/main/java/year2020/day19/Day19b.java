package year2020.day19;

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
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day19b {

//	private static final String FILENAME = "src/main/resources/2020/day19bInputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day19bInput.txt";
	
	private static List<String> messages = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		Map<Long, Rule> rules = readRules();
		
		rules.values().forEach(rule -> expandRule(rule, rules));
		
		rules.values().forEach(r -> System.out.println(ReflectionToStringBuilder.toString(r, ToStringStyle.MULTI_LINE_STYLE)));
		
		String regex = rules.get(0L).getRegex();
		
		List<String> validMessages = messages.stream()
				.filter(m -> m.matches(regex))
				.collect(Collectors.toList());
		
		System.out.println(validMessages.size());
	}
	
	private static String expandRule(Rule rule, Map<Long, Rule> rules) {
		String ruleText = rule.getRuleText();
		
		String[] split = ruleText.split(" \\| ");
		
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for(String ruleDefinition : split) {
			sb.append("(");
			sb.append(expandRule(ruleDefinition, rules, rule));
			sb.append(")|");
		}
		if(StringUtils.right(sb.toString(), 1).equals("|")) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append(")");
		
		String result = sb.toString();
		
		rule.setRegex(result);
		
		return rule.getRegex();
	}

	private static String expandRule(String ruleDefinition, Map<Long, Rule> rules, Rule parentRule) {
		if(ruleDefinition.matches("\".*\"")) {
			return ruleDefinition.replaceAll("\"", "");
		}

		String[] split = ruleDefinition.split(" ");

		
		/*
		 * Rule 11 is supposed to be this:
		 * 
		 * 11: 42 31 | 42 11 31
		 * 
		 * My initial cut was 42+31+, but they need to have the same number of occurences.  How?
		 * 
		 * Idea: loop to an arbitrary number, say 10, and generate 42{1}31{1} | 42{2}31{2} | 42{3}31{3} etc.  How shitty of a solution. 
		 * 
		 * Instead of implementing the idea, I manually expanded the rule in the input to 42 31 | 42 42 31 31 | 42 42 42 31 31 31 etc until 10 numbers (and commented out my inner loop code below).  I'm a monster I know.
		 * 
		 */
		
		boolean innerLoop = false;
		boolean outerLoop = false;
		for(int i = 0; i < split.length; i++) {
			String ruleNumber = split[i];
			Rule rule = rules.get(Long.parseLong(ruleNumber));
			if(parentRule.equals(rule)) {
				if(i < split.length - 1) {
					innerLoop = true;
				} else {
					outerLoop = true;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(String ruleNumber : split) {
			Rule rule = rules.get(Long.parseLong(ruleNumber));
			if( ! parentRule.equals(rule)) {
				sb.append(expandRule(rule, rules));	
//				if(innerLoop) {
//					sb.append("+");
//				}
			}
		}
		
		if(outerLoop) {
			sb.append("+");	
		}
		
		return sb.toString();
	}

	private static Map<Long, Rule> readRules() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		Map<Long, Rule> rules = new HashMap<>();
		int i = 0;
		String line = lines.get(i);
		while((StringUtils.isNotBlank(line))) {
			addRule(line, rules);
			i++;
			line = lines.get(i);
		}

		i++;
		while(i < lines.size()) {
			messages.add(lines.get(i));
			i++;
		}
		
		return rules;
	}

	private static Rule addRule(String line, Map<Long, Rule> rules) {
		String[] split = line.split(": ");
		
		long index = Long.parseLong(split[0]);
		
		Rule rule = new Rule();
		rule.setRuleText(split[1]);

		rules.put(index, rule);
		
		return rule;
	}


}
