package year2020.day21;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day21 {

//	private static final String FILENAME = "src/main/resources/2020/day21InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day21Input.txt";
	
	private static Map<Allergen, Set<Ingredient>> ALLERGEN_MAP = new HashMap<>();
	private static List<Ingredient> INGREDIENTS = new ArrayList<>();
	private static Set<Allergen> ALLERGENS = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		readIngredients();
		
		boolean change = true;
		while(change) {
			change = false;
			
			Set<Allergen> singleAllergens = ALLERGEN_MAP.keySet().stream()
					.filter(k -> ALLERGEN_MAP.get(k).size() == 1)
					.collect(Collectors.toSet());

			for(Allergen singleAllergen : singleAllergens) {
				for(Allergen allergen : ALLERGEN_MAP.keySet()) {
					Set<Ingredient> ingredients = ALLERGEN_MAP.get(allergen);
					Ingredient singleIngredient = ALLERGEN_MAP.get(singleAllergen).iterator().next();
					if( ! allergen.equals(singleAllergen)
							&& ingredients.contains(singleIngredient)
							) {
						ingredients.remove(singleIngredient);
						change = true;
						
					}
				}
			}
		}

		Set<Allergen> singleAllergens = ALLERGEN_MAP.keySet().stream()
				.filter(k -> ALLERGEN_MAP.get(k).size() == 1)
				.collect(Collectors.toSet());

		Set<Ingredient> allergenIngredients = singleAllergens.stream()
				.flatMap(sa -> ALLERGEN_MAP.get(sa).stream())
				.collect(Collectors.toSet());
		
		Set<Ingredient> nonAllergenIngredients = INGREDIENTS.stream().collect(Collectors.toSet());
		nonAllergenIngredients.removeAll(allergenIngredients);
		
		nonAllergenIngredients.forEach(i -> System.out.println(i.getName()));
		
		int count = 0;
		for(Ingredient originalIngredient : INGREDIENTS) {
			if(nonAllergenIngredients.contains(originalIngredient)) {
				count++;
			}
		}
		
		System.out.println(count);
	}

	private static void readIngredients() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		lines.forEach(Day21::processLine);
	}

	private static void processLine(String line) {
		String[] split = line.split(" \\(contains ");
		
		List<String> ingredientNames = Arrays.asList(split[0].split(" "));
		List<Ingredient> ingredients = ingredientNames.stream()
				.map(Ingredient::new)
				.collect(Collectors.toList());
		
		List<String> allergenNames = Arrays.asList(split[1].replaceAll("\\)", "").split(", "));
		Set<Allergen> allergens = allergenNames.stream()
				.map(Allergen::new)
				.collect(Collectors.toSet());
		
		for(Allergen allergen : allergens) {
			if(ALLERGEN_MAP.containsKey(allergen)) {
				Set<Ingredient> existingIngredients = ALLERGEN_MAP.get(allergen).stream().collect(Collectors.toSet());
				existingIngredients.retainAll(ingredients);
				ALLERGEN_MAP.put(allergen, new HashSet<>(existingIngredients));
			} else {
				ALLERGEN_MAP.put(allergen, new HashSet<>(ingredients));
			}
		}
		
		INGREDIENTS.addAll(ingredients);
		ALLERGENS.addAll(allergens);
	}

}
