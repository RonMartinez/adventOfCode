package util;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Cube {

	private Long x;
	private Long y;
	private Long z;

	public Cube(Long x, Long y, Long z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(x)
				.append(y)
				.append(z)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
		Cube rhs = (Cube) obj;
		return new EqualsBuilder()
				.append(x, rhs.x)
				.append(y, rhs.y)
				.append(z, rhs.z)
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

	public Long getZ() {
		return z;
	}

	public void setZ(Long z) {
		this.z = z;
	}

}
