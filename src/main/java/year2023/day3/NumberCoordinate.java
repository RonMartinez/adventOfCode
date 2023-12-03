package year2023.day3;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class NumberCoordinate {

	private Long number;
	private int row;
	private int start;
	private int end;
	private Set<SymbolCoordinate> symbolCoordinates;
	
	public NumberCoordinate(Long number, int row, int start, int end) {
		this.number = number;
		this.row = row;
		this.start = start;
		this.end = end;
	}
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	public void addSymbolCoordinate(SymbolCoordinate symbolCoordinate) {
		getSymbolCoordinates().add(symbolCoordinate);
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public Set<SymbolCoordinate> getSymbolCoordinates() {
		if(symbolCoordinates == null) {
			symbolCoordinates = new HashSet<>();
		}
		return symbolCoordinates;
	}

	public void setSymbolCoordinates(Set<SymbolCoordinate> symbolCoordinates) {
		this.symbolCoordinates = symbolCoordinates;
	}

}
