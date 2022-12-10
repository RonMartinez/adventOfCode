package year2022.day10;

public class PendingInstruction {

	private Long completeOnCycle;
	private Instruction instruction;
	
	public PendingInstruction(Long completeOnCycle, Instruction instruction) {
		this.completeOnCycle = completeOnCycle;
		this.instruction = instruction;
	}

	public Long getCompleteOnCycle() {
		return completeOnCycle;
	}

	public void setCompleteOnCycle(Long completeOnCycle) {
		this.completeOnCycle = completeOnCycle;
	}

	public Instruction getInstruction() {
		return instruction;
	}

	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}

}
