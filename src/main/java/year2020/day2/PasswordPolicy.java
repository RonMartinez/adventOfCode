package year2020.day2;

public class PasswordPolicy {
	
	private int minimum;
	private int maximum;
	private char character;

	public PasswordPolicy(int minimum, int maximum, char character) {
		this.minimum = minimum;
		this.maximum = maximum;
		this.character = character;
	}
	
	/* Getters / Setters */

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}
	
}
