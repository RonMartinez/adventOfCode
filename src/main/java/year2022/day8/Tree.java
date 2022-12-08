package year2022.day8;

public class Tree {

	private Long height;
	private TreeRow treeRow;
	private boolean visibleFromLeft;
	private boolean visibleFromRight;
	private boolean visibleFromTop;
	private boolean visibleFromBottom;

	private Long scenicScoreLeft;
	private Long scenicScoreRight;
	private Long scenicScoreTop;
	private Long scenicScoreBottom;

	public Long getScenicScore() {
		return scenicScoreLeft
				* scenicScoreRight
				* scenicScoreTop
				* scenicScoreBottom
				;
	}

	public boolean isVisible() {
		return visibleFromLeft
				|| visibleFromRight
				|| visibleFromTop
				|| visibleFromBottom
				;
	}
	
	public Tree(Long height) {
		this.height = height;
	}

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public TreeRow getTreeRow() {
		return treeRow;
	}

	public void setTreeRow(TreeRow treeRow) {
		this.treeRow = treeRow;
	}

	public boolean isVisibleFromLeft() {
		return visibleFromLeft;
	}

	public void setVisibleFromLeft(boolean visibleFromLeft) {
		this.visibleFromLeft = visibleFromLeft;
	}

	public boolean isVisibleFromRight() {
		return visibleFromRight;
	}

	public void setVisibleFromRight(boolean visibleFromRight) {
		this.visibleFromRight = visibleFromRight;
	}

	public boolean isVisibleFromTop() {
		return visibleFromTop;
	}

	public void setVisibleFromTop(boolean visibleFromTop) {
		this.visibleFromTop = visibleFromTop;
	}

	public boolean isVisibleFromBottom() {
		return visibleFromBottom;
	}

	public void setVisibleFromBottom(boolean visibleFromBottom) {
		this.visibleFromBottom = visibleFromBottom;
	}

	public Long getScenicScoreLeft() {
		return scenicScoreLeft;
	}

	public void setScenicScoreLeft(Long scenicScoreLeft) {
		this.scenicScoreLeft = scenicScoreLeft;
	}

	public Long getScenicScoreRight() {
		return scenicScoreRight;
	}

	public void setScenicScoreRight(Long scenicScoreRight) {
		this.scenicScoreRight = scenicScoreRight;
	}

	public Long getScenicScoreTop() {
		return scenicScoreTop;
	}

	public void setScenicScoreTop(Long scenicScoreTop) {
		this.scenicScoreTop = scenicScoreTop;
	}

	public Long getScenicScoreBottom() {
		return scenicScoreBottom;
	}

	public void setScenicScoreBottom(Long scenicScoreBottom) {
		this.scenicScoreBottom = scenicScoreBottom;
	}

}
