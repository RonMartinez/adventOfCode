package year2022.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cpu {

	private Long xRegister;
	private Long currentCycle;
	private List<PendingInstruction> pendingInstructions;
	
	public Cpu() {
		this.xRegister = 1L;
		this.currentCycle = 1L;
	}

	public Long getSignalStrength() {
		return currentCycle * xRegister;
	}
	
	public void addPendingInstruction(PendingInstruction pendingInstruction) {
		getPendingInstructions().add(pendingInstruction);
	}

	public void processCurrentCycle() {
		currentCycle++;

		List<PendingInstruction> pendingInstructionsToComplete = pendingInstructions.stream()
				.filter(pi -> pi.getCompleteOnCycle() < currentCycle)
				.collect(Collectors.toList());
		
		pendingInstructionsToComplete.forEach(pi -> pi.getInstruction().execute(this));
		
		pendingInstructions.removeAll(pendingInstructionsToComplete);
	}
	
	public Long getxRegister() {
		return xRegister;
	}

	public void setxRegister(Long xRegister) {
		this.xRegister = xRegister;
	}

	public List<PendingInstruction> getPendingInstructions() {
		if(pendingInstructions == null) {
			pendingInstructions = new ArrayList<>();
		}
		return pendingInstructions;
	}

	public void setPendingInstructions(List<PendingInstruction> pendingInstructions) {
		this.pendingInstructions = pendingInstructions;
	}

	public Long getCurrentCycle() {
		return currentCycle;
	}

	public void setCurrentCycle(Long currentCycle) {
		this.currentCycle = currentCycle;
	}

}
