package year2020.day11;

public class Seat {
	
	public static final char FLOOR = '.';
	public static final char EMPTY = 'L';
	public static final char OCCUPIED = '#';
	
	private char character;
	private SeatRow seatRow;

	public boolean isOccupied() {
		return OCCUPIED == getCharacter();
	}

	public boolean isSeat() {
		return OCCUPIED == getCharacter()
				|| EMPTY == getCharacter();
	}

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

	public SeatRow getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(SeatRow seatRow) {
		this.seatRow = seatRow;
	}
	

}
