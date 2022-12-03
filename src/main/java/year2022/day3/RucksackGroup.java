package year2022.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RucksackGroup {

	private List<Rucksack> rucksacks;
	private Character commonItem;
	
	public RucksackGroup(List<Rucksack> rucksacks) {
		this.rucksacks = rucksacks;
		this.commonItem = calculateCommonItem();
	}
	
	private Character calculateCommonItem() {
		Character commonItem = null;

		List<String> rucksackItems = rucksacks.stream()
				.map(rucksack -> rucksack.getFirstHalfItems().concat(rucksack.getSecondHalfItems()))
				.collect(Collectors.toList());
	
		for(char item0 : rucksackItems.get(0).toCharArray()) {
			if(containsItem(rucksackItems.get(1), item0)
					&& containsItem(rucksackItems.get(2), item0)
					) {
				commonItem = item0;
				break;
			}
		}

		return commonItem;
	}

	private boolean containsItem(String items, char itemToFind) {
		boolean found = false;
		for(char item : items.toCharArray()) {
			if(item == itemToFind) {
				found = true;
				break;
			}
		}
		return found;
	}



	public List<Rucksack> getRucksacks() {
		if(rucksacks == null) {
			rucksacks = new ArrayList<>();
		}
		return rucksacks;
	}

	public void setRucksacks(List<Rucksack> rucksacks) {
		this.rucksacks = rucksacks;
	}

	public Character getCommonItem() {
		return commonItem;
	}

	public void setCommonItem(Character commonItem) {
		this.commonItem = commonItem;
	}


}
