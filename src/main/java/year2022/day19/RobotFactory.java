package year2022.day19;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class RobotFactory {
	
	public static final Comparator<RobotFactory> GEODE_ORE_COUNT_COMPARATOR = Comparator.comparing(RobotFactory::getGeodeOreCount);
	
	private Blueprint blueprint;
	private Map<RobotType, Long> robotsMap;
	private Map<OreType, Long> oresMap;
	private Long minute;

	public RobotFactory() {
	}

	public RobotFactory(Blueprint blueprint, Long minute) {
		this.blueprint = blueprint;
		this.minute = minute;
	}
	
	public RobotFactory copy() {
		RobotFactory copy = new RobotFactory();
		copy.setBlueprint(getBlueprint());
		copy.setRobotsMap(new HashMap<>(getRobotsMap()));
		copy.setOresMap(new HashMap<>(getOresMap()));
		copy.setMinute(getMinute());
		return copy;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(blueprint)
				.append(robotsMap)
				.append(oresMap)
				.append(minute)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
		RobotFactory rhs = (RobotFactory) obj;
		return new EqualsBuilder()
				.append(blueprint, rhs.blueprint)
				.append(robotsMap, rhs.robotsMap)
				.append(oresMap, rhs.oresMap)
				.append(minute, rhs.minute)
				.isEquals();
	}
	
	public Long getGeodeOreCount() {
		return getOresMap().getOrDefault(OreType.GEODE, 0L);
	}
	
	public void process(RobotTypeOreTypeCost robotTypeOreTypeCost) {
		setMinute(getMinute() + 1L);
		if(robotTypeOreTypeCost != null) {
			buyRobotType(robotTypeOreTypeCost);	
		}
		
		getRobotsMap().keySet().forEach(rt -> addOreType(rt.getOreType()));
		
		if(robotTypeOreTypeCost != null) {
			addRobotType(robotTypeOreTypeCost.getRobotType());
		}
	}

	private void buyRobotType(RobotTypeOreTypeCost robotTypeOreTypeCost) {
		List<OreTypeCost> oreTypeCosts = robotTypeOreTypeCost.getOreTypeCosts();

		System.out.println("building a " + robotTypeOreTypeCost.getRobotType().getOreType().getName() + "-collecting robot");
		for(OreTypeCost oreTypeCost : oreTypeCosts) {
			OreType oreType = oreTypeCost.getOreType();
			getOresMap().put(oreType, getOresMap().get(oreType) - oreTypeCost.getCost());
		}
	}

	public List<RobotTypeOreTypeCost> getPurchaseableRobotTypeOreTypeCosts() {
		return blueprint.getRobotTypeOreTypeCosts().stream()
				.filter(rtotc -> rtotc.getOreTypeCosts().stream()
					.allMatch(otc -> getOresMap().getOrDefault(otc.getOreType(), 0L) >= otc.getCost()))
				.collect(Collectors.toList());
	}

	public void addRobotType(RobotType robotType) {
		getRobotsMap().put(robotType, getRobotsMap().getOrDefault(robotType, 0L) + 1);	
	}

	public void addOreType(OreType oreType) {
		getOresMap().put(oreType, getOresMap().getOrDefault(oreType, 0L) + 1);
	}

	public Blueprint getBlueprint() {
		return blueprint;
	}

	public void setBlueprint(Blueprint blueprint) {
		this.blueprint = blueprint;
	}

	public Long getMinute() {
		return minute;
	}

	public void setMinute(Long minute) {
		this.minute = minute;
	}

	public Map<RobotType, Long> getRobotsMap() {
		if(robotsMap == null) {
			robotsMap = new HashMap<>();
		}
		return robotsMap;
	}

	public void setRobotsMap(Map<RobotType, Long> robotsMap) {
		this.robotsMap = robotsMap;
	}

	public Map<OreType, Long> getOresMap() {
		if(oresMap == null) {
			oresMap = new HashMap<>();
		}
		return oresMap;
	}

	public void setOresMap(Map<OreType, Long> oresMap) {
		this.oresMap = oresMap;
	}

}
