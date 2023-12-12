package year2023.day10;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

import util.Coordinate;

public class Day10 {

//	private static final String FILENAME = "src/main/resources/2023/day10InputSample.txt";
//	private static final String FILENAME = "src/main/resources/2023/day10InputSample2.txt";
	private static final String FILENAME = "src/main/resources/2023/day10Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<String> lines = readLines();

		List<PipeMazeRow> pipeMazeRows = lines.stream()
				.map(Day10::processLine)
				.collect(Collectors.toList());

		PipeMaze pipeMaze = new PipeMaze(pipeMazeRows);
		
		Coordinate sCoordinate = pipeMaze.getSCoordinate();
		
		PipeMazeConnector northOfSPipeMazeConnector = pipeMaze.getPipeMazeConnector(getNextCoordinate(sCoordinate, Direction.NORTH));
		PipeMazeConnector eastOfSPipeMazeConnector = pipeMaze.getPipeMazeConnector(getNextCoordinate(sCoordinate, Direction.EAST));
		PipeMazeConnector southOfSPipeMazeConnector = pipeMaze.getPipeMazeConnector(getNextCoordinate(sCoordinate, Direction.SOUTH));
		PipeMazeConnector westOfSPipeMazeConnector = pipeMaze.getPipeMazeConnector(getNextCoordinate(sCoordinate, Direction.WEST));
		
		PipeMazeConnector sPipeMazeConnector = PipeMazeConnector.EAST_WEST;
		if(northOfSPipeMazeConnector != null
				&& northOfSPipeMazeConnector.isConnectSouth()
				&& eastOfSPipeMazeConnector != null
				&& eastOfSPipeMazeConnector.isConnectWest()
				) {
			sPipeMazeConnector = PipeMazeConnector.NORTH_EAST;
		} else if(eastOfSPipeMazeConnector != null
				&& eastOfSPipeMazeConnector.isConnectWest()
				&& southOfSPipeMazeConnector != null
				&& southOfSPipeMazeConnector.isConnectNorth()
				) {
			sPipeMazeConnector = PipeMazeConnector.SOUTH_EAST;
		} else if(southOfSPipeMazeConnector != null
				&& southOfSPipeMazeConnector.isConnectNorth()
				&& westOfSPipeMazeConnector != null
				&& westOfSPipeMazeConnector.isConnectEast()
				) {
			sPipeMazeConnector = PipeMazeConnector.SOUTH_WEST;
		} else if(westOfSPipeMazeConnector != null
				&& westOfSPipeMazeConnector.isConnectEast()
				&& northOfSPipeMazeConnector != null
				&& northOfSPipeMazeConnector.isConnectSouth()
				) {
			sPipeMazeConnector = PipeMazeConnector.NORTH_WEST;
		} else if(northOfSPipeMazeConnector != null
				&& northOfSPipeMazeConnector.isConnectSouth()
				&& southOfSPipeMazeConnector != null
				&& southOfSPipeMazeConnector.isConnectNorth()
				) {
			sPipeMazeConnector = PipeMazeConnector.NORTH_SOUTH;
		}
		
		pipeMaze.getPipeMazeRows().get(sCoordinate.getY().intValue()).getPipeMazeColumns().set(sCoordinate.getX().intValue(), sPipeMazeConnector);

		System.out.println(pipeMaze.outputPipeMaze());
		
		Direction lastDirection = Direction.EAST;
		Coordinate currentCoordinate = sCoordinate;
		PipeMazeConnector currentPipeMazeConnector = pipeMaze.getPipeMazeConnector(sCoordinate);
		int index = Direction.DIRECTIONS.indexOf(lastDirection) - 1;
		if(index < 0) {
			index = Direction.DIRECTIONS.size()-1;
		}
		
		long stepCount = 0;
		do {
			
			boolean foundNextCoordinate = false;
			while( ! foundNextCoordinate) {
				Direction nextDirection = Direction.DIRECTIONS.get(index);
				Coordinate nextCoordinate = getNextCoordinate(currentCoordinate, Direction.DIRECTIONS.get(index));
				PipeMazeConnector nextPipeMazeConnector = pipeMaze.getPipeMazeConnector(nextCoordinate);
				if(isPipeMazeConnectorValidForIncomingDirection(nextPipeMazeConnector, nextDirection)
						&& isPipeMazeConnectorValidForOutgoingDirection(currentPipeMazeConnector, nextDirection)
						) {
					lastDirection = nextDirection;
					currentCoordinate = nextCoordinate;
					currentPipeMazeConnector = nextPipeMazeConnector;
					index--;
					if(index < 0) {
						index = Direction.DIRECTIONS.size()-1;
					}
					foundNextCoordinate = true;
					stepCount++;
				} else {
					index++;
					if(index >= Direction.DIRECTIONS.size()) {
						index = 0;
					}
				}
			}
		} while( ! currentCoordinate.equals(sCoordinate));
		
		System.out.println(stepCount / 2);
		
		System.out.println("done");
	}
	
	private static boolean isPipeMazeConnectorValidForOutgoingDirection(PipeMazeConnector pipeMazeConnector, Direction outgoingDirection) {
		boolean valid = false;
		
		if(pipeMazeConnector != null) {
			if(Direction.NORTH.equals(outgoingDirection)
					&& pipeMazeConnector.isConnectNorth()
					) {
				valid = true;
			} else if(Direction.EAST.equals(outgoingDirection)
					&& pipeMazeConnector.isConnectEast()
					) {
				valid = true;
			} else if(Direction.SOUTH.equals(outgoingDirection)
					&& pipeMazeConnector.isConnectSouth()
					) {
				valid = true;
			} else if(Direction.WEST.equals(outgoingDirection)
					&& pipeMazeConnector.isConnectWest()
					) {
				valid = true;
			}
		}

		return valid;
	}

	private static boolean isPipeMazeConnectorValidForIncomingDirection(PipeMazeConnector pipeMazeConnector, Direction incomingDirection) {
		boolean valid = false;
		
		if(pipeMazeConnector != null) {
			if(Direction.NORTH.equals(incomingDirection)
					&& pipeMazeConnector.isConnectSouth()
					) {
				valid = true;
			} else if(Direction.EAST.equals(incomingDirection)
					&& pipeMazeConnector.isConnectWest()
					) {
				valid = true;
			} else if(Direction.SOUTH.equals(incomingDirection)
					&& pipeMazeConnector.isConnectNorth()
					) {
				valid = true;
			} else if(Direction.WEST.equals(incomingDirection)
					&& pipeMazeConnector.isConnectEast()
					) {
				valid = true;
			}
		}

		return valid;
	}

	private static Coordinate getNextCoordinate(Coordinate coordinate, Direction direction) {
		return new Coordinate(coordinate.getX() + direction.getxOffset(), coordinate.getY() + direction.getyOffset());
	}

	private static PipeMazeRow processLine(String line) {
		List<PipeMazeConnector> pipeMazeColumns = new ArrayList<>();
		for(char c : line.toCharArray()) {
			String pipeMazeColumnCharacter = String.valueOf(c);
			pipeMazeColumns.add(PipeMazeConnector.createPipeMazeConnector(pipeMazeColumnCharacter));
		}
		
		return new PipeMazeRow(pipeMazeColumns);
	}

	private static List<String> readLines() throws IOException {
		return IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
	}

}
