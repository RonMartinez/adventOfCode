package year2023.day10;

import java.util.Arrays;
import java.util.List;

public class Direction {
	
	public static final Direction NORTH = new Direction("N", 0, -1);
	public static final Direction EAST = new Direction("E", 1, 0);
	public static final Direction SOUTH = new Direction("S", 0, 1);
	public static final Direction WEST = new Direction("W", -1, 0);
	
	public static final List<Direction> DIRECTIONS = Arrays.asList(
			NORTH,
			EAST,
			SOUTH,
			WEST);

	private String character;
	private int xOffset;
	private int yOffset;
	
	public Direction(String character, int xOffset, int yOffset) {
		this.character = character;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}


}