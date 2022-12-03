package year2020.day13;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day13 {
	
//	private static final String FILENAME = "src/main/resources/2020/day13InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day13Input.txt";
	
	public static void main(String[] args) throws IOException {
		long targetTime = readTargetTime();
		List<Bus> buses = createBuses();
		
		buses.forEach(b -> System.out.println(ReflectionToStringBuilder.toString(b, ToStringStyle.MULTI_LINE_STYLE)));
		
		Long earliestTime = Long.MAX_VALUE;
		Bus foundBus = null;
		for(Bus bus : buses) {
			long interimTime = targetTime / bus.getId();

			long mod = 0;
			if(targetTime % bus.getId() > 0) {
				mod = 1;
			}
			long time = (interimTime + mod) * bus.getId();
			if(time < earliestTime) {
				foundBus = bus;
				earliestTime = time;
			}
		}

		System.out.println(earliestTime);
		System.out.println(foundBus.getId());
		System.out.println(foundBus.getId() * (earliestTime - targetTime));
	}

	private static long readTargetTime() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		return Long.parseLong(lines.get(0));
	}

	private static List<Bus> createBuses() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		List<Bus> buses = new ArrayList<>();
		for(String idString : lines.get(1).split(",")) {
			if( ! "x".equals(idString)) {
				Bus bus = new Bus();
				bus.setId(Long.parseLong(idString));
				buses.add(bus);
			}
		}

		return buses;
	}


}
