package year2022.day11;

public class AddOperation implements Operation {
	
	private Long value;
	
	public AddOperation(Long value) {
		this.value = value;
	}

	@Override
	public Long execute(Item item) {
		return item.getWorryLevel() + value;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	
}
