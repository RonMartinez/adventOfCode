package year2023.day10;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Coordinate;

public class PipeMaze {
	
	public static final String START = "S";
	public static final String NORTH_SOUTH = "|";
	public static final String EAST_WEST = "-";
	public static final String NORTH_EAST = "L";
	public static final String NORTH_WEST = "J";
	public static final String SOUTH_WEST = "7";
	public static final String SOUTH_EAST = "F";
	public static final String GROUND = ".";

	private List<PipeMazeRow> pipeMazeRows;
	
	public PipeMaze(List<PipeMazeRow> pipeMazeRows) {
		this.pipeMazeRows = pipeMazeRows;
	}

	public Set<Coordinate> getCoordinates(PipeMazeConnector pipeMazeConnector) {
		Set<Coordinate> coordinates = new HashSet<>();

		for(int y = 0; y < getPipeMazeRows().size(); y++) {
			PipeMazeRow pipeMazeRow = getPipeMazeRows().get(y);
			
			for(int x = 0; x < pipeMazeRow.getPipeMazeColumns().size(); x++) {
				Coordinate coordinate = new Coordinate((long) x, (long) y);
				if(pipeMazeConnector.equals(getPipeMazeConnector(coordinate))) {
					coordinates.add(coordinate);
				}
			}
		}
		
		return coordinates;
	}

	public String outputPipeMaze() {
		StringBuilder sb = new StringBuilder();
		for(PipeMazeRow pipeMazeRow : pipeMazeRows) {
			sb.append(pipeMazeRow.outputPipeMazeRow() + "\n");
		}
		return sb.toString();
	}

	public PipeMazeConnector getPipeMazeConnector(Coordinate coordinate) {
		PipeMazeConnector pipeMazeConnector = null;
		
		int y = coordinate.getY().intValue();
		int x = coordinate.getX().intValue();
				
		if(y >= 0
				&& y < getPipeMazeRows().size()
				) {
			pipeMazeConnector = pipeMazeRows.get(y).getPipeMazeConnector(x);
		}
		
		return pipeMazeConnector;
	}

	public Coordinate getSCoordinate() {
		Set<Coordinate> sCoordinates = getCoordinates(PipeMazeConnector.START);
		
		if(sCoordinates.size() > 1) {
			throw new RuntimeException("there should only be one S coordinate");
		}
		
		return sCoordinates.iterator().next();
	}

	public List<PipeMazeRow> getPipeMazeRows() {
		return pipeMazeRows;
	}

	public void setPipeMazeRows(List<PipeMazeRow> pipeMazeRows) {
		this.pipeMazeRows = pipeMazeRows;
	}

}