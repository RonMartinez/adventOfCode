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

public class Day2b {

//	private static final String FILENAME = "src/main/resources/2021/day2InputSample.txt";
	private static final String FILENAME = "src/main/resources/2021/day2Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<Instructionb> instructionbs = readInstructionbs();
		
		instructionbs.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		Long x = 0L;
		Long y = 0L;
		Long aim = 0L;
		
		for(Instructionb instructionb : instructionbs) {
			aim += instructionb.getAimChange();
			x += instructionb.getxChange();
			y += instructionb.getxChange() * aim;
		}
		
		System.out.println("x = " + x);
		System.out.println("y = " + y);
		System.out.println("aim = " + aim);
		System.out.println(x * y);
	}

	private static List<Instructionb> readInstructionbs() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(line -> createInstructionb(line))
				.collect(Collectors.toList());
	}

	private static Instructionb createInstructionb(String line) {
		String[] instructionLine = StringUtils.split(line, " ");
		String command = instructionLine[0];
		Long value = Long.valueOf(instructionLine[1]);
		
		Instructionb instructionb = new Instructionb();
		instructionb.setxChange(0L);
		instructionb.setAimChange(0L);
		
		if("forward".equals(command)) {
			instructionb.setxChange(value);
		} else if("down".equals(command)) {
			instructionb.setAimChange(value);
		} else if("up".equals(command)) {
			instructionb.setAimChange(value * -1);
		} else {
			throw new RuntimeException("unknown direction");
		}
		
		return instructionb;
	}

}
