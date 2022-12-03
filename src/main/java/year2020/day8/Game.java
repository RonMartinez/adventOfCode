package year2020.day8;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {

	private int accumulator;
	private List<Instruction> instructions;
	private Set<Instruction> visitedInstructions;

	public Game(List<Instruction> instructions) {
		this.accumulator = 0;
		this.instructions = instructions;
		this.visitedInstructions = new HashSet<>();
	}

	public void boot() {
		int i = 0;
		while( ! visitedInstructions.contains(instructions.get(i))) {
			i = processInstruction(i);
		}

	}

	private int processInstruction(int i) {
		Instruction instruction = instructions.get(i);
		visitedInstructions.add(instruction);
		if(Instruction.NOP.equals(instruction.getOperation())) {
			i++;
		} else if(Instruction.ACC.equals(instruction.getOperation())) {
			accumulator += instruction.getArgument();
			i++;
		} else if(Instruction.JMP.equals(instruction.getOperation())) {
			i += instruction.getArgument();
		}
		return i;
	}

	public int getAccumulator() {
		return accumulator;
	}

	public void setAccumulator(int accumulator) {
		this.accumulator = accumulator;
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}

}
