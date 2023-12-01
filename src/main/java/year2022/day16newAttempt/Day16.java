package year2022.day16newAttempt;

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
	public static final Long TOTAL_MINUTES = 26L;
	public static final int MAX_OPTIMAL_VALVE_SYSTEMS_SIZE = 5;
	public static final Long OPEN_VALVE_TIME = 1L;
	public static final Long TRAVEL_TIME = 1L;
	public static final Long NUMBER_OF_PLAYERS = 2L;
	
	private static Valve startValve;
	private static int nonZeroRateValveCount;
	
	public static void main(String[] args) throws IOException {
		Set<Valve> valves = readValves();
		
		Set<Valve> nonZeroRateValves = valves.stream()
				.filter(v -> ! v.isZeroRate())
				.collect(Collectors.toSet());
		nonZeroRateValveCount = nonZeroRateValves.size();
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
		startValve = findValveByName(valves, START_VALVE_NAME);

//		Set<ValveLink> valveLinksToRemove = new HashSet<>();
//		for(ValveLink valveLink : startValve.getValveLinks()) {
//			System.out.println(valveLink.getValve().getName());
//			System.out.println(valveLink.getPath().getSize());
//			List<Valve> pathValves = valveLink.getPath().getValves();
//			for(int i = 1; i < pathValves.size() - 1; i++) {
//				if( ! pathValves.get(i).isZeroRate()) {
//					valveLinksToRemove.add(valveLink);
//					break;
//				}
//			}
//		}
//		startValve.getValveLinks().removeAll(valveLinksToRemove);
//
//		for(Valve valve : nonZeroRateValves) {
//			valveLinksToRemove = new HashSet<>();
//			for(ValveLink valveLink : valve.getValveLinks()) {
//				System.out.println(valveLink.getValve());
//				System.out.println(valveLink.getPath().getSize());
//				List<Valve> pathValves = valveLink.getPath().getValves();
//				for(int i = 1; i < pathValves.size() - 1; i++) {
//					if( ! pathValves.get(i).isZeroRate()) {
//						valveLinksToRemove.add(valveLink);
//						break;
//					}
//				}
//			}
//			valve.getValveLinks().removeAll(valveLinksToRemove);
//		}
		
		ValveSystem valveSystem = new ValveSystem(
				TOTAL_MINUTES,
				startMinute,
				startValve,
				0L,
				new HashSet<>(),
				0L,
				NUMBER_OF_PLAYERS - 1
				);

		TreeSet<ValveSystem> optimalValveSystems = new TreeSet<>(ValveSystem.TOTAL_PRESSURE_RELEASED_COMPARATOR.reversed());
		Set<Integer> processed = new HashSet<>();
		
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

	private static void recurse(ValveSystem valveSystem, TreeSet<ValveSystem> optimalValveSystems, Set<Integer> processed) {
		if( ! processed.contains(valveSystem.hashCode())) {
			ValveSystem original = valveSystem.copyValveSystem();
			Long currentMinute = valveSystem.getCurrentMinute();
			Valve currentValve = valveSystem.getCurrentValve();
			
			if(currentMinute.compareTo(valveSystem.getTotalMinutes()) >= 0
					) {
				if(valveSystem.getPlayersRemaining().compareTo(0L) > 0) {
					valveSystem.setCurrentMinute(0L);
					valveSystem.setCurrentRate(0L);
					valveSystem.setCurrentValve(startValve);
					valveSystem.setPlayersRemaining(valveSystem.getPlayersRemaining() - 1L);
//					System.out.println("passing to next player: " + copy.getValves().stream().map(Valve::getName).collect(Collectors.joining("/")));
					
					recurse(valveSystem, optimalValveSystems, processed);
//					valveSystem.applyValues(original);
				} else {
					addEntry(optimalValveSystems, valveSystem);	
				}
			} else {				
				Set<ValveLink> unopenedValveLinks = currentValve.getValveLinks().stream()
						.filter(vl ->  ! valveSystem.getValves().contains(vl.getValve()))
						.collect(Collectors.toSet());
				if(valveSystem.getPlayersRemaining() > 0L
						&& valveSystem.getValves().size() >= (nonZeroRateValveCount / 2)
						) {
					unopenedValveLinks.clear();
//					unopenedValveLinks.add(null);	
				}
				
//				if(valveSystem.getPlayersRemaining() > 0L
//						&& valveSystem.getValves().size() >= (nonZeroRateValveCount / 3 * 2)
//						) {
//					unopenedValveLinks.clear();
//					unopenedValveLinks.add(null);
//				}
				
				if( ! currentValve.isZeroRate()
						&& ! valveSystem.getValves().contains(currentValve)
						) {
					valveSystem.openValve(currentValve);
					recurse(valveSystem, optimalValveSystems, processed);
//					valveSystem.applyValues(original);
				} else if(unopenedValveLinks.isEmpty()
						) {
					valveSystem.waitUntilEnd();
					recurse(valveSystem, optimalValveSystems, processed);
//					valveSystem.applyValues(original);
				} else {
					for(ValveLink valveLink : unopenedValveLinks) {
						if(valveLink == null) {
							valveSystem.waitUntilEnd();
							recurse(valveSystem, optimalValveSystems, processed);
						} else {
							valveSystem.travelToValve2(valveLink);
							recurse(valveSystem, optimalValveSystems, processed);
						}
						valveSystem.applyValues(original);
					}					
				}
			}

			valveSystem.applyValues(original);
			if( ! (valveSystem.getPlayersRemaining() == 0L
					&& valveSystem.getCurrentMinute().equals(valveSystem.getTotalMinutes()))
					) {
				processed.add(valveSystem.hashCode());
			}
//			System.out.println(processed.size());
		} else {
//			System.out.println("skipping");
		}
	}

	private static void addEntry(TreeSet<ValveSystem> optimalValveSystems, ValveSystem valveSystem) {
//		valveSystem.getOpenedValves().stream()
//				.forEach(ov -> {
//						System.out.print(ov.getValve().getName() + " / " + ov.getMinutedOpened() + " / " + ov.getPressureReleased());
//						System.out.println();
//				});
//		System.out.println(valveSystem.getTotalPressureReleased());
		
		optimalValveSystems.add(valveSystem.copyValveSystem());
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
