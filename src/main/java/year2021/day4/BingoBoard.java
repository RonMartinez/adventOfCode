package year2021.day4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BingoBoard {
	
	private List<BingoRow> bingoRows;
	private Map<Number, BingoNumber> bingoNumberMap;
	
	public void addBingoRow(BingoRow bingoRow) {
		bingoRow.setBingoBoard(this);
		getBingoRows().add(bingoRow);
	}
	
	public boolean isWinning() {
		boolean winning = false;
				
		boolean winningRow = getBingoRows().stream()
				.anyMatch(BingoRow::isWinning);
		
		winning |= winningRow;
		
		if( ! winning ) {
			for(int j = 0; j < getBingoRows().iterator().next().getBingoNumbers().size(); j++) {
				boolean winningColumn = true;	
				for(int i = 0; i < getBingoRows().size(); i++) {
					winningColumn &= getBingoRows().get(i).getBingoNumbers().get(j).isMarked();
					if( ! winningColumn) {
						break;
					}
				}
				if(winningColumn) {
					winning |= winningColumn;
					break;
				}
			}
		}
		
		return winning;
	}
	
	public void markNumber(Long number) {
		BingoNumber bingoNumber = getBingoNumberMap().get(number);
		if(bingoNumber != null) {
			bingoNumber.setMarked(true);
		}
	}

	public Long getUnmarkedSum() {
		Long sum = 0L;

		for(BingoRow bingoRow : getBingoRows()) {
			sum += bingoRow.getUnmarkedSum();
		}
		
		return sum;
	}

	public List<BingoRow> getBingoRows() {
		if(bingoRows == null) {
			bingoRows = new ArrayList<>();
		}
		return bingoRows;
	}

	public void setBingoRows(List<BingoRow> bingoRows) {
		this.bingoRows = bingoRows;
	}

	public Map<Number, BingoNumber> getBingoNumberMap() {
		if(bingoNumberMap == null) {
			bingoNumberMap = new HashMap<>();
		}
		return bingoNumberMap;
	}

	public void setBingoNumberMap(Map<Number, BingoNumber> bingoNumberMap) {
		this.bingoNumberMap = bingoNumberMap;
	}

}
