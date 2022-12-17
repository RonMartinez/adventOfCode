package year2022.day17;

import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import util.Coordinate;

public class ChamberDropRockState {

	private DropRockInput dropRockInput;
	private Set<Coordinate> towerState;

	public ChamberDropRockState(DropRockInput dropRockInput, Set<Coordinate> towerState) {
		this.dropRockInput = dropRockInput;
		this.towerState = towerState;
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public DropRockInput getDropRockInput() {
		return dropRockInput;
	}

	public void setDropRockInput(DropRockInput dropRockInput) {
		this.dropRockInput = dropRockInput;
	}

	public Set<Coordinate> getTowerState() {
		return towerState;
	}

	public void setTowerState(Set<Coordinate> towerState) {
		this.towerState = towerState;
	}

}
