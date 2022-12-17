package year2022.day17;

public class RockMovement {
	
	public static final RockMovement LEFT = new RockMovement("Left", -1L, 0L);
	public static final RockMovement RIGHT = new RockMovement("Right", 1L, 0L);
	public static final RockMovement UP = new RockMovement("Up", 0L, 1L);
	public static final RockMovement DOWN = new RockMovement("Down", 0L, -1L);
	public static final RockMovement UP_RIGHT = new RockMovement("Up Right", 1L, 1L);
	public static final RockMovement DOWN_RIGHT = new RockMovement("Down Right", 1L, -1L);
	public static final RockMovement DOWN_LEFT = new RockMovement("Down Left", -1L, -1L);
	public static final RockMovement UP_LEFT = new RockMovement("Up Left", -1L, 1L);

	private String name;
	private long xChange;
	private long yChange;
	private RockMovement oppositeRockMovement;

	public RockMovement() {
	}

	public RockMovement(String name, long xChange, long yChange) {
		this.name = name;
		this.xChange = xChange;
		this.yChange = yChange;
		this.oppositeRockMovement = new RockMovement();
		oppositeRockMovement.setName("Reverse " + name);
		oppositeRockMovement.setxChange(xChange * -1);
		oppositeRockMovement.setyChange(yChange * -1);
	}

	public long getxChange() {
		return xChange;
	}

	public void setxChange(long xChange) {
		this.xChange = xChange;
	}

	public long getyChange() {
		return yChange;
	}

	public void setyChange(long yChange) {
		this.yChange = yChange;
	}

	public RockMovement getOppositeRockMovement() {
		return oppositeRockMovement;
	}

	public void setOppositeRockMovement(RockMovement oppositeRockMovement) {
		this.oppositeRockMovement = oppositeRockMovement;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
