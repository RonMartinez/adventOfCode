package year2023.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

	private Long id;
	private List<ColourCount> colourCounts;
	
	public Game(Long id) {
		this.id = id;
	}

	public Long getPower() {
		return getCounts().stream()
				.reduce(1L, (a, b) -> a * b);
	}

	public List<Long> getCounts() {
		return getColourCounts().stream()
				.map(ColourCount::getCount)
				.collect(Collectors.toList());
	}

	public ColourCount getColourCountByColour(Colour colour) {
		return getColourCounts().stream()
				.filter(cc -> cc.getColour().equals(colour))
				.findFirst().orElse(null);
	}

	public void addColourCount(ColourCount newColourCount) {
		ColourCount existingColourCount = getColourCountByColour(newColourCount.getColour());
		
		if(existingColourCount != null) {
			if(existingColourCount.getCount() < newColourCount.getCount()) {
				existingColourCount.setCount(newColourCount.getCount());
			}
		} else {
			getColourCounts().add(newColourCount);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ColourCount> getColourCounts() {
		if(colourCounts == null) {
			colourCounts = new ArrayList<>();
		}
		return colourCounts;
	}

	public void setColourCounts(List<ColourCount> colourCounts) {
		this.colourCounts = colourCounts;
	}

}
