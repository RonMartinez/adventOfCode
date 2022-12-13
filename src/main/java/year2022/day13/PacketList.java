package year2022.day13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class PacketList implements Packet {
	
	public static final String PACKET_LIST_START = "["; 
	public static final String PACKET_LIST_END = "]";
	public static final String PACKET_LIST_DELIMITER = ",";
	
	private List<Packet> packets;

	public PacketList() {
	}

	public PacketList(Long value) {
		this(Collections.singletonList(value));
	}

	public PacketList(List<Long> values) {
		values.stream()
				.map(PacketNumber::new)
				.forEach(this::addPacket);
	}

	public PacketList(Packet packet) {
		addPacket(packet);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(PACKET_LIST_START);
		
		sb.append(getPackets().stream()
				.map(p -> p.toString())
				.collect(Collectors.joining(PACKET_LIST_DELIMITER)));
		
		sb.append(PACKET_LIST_END);
		return sb.toString();
	}
	
	public Packet getPacket(int index) {
		Packet packet = null;
		if(index < getPackets().size()) {
			packet = getPackets().get(index);
		}
		return packet;
	}
	

	public void addPacket(Packet packet) {
		getPackets().add(packet);
	}

	public List<Packet> getPackets() {
		if(packets == null) {
			packets = new ArrayList<>();
		}
		return packets;
	}

	public void setPackets(List<Packet> packets) {
		this.packets = packets;
	}

	@Override
	public PacketNumber getPacketNumber() {
		return null;
	}

	@Override
	public PacketList getPacketList() {
		return this;
	}
	
}
