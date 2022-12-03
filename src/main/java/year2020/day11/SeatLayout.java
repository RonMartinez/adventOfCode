package year2020.day11;

import java.util.ArrayList;
import java.util.List;

public class SeatLayout {
	
	private List<SeatRow> seatRows;

	public void addSeatRow(SeatRow seatRow) {
		seatRow.setSeatLayout(this);
		getSeatRows().add(seatRow);
	}

	public List<SeatRow> getSeatRows() {
		if(seatRows == null) {
			seatRows = new ArrayList<>();
		}
		return seatRows;
	}

	public void setSeatRows(List<SeatRow> seatRows) {
		this.seatRows = seatRows;
	}

}
