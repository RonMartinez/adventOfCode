package year2022.day11;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day11 {

//	private static final String FILENAME = "src/main/resources/2022/day11InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day11Input.txt";

	public static final String OLD = "old";
	public static final String OPERAND_MULITPLY = "*";
	public static final String OPERAND_ADD = "+";
	public static final Long RELIEF_DIVISOR = 3L;
	public static final Long ITERATIONS = 20L;
	public static final Long NUMBER_OF_MONKEYS_TO_CHASE = 2L;

	public static void main(String[] args) throws IOException {
		MonkeyGroup monkeyGroup = readMonkeyGroup();
		
		monkeyGroup.getMonkeys().forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		for(long i = 0; i < ITERATIONS; i++) {
			monkeyGroup.processMonkeys();
		}
		
		List<Monkey> mostActiveMonkeys = monkeyGroup.getMonkeys().stream()
				.sorted(Monkey.TIMES_INSPECTED_COMPARATOR.reversed())
				.limit(NUMBER_OF_MONKEYS_TO_CHASE)
				.collect(Collectors.toList());
		
		Long monkeyBusinessLevel = calculateMonkeyBusinessLevel(mostActiveMonkeys);
			
		System.out.println(monkeyBusinessLevel);
		
		System.out.println("done");
	}

	private static Long calculateMonkeyBusinessLevel(List<Monkey> mostActiveMonkeys) {
		Long monkeyBusinessLevel = null;
		if( ! mostActiveMonkeys.isEmpty()) {
			monkeyBusinessLevel = 1L;
			for(int i = 0; i < mostActiveMonkeys.size(); i++) {
				monkeyBusinessLevel = monkeyBusinessLevel * mostActiveMonkeys.get(i).getTimesInspected();
			}
		}
		return monkeyBusinessLevel;
	}

	private static MonkeyGroup readMonkeyGroup() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		MonkeyGroup monkeyGroup = new MonkeyGroup();
		
		Iterator<String> iterator = lines.iterator();
		while(iterator.hasNext()) {
			processLine(monkeyGroup, iterator);
		}
		
		return monkeyGroup;
	}

	private static void processLine(MonkeyGroup monkeyGroup, Iterator<String> iterator) {
		String line = iterator.next();
		if(StringUtils.isNotBlank(line)) {
			monkeyGroup.addMonkey(createMonkey(iterator));	
		}
	}
	
	private static Monkey createMonkey(Iterator<String> iterator) {
		Monkey monkey = new Monkey();
		createItems(iterator.next()).forEach(i -> monkey.addItem(i));
		monkey.setOperation(createOperation(iterator.next()));
		monkey.setTest(createTest(iterator.next()));
		monkey.setTrueThrowToMonkeyIndex(createThrowToMonkeyIndex(iterator.next()));
		monkey.setFalseThrowToMonkeyIndex(createThrowToMonkeyIndex(iterator.next()));
		monkey.setReliefOperation(createReliefOperation());
		
		return monkey;
	}

	private static Operation createReliefOperation() {
		return new DivideOperation(RELIEF_DIVISOR);
	}

	private static int createThrowToMonkeyIndex(String testString) {
		List<String> split = Arrays.asList(testString.split(" "));
		
		return Integer.valueOf(split.get(split.size()-1));
	}

	private static Test createTest(String testOperationString) {
		List<String> split = Arrays.asList(testOperationString.split(" "));
		
		return new DivisibleByTest(Long.valueOf(split.get(split.size()-1)));
	}

	private static Operation createOperation(String operationString) {
		List<String> split = Arrays.asList(operationString.split(" "));
		
		String operand = split.get(split.size()-2);
		String valueString = split.get(split.size()-1);
		
		Long value = null;
		if( ! valueString.equals(OLD)) {
			value = Long.valueOf(valueString);
		}

		Operation operation;
		if(operand.equals(OPERAND_ADD)) {
			operation = new DoubleOperation();
			if(value != null) {
				operation = new AddOperation(value);
			}
		} else if(operand.equals(OPERAND_MULITPLY)) {
			operation = new PowerOperation();
			if(value != null) {
				operation = new MultiplyOperation(value);
			}
		} else {
			throw new RuntimeException("unknown operation: " + operationString);
		}
		return operation;
	}
	
	private static List<Item> createItems(String itemsString) {
		String cleanedItemsString = itemsString.replaceAll("  Starting items: ", "");
		String[] split = cleanedItemsString.split(", ");
		return Arrays.asList(split).stream()
				.map(s -> new Item(Long.valueOf(s)))
				.collect(Collectors.toList());
	}

}
