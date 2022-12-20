package year2022.day19;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class OreTypeCost {

	private OreType oreType;
	private Long cost;
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(oreType)
				.append(cost)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
		OreTypeCost rhs = (OreTypeCost) obj;
		return new EqualsBuilder()
				.append(oreType, rhs.oreType)
				.append(cost, rhs.cost)
				.isEquals();
	}
	
	public OreTypeCost(OreType oreType, Long cost) {
		this.oreType = oreType;
		this.cost = cost;
	}

	public OreType getOreType() {
		return oreType;
	}

	public void setOreType(OreType oreType) {
		this.oreType = oreType;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

}
