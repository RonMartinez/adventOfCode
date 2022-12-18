package util;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Coordinate {

	private Long x;
	private Long y;

	public Coordinate(Long x, Long y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(x)
				.append(y)
				.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(x)
				.append(y)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
		Coordinate rhs = (Coordinate) obj;
		return new EqualsBuilder()
				.append(x, rhs.x)
				.append(y, rhs.y)
				.isEquals();
	}

	public Long getX() {
		return x;
	}

	public void setX(Long x) {
		this.x = x;
	}

	public Long getY() {
		return y;
	}

	public void setY(Long y) {
		this.y = y;
	}

}
