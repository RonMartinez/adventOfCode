package year2023.day5;

public class MapRange {

	private Range sourceRange;
	private Range destinationRange;
	
	public MapRange(Long sourceRangeStart, Long destinationRangeStart, Long rangeLength) {
		this.sourceRange = new Range(sourceRangeStart, sourceRangeStart + rangeLength);
		this.destinationRange = new Range(destinationRangeStart, destinationRangeStart + rangeLength);
	}
	
	public String toString() {
		return sourceRange.toString() + " -> " + destinationRange.toString();
	}
	
	public Long convertValueFromSourceToDestination(Long value) {
		return destinationRange.getStart() + (value - sourceRange.getStart());
	}

	public Range getSourceRange() {
		return sourceRange;
	}

	public void setSourceRange(Range sourceRange) {
		this.sourceRange = sourceRange;
	}

	public Range getDestinationRange() {
		return destinationRange;
	}

	public void setDestinationRange(Range destinationRange) {
		this.destinationRange = destinationRange;
	}
	
}
