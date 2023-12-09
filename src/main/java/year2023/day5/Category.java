package year2023.day5;

public class Category {
	
	public static final Category SEED = new Category("seed");
	public static final Category SOIL = new Category("soil");
	public static final Category FERTILIZER = new Category("fertilizer");
	public static final Category WATER = new Category("water");
	public static final Category LIGHT = new Category("light");
	public static final Category TEMPERATURE = new Category("temperature");
	public static final Category HUMIDITY = new Category("humidity");
	public static final Category LOCATION = new Category("location");
	
	private String name;

	public Category(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
