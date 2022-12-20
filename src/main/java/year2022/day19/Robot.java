package year2022.day19;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Robot {
	
	private RobotType robotType;
	
	public Robot(RobotType robotType) {
		this.robotType = robotType;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(robotType)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
		Robot rhs = (Robot) obj;
		return new EqualsBuilder()
				.append(robotType, rhs.robotType)
				.isEquals();
	}

	public RobotType getRobotType() {
		return robotType;
	}

	public void setRobotType(RobotType robotType) {
		this.robotType = robotType;
	}
	
}
