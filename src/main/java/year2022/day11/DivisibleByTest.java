package year2022.day11;

public class DivisibleByTest implements Test {
	
	private ModOperation modOperation;
	
	public DivisibleByTest(Long value) {
		this.modOperation = new ModOperation(value);
	}

	@Override
	public boolean execute(Item item) {
		return modOperation.execute(item) == 0;
	}

	public ModOperation getModOperation() {
		return modOperation;
	}

	public void setModOperation(ModOperation modOperation) {
		this.modOperation = modOperation;
	}

}
