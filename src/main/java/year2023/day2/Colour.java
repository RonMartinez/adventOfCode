package year2023.day2;

public class Colour {
	
	public static final Colour RED = new Colour("red");
	public static final Colour GREEN = new Colour("green");
	public static final Colour BLUE = new Colour("blue");

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
