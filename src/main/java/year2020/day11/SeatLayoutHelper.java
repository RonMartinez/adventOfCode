package year2020.day11;

import java.util.Set;
import java.util.stream.Collectors;

public class SeatLayoutHelper {
	
	public Set<Seat> getSeats(SeatLayout seatLayout) {
		return seatLayout.getSeatRows().stream()
				.flatMap(sr -> sr.getSeats().stream())
				.collect(Collectors.toSet());
	}

}
