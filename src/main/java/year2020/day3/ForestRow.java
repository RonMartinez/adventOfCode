package year2020.day3;

import java.util.List;

public class ForestRow {
	
	private List<ForestCoordinate> forestCoordinates;

	public ForestRow(List<ForestCoordinate> forestCoordinates) {
		super();
		this.forestCoordinates = forestCoordinates;
	}

	public List<ForestCoordinate> getForestCoordinates() {
		return forestCoordinates;
	}

	public void setForestCoordinates(List<ForestCoordinate> forestCoordinates) {
		this.forestCoordinates = forestCoordinates;
	}

}
