package year2020.day12;

import java.util.Arrays;
import java.util.List;

public class Action {
	
	public static final Action MOVE_NORTH = new Action("N");
	public static final Action MOVE_EAST = new Action("E");
	public static final Action MOVE_SOUTH = new Action("S");
	public static final Action MOVE_WEST = new Action("W");
	public static final Action TURN_LEFT = new Action("L");
	public static final Action TURN_RIGHT = new Action("R");
	public static final Action MOVE_FORWARD = new Action("F");
	
	public static final List<Action> ACTIONS = Arrays.asList(
			MOVE_NORTH,
			MOVE_EAST,
			MOVE_SOUTH,
			MOVE_WEST,
			TURN_LEFT,
			TURN_RIGHT,
			MOVE_FORWARD
			);

	private String value;

	public Action(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Action other = (Action) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
