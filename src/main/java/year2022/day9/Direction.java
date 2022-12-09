package year2022.day9;

public class Direction {
	
	public static final Direction UP = new Direction("U", 0L, 1L);
	public static final Direction DOWN = new Direction("D", 0L, -1L);
	public static final Direction LEFT = new Direction("L", -1L, 0L);
	public static final Direction RIGHT = new Direction("R", 1L, 0L);

	private String code;
	private Movement movement;
	
	public Direction(String code, Long moveX, Long moveY) {
		this.code = code;
		this.movement = new Movement(moveX, moveY);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Movement getMovement() {
		return movement;
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
	}

}
