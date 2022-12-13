package year2022.day13;

public class PacketPair {
	
	private Packet leftPacket;
	private Packet rightPacket;
	
	public PacketPair(Packet leftPacket, Packet rightPacket) {
		this.leftPacket = leftPacket;
		this.rightPacket = rightPacket;
	}
	
	public int getOrder() {
		return PacketHelper.getOrder(leftPacket, rightPacket);
	}

	public Packet getLeftPacket() {
		return leftPacket;
	}

	public void setLeftPacket(Packet leftPacket) {
		this.leftPacket = leftPacket;
	}

	public Packet getRightPacket() {
		return rightPacket;
	}

	public void setRightPacket(Packet rightPacket) {
		this.rightPacket = rightPacket;
	}

}
