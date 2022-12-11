package year2022.day11;

public class DoubleOperation implements Operation {

	@Override
	public Long execute(Item item) {
		return item.getWorryLevel() + item.getWorryLevel();
	}

}
