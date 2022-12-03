package year2020.day12;

import java.util.Arrays;
import java.util.List;

public class Direction {
	
	public static final long MAX_DEGREES = 360;
	
	public static final Direction NORTH = new Direction(0, -1, 0);
	public static final Direction EAST = new Direction(1, 0, 90);
	public static final Direction SOUTH = new Direction(0, 1, 180);
	public static final Direction WEST = new Direction(-1, 0, 270);
	
	public static final List<Direction> DIRECTIONS = Arrays.asList(NORTH, EAST, SOUTH, WEST); 

	private long xChange;
	private long yChange;
	private long degrees;

	public Direction(long xChange, long yChange, long degrees) {
		this.xChange = xChange;
		this.yChange = yChange;
		this.degrees = degrees;
	}

	public long getxChange() {
		return xChange;
	}

	public void setxChange(long xChange) {
		this.xChange = xChange;
	}

	public long getyChange() {
		return yChange;
	}

	public void setyChange(long yChange) {
		this.yChange = yChange;
	}

	public long getDegrees() {
		return degrees;
	}

	public void setDegrees(long degrees) {
		this.degrees = degrees;
	}

}
