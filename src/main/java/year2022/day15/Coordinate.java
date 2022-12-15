package year2022.day15;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Coordinate {

	private Long x;
	private Long y;

	public Coordinate(Long x, Long y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
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
