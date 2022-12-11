package year2022.day11;

public class DivideOperation implements Operation {
	
	private Long value;
	
	public DivideOperation(Long value) {
		this.value = value;
	}

	@Override
	public Long execute(Item item) {
		return item.getWorryLevel() / value;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	
}
