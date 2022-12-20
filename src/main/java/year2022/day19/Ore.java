package year2022.day19;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Ore {
	
	private OreType oreType;
	
	public Ore(OreType oreType) {
		this.oreType = oreType;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(oreType)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
		Ore rhs = (Ore) obj;
		return new EqualsBuilder()
				.append(oreType, rhs.oreType)
				.isEquals();
	}

	public OreType getOreType() {
		return oreType;
	}

	public void setOreType(OreType oreType) {
		this.oreType = oreType;
	}

}
