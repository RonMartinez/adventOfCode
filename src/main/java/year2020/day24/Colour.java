package year2020.day24;

public class Colour {
	
	public static final Colour WHITE = new Colour("white");
	public static final Colour BLACK = new Colour("black");
	
	private String name;
	
	public Colour(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
