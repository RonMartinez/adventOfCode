package year2022.day11;

public class MultiplyOperation implements Operation {
	
	private Long value;
	
	public MultiplyOperation(Long value) {
		this.value = value;
	}

	@Override
	public Long execute(Item item) {
		return item.getWorryLevel() * value;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	
}
