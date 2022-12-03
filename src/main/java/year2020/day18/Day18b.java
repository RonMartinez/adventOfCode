package year2020.day18;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day18b {

//	private static final String FILENAME = "src/main/resources/2020/day18InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day18Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<Long> results = calculateResults();
		
		Long sum = results.stream()
				.mapToLong(Long::longValue)
				.sum();
		
		System.out.println(sum);
	}

	private static List<Long> calculateResults() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		return lines.stream()
				.map(Day18b::calculateResult)
				.collect(Collectors.toList());
	}

	private static Long calculateResult(String line) {
		System.out.println(line);
		
		while(line.contains("(")) {
			line = processParentheses(line);
		}
		
		line = processExpression(line);
		
		System.out.println(line);
		
		return Long.parseLong(line);
	}

	private static String processParentheses(String line) {
	    int closingParenthesis = line.indexOf(")");
	    int openingParenthesis = line.lastIndexOf("(", closingParenthesis);
	    String parenthesisResult = processExpression(line.substring(openingParenthesis + 1, closingParenthesis));
	    return line.substring(0, openingParenthesis) + parenthesisResult + line.substring(closingParenthesis + 1); 
	}

	private static String processExpression(String expression) {
		List<String> tokens = new ArrayList<>(Arrays.asList(expression.split(" "))); 
		
		while(tokens.contains(Operation.ADD.getSign())) {
			int index = tokens.indexOf(Operation.ADD.getSign());
			long operand1 = Long.parseLong(tokens.get(index - 1));
			long operand2 = Long.parseLong(tokens.get(index + 1));
			tokens.set(index - 1, Long.toString(operand1 + operand2));
			tokens.remove(index+1);
			tokens.remove(index);
		}

		while(tokens.contains(Operation.MULTIPLY.getSign())) {
			int index = tokens.indexOf(Operation.MULTIPLY.getSign());
			long operand1 = Long.parseLong(tokens.get(index - 1));
			long operand2 = Long.parseLong(tokens.get(index + 1));
			tokens.set(index - 1, Long.toString(operand1 * operand2));
			tokens.remove(index+1);
			tokens.remove(index);
		}

	    return tokens.iterator().next().toString();
	}

}
