package year2022.day13;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day13b {

//	private static final String FILENAME = "src/main/resources/2022/day13InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day13Input.txt";
	
	public static final List<String> DIVIDER_PACKET_STRINGS = Arrays.asList("[[2]]", "[[6]]");
	
	public static void main(String[] args) throws IOException {
		List<PacketPair> packetPairs = readPacketPairs();
		
		packetPairs.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		for(PacketPair packetPair : packetPairs) {
			System.out.println(packetPair.getLeftPacket().toString());
			System.out.println(packetPair.getRightPacket().toString());
			System.out.println();
		}
		
		Set<Packet> packets = packetPairs.stream()
				.map(PacketPair::getLeftPacket)
				.collect(Collectors.toSet());
		
		packets.addAll(packetPairs.stream()
				.map(PacketPair::getRightPacket)
				.collect(Collectors.toSet()));
		
		Set<Packet> dividerPackets = DIVIDER_PACKET_STRINGS.stream()
				.map(Day13b::createPacket)
				.collect(Collectors.toSet());

		packets.addAll(dividerPackets);
		
		List<Packet> orderedPackets = packets.stream()
				.sorted(Packet.ORDER_COMPARATOR)
				.collect(Collectors.toList());
		
		Integer result = orderedPackets.stream()
				.filter(op -> dividerPackets.contains(op))
				.map(op -> orderedPackets.indexOf(op) + 1)
				.reduce(1, (a,b) -> a*b);
		
		System.out.println(result);

		System.out.println("done");
	}
	
	private static List<PacketPair> readPacketPairs() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		List<PacketPair> packetPairs = new ArrayList<>(); 

		Iterator<String> iterator = lines.iterator();
		while(iterator.hasNext()) {
			String leftPacketString = iterator.next();
			if(StringUtils.isNotBlank(leftPacketString)) {
				packetPairs.add(createPacketPair(leftPacketString, iterator.next()));	
			}
			
		}
		
		return packetPairs;
	}

	private static PacketPair createPacketPair(String leftPacketString, String rightPacketString) {
		Packet leftPacket = createPacket(leftPacketString);
		Packet rightPacket = createPacket(rightPacketString);
		
		return new PacketPair(leftPacket, rightPacket);
	}

	private static Packet createPacket(String packetString) {
		PacketList packetList = null;
		
		Stack<PacketList> packetListStack = new Stack<>();
		
		//kind of a kludgey way to build up the numbers, whatever
		StringBuilder sb = new StringBuilder();
		for(char c : packetString.toCharArray()) {
			String element = String.valueOf(c);
			if(PacketList.PACKET_LIST_START.equals(element)) {
				packetListStack.add(new PacketList());
			} else if (PacketList.PACKET_LIST_END.equals(element)) {
				sb = addPacketNumberIfExists(packetListStack, sb);
				
				PacketList completedPacketList = packetListStack.pop();
				if( ! packetListStack.isEmpty()) {
					packetList = packetListStack.lastElement();
					packetList.addPacket(completedPacketList);
				} else {
					packetList = completedPacketList;
				}
			} else if(PacketList.PACKET_LIST_DELIMITER.equals(element)) {
				sb = addPacketNumberIfExists(packetListStack, sb);
			} else {
				sb.append(c);
			}
		}
		
		return packetList;
	}

	private static StringBuilder addPacketNumberIfExists(Stack<PacketList> packetListStack, StringBuilder sb) {
		String valueString = sb.toString();
		if(StringUtils.isNotBlank(valueString)) {
			packetListStack.lastElement().addPacket(new PacketNumber(Long.valueOf(valueString)));
		}
		return new StringBuilder();
	}

}
