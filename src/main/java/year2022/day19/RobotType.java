package year2022.day19;

public class RobotType {
	
	public static final RobotType ORE = new RobotType(OreType.ORE);
	public static final RobotType CLAY = new RobotType(OreType.CLAY);
	public static final RobotType OBSIDIAN = new RobotType(OreType.OBSIDIAN);
	public static final RobotType GEODE = new RobotType(OreType.GEODE);
	
	private OreType oreType;
	
	public RobotType(OreType oreType) {
		this.oreType = oreType;
	}

	public OreType getOreType() {
		return oreType;
	}

	public void setOreType(OreType oreType) {
		this.oreType = oreType;
	}

}
