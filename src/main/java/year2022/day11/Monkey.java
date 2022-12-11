package year2022.day11;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Monkey {
	
	public static final Comparator<Monkey> TIMES_INSPECTED_COMPARATOR = Comparator.comparing(Monkey::getTimesInspected);
	
	private List<Item> items;
	private Operation operation;
	private Test test;
	private int trueThrowToMonkeyIndex;
	private int falseThrowToMonkeyIndex;
	private MonkeyGroup monkeyGroup;
	private Operation reliefOperation;
	private Map<Item, Long> inspectMap;

	public Long getTimesInspected() {
		return getInspectMap().values().stream()
				.reduce(0L, Long::sum);
	}
	
	public void addItem(Item item) {
		item.setMonkey(this);
		getItems().add(item);
	}
	
	public void processItems() {
		Set<Item> itemsToRemove = new HashSet<>();
		for(Item item : items) {
			itemsToRemove.add(processItem(item));
		}
		items.removeAll(itemsToRemove);
	}
	
	private Item processItem(Item item) {
		processOperation(item);
		processReliefOperation(item);
		processTestOperation(item);
		processInspectMap(item);
		return item;
	}

	private void processInspectMap(Item item) {
		Long timesInspected = getInspectMap().getOrDefault(item, 0L);
		getInspectMap().put(item, timesInspected+1);
	}

	private void processTestOperation(Item item) {
		if(test.execute(item)) {
			getMonkeyGroup().getMonkeyByIndex(trueThrowToMonkeyIndex).addItem(item);
		} else {
			getMonkeyGroup().getMonkeyByIndex(falseThrowToMonkeyIndex).addItem(item);
		}
	}

	private void processReliefOperation(Item item) {
		Long worryLevel = reliefOperation.execute(item);
		item.setWorryLevel(worryLevel);
	}

	private void processOperation(Item item) {
		Long worryLevel = operation.execute(item);
		item.setWorryLevel(worryLevel);
	}

	public List<Item> getItems() {
		if(items == null) {
			items = new ArrayList<>();
		}
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public MonkeyGroup getMonkeyGroup() {
		return monkeyGroup;
	}

	public void setMonkeyGroup(MonkeyGroup monkeyGroup) {
		this.monkeyGroup = monkeyGroup;
	}

	public int getTrueThrowToMonkeyIndex() {
		return trueThrowToMonkeyIndex;
	}

	public void setTrueThrowToMonkeyIndex(int trueThrowToMonkeyIndex) {
		this.trueThrowToMonkeyIndex = trueThrowToMonkeyIndex;
	}

	public int getFalseThrowToMonkeyIndex() {
		return falseThrowToMonkeyIndex;
	}

	public void setFalseThrowToMonkeyIndex(int falseThrowToMonkeyIndex) {
		this.falseThrowToMonkeyIndex = falseThrowToMonkeyIndex;
	}

	public Operation getReliefOperation() {
		return reliefOperation;
	}

	public void setReliefOperation(Operation reliefOperation) {
		this.reliefOperation = reliefOperation;
	}

	public Map<Item, Long> getInspectMap() {
		if(inspectMap == null) {
			inspectMap = new HashMap<>();
		}
		return inspectMap;
	}

	public void setInspectMap(Map<Item, Long> inspectMap) {
		this.inspectMap = inspectMap;
	}

}
