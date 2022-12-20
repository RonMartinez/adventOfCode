package year2022.day19;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Blueprint {
	
	private Long number;
	private List<RobotTypeOreTypeCost> robotTypeOreTypeCosts;
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(number)
				.append(robotTypeOreTypeCosts)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
		Blueprint rhs = (Blueprint) obj;
		return new EqualsBuilder()
				.append(number, rhs.number)
				.append(robotTypeOreTypeCosts, rhs.robotTypeOreTypeCosts)
				.isEquals();
	}

	public void addRobotTypeOreTypeCost(RobotTypeOreTypeCost robotTypeOreTypeCost) {
		getRobotTypeOreTypeCosts().add(robotTypeOreTypeCost);
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public List<RobotTypeOreTypeCost> getRobotTypeOreTypeCosts() {
		if(robotTypeOreTypeCosts == null) {
			robotTypeOreTypeCosts = new ArrayList<>();
		}
		return robotTypeOreTypeCosts;
	}

	public void setRobotTypeOreTypeCosts(List<RobotTypeOreTypeCost> robotTypeOreTypeCosts) {
		this.robotTypeOreTypeCosts = robotTypeOreTypeCosts;
	}

}
