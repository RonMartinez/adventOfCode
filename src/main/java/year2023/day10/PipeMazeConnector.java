package year2023.day10;

import java.util.Arrays;
import java.util.List;

public class PipeMazeConnector {
	
	public static final String START_CHARACTER = "S";
	public static final PipeMazeConnector START = new PipeMazeConnector("S", false, false, false, false);
	public static final PipeMazeConnector NORTH_SOUTH = new PipeMazeConnector("|", true, false, true, false);
	public static final PipeMazeConnector EAST_WEST = new PipeMazeConnector("-", false, true, false, true);
	public static final PipeMazeConnector NORTH_EAST = new PipeMazeConnector("L", true, true, false, false);
	public static final PipeMazeConnector NORTH_WEST = new PipeMazeConnector("J", true, false, false, true);
	public static final PipeMazeConnector SOUTH_WEST = new PipeMazeConnector("7", false, false, true, true);
	public static final PipeMazeConnector SOUTH_EAST = new PipeMazeConnector("F", false, true, true, false);
	public static final PipeMazeConnector GROUND = new PipeMazeConnector(".", false, false, false, false);
	public static final PipeMazeConnector OPEN = new PipeMazeConnector("o", false, false, false, false);
	public static final PipeMazeConnector GROUND_FILLED = new PipeMazeConnector("I", false, false, false, false);
	public static final PipeMazeConnector OPEN_FILLED = new PipeMazeConnector("i", false, false, false, false);
	public static final PipeMazeConnector ESCAPED = new PipeMazeConnector("*", false, false, false, false);
	
	private static final List<PipeMazeConnector> PIPE_MAZE_CONNECTORS = Arrays.asList(
			START,
			NORTH_SOUTH,
			EAST_WEST,
			NORTH_EAST,
			NORTH_WEST,
			SOUTH_WEST,
			SOUTH_EAST,
			GROUND
			);

	private String character;
	private boolean connectNorth;
	private boolean connectEast;
	private boolean connectSouth;
	private boolean connectWest;
	
	public static PipeMazeConnector createPipeMazeConnector(String character) {
		return PIPE_MAZE_CONNECTORS.stream()
				.filter(pmc -> pmc.getCharacter().equals(character))
				.findFirst().orElse(null);
	}
	
	public PipeMazeConnector(String character, boolean connectNorth, boolean connectEast, boolean connectSouth, boolean connectWest) {
		this.character = character;
		this.connectNorth = connectNorth;
		this.connectEast = connectEast;
		this.connectSouth = connectSouth;
		this.connectWest = connectWest;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public boolean isConnectNorth() {
		return connectNorth;
	}

	public void setConnectNorth(boolean connectNorth) {
		this.connectNorth = connectNorth;
	}

	public boolean isConnectEast() {
		return connectEast;
	}

	public void setConnectEast(boolean connectEast) {
		this.connectEast = connectEast;
	}

	public boolean isConnectSouth() {
		return connectSouth;
	}

	public void setConnectSouth(boolean connectSouth) {
		this.connectSouth = connectSouth;
	}

	public boolean isConnectWest() {
		return connectWest;
	}

	public void setConnectWest(boolean connectWest) {
		this.connectWest = connectWest;
	}


}