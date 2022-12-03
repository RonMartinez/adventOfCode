package year2020.day1;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day1b {
	
	public static void main(String[] args) throws IOException {
		List<Integer> entries = readEntries();
		
		ExpenseValueObject expenseValueObject = findExpenseValueObject(entries);
		
		System.out.println("result: " + expenseValueObject.product());
	}
	
	private static List<Integer> readEntries() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream("src/main/resources/2020/day1Input.txt"), StandardCharsets.UTF_8);
		
		return lines.stream()
				.map(line -> Integer.parseInt(line))
				.collect(Collectors.toList());
	}

	private static ExpenseValueObject findExpenseValueObject(List<Integer> entries) {
		ExpenseValueObject foundExpenseValueObject = null;
		
		for(int i = 0; i < entries.size() && foundExpenseValueObject == null; i++) {
			for(int j = i+1; j < entries.size() && foundExpenseValueObject == null; j++) {
				for(int k = j+1; k < entries.size() && foundExpenseValueObject == null; k++) {
					ExpenseValueObject expenseValueObject = new ExpenseValueObject(Arrays.asList(
							entries.get(i),
							entries.get(j),
							entries.get(k)
							));

					if(expenseValueObject.sum() == 2020) {
						foundExpenseValueObject = expenseValueObject;
					}
				}
			}
		}
		
		return foundExpenseValueObject;
	}

}
