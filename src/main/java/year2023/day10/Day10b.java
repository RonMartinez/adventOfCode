package year2023.day10;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

import util.Coordinate;

public class Day10b {

//	private static final String FILENAME = "src/main/resources/2023/day10InputSample.txt";
//	private static final String FILENAME = "src/main/resources/2023/day10InputSample2.txt";
//	private static final String FILENAME = "src/main/resources/2023/day10InputSample3.txt";
//	private static final String FILENAME = "src/main/resources/2023/day10InputSample4.txt";
	private static final String FILENAME = "src/main/resources/2023/day10Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<String> lines = readLines();

		List<PipeMazeRow> pipeMazeRows = lines.stream()
				.map(Day10b::processLine)
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
		
		Set<Coordinate> loopCoordinates = new HashSet<>();
		
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
					loopCoordinates.add(currentCoordinate);
				} else {
					index++;
					if(index >= Direction.DIRECTIONS.size()) {
						index = 0;
					}
				}
			}
		} while( ! currentCoordinate.equals(sCoordinate));
		
		System.out.println(stepCount / 2);
		
		markNonLoopPipeMazeConnectorsAsGround(pipeMaze, loopCoordinates);
		
		System.out.println(pipeMaze.outputPipeMaze());
		
		expandMazeVertically(pipeMaze);
		
		System.out.println(pipeMaze.outputPipeMaze());

		expandMazeHorizontally(pipeMaze);
		
		System.out.println(pipeMaze.outputPipeMaze());

//		floodFillGroundCoordinates(pipeMaze);
		floodFillGroundCoordinatesIterative(pipeMaze);
		
		System.out.println(pipeMaze.outputPipeMaze());
		
		Set<Coordinate> groundFilledCoordinates = pipeMaze.getCoordinates(PipeMazeConnector.GROUND_FILLED);
		
		System.out.println(groundFilledCoordinates.size());

		System.out.println("done");
	}

	private static void floodFillGroundCoordinatesIterative(PipeMaze pipeMaze) {
		Set<Coordinate> groundCoordinates = pipeMaze.getCoordinates(PipeMazeConnector.GROUND);
		
		while( ! groundCoordinates.isEmpty()) {
			List<Coordinate> coordinatesToProcess = new ArrayList<>();
			Set<Coordinate> visited = new HashSet<>();
			Coordinate firstCoordinate = groundCoordinates.iterator().next();
			coordinatesToProcess.add(firstCoordinate);
			visited.add(firstCoordinate);
			
			Set<Coordinate> coordinatesToFloodFill = new HashSet<>();
			while( ! coordinatesToProcess.isEmpty()) {
				Coordinate currentCoordinate = coordinatesToProcess.remove(0);	
				PipeMazeConnector currentPipeMazeConnector = pipeMaze.getPipeMazeConnector(currentCoordinate);
				
				if(PipeMazeConnector.GROUND.equals(currentPipeMazeConnector)
						|| PipeMazeConnector.OPEN.equals(currentPipeMazeConnector)
						) {
					coordinatesToFloodFill.add(currentCoordinate);

					Coordinate northCoordinate = getNextCoordinate(currentCoordinate, Direction.NORTH);
					Coordinate eastCoordinate = getNextCoordinate(currentCoordinate, Direction.EAST);
					Coordinate southCoordinate = getNextCoordinate(currentCoordinate, Direction.SOUTH);
					Coordinate westCoordinate = getNextCoordinate(currentCoordinate, Direction.WEST);
					
					if( ! visited.contains(northCoordinate)) {
						coordinatesToProcess.add(northCoordinate);
						visited.add(northCoordinate);
					}
					if( ! visited.contains(eastCoordinate)) {
						coordinatesToProcess.add(eastCoordinate);
						visited.add(eastCoordinate);
					}
					if( ! visited.contains(southCoordinate)) {
						coordinatesToProcess.add(southCoordinate);
						visited.add(southCoordinate);
					}
					if( ! visited.contains(westCoordinate)) {
						coordinatesToProcess.add(westCoordinate);
						visited.add(westCoordinate);
					}
				}
			}
			
			boolean reachedEdge = coordinatesToFloodFill.stream()
							.anyMatch(c -> c.getX() == 0
									|| c.getX() >= pipeMaze.getPipeMazeRows().get(c.getY().intValue()).getPipeMazeColumns().size()-1
									|| c.getY() == 0
									|| c.getY() >= pipeMaze.getPipeMazeRows().size()-1
									);

			for(Coordinate coordinateToFloodFill : coordinatesToFloodFill) {
				PipeMazeConnector pipeMazeConnectorToFloodFill = pipeMaze.getPipeMazeConnector(coordinateToFloodFill);
				if(reachedEdge) {
					pipeMaze.getPipeMazeRows().get(coordinateToFloodFill.getY().intValue()).getPipeMazeColumns().set(coordinateToFloodFill.getX().intValue(), PipeMazeConnector.ESCAPED);
				} else {
					if(PipeMazeConnector.GROUND.equals(pipeMazeConnectorToFloodFill)) {
						pipeMaze.getPipeMazeRows().get(coordinateToFloodFill.getY().intValue()).getPipeMazeColumns().set(coordinateToFloodFill.getX().intValue(), PipeMazeConnector.GROUND_FILLED);
					} else if(PipeMazeConnector.OPEN.equals(pipeMazeConnectorToFloodFill)) {
						pipeMaze.getPipeMazeRows().get(coordinateToFloodFill.getY().intValue()).getPipeMazeColumns().set(coordinateToFloodFill.getX().intValue(), PipeMazeConnector.OPEN_FILLED);
					}
				}
			}
			
			groundCoordinates = pipeMaze.getCoordinates(PipeMazeConnector.GROUND);
		}
	}

	private static void floodFillGroundCoordinates(PipeMaze pipeMaze) {
		Set<Coordinate> groundCoordinates = pipeMaze.getCoordinates(PipeMazeConnector.GROUND);
		while( ! groundCoordinates.isEmpty()) {
			Set<Coordinate> coordinatesToFloodFill = new HashSet<>();
			long depth = 0;
			floodFill(pipeMaze, groundCoordinates.iterator().next(), coordinatesToFloodFill, depth);

			boolean reachedEdge = coordinatesToFloodFill.stream()
							.anyMatch(c -> c.getX() == 0
									|| c.getX() >= pipeMaze.getPipeMazeRows().get(c.getY().intValue()).getPipeMazeColumns().size()-1
									|| c.getY() == 0
									|| c.getY() >= pipeMaze.getPipeMazeRows().size()-1
									);

			for(Coordinate coordinateToFill : coordinatesToFloodFill) {
				PipeMazeConnector pipeMazeConnectorToFill = pipeMaze.getPipeMazeConnector(coordinateToFill);
				if(reachedEdge) {
					pipeMaze.getPipeMazeRows().get(coordinateToFill.getY().intValue()).getPipeMazeColumns().set(coordinateToFill.getX().intValue(), PipeMazeConnector.ESCAPED);
				} else if(PipeMazeConnector.GROUND.equals(pipeMazeConnectorToFill)) {
					pipeMaze.getPipeMazeRows().get(coordinateToFill.getY().intValue()).getPipeMazeColumns().set(coordinateToFill.getX().intValue(), PipeMazeConnector.GROUND_FILLED);
				} else if(PipeMazeConnector.OPEN.equals(pipeMazeConnectorToFill)) {
					pipeMaze.getPipeMazeRows().get(coordinateToFill.getY().intValue()).getPipeMazeColumns().set(coordinateToFill.getX().intValue(), PipeMazeConnector.OPEN_FILLED);
				}
			}

			
			groundCoordinates = pipeMaze.getCoordinates(PipeMazeConnector.GROUND);
		}
	}

	private static void floodFill(PipeMaze pipeMaze, Coordinate coordinate, Set<Coordinate> coordinatesToFill, long depth) {
		System.out.println(depth);
		PipeMazeConnector pipeMazeConnector = pipeMaze.getPipeMazeConnector(coordinate);
		if( ! coordinatesToFill.contains(coordinate)
				&& pipeMazeConnector != null
				&& (
						PipeMazeConnector.GROUND.equals(pipeMazeConnector)
						|| PipeMazeConnector.OPEN.equals(pipeMazeConnector)
					)
				) {
			coordinatesToFill.add(coordinate);
			
			floodFill(pipeMaze, getNextCoordinate(coordinate, Direction.NORTH), coordinatesToFill, depth+1);
			floodFill(pipeMaze, getNextCoordinate(coordinate, Direction.EAST), coordinatesToFill, depth+1);
			floodFill(pipeMaze, getNextCoordinate(coordinate, Direction.SOUTH), coordinatesToFill, depth+1);
			floodFill(pipeMaze, getNextCoordinate(coordinate, Direction.WEST), coordinatesToFill, depth+1);
		}
	}

	private static void expandMazeHorizontally(PipeMaze pipeMaze) {
		for(int y = 0; y < pipeMaze.getPipeMazeRows().size(); y++) {
			PipeMazeRow pipeMazeRow = pipeMaze.getPipeMazeRows().get(y);
			
			for(int x = 0; x < pipeMazeRow.getPipeMazeColumns().size(); x++) {
				Coordinate previousCoordinate = new Coordinate((long) x-1, (long) y);
				PipeMazeConnector previousPipeMazeConnector = pipeMaze.getPipeMazeConnector(previousCoordinate);
				
				Coordinate coordinate = new Coordinate((long) x, (long) y);
				PipeMazeConnector pipeMazeConnector = pipeMaze.getPipeMazeConnector(coordinate);
				
				PipeMazeConnector newPipeMazeConnector = PipeMazeConnector.OPEN;
				if(previousPipeMazeConnector != null
						&& previousPipeMazeConnector.isConnectEast()
						&& pipeMazeConnector.isConnectWest()
						) {
					newPipeMazeConnector = PipeMazeConnector.EAST_WEST;	
				}
				
				pipeMazeRow.getPipeMazeColumns().add(x, newPipeMazeConnector);
				x++;
			}
		}
	}

	private static void expandMazeVertically(PipeMaze pipeMaze) {
		for(int y = 0; y < pipeMaze.getPipeMazeRows().size(); y++) {
			PipeMazeRow pipeMazeRow = pipeMaze.getPipeMazeRows().get(y);
			
			List<PipeMazeConnector> newPipeMazeColumns = new ArrayList<>();
			for(int x = 0; x < pipeMazeRow.getPipeMazeColumns().size(); x++) {
				Coordinate previousCoordinate = new Coordinate((long) x, (long) y-1);
				PipeMazeConnector previousPipeMazeConnector = pipeMaze.getPipeMazeConnector(previousCoordinate);
				
				Coordinate coordinate = new Coordinate((long) x, (long) y);
				PipeMazeConnector pipeMazeConnector = pipeMaze.getPipeMazeConnector(coordinate);
				
				PipeMazeConnector newPipeMazeConnector = PipeMazeConnector.OPEN;
				if(previousPipeMazeConnector != null
						&& previousPipeMazeConnector.isConnectSouth()
						&& pipeMazeConnector.isConnectNorth()
						) {
					newPipeMazeConnector = PipeMazeConnector.NORTH_SOUTH;	
				}
				
				newPipeMazeColumns.add(newPipeMazeConnector);
			}
			
			pipeMaze.getPipeMazeRows().add(y, new PipeMazeRow(newPipeMazeColumns));
			y++;
		}
	}

	private static void markNonLoopPipeMazeConnectorsAsGround(PipeMaze pipeMaze, Set<Coordinate> loopCoordinates) {
		for(int y = 0; y < pipeMaze.getPipeMazeRows().size(); y++) {
			PipeMazeRow pipeMazeRow = pipeMaze.getPipeMazeRows().get(y);
			for(int x = 0; x < pipeMazeRow.getPipeMazeColumns().size(); x++) {
				Coordinate coordinate = new Coordinate((long) x, (long) y);
				if( ! loopCoordinates.contains(coordinate)) {
					pipeMazeRow.getPipeMazeColumns().set(x, PipeMazeConnector.GROUND);
				}
			}
		}
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
