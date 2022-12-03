package year2022.day3;

public class Rucksack {

	private String firstHalfItems;
	private String secondHalfItems;
	private Character commonItem;
	
	public Rucksack(String firstHalfItems, String secondHalfItems) {
		this.firstHalfItems = firstHalfItems;
		this.secondHalfItems = secondHalfItems;
		this.commonItem = calculateCommonItem();
	}

	private Character calculateCommonItem() {
		Character commonItem = null;
		
		for(char firstHalfItem : firstHalfItems.toCharArray()) {
			if(secondHalfContainsCharacter(firstHalfItem)) {
				commonItem = firstHalfItem;
				break;
			}
		}

		return commonItem;
	}

	private boolean secondHalfContainsCharacter(char firstHalfItem) {
		boolean found = false;
		for(char secondHalfItem : secondHalfItems.toCharArray()) {
			if(firstHalfItem == secondHalfItem) {
				found = true;
				break;
			}
		}
		return found;
	}

	public String getFirstHalfItems() {
		return firstHalfItems;
	}

	public void setFirstHalfItems(String firstHalfItems) {
		this.firstHalfItems = firstHalfItems;
	}

	public String getSecondHalfItems() {
		return secondHalfItems;
	}

	public void setSecondHalfItems(String secondHalfItems) {
		this.secondHalfItems = secondHalfItems;
	}

	public Character getCommonItem() {
		return commonItem;
	}

	public void setCommonItem(Character commonItem) {
		this.commonItem = commonItem;
	}

}
