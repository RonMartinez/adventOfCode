package year2022.day1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FoodLog {
	
	public static final Comparator<FoodLog> TOTAL_CALORIES_COMPARATOR = Comparator.comparing(FoodLog::getTotalCalories);
	
	private List<Long> calories;
	private Long totalCalories;
	
	public void addCalorie(Long calorie) {
		getCalories().add(calorie);
		setTotalCalories(calculateTotalCalories());
	}

	private Long calculateTotalCalories() {
		return getCalories().stream()
				.reduce(0L, Long::sum);
	}

	public List<Long> getCalories() {
		if(calories == null) {
			calories = new ArrayList<>();
		}
		return calories;
	}

	public void setCalories(List<Long> calories) {
		this.calories = calories;
	}

	public Long getTotalCalories() {
		return totalCalories;
	}

	public void setTotalCalories(Long totalCalories) {
		this.totalCalories = totalCalories;
	}

}
