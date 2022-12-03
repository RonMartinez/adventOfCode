package year2022.day2;

public class RpsShape {
	
	public static final RpsShape ROCK = new RpsShape("rock", 1L);
	public static final RpsShape PAPER = new RpsShape("paper", 2L);
	public static final RpsShape SCISSORS = new RpsShape("scissors", 3L);
	
	private String name;
	private Long value;

	public RpsShape(String name, Long value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}


}
