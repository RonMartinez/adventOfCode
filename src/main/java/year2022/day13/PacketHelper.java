package year2022.day13;

public class PacketHelper {
	
	public static int getOrder(Packet leftPacket, Packet rightPacket) {
		int returnValue = 0;
		
		PacketNumber leftPacketNumber = leftPacket.getPacketNumber();
		PacketNumber rightPacketNumber = rightPacket.getPacketNumber();
		
		if(leftPacketNumber != null
				&& rightPacketNumber != null) {
			System.out.println("Compare " + leftPacketNumber.getValue() + " vs " + rightPacketNumber.getValue());
			returnValue = leftPacketNumber.getValue().compareTo(rightPacketNumber.getValue());
			
		} else {
			returnValue = getOrder(leftPacket.getPacketList(), rightPacket.getPacketList());
		}
		
		return returnValue;
	}

	private static int getOrder(PacketList leftPacketList, PacketList rightPacketList) {
		int returnValue = 0;
		
		int i = 0;
		Packet leftPacket = leftPacketList.getPacket(i);
		Packet rightPacket = rightPacketList.getPacket(i);
		while(returnValue == 0
				&& (
						leftPacket != null
						|| rightPacket != null
						)
				) {
			if(leftPacket != null
					&& rightPacket != null) {
				returnValue = getOrder(leftPacket, rightPacket);
			} else {
				if(leftPacket == null
						&& rightPacket != null) {
					System.out.println("Left side ran out of items, so inputs are in the right order");
					returnValue = -1;
				} else if(leftPacket != null
						&& rightPacket == null) {
					System.out.println("Right side ran out of items, so inputs are not in the right order");
					returnValue = 1;
				}
			}
			
			i++;
			leftPacket = leftPacketList.getPacket(i);
			rightPacket = rightPacketList.getPacket(i);
		}

		return returnValue;
	}

}
