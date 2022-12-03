package year2020.day12;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day12b {
	
//	private static final String FILENAME = "src/main/resources/2020/day12InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day12Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<Instruction> instructions = createInstructions();
		
		instructions.forEach(i -> System.out.println(ReflectionToStringBuilder.toString(i, ToStringStyle.MULTI_LINE_STYLE)));
		
		Ship ship = new Ship();
		ship.setDirection(Direction.EAST);
		
		Ship waypoint = new Ship();
		waypoint.setX(10);
		waypoint.setY(-1);
		
		instructions.forEach(i -> processInstruction(ship, waypoint, i));
		
		System.out.println(ship.getX());
		System.out.println(ship.getY());
		System.out.println(Math.abs(ship.getX()) + Math.abs(ship.getY()));
	}

	private static void processInstruction(Ship ship, Ship waypoint, Instruction instruction) {
		if(instruction.getAction().equals(Action.MOVE_NORTH)) {
			move(waypoint, Direction.NORTH, instruction.getValue());
		} else if(instruction.getAction().equals(Action.MOVE_EAST)) {
			move(waypoint, Direction.EAST, instruction.getValue());
		} else if(instruction.getAction().equals(Action.MOVE_SOUTH)) {
			move(waypoint, Direction.SOUTH, instruction.getValue());
		} else if(instruction.getAction().equals(Action.MOVE_WEST)) {
			move(waypoint, Direction.WEST, instruction.getValue());
		} else if(instruction.getAction().equals(Action.TURN_LEFT)) {
			long turns = instruction.getValue() / 90;
			for(int i = 0; i < turns; i++) {
				long oldX = waypoint.getX();
				long oldY = waypoint.getY();
				long newX = oldY;
				long newY = oldX * -1;
				waypoint.setX(newX);
				waypoint.setY(newY);
			}
		} else if(instruction.getAction().equals(Action.TURN_RIGHT)) {
			long turns = instruction.getValue() / 90;
			for(int i = 0; i < turns; i++) {
				long oldX = waypoint.getX();
				long oldY = waypoint.getY();
				long newX = oldY * -1;
				long newY = oldX;
				waypoint.setX(newX);
				waypoint.setY(newY);
			}
		} else if(instruction.getAction().equals(Action.MOVE_FORWARD)) {
			long xChange = waypoint.getX() * instruction.getValue();
			long yChange = waypoint.getY() * instruction.getValue();
			
			Direction direction = new Direction(xChange, yChange, 0);
			
			move(ship, direction);
		}
	}

	private static void move(Ship ship, Direction direction) {
		long x = ship.getX();
		long y = ship.getY();
		
		ship.setX(x + direction.getxChange());
		ship.setY(y + direction.getyChange());
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
				.map(Day12b::createInstruction)
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
