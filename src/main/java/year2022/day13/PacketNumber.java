package year2022.day13;

public class PacketNumber implements Packet {
	
	private Long value;
	
	public PacketNumber(Long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public PacketNumber getPacketNumber() {
		return this;
	}

	@Override
	public PacketList getPacketList() {
		return new PacketList(this);
	}

}
