package year2022.day9;

import java.util.ArrayList;
import java.util.List;

public class GeneralRope {

	private List<Knot> knots;

	public GeneralRope(int numberOfKnots) {
		knots = new ArrayList<>();
		for(int i = 0; i < numberOfKnots; i++) {
			knots.add(new Knot(new Coordinate(0L, 0L)));
		}
	}

	public void processMotion(Motion motion) {
		Movement movement = motion.getDirection().getMovement();
		for(int i = 0; i < motion.getSteps(); i++) {
			processMovement(movement);
		}
	}

	private void processMovement(Movement movement) {
		//head case
		knots.get(0).processMovement(movement);
		
		for(int i = 1; i < knots.size(); i++) {
			Knot previousKnot = knots.get(i-1);
			Knot knot = knots.get(i);
			updateKnot(knot, previousKnot);
		}
		
		
	}
	
	private void updateKnot(Knot knot, Knot previousKnot) {
		Long xDifference = previousKnot.getCoordinate().getX() - knot.getCoordinate().getX();
		Long yDifference = previousKnot.getCoordinate().getY() - knot.getCoordinate().getY();
		
		if(Math.abs(xDifference) > 1
				|| Math.abs(yDifference) > 1
				) {
			Movement movement = createMovement(xDifference, yDifference);
			knot.processMovement(movement);
		}
	}

	private Movement createMovement(Long xDifference, Long yDifference) {
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

	public List<Knot> getKnots() {
		return knots;
	}

	public void setKnots(List<Knot> knots) {
		this.knots = knots;
	}

}
