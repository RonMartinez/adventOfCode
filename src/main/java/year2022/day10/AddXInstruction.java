package year2022.day10;

public class AddXInstruction implements Instruction {
	
	public static final Long CYCLE_TIME = 2L;
	
	private Long value;
	
	public AddXInstruction(Long value) {
		this.value = value;
	}

	@Override
	public Long getCycleTime() {
		return CYCLE_TIME;
	}

	@Override
	public void execute(Cpu cpu) {
		cpu.setxRegister(cpu.getxRegister() + value);
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	
}
