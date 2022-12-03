package year2020.day11;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class SeatHelper {
	
	public Set<Seat> getAdjacentSeats(Seat seat) {
		Set<Seat> adjacentSeats = new HashSet<>();
		
		SeatRow seatRow = seat.getSeatRow();
		int currentSeatIndex = seatRow.getSeats().indexOf(seat);
		
		SeatLayout seatLayout = seatRow.getSeatLayout();
		int currentRowIndex = seatLayout.getSeatRows().indexOf(seatRow);
		int priorRowIndex = currentRowIndex - 1;
		int nextRowIndex = currentRowIndex + 1;
		
		adjacentSeats.addAll(findAdjacentSeats(seatLayout, priorRowIndex, currentSeatIndex));
		adjacentSeats.addAll(findAdjacentSeats(seatLayout, currentRowIndex, currentSeatIndex));
		adjacentSeats.addAll(findAdjacentSeats(seatLayout, nextRowIndex, currentSeatIndex));
		
		adjacentSeats.remove(seat);
		
		return adjacentSeats.stream()
				.filter(Seat::isSeat)
				.collect(Collectors.toSet());
	}

	private Set<Seat> findAdjacentSeats(SeatLayout seatLayout, int rowIndex, int currentSeatIndex) {
		Set<Seat> adjacentSeats = new HashSet<>();
		
		if(rowIndex >= 0
				&& rowIndex < seatLayout.getSeatRows().size()
				) {
			int priorSeatIndex = currentSeatIndex - 1;
			int nextSeatIndex = currentSeatIndex + 1;
			
			SeatRow seatRow = seatLayout.getSeatRows().get(rowIndex);
			adjacentSeats.add(findSeat(seatRow, priorSeatIndex));
			adjacentSeats.add(findSeat(seatRow, currentSeatIndex));
			adjacentSeats.add(findSeat(seatRow, nextSeatIndex));
		}
		
		return adjacentSeats.stream().filter(Objects::nonNull).collect(Collectors.toSet());
	}

	private Seat findSeat(SeatRow seatRow, int seatIndex) {
		Seat seat = null;
		if(seatIndex >= 0
				&& seatIndex < seatRow.getSeats().size()
				) {
			seat = seatRow.getSeats().get(seatIndex);
		}
		return seat;
	}

	public Set<Seat> getVisibleAdjacentSeats(Seat seat) {
		Set<Seat> adjacentSeats = new HashSet<>();
		
		SeatRow seatRow = seat.getSeatRow();
		int currentSeatIndex = seatRow.getSeats().indexOf(seat);
		
		SeatLayout seatLayout = seatRow.getSeatLayout();
		int currentRowIndex = seatLayout.getSeatRows().indexOf(seatRow);

		Direction upLeft = new Direction(-1, -1);
		Direction up = new Direction(-1, 0);
		Direction upRight = new Direction(-1, 1);
		Direction left = new Direction(0, -1);
		Direction right = new Direction(0, 1);
		Direction downLeft = new Direction(1, -1);
		Direction down = new Direction(1, 0);
		Direction downRight = new Direction(1, 1);
		
		adjacentSeats.add(findSeat(seatLayout, currentRowIndex, currentSeatIndex, upLeft));
		adjacentSeats.add(findSeat(seatLayout, currentRowIndex, currentSeatIndex, up));
		adjacentSeats.add(findSeat(seatLayout, currentRowIndex, currentSeatIndex, upRight));
		adjacentSeats.add(findSeat(seatLayout, currentRowIndex, currentSeatIndex, left));
		adjacentSeats.add(findSeat(seatLayout, currentRowIndex, currentSeatIndex, right));
		adjacentSeats.add(findSeat(seatLayout, currentRowIndex, currentSeatIndex, downLeft));
		adjacentSeats.add(findSeat(seatLayout, currentRowIndex, currentSeatIndex, down));
		adjacentSeats.add(findSeat(seatLayout, currentRowIndex, currentSeatIndex, downRight));
		
		return adjacentSeats.stream()
				.filter(Objects::nonNull)
				.filter(Seat::isSeat)
				.collect(Collectors.toSet());
	}

	public Seat findSeat(SeatLayout seatLayout, int currentRowIndex, int currentSeatIndex, Direction direction) {
		int rowIndex = currentRowIndex + direction.getyChange();
		int seatIndex = currentSeatIndex + direction.getxChange();
		
		Seat seat = null;
		
		if(rowIndex >= 0
				&& rowIndex < seatLayout.getSeatRows().size()
				) {
			SeatRow seatRow = seatLayout.getSeatRows().get(rowIndex);
			
			if(seatIndex >= 0
					&& seatIndex < seatRow.getSeats().size()
					) {
				
				Seat seatCandidate = seatRow.getSeats().get(seatIndex);
				if(seatCandidate.isSeat()) {
					seat = seatCandidate;
				} else {
					seat = findSeat(seatLayout, rowIndex, seatIndex, direction);
				}
				
			}
		}
		
		return seat;
	}
	
}
