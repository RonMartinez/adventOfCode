package year2020.day17;

public class Cube {

	private String value;
	private String nextValue;
	private Coordinate coordinate;

	public Cube(String value, Coordinate coordinate) {
		this.value = value;
		this.coordinate = coordinate;
	}

	public boolean isActive() {
		return "#".equals(value);
	}
	
	public void processChange() {
		value = nextValue;
		nextValue = null;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getNextValue() {
		return nextValue;
	}

	public void setNextValue(String nextValue) {
		this.nextValue = nextValue;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

}
