package year2020.day12;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day12 {
	
//	private static final String FILENAME = "src/main/resources/2020/day12InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day12Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<Instruction> instructions = createInstructions();
		
		instructions.forEach(i -> System.out.println(ReflectionToStringBuilder.toString(i, ToStringStyle.MULTI_LINE_STYLE)));
		
		Ship ship = new Ship();
		ship.setDirection(Direction.EAST);
		
		instructions.forEach(i -> processInstruction(ship, i));
		
		System.out.println(ship.getX());
		System.out.println(ship.getY());
		System.out.println(Math.abs(ship.getX()) + Math.abs(ship.getY()));
	}

	private static void processInstruction(Ship ship, Instruction instruction) {
		if(instruction.getAction().equals(Action.MOVE_NORTH)) {
			move(ship, Direction.NORTH, instruction.getValue());
		} else if(instruction.getAction().equals(Action.MOVE_EAST)) {
			move(ship, Direction.EAST, instruction.getValue());
		} else if(instruction.getAction().equals(Action.MOVE_SOUTH)) {
			move(ship, Direction.SOUTH, instruction.getValue());
		} else if(instruction.getAction().equals(Action.MOVE_WEST)) {
			move(ship, Direction.WEST, instruction.getValue());
		} else if(instruction.getAction().equals(Action.TURN_LEFT)) {
			long newDegrees = ((ship.getDirection().getDegrees() - instruction.getValue()) % Direction.MAX_DEGREES + Direction.MAX_DEGREES) % Direction.MAX_DEGREES;
			
			Direction newDirection = Direction.DIRECTIONS.stream()
					.filter(d -> d.getDegrees() == newDegrees)
					.findFirst().orElse(null);
			
			ship.setDirection(newDirection);
		} else if(instruction.getAction().equals(Action.TURN_RIGHT)) {
			long newDegrees = ((ship.getDirection().getDegrees() + instruction.getValue()) % Direction.MAX_DEGREES + Direction.MAX_DEGREES) % Direction.MAX_DEGREES;
			
			Direction newDirection = Direction.DIRECTIONS.stream()
					.filter(d -> d.getDegrees() == newDegrees)
					.findFirst().orElse(null);
			
			ship.setDirection(newDirection);
		} else if(instruction.getAction().equals(Action.MOVE_FORWARD)) {
			move(ship, ship.getDirection(), instruction.getValue());
		}
	}
	
	private static void move(Ship ship, Direction direction, long value) {
		long x = ship.getX();
		long y = ship.getY();
		
		ship.setX(x + value * direction.getxChange());
		ship.setY(y + value * direction.getyChange());
	}

	private static List<Instruction> createInstructions() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(Day12::createInstruction)
				.collect(Collectors.toList());
	}

	private static Instruction createInstruction(String line) {
		String actionValue = line.substring(0, 1);
		
		Action action = Action.ACTIONS.stream()
				.filter(a -> a.getValue().equals(actionValue))
				.findFirst().orElse(null);
		
		Long instructionValue = Long.parseLong(line.substring(1));

		Instruction instruction = new Instruction();
		instruction.setAction(action);
		instruction.setValue(instructionValue);
		
		return instruction;
	}

}
