package year2022.day19;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day19 {

	private static final String FILENAME = "src/main/resources/2022/day19InputSample.txt";
//	private static final String FILENAME = "src/main/resources/2022/day19Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<Blueprint> blueprints = readBlueprints();
		
		blueprints.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		List<RobotFactory> robotFactories = blueprints.stream()
				.map(b -> new RobotFactory(b, 0L))
				.collect(Collectors.toList());
		robotFactories.forEach(rf -> rf.addRobotType(RobotType.ORE));
		
		
		for(RobotFactory robotFactory : robotFactories) {
			TreeSet<RobotFactory> optimalRobotFactories = new TreeSet<>(RobotFactory.GEODE_ORE_COUNT_COMPARATOR.reversed());
			Set<RobotFactory> processed = new HashSet<>();
			recurse(robotFactory, optimalRobotFactories, processed);
			RobotFactory optimalRobotFactory = optimalRobotFactories.first();
			System.out.println("blueprint: " + optimalRobotFactory.getBlueprint().getNumber());
			System.out.println("geode size: " + optimalRobotFactory.getOresMap().get(OreType.GEODE));
		}
		
		System.out.println("done");
	}
	
	private static void recurse(RobotFactory robotFactory, TreeSet<RobotFactory> optimalRobotFactories, Set<RobotFactory> processed) {
		if( ! processed.contains(robotFactory)) {
			Long minute = robotFactory.getMinute();
			
			if(minute.compareTo(24L) >= 0
					) {
				addEntry(optimalRobotFactories, robotFactory);
			} else {				
				List<RobotTypeOreTypeCost> purchaseableRobotTypeOreTypeCosts = robotFactory.getPurchaseableRobotTypeOreTypeCosts();
				purchaseableRobotTypeOreTypeCosts.add(null);
				
				for(RobotTypeOreTypeCost purchaseableRobotTypeOreTypeCost : purchaseableRobotTypeOreTypeCosts) {
					RobotFactory copy = robotFactory.copy();
					copy.process(purchaseableRobotTypeOreTypeCost);
					recurse(copy, optimalRobotFactories, processed);
				}
			}
			processed.add(robotFactory);	
		}
	}
	
	private static void addEntry(TreeSet<RobotFactory> optimalRobotFactories, RobotFactory robotFactory) {
		optimalRobotFactories.add(robotFactory);
		if(optimalRobotFactories.size() > 5) {
			optimalRobotFactories.pollLast();
		}
	}

	private static List<Blueprint> readBlueprints() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(line -> createBlueprint(line))
				.collect(Collectors.toList());
	}
	
	private static Blueprint createBlueprint(String line) {
		line = line.replaceAll("Blueprint ", "");
		String[] split = line.split(": ");
		
		Blueprint blueprint = new Blueprint();
		blueprint.setNumber(Long.valueOf(split[0]));
		
		String robotTypeOreTypeCostsString = split[1].substring(0, split[1].length()-1);
		String[] robotTypeOreTypeCostsStringSplit = robotTypeOreTypeCostsString.split("\\. ");
		for(String robotTypeOreCostsString : robotTypeOreTypeCostsStringSplit) {
			blueprint.addRobotTypeOreTypeCost(createRobotOreCost(robotTypeOreCostsString));
		}
		Collections.reverse(blueprint.getRobotTypeOreTypeCosts());
		
		return blueprint;
	}

//	Each geode robot costs 2 ore and 7 obsidian
	private static RobotTypeOreTypeCost createRobotOreCost(String robotTypeOreCostsString) {
		robotTypeOreCostsString = robotTypeOreCostsString.replaceAll("Each ", "");
		String[] split = robotTypeOreCostsString.split(" robot costs ");
		
		RobotType robotType = RobotType.GEODE;
		String robotName = split[0];
		if(RobotType.ORE.getOreType().getName().equals(robotName)) {
			robotType = RobotType.ORE;	
		} else if(RobotType.CLAY.getOreType().getName().equals(robotName)) {
			robotType = RobotType.CLAY;
		} else if(RobotType.OBSIDIAN.getOreType().getName().equals(robotName)) {
			robotType = RobotType.OBSIDIAN;
		}
		
		List<OreTypeCost> oreCosts = createOreTypeCosts(split[1]);
		
		return new RobotTypeOreTypeCost(robotType, oreCosts);
	}

	//2 ore and 7 obsidian
	private static List<OreTypeCost> createOreTypeCosts(String line) {
		String[] split = line.split(" and ");
		
		List<OreTypeCost> oreTypeCosts = new ArrayList<>();
		for(String oreCostString : split) {
			oreTypeCosts.add(createOreTypeCost(oreCostString));
		}

		return oreTypeCosts;
	}

	private static OreTypeCost createOreTypeCost(String oreCostString) {
		String[] split = oreCostString.split(" ");
		
		OreType oreType = OreType.GEODE;
		String oreName = split[1];
		if(OreType.ORE.getName().equals(oreName)) {
			oreType = OreType.ORE;
		} else if(OreType.CLAY.getName().equals(oreName)) {
			oreType = OreType.CLAY;
		} else if(OreType.OBSIDIAN.getName().equals(oreName)) {
			oreType = OreType.OBSIDIAN;
		}
 		
		OreTypeCost oreTypeCost = new OreTypeCost(oreType, Long.valueOf(split[0]));

		return oreTypeCost;
	}

}
