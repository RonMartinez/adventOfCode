package year2020.day16;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day16 {
	
//	private static final String FILENAME = "src/main/resources/2020/day16InputSample.txt";
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
		
		List<Long> invalidNumbers = tickets.subList(1, tickets.size()).stream()
				.flatMap(t -> t.getNumbers().stream())
				.filter(n -> ! isValid(n, rules))
				.collect(Collectors.toList());
				
		Long sum = invalidNumbers.stream()
				.mapToLong(Long::longValue)
				.sum();
		
		System.out.println(sum);
	}

	private static boolean isValid(Long n, List<Rule> rules) {
		return rules.stream()
				.flatMap(r -> r.getRanges().stream())
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
