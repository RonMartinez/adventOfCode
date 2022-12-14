package year2022.day14;

import java.util.ArrayList;
import java.util.List;

public class CoordinatePath {

	private List<Coordinate> coordinates;
	
	public void addCoordinate(Coordinate coordinate) {
		getCoordinates().add(coordinate);
	}

	public List<Coordinate> getCoordinates() {
		if(coordinates == null) {
			coordinates = new ArrayList<>();
		}
		return coordinates;
	}

	public void setCoordinates(List<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}


}
