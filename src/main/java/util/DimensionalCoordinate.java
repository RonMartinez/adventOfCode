package util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DimensionalCoordinate {

	private List<Long> dimensions;
	
	public DimensionalCoordinate() {
	}
	
	public DimensionalCoordinate(List<Long> dimensions) {
		this.dimensions = dimensions;
	}
	
	@Override
	public String toString() {
		ToStringBuilder toStringBuilder = new ToStringBuilder(this);
		for(Long dimension : dimensions) {
			toStringBuilder.append(dimension);
		}
		return toStringBuilder.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(dimensions)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
		DimensionalCoordinate rhs = (DimensionalCoordinate) obj;
		return new EqualsBuilder()
				.append(dimensions, rhs.dimensions)
				.isEquals();
	}
	
	public void addDimension(Long dimension) {
		getDimensions().add(dimension);
	}

	public List<Long> getDimensions() {
		if(dimensions == null) {
			dimensions = new ArrayList<>();
		}
		return dimensions;
	}

	public void setDimensions(List<Long> dimensions) {
		this.dimensions = dimensions;
	}

}
