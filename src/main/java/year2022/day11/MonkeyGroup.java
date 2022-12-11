package year2022.day11;

import java.util.ArrayList;
import java.util.List;

public class MonkeyGroup {
	
	private List<Monkey> monkeys;
	
	public void processMonkeys() {
		getMonkeys().forEach(Monkey::processItems);
	}
	
	public Monkey getMonkeyByIndex(int index) {
		return getMonkeys().get(index);
	}
	
	public void addMonkey(Monkey monkey) {
		monkey.setMonkeyGroup(this);
		getMonkeys().add(monkey);
	}

	public List<Monkey> getMonkeys() {
		if(monkeys == null) {
			monkeys = new ArrayList<>();
		}
		return monkeys;
	}

	public void setMonkeys(List<Monkey> monkeys) {
		this.monkeys = monkeys;
	}
	
}
