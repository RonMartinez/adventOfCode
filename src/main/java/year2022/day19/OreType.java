package year2022.day19;

public class OreType {
	
	public static final OreType ORE = new OreType("ore");
	public static final OreType CLAY = new OreType("clay");
	public static final OreType OBSIDIAN = new OreType("obsidian");
	public static final OreType GEODE = new OreType("geode");
	
	private String name;

	public OreType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
