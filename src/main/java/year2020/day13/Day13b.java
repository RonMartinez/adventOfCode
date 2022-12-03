package year2020.day13;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day13b {
	
//	private static final String FILENAME = "src/main/resources/2020/day13InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day13Input.txt";
	
	public static void main(String[] args) throws IOException {
//		long targetTime = readTargetTime();
		List<Bus> buses = createBuses();
		
		buses.forEach(b -> System.out.println(ReflectionToStringBuilder.toString(b, ToStringStyle.MULTI_LINE_STYLE)));

		long baselineId = buses.get(0).getId();
		long timestamp = 0L;
		long step = baselineId;
		int currentIndex = 0;
		
		boolean found = false;
		while( ! found) {
			System.out.println("timestamp:\t" + timestamp);
			System.out.println("step\t" + step);
			
			boolean allMatch = true;
			for(Bus bus : buses) {
				if( ! departsAtOffset(bus, timestamp)) {
					allMatch = false;
					break;
				} else {
					int index = buses.indexOf(bus);
					if(index > currentIndex) {
						currentIndex = index;
						step = lcm(step, bus.getId());
					}
				}
			}
			
			if(allMatch) {
				found = true;
				break;
			}
			
			timestamp += step;
		}
		
		System.out.println(timestamp);
	}
	
	private static boolean departsAtOffset(Bus bus, long t) {
		return (t + bus.getOffset() ) % bus.getId() == 0;
	}

	private static List<Bus> createBuses() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		List<Bus> buses = new ArrayList<>();
		int i = 0;
		for(String idString : lines.get(1).split(",")) {
			if( ! "x".equals(idString)) {
				Bus bus = new Bus();
				bus.setId(Long.parseLong(idString));
				bus.setOffset(i);
				buses.add(bus);
			}
			i++;
		}

		return buses;
	}

	private static long gcd(long a, long b) {
	    while (b > 0) {
	        long temp = b;
	        b = a % b; // % is remainder
	        a = temp;
	    }
	    return a;
	}

	private static long lcm(long a, long b) {
	    return a * (b / gcd(a, b));
	}

}
