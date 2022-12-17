package year2022.day17;

public class DropRockResult {

	private int rockMovementsIndex;
	private Long previousIteration;

	public DropRockResult(int rockMovementsIndex, Long previousIteration) {
		this.rockMovementsIndex = rockMovementsIndex;
		this.previousIteration = previousIteration;
	}

	public int getRockMovementsIndex() {
		return rockMovementsIndex;
	}

	public void setRockMovementsIndex(int rockMovementsIndex) {
		this.rockMovementsIndex = rockMovementsIndex;
	}

	public Long getPreviousIteration() {
		return previousIteration;
	}

	public void setPreviousIteration(Long previousIteration) {
		this.previousIteration = previousIteration;
	}

}
