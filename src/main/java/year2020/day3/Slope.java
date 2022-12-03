package year2020.day3;

public class Slope {
	private int xChange;
	private int yChange;

	public Slope(int xChange, int yChange) {
		super();
		this.xChange = xChange;
		this.yChange = yChange;
	}

	public int getxChange() {
		return xChange;
	}

	public void setxChange(int xChange) {
		this.xChange = xChange;
	}

	public int getyChange() {
		return yChange;
	}

	public void setyChange(int yChange) {
		this.yChange = yChange;
	}
}
