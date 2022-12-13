package year2022.day13;

import java.util.Comparator;

public interface Packet {
	
	public static final Comparator<Packet> ORDER_COMPARATOR = (Packet leftPacket, Packet rightPacket) -> PacketHelper.getOrder(leftPacket, rightPacket);
	
	public String toString();
	public PacketNumber getPacketNumber();
	public PacketList getPacketList();

}
