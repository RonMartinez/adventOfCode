package year2022.day11;

public class Item {
	
	private Long worryLevel;
	private Monkey monkey;
	
	public Item(Long worryLevel) {
		this.worryLevel = worryLevel;
	}

	public Long getWorryLevel() {
		return worryLevel;
	}

	public void setWorryLevel(Long worryLevel) {
		this.worryLevel = worryLevel;
	}

	public Monkey getMonkey() {
		return monkey;
	}

	public void setMonkey(Monkey monkey) {
		this.monkey = monkey;
	}

}
