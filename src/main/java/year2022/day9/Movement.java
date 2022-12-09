package year2022.day9;

public class Movement {
	
	private Long moveX;
	private Long moveY;
	
	public Movement(Long moveX, Long moveY) {
		this.moveX = moveX;
		this.moveY = moveY;
	}
	
	public boolean isNoChangeMovement() {
		return moveX == 0
				&& moveY == 0;
	}

	public Long getMoveX() {
		return moveX;
	}

	public void setMoveX(Long moveX) {
		this.moveX = moveX;
	}

	public Long getMoveY() {
		return moveY;
	}

	public void setMoveY(Long moveY) {
		this.moveY = moveY;
	}

}
