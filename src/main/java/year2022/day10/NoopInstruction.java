package year2022.day10;

public class NoopInstruction implements Instruction {
	
	public static final Long CYCLE_TIME = 1L;
	
	@Override
	public Long getCycleTime() {
		return CYCLE_TIME;
	}

	@Override
	public void execute(Cpu cpu) {
		//do nothing
	}

}
