package year2022.day1;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day1 {

//	private static final String FILENAME = "src/main/resources/2022/day1InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day1Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<FoodLog> foodLogs = readFoodLogs();
		
		foodLogs.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		FoodLog maxFoodLog = foodLogs.stream()
				.sorted(FoodLog.TOTAL_CALORIES_COMPARATOR.reversed())
				.findFirst().orElse(null);
		
		System.out.println(maxFoodLog.getTotalCalories().toString());
		
		System.out.println("done");
	}

	private static List<FoodLog> readFoodLogs() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		List<FoodLog> foodLogs = new ArrayList<>();
		
		FoodLog foodLog = new FoodLog();
		for(String line : lines) {
			if(StringUtils.isBlank(line)) {
				foodLogs.add(foodLog);
				foodLog = new FoodLog();
			} else {
				foodLog.addCalorie(Long.valueOf(line));
			}
		}
		foodLogs.add(foodLog);
		
		return foodLogs;
	}

}
