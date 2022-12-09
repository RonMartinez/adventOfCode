package year2022.day9;

public class Rope {

	private Knot head;
	private Knot tail;

	public Rope(Knot head, Knot tail) {
		this.head = head;
		this.tail = tail;
	}

	public void processMotion(Motion motion) {
		Movement movement = motion.getDirection().getMovement();
		for(int i = 0; i < motion.getSteps(); i++) {
			processMovement(movement);
		}
	}

	private void processMovement(Movement movement) {
		head.processMovement(movement);
		updateTail();
	}
	
	private void updateTail() {
		Long xDifference = head.getCoordinate().getX() - tail.getCoordinate().getX();
		Long yDifference = head.getCoordinate().getY() - tail.getCoordinate().getY();
		
		if(Math.abs(xDifference) > 1
				|| Math.abs(yDifference) > 1
				) {
			Movement tailMovement = createTailMovement(xDifference, yDifference);
			tail.processMovement(tailMovement);
		}
	}

	private Movement createTailMovement(Long xDifference, Long yDifference) {
		Long moveX = 0L;
		Long moveY = 0L;
		if(xDifference < 0L) {
			moveX = -1L;
		} else if(xDifference > 0L) {
			moveX = 1L;
		}

		if(yDifference < 0L) {
			moveY = -1L;
		} else if(yDifference > 0L) {
			moveY = 1L;
		}
		return new Movement(moveX, moveY);
	}

	public Knot getHead() {
		return head;
	}

	public void setHead(Knot head) {
		this.head = head;
	}

	public Knot getTail() {
		return tail;
	}

	public void setTail(Knot tail) {
		this.tail = tail;
	}


}
