package year2022.day10;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day10 {

//	private static final String FILENAME = "src/main/resources/2022/day10InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day10Input.txt";
	
	public static final String NOOP = "noop";
	public static final String ADDX = "addx";
	
	public static final Long SIGNAL_STRENGTH_FREQUENCY = 40L;
	public static final Long SIGNAL_STRENGTH_OFFSET = 20L;

	public static void main(String[] args) throws IOException {
		List<Instruction> instructions = readInstructions();
		
		instructions.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		Cpu cpu = createCpu(instructions);
		
		cpu.getPendingInstructions().forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));

		List<Long> signalStrengths = new ArrayList<>();
		while( ! cpu.getPendingInstructions().isEmpty()) {
			if(shouldCheckSignalStrength(cpu)) {
				Long signalStrength = cpu.getSignalStrength();
				System.out.println(cpu.getCurrentCycle() + " " + signalStrength);
				signalStrengths.add(signalStrength);
			}
			cpu.processCurrentCycle();
		}
		
		Long totalSignalStrenth = signalStrengths.stream()
				.reduce(0L, Long::sum);

		System.out.println(totalSignalStrenth);
		
		System.out.println("done");
	}

	private static boolean shouldCheckSignalStrength(Cpu cpu) {
		return (cpu.getCurrentCycle() + SIGNAL_STRENGTH_OFFSET) % SIGNAL_STRENGTH_FREQUENCY == 0;
	}

	private static Cpu createCpu(List<Instruction> instructions) {
		Cpu cpu = new Cpu();
		
		long i = 0;
		Iterator<Instruction> iterator = instructions.iterator();
		while(iterator.hasNext()) {
			Instruction instruction = iterator.next();
			
			long completeOnCycle = i + instruction.getCycleTime();
			
			PendingInstruction pendingInstruction = new PendingInstruction(completeOnCycle, instruction);
			
			cpu.addPendingInstruction(pendingInstruction);
			
			i = completeOnCycle;
		}
		
		return cpu;
	}

	private static List<Instruction> readInstructions() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(line -> createInstruction(line))
				.collect(Collectors.toList());
	}
	
	private static Instruction createInstruction(String line) {
		Instruction instruction;
		if(line.equals(NOOP)) {
			instruction = createNoopInstruction();
		} else if (line.startsWith(ADDX + " ")) {
			instruction = createAddxInstruction(line);
		} else {
			throw new RuntimeException("unknown instruction: " + line);
		}
		
		return instruction;
	}

	private static Instruction createAddxInstruction(String line) {
		String[] split = line.split(" ");
		return new AddXInstruction(Long.valueOf(split[1]));
	}

	private static Instruction createNoopInstruction() {
		return new NoopInstruction();
	}

}
