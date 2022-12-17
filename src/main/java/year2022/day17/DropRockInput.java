package year2022.day17;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DropRockInput {

	private Rock rock;
	private int rockMovementsIndex;
	private List<RockMovement> rockMovements;
	
	public DropRockInput(Rock rock, int rockMovementsIndex, List<RockMovement> rockMovements) {
		this.rock = rock;
		this.rockMovementsIndex = rockMovementsIndex;
		this.rockMovements = rockMovements;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public Rock getRock() {
		return rock;
	}

	public void setRock(Rock rock) {
		this.rock = rock;
	}

	public int getRockMovementsIndex() {
		return rockMovementsIndex;
	}

	public void setRockMovementsIndex(int rockMovementsIndex) {
		this.rockMovementsIndex = rockMovementsIndex;
	}

	public List<RockMovement> getRockMovements() {
		return rockMovements;
	}

	public void setRockMovements(List<RockMovement> rockMovements) {
		this.rockMovements = rockMovements;
	}

}
