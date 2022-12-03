package year2020.day18;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day18 {

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
				.map(Day18::calculateResult)
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
		String[] tokens = expression.split(" ");
		
	    Long result = Long.parseLong(tokens[0]);
	    
	    Operation nextOperation = null;
	    
	    Set<Operation> operations = new HashSet<>();
	    operations.add(Operation.ADD);
	    operations.add(Operation.MULTIPLY);
	    
	    for (int i = 1; i < tokens.length; i++) {
	    	String token = tokens[i];

	    	Optional<Operation> optional = operations.stream()
	    			.filter(o -> o.getSign().equals(token))
	    			.findFirst();
	    	if(optional.isPresent()) {
	    		nextOperation = optional.get();
	    	} else {
	    		Long value = Long.parseLong(token);
	    		if(Operation.ADD.equals(nextOperation)) {
	    			result += value;
	    		} else if(Operation.MULTIPLY.equals(nextOperation)) {
	    			result *= value;
	    		}
	    	}
	    }
	    
	    return result.toString();
	}

}
