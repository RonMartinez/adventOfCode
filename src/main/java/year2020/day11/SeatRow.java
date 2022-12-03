package year2020.day11;

import java.util.ArrayList;
import java.util.List;

public class SeatRow {
	
	private List<Seat> seats;
	private SeatLayout seatLayout;

	public void addSeat(Seat seat) {
		seat.setSeatRow(this);
		getSeats().add(seat);
	}

	public List<Seat> getSeats() {
		if(seats == null) {
			seats = new ArrayList<>();
		}
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public SeatLayout getSeatLayout() {
		return seatLayout;
	}

	public void setSeatLayout(SeatLayout seatLayout) {
		this.seatLayout = seatLayout;
	}

}
