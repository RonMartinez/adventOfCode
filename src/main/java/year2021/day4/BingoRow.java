package year2021.day4;

import java.util.ArrayList;
import java.util.List;

public class BingoRow {

	private BingoBoard bingoBoard;
	private List<BingoNumber> bingoNumbers;

	public void addBingoNumber(BingoNumber bingoNumber) {
		bingoNumber.setBingoRow(this);
		getBingoNumbers().add(bingoNumber);
		
		getBingoBoard().getBingoNumberMap().put(bingoNumber.getNumber(), bingoNumber);
	}
	
	public boolean isWinning() {
		return getBingoNumbers().stream()
				.allMatch(BingoNumber::isMarked);
	}

	public Long getUnmarkedSum() {
		Long sum = 0L;
		
		for(BingoNumber bingoNumber : getBingoNumbers()) {
			if( ! bingoNumber.isMarked()) {
				sum += bingoNumber.getNumber();
			}
		}
		
		return sum;
	}

	public BingoBoard getBingoBoard() {
		return bingoBoard;
	}

	public void setBingoBoard(BingoBoard bingoBoard) {
		this.bingoBoard = bingoBoard;
	}

	public List<BingoNumber> getBingoNumbers() {
		if(bingoNumbers == null) {
			bingoNumbers = new ArrayList<>();
		}
		return bingoNumbers;
	}

	public void setBingoNumbers(List<BingoNumber> bingoNumbers) {
		this.bingoNumbers = bingoNumbers;
	}

}
