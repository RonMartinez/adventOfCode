package year2020.day8;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day8b {
	
//	private static final String FILENAME = "src/main/resources/2020/day8InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day8Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<Instruction> instructions = readInstructions();
		
		instructions.forEach(i -> System.out.println(ReflectionToStringBuilder.toString(i, ToStringStyle.MULTI_LINE_STYLE)));
		
		List<Integer> nopAndJmpIndices = instructions.stream()
				.filter(i -> Instruction.NOP.equals(i.getOperation()) || Instruction.JMP.equals(i.getOperation()))
				.map(i -> instructions.indexOf(i))
				.collect(Collectors.toList());

		Gameb gameb = null;
		for(Integer index : nopAndJmpIndices) {
			Instruction instruction = instructions.get(index);
			String originalOperation = instruction.getOperation();
			
			if(Instruction.NOP.equals(originalOperation)) {
				instruction.setOperation(Instruction.JMP);
			} else if(Instruction.JMP.equals(originalOperation)) {
				instruction.setOperation(Instruction.NOP);
			}

			gameb = new Gameb(instructions);
			boolean successfulBoot = gameb.boot();
			
			if(successfulBoot) {
				System.out.println("Swapped index " + index);				
				break;
			}
			
			instruction.setOperation(originalOperation);
		}
		
		System.out.println(gameb.getAccumulator());
	}
	
	private static List<Instruction> readInstructions() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(Day8b::createInstruction)
				.collect(Collectors.toList());
	}
	
	private static Instruction createInstruction(String line) {
		String[] splitLine = line.split(" ");
		String operation = splitLine[0];
		int argument = Integer.parseInt(splitLine[1]);
		
		return new Instruction(operation, argument);
	}

}
