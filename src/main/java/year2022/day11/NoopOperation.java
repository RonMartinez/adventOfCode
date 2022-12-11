package year2022.day11;

public class NoopOperation implements Operation {
	
	@Override
	public Long execute(Item item) {
		return item.getWorryLevel();
	}

}
