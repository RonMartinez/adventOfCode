package year2023.day2;

public class ColourCount {

	private Colour colour;
	private Long count;

	public ColourCount(Colour colour, Long count) {
		this.colour = colour;
		this.count = count;
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
