package year2020.day24;

public class Direction {
	
	public static final Direction EAST = new Direction("e");
	public static final Direction SOUTH_EAST = new Direction("se");
	public static final Direction SOUTH_WEST = new Direction("sw");
	public static final Direction WEST = new Direction("w");
	public static final Direction NORTH_WEST = new Direction("nw");
	public static final Direction NORTH_EAST = new Direction("ne");
	
	private String code;
	
	public Direction(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
