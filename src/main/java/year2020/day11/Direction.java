package year2020.day11;

public class Direction {

	private int yChange;
	private int xChange;

	public Direction(int yChange, int xChange) {
		this.yChange = yChange;
		this.xChange = xChange;
	}

	public int getyChange() {
		return yChange;
	}

	public void setyChange(int yChange) {
		this.yChange = yChange;
	}

	public int getxChange() {
		return xChange;
	}

	public void setxChange(int xChange) {
		this.xChange = xChange;
	}

}
