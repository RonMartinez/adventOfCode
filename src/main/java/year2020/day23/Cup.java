package year2020.day23;

public class Cup {
	
	private Long label;
	private Cup nextCup;
	private Cup previousCup;

	public Long getLabel() {
		return label;
	}

	public void setLabel(Long label) {
		this.label = label;
	}

	public Cup getNextCup() {
		return nextCup;
	}

	public void setNextCup(Cup nextCup) {
		this.nextCup = nextCup;
	}

	public Cup getPreviousCup() {
		return previousCup;
	}

	public void setPreviousCup(Cup previousCup) {
		this.previousCup = previousCup;
	}

}
