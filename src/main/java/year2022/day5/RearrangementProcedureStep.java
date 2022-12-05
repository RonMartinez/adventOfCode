package year2022.day5;

public class RearrangementProcedureStep {

	private Integer numberOfCratesToMove;
	private Integer sourceStack;
	private Integer destinationStack;

	public RearrangementProcedureStep(Integer numberOfCratesToMove, Integer sourceStack, Integer destinationStack) {
		this.numberOfCratesToMove = numberOfCratesToMove;
		this.sourceStack = sourceStack;
		this.destinationStack = destinationStack;
	}

	public Integer getNumberOfCratesToMove() {
		return numberOfCratesToMove;
	}

	public void setNumberOfCratesToMove(Integer numberOfCratesToMove) {
		this.numberOfCratesToMove = numberOfCratesToMove;
	}

	public Integer getSourceStack() {
		return sourceStack;
	}

	public void setSourceStack(Integer sourceStack) {
		this.sourceStack = sourceStack;
	}

	public Integer getDestinationStack() {
		return destinationStack;
	}

	public void setDestinationStack(Integer destinationStack) {
		this.destinationStack = destinationStack;
	}

}
