package year2022.day19;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class RobotTypeOreTypeCost {

	private RobotType robotType;
	private List<OreTypeCost> oreTypeCosts;
	
	public RobotTypeOreTypeCost(RobotType robotType, List<OreTypeCost> oreTypeCosts) {
		this.robotType = robotType;
		this.oreTypeCosts = oreTypeCosts;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(robotType)
				.append(oreTypeCosts)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
		RobotTypeOreTypeCost rhs = (RobotTypeOreTypeCost) obj;
		return new EqualsBuilder()
				.append(robotType, rhs.robotType)
				.append(oreTypeCosts, rhs.oreTypeCosts)
				.isEquals();
	}
	
	public void addOreTypeCost(OreTypeCost oreTypeCost) {
		getOreTypeCosts().add(oreTypeCost);
	}

	public RobotType getRobotType() {
		return robotType;
	}

	public void setRobotType(RobotType robotType) {
		this.robotType = robotType;
	}

	public List<OreTypeCost> getOreTypeCosts() {
		if(oreTypeCosts == null) {
			oreTypeCosts = new ArrayList<>();
		}
		return oreTypeCosts;
	}

	public void setOreTypeCosts(List<OreTypeCost> oreTypeCosts) {
		this.oreTypeCosts = oreTypeCosts;
	}

}
