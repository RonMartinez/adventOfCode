package year2022.day9;

public class Motion {

	private Direction direction;
	private Long steps;
	
	public Motion(Direction direction, Long steps) {
		this.direction = direction;
		this.steps = steps;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Long getSteps() {
		return steps;
	}

	public void setSteps(Long steps) {
		this.steps = steps;
	}


}
