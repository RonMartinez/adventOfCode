package year2022.day16b;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day16b {

//	private static final String FILENAME = "src/main/resources/2022/day16InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day16Input.txt";
	
	public static final String START_VALVE_NAME = "AA";
	public static final Long TOTAL_MINUTES = 26L;
	public static final int MAX_OPTIMAL_VALVE_SYSTEMS_SIZE = 5;
	public static final Long OPEN_VALVE_TIME = 1L;
	public static final Long TRAVEL_TIME = 1L;
	
	public static void main(String[] args) throws IOException {
		Set<Valve> valves = readValves();
		
		valves.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
//		for(Valve valve : valves) {
//			System.out.println(valve.getName());
//			System.out.println(valve.getRate());
//			System.out.println(valve.getValves().stream()
//					.map(Valve::getName)
//					.collect(Collectors.joining(",")));
//		}
		
		Long startMinute = 0L;
		Valve startValve = findValveByName(valves, START_VALVE_NAME);
		long nonZeroRateCount = valves.stream()
				.filter(v -> ! v.isZeroRate())
				.count();
		
		ValveSystem valveSystem = new ValveSystem(TOTAL_MINUTES, 0L, nonZeroRateCount);
		valveSystem.setCurrentMinute(startMinute);
		valveSystem.setCurrentValve(startValve);
		valveSystem.setCurrentElephantValve(startValve);

		TreeSet<ValveSystem> optimalValveSystems = new TreeSet<>(ValveSystem.TOTAL_PRESSURE_RELEASED_COMPARATOR.reversed());
		Set<ValveSystem> processed = new HashSet<>();
		
		recurse(valveSystem, optimalValveSystems, processed);
	
		ValveSystem optimalValveSystem = optimalValveSystems.first();
		
		optimalValveSystem.getOpenedValves().forEach(ov -> {
			System.out.println(ov.getValve().getName() + " / " + ov.getMinutedOpened() + " / " + ov.getPressureReleased());
		});
		
		System.out.println(optimalValveSystem.getTotalPressureReleased());
		
		
	}
	
	private static void recurse(ValveSystem valveSystem, TreeSet<ValveSystem> optimalValveSystems, Set<ValveSystem> processed) {
//		valveSystem.getOpenedValves().stream()
//				.forEach(ov -> {
//					System.out.print(ov.getValve().getName() + " / " + ov.getMinutedOpened() + " / " + ov.getPressureReleased());
//					System.out.println();
//				});
//		System.out.println("#######################");
		
		if(processed.stream()
				.filter(p -> p.getValves().equals(valveSystem.getValves()))
				.filter(p -> p.getCurrentValve().equals(valveSystem.getCurrentValve()))
				.filter(p -> p.getCurrentElephantValve().equals(valveSystem.getCurrentElephantValve()))
				.filter(p -> p.getTotalPressureReleased() >= valveSystem.getTotalPressureReleased())
				.filter(p -> p.getCurrentMinute() <= valveSystem.getCurrentMinute())
				.collect(Collectors.toSet()).isEmpty()
				) {
			Long currentMinute = valveSystem.getCurrentMinute();
			Valve currentValve = valveSystem.getCurrentValve();
			Valve currentElephantValve = valveSystem.getCurrentElephantValve();
			if(currentMinute.compareTo(valveSystem.getTotalMinutes()) >= 0
					|| valveSystem.getOpenedValves().size() == valveSystem.getValveNonZeroRateCount()
					) {
				addEntry(optimalValveSystems, valveSystem);
			} else {
				Long newMinute = currentMinute + OPEN_VALVE_TIME;
				Set<Valve> currentValveOptions = currentValve.getValves();
				if( ! currentValve.isZeroRate()
						&& valveSystem.getOpenedValveByValve(currentValve) == null) {
					currentValveOptions.add(currentValve);
				}

				Set<Valve> currentElephantValveOptions = currentElephantValve.getValves();
				if( ! currentElephantValve.isZeroRate()
						&& valveSystem.getOpenedValveByValve(currentElephantValve) == null) {
					currentElephantValveOptions.add(currentElephantValve);
				}
				
				for(Valve currentValveOption : currentValveOptions) {
					for(Valve currentElephantValveOption : currentElephantValveOptions) {
						ValveSystem newValveSystem = valveSystem.copyValveSystem();
						newValveSystem.setCurrentMinute(newMinute);
						
						newValveSystem.setCurrentValve(currentValveOption);
						if(currentValveOption.equals(currentValve)) {
							newValveSystem.addOpenedValve(currentValveOption);	
						}

						newValveSystem.setCurrentElephantValve(currentValveOption);
						if(currentElephantValveOption.equals(currentElephantValve)) {
							newValveSystem.addOpenedValve(currentValveOption);	
						}

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
