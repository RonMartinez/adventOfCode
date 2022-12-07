package year2022.day6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Datastream {
	
	public static final int PACKET_MARKER_SIZE = 4;
	public static final int MESSAGE_MARKER_SIZE = 14;

	private String datastream;
	private List<Integer> packetMarkers;
	private List<Integer> messageMarkers;

	public Datastream(String datastream) {
		this.datastream = datastream;
		this.packetMarkers = calculateMarkers(PACKET_MARKER_SIZE);
		this.messageMarkers = calculateMarkers(MESSAGE_MARKER_SIZE);
	}

	protected List<Integer> calculateMarkers(int markerSize) {
		List<Integer> markers = new ArrayList<>();
		for(int i = markerSize; i < datastream.length(); i++) {
			String potentialMarker = datastream.substring(i-markerSize, i); 
			if(isMarker(potentialMarker)) {
				markers.add(i);
			}
		}
		return markers;
	}

	private boolean isMarker(String potentialMarker) {
		Set<Character> characters = new HashSet<>();
		for(char c : potentialMarker.toCharArray()) {
			characters.add(c);
		}
		
		return characters.size() == potentialMarker.length();
	}

	public String getDatastream() {
		return datastream;
	}

	public void setDatastream(String datastream) {
		this.datastream = datastream;
	}

	public List<Integer> getPacketMarkers() {
		return packetMarkers;
	}

	public void setPacketMarkers(List<Integer> packetMarkers) {
		this.packetMarkers = packetMarkers;
	}

	public List<Integer> getMessageMarkers() {
		return messageMarkers;
	}

	public void setMessageMarkers(List<Integer> messageMarkers) {
		this.messageMarkers = messageMarkers;
	}

}
