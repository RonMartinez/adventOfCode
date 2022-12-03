package year2020.day16;

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

public class Day16b {
	
//	private static final String FILENAME = "src/main/resources/2020/day16bInputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day16Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<String> lines = readLines();
		
		List<Rule> rules = new ArrayList<>();
		
		int i = 0;
		String line = lines.get(i);
		while(i < lines.size()
				&& StringUtils.isNotBlank(line)
				) {
			rules.add(createRule(line));
			
			i++;
			line = lines.get(i);
		}
		
		rules.forEach(rule -> {
			System.out.println(ReflectionToStringBuilder.toString(rule, ToStringStyle.MULTI_LINE_STYLE));
			rule.getRanges().forEach(range -> {
				System.out.println(ReflectionToStringBuilder.toString(range, ToStringStyle.MULTI_LINE_STYLE));
			});
		});

		i++;
		List<Ticket> tickets = new ArrayList<>();
		while(i < lines.size()) {
			line = lines.get(i);
			if(StringUtils.isNotBlank(line)
					&& ! "your ticket:".equals(line)
					&& ! "nearby tickets:".equals(line)
					) {
				tickets.add(createTicket(line));
			}
			
			i++;
		}

		tickets.forEach(ticket -> {
			System.out.println(ReflectionToStringBuilder.toString(ticket, ToStringStyle.MULTI_LINE_STYLE));
		});
		
		List<Ticket> validTickets = tickets.subList(1, tickets.size()).stream()
				.filter(ticket -> isValid(ticket, rules))
				.collect(Collectors.toList());
		
		Map<Rule, Integer> ruleMap = new HashMap<>();
		while( ruleMap.keySet().size() < validTickets.get(0).getNumbers().size()) {
			for(int j = 0; j < validTickets.get(0).getNumbers().size(); j++) {
				if( ! ruleMap.containsValue(j)) {
					List<Long> numbers = new ArrayList<>();
					for(Ticket validTicket : validTickets) {
						numbers.add(validTicket.getNumbers().get(j));
					}
					
					List<Rule> applicableRules = rules.stream()
							.filter(r -> ! ruleMap.containsKey(r))
							.filter(r -> isApplicable(r, numbers))
							.collect(Collectors.toList());
					
					if(applicableRules.size() == 1) {
						ruleMap.put(applicableRules.get(0), j);
					}
				}
			}
		}

		ruleMap.keySet().forEach(key -> {
				int index = ruleMap.get(key);
				System.out.println("index: " + index);
				System.out.println("rule: " + key.getField());
		});
		
		Ticket myTicket = tickets.get(0);
		List<Rule> departureRules = rules.stream()
				.filter(rule -> rule.getField().startsWith("departure"))
				.collect(Collectors.toList());
		
		List<Long> departureNumbers = departureRules.stream()
				.map(dr -> myTicket.getNumbers().get(ruleMap.get(dr)))
				.collect(Collectors.toList());
		
		System.out.println("departureNumbers.size(): " + departureNumbers.size());
		
		long product = 1L;
		for(Long number : departureNumbers) {
			product *= number;
		}
				
		System.out.println(product);
	}

	private static boolean isApplicable(Rule rule, List<Long> numbers) {
		return numbers.stream()
				.allMatch(n -> isValid(n, rule));
	}

	private static boolean isValid(Ticket ticket, List<Rule> rules) {
		return ticket.getNumbers().stream()
				.allMatch(number -> isValid(number, rules));
	}

	private static boolean isValid(Long n, List<Rule> rules) {
		return rules.stream()
				.anyMatch(rule -> isValid(n, rule));
	}

	private static boolean isValid(Long n, Rule rule) {
		return rule.getRanges().stream()
				.anyMatch(range -> n >= range.getMin() && n <= range.getMax());
	}

	private static Ticket createTicket(String line) {
		System.out.println(line);
		String[] split = line.split(",");
		
		Ticket ticket = new Ticket();
		List<Long> numbers = new ArrayList<>();
		for(String numberString : split) {
			
			numbers.add(Long.parseLong(numberString));
		}
		ticket.setNumbers(numbers);

		return ticket;
	}

	private static Rule createRule(String line) {
		String[] split = line.split(": ");
		
		String field = split[0];
		
		Rule rule = new Rule();
		rule.setField(field);
		
		String[] rangeSplit = split[1].split(" or ");
		List<Range> ranges = new ArrayList<>();
		for(String rangeDefinition : rangeSplit) {
			ranges.add(createRange(rangeDefinition));
		}
		rule.setRanges(ranges);
		return rule;
	}

	private static Range createRange(String rangeDefinition) {
		String[] rangeDefinitionSplit = rangeDefinition.split("-");
		
		Range range = new Range();
		range.setMin(Long.parseLong(rangeDefinitionSplit[0]));
		range.setMax(Long.parseLong(rangeDefinitionSplit[1]));
		
		return range;
	}

	private static List<String> readLines() throws IOException {
		return IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
	}

}
