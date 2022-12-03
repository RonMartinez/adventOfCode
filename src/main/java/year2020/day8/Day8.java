package year2020.day8;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day8 {
	
//	private static final String FILENAME = "src/main/resources/2020/day8InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day8Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<Instruction> instructions = readInstructions();
		
		instructions.forEach(i -> System.out.println(ReflectionToStringBuilder.toString(i, ToStringStyle.MULTI_LINE_STYLE)));
		
		Game game = new Game(instructions);
		game.boot();
		
		System.out.println(game.getAccumulator());
	}
	
	private static List<Instruction> readInstructions() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(Day8::createInstruction)
				.collect(Collectors.toList());
	}
	
	private static Instruction createInstruction(String line) {
		String[] splitLine = line.split(" ");
		String operation = splitLine[0];
		int argument = Integer.parseInt(splitLine[1]);
		
		return new Instruction(operation, argument);
	}

}
