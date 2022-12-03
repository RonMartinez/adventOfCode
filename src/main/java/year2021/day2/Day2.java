package year2021.day2;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day2 {

//	private static final String FILENAME = "src/main/resources/2021/day2InputSample.txt";
	private static final String FILENAME = "src/main/resources/2021/day2Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<Instruction> instructions = readInstructions();
		
		instructions.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		Long x = 0L;
		Long y = 0L;
		
		for(Instruction instruction : instructions) {
			x += instruction.getX();
			y += instruction.getY();
		}
		
		System.out.println("x = " + x);
		System.out.println("y = " + y);
		System.out.println(x * y);
	}

	private static List<Instruction> readInstructions() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(line -> createInstruction(line))
				.collect(Collectors.toList());
	}

	private static Instruction createInstruction(String line) {
		String[] instructionLine = StringUtils.split(line, " ");
		String direction = instructionLine[0];
		Long value = Long.valueOf(instructionLine[1]);
		
		Instruction instruction = new Instruction();
		instruction.setX(0L);
		instruction.setY(0L);
		
		if("forward".equals(direction)) {
			instruction.setX(value);
		} else if("down".equals(direction)) {
			instruction.setY(value);
		} else if("up".equals(direction)) {
			instruction.setY(value * -1);
		} else {
			throw new RuntimeException("unknown direction");
		}
		
		return instruction;
	}

}
