package year2020.day8;

public class Instruction {

	public static final String JMP = "jmp";
	public static final String ACC = "acc";
	public static final String NOP = "nop";

	private String operation;
	private int argument;

	public Instruction(String operation, int argument) {
		this.operation = operation;
		this.argument = argument;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public int getArgument() {
		return argument;
	}

	public void setArgument(int argument) {
		this.argument = argument;
	}

}
