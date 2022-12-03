package year2021.day4;

public class BingoNumber {


	private BingoRow bingoRow;
	private Long number;
	private boolean marked;

	public BingoNumber(Long number) {
		this.number = number;
	}

	public BingoRow getBingoRow() {
		return bingoRow;
	}

	public void setBingoRow(BingoRow bingoRow) {
		this.bingoRow = bingoRow;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

}
