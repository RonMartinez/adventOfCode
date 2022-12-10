package year2022.day10;

public interface Instruction {
	
	public abstract Long getCycleTime();
	public abstract void execute(Cpu cpu);

}
