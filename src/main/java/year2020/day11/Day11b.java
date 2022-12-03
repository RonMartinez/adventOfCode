package year2020.day11;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day11b {
	
//	private static final String FILENAME = "src/main/resources/2020/day11InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day11Input.txt";
	
	public static final long EMPTY_TO_OCCUPIED_THRESHOLD = 0;
	public static final long OCCUPIED_TO_EMPTY_THRESHOLD = 5;
	
	public static final SeatHelper seatHelper = new SeatHelper();
	public static final SeatLayoutHelper seatLayoutHelper = new SeatLayoutHelper();
	
	public static void main(String[] args) throws IOException {
		SeatLayout seatLayout = createSeatLayout();
		
		printSeatLayout(seatLayout);
		
		Set<Seat> seatsToChange = new HashSet<>();
		
		seatsToChange = findSeatsToChange(seatLayout);
		
		while( ! seatsToChange.isEmpty()) {
			seatsToChange.forEach(Day11b::processSeatChange);
			printSeatLayout(seatLayout);
			seatsToChange = findSeatsToChange(seatLayout);
		}
		
		Set<Seat> occupiedSeats = seatLayoutHelper.getSeats(seatLayout).stream()
				.filter(Seat::isOccupied)
				.collect(Collectors.toSet());
		
		System.out.print(occupiedSeats.size());
	}

	private static Set<Seat> findSeatsToChange(SeatLayout seatLayout) {
		return seatLayoutHelper.getSeats(seatLayout).stream()
				.filter(Day11b::shouldSeatChange)
				.collect(Collectors.toSet());
	}

	private static void printSeatLayout(SeatLayout seatLayout) {
		for(SeatRow seatRow : seatLayout.getSeatRows()) {
			for(Seat seat : seatRow.getSeats()) {
				System.out.print(seat.getCharacter());	
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static void processSeatChange(Seat seat) {
		if(Seat.EMPTY == seat.getCharacter()) {
			seat.setCharacter(Seat.OCCUPIED);
		} else if(Seat.OCCUPIED == seat.getCharacter()) {
			seat.setCharacter(Seat.EMPTY);
		}
	}

	private static boolean shouldSeatChange(Seat seat) {
		boolean shouldSeatChange = false;
		
		Set<Seat> adjacentSeats = seatHelper.getVisibleAdjacentSeats(seat);
		if(Seat.EMPTY == seat.getCharacter()) {
			shouldSeatChange = shouldEmptyChange(seat, adjacentSeats);
		} else if(Seat.OCCUPIED == seat.getCharacter()) {
			shouldSeatChange = shouldOccupiedChange(seat, adjacentSeats);
		}
		
		return shouldSeatChange; 
	}
	
	private static boolean shouldEmptyChange(Seat seat, Set<Seat> adjacentSeats) {
		Set<Seat> occupiedAdjacentSeats = adjacentSeats.stream()
				.filter(Seat::isOccupied)
				.collect(Collectors.toSet());
		return occupiedAdjacentSeats.size() <= EMPTY_TO_OCCUPIED_THRESHOLD;
	}

	private static boolean shouldOccupiedChange(Seat seat, Set<Seat> adjacentSeats) {
		Set<Seat> occupiedAdjacentSeats = adjacentSeats.stream()
				.filter(Seat::isOccupied)
				.collect(Collectors.toSet());
		return occupiedAdjacentSeats.size() >= OCCUPIED_TO_EMPTY_THRESHOLD;
	}

	private static SeatLayout createSeatLayout() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		SeatLayout seatLayout = new SeatLayout();
		lines.forEach(l -> seatLayout.addSeatRow(createSeatRow(l)));
		
		return seatLayout;
	}

	private static SeatRow createSeatRow(String line) {
		SeatRow seatRow = new SeatRow();
		for(char character: line.toCharArray()) {
			seatRow.addSeat(createSeat(character));
		}
		return seatRow;
	}

	private static Seat createSeat(char character) {
		Seat seat = new Seat();
		seat.setCharacter(character);
		return seat;
	}

}
