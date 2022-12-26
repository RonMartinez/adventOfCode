package year2022.day16;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import util.ListHelper;

public class Day16 {

//	private static final String FILENAME = "src/main/resources/2022/day16InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day16Input.txt";
	
	public static final String START_VALVE_NAME = "AA";
	public static final Long TOTAL_MINUTES = 30L;
	public static final int MAX_OPTIMAL_VALVE_SYSTEMS_SIZE = 5;
	public static final Long OPEN_VALVE_TIME = 1L;
	public static final Long TRAVEL_TIME = 1L;
	
	public static void main(String[] args) throws IOException {
		Set<Valve> valves = readValves();
		
		Set<Valve> nonZeroRateValves = valves.stream()
				.filter(v -> ! v.isZeroRate())
				.collect(Collectors.toSet());
		for(Valve valve : valves) {
			Set<Valve> otherNonZeroRateValves = nonZeroRateValves.stream()
					.filter(v -> ! valve.equals(v))
					.collect(Collectors.toSet());
			for(Valve otherNonZeroRateValve : otherNonZeroRateValves) {
				Path path = findShortestPath(valve, otherNonZeroRateValve);
				valve.addValveLink(new ValveLink(path, otherNonZeroRateValve));
			}
		}
		
		valves.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		Long startMinute = 0L;
		Valve startValve = findValveByName(valves, START_VALVE_NAME);

		
		ValveSystem valveSystem = new ValveSystem(
				TOTAL_MINUTES,
				startMinute,
				startValve,
				0L,
				new HashSet<>(),
				0L
				);

		TreeSet<ValveSystem> optimalValveSystems = new TreeSet<>(ValveSystem.TOTAL_PRESSURE_RELEASED_COMPARATOR.reversed());
		Set<ValveSystem> processed = new HashSet<>();
		
		recurse(valveSystem, optimalValveSystems, processed);
	
		ValveSystem optimalValveSystem = optimalValveSystems.first();
		
		System.out.println(optimalValveSystem.getTotalPressureReleased());
	}
	
	private static Path findShortestPath(Valve startValve, Valve endValve) {
		Path shortestPath = null;
		
		Set<Valve> visited = new HashSet<>();

		LinkedList<Path> queue = new LinkedList<>();

		Path startPath = new Path();
		startPath.addValve(startValve);

		visited.add(startValve);
		queue.add(startPath);
		while ( ! queue.isEmpty()) {
			Path path = queue.poll();
			
			List<Valve> valves = path.getValves();
			
			Valve valve = ListHelper.getLast(valves); 
			if(valve.equals(endValve)) {
				shortestPath = path;
			} else {
				for(Valve unvisitedValve : getUnvisitedValves(valve, visited)) {
					Path copyPath = path.copyPath();
					copyPath.addValve(unvisitedValve);
					
					visited.add(unvisitedValve);
					queue.add(copyPath);
				}
			}
		}
		
		return shortestPath;
	}

	
	private static Set<Valve> getUnvisitedValves(Valve valve, Set<Valve> visited) {
		return valve.getValves().stream()
				.filter(v -> ! visited.contains(v))
				.collect(Collectors.toSet());
	}

	private static void recurse(ValveSystem valveSystem, TreeSet<ValveSystem> optimalValveSystems, Set<ValveSystem> processed) {
		if( ! processed.contains(valveSystem)) {
			Long currentMinute = valveSystem.getCurrentMinute();
			Valve currentValve = valveSystem.getCurrentValve();
			
			if(currentMinute.compareTo(valveSystem.getTotalMinutes()) >= 0
					) {
				addEntry(optimalValveSystems, valveSystem);
			} else {				
				Set<ValveLink> unopenedValveLinks = currentValve.getValveLinks().stream()
						.filter(vl ->  ! valveSystem.getValves().contains(vl.getValve()))
						.collect(Collectors.toSet());
				
				if( ! currentValve.isZeroRate()
						&& ! valveSystem.getValves().contains(currentValve)
						) {
					valveSystem.openValve(currentValve);
					recurse(valveSystem, optimalValveSystems, processed);
				} else if(unopenedValveLinks.isEmpty()
						&& valveSystem.getValves().contains(currentValve)
						) {
					valveSystem.waitUntilEnd();
					recurse(valveSystem, optimalValveSystems, processed);
				} else {
					for(ValveLink valveLink : unopenedValveLinks) {
						ValveSystem newValveSystem = valveSystem.travelToValve(valveLink);
						recurse(newValveSystem, optimalValveSystems, processed);
					}					
				}
			}
			
			processed.add(valveSystem);	
		}
	}

	private static void addEntry(TreeSet<ValveSystem> optimalValveSystems, ValveSystem valveSystem) {
//		valveSystem.getOpenedValves().stream()
//				.forEach(ov -> {
//						System.out.print(ov.getValve().getName() + " / " + ov.getMinutedOpened() + " / " + ov.getPressureReleased());
//						System.out.println();
//				});
//		System.out.println(valveSystem.getTotalPressureReleased());
		
		optimalValveSystems.add(valveSystem);
		if(optimalValveSystems.size() > MAX_OPTIMAL_VALVE_SYSTEMS_SIZE) {
			optimalValveSystems.pollLast();
		}
	}

	private static Valve findValveByName(Set<Valve> valves, String name) {
		return valves.stream()
				.filter(v -> name.equals(v.getName()))
				.findFirst().orElse(null);
	}
	
	private static Set<Valve> readValves() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		Set<Map<Valve, List<String>>> valveMaps = lines.stream()
				.map(line -> createValve(line))
				.collect(Collectors.toSet());
		
		Set<Valve> valves = valveMaps.stream()
				.map(vm -> vm.keySet().iterator().next())
				.collect(Collectors.toSet());
		
		for(Map<Valve, List<String>> valveMap : valveMaps) {
			Valve valve = valveMap.keySet().iterator().next();
			List<String> names = valveMap.get(valve);
			
			for(String name : names) {
				Valve linkedValve = valves.stream()
						.filter(v -> name.equals(v.getName()))
						.findFirst().orElse(null);
				
				valve.addValve(linkedValve);
			}
		}

		return valves;
	}

	private static Map<Valve, List<String>> createValve(String line) {
		line = line.replaceAll("Valve ", "");
		line = line.replaceAll(" has flow rate=", ",");
		line = line.replaceAll(" tunnels lead to valves ", "");
		line = line.replaceAll(" tunnel leads to valve ", "");
		
		String[] lineSplit = line.split(";");
		String[] nameRateSplit = lineSplit[0].split(",");
		
		Valve valve = new Valve();
		valve.setName(nameRateSplit[0]);
		valve.setRate(Long.valueOf(nameRateSplit[1]));
		
		List<String> valves = Arrays.asList(lineSplit[1].split(", "));
		
		Map<Valve, List<String>> valveMap = new HashMap<>();
		valveMap.put(valve, valves);
		
		return valveMap;
	}

}
