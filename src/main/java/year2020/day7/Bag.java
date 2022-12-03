package year2020.day7;

import java.util.ArrayList;
import java.util.List;

public class Bag {

	private String colour;
	private List<Bag> childBags;
	
	public void addChildBag(Bag childBag) {
		getChildBags().add(childBag);
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public List<Bag> getChildBags() {
		if(childBags == null) {
			childBags = new ArrayList<>();
		}
		return childBags;
	}

	public void setChildBags(List<Bag> childBags) {
		this.childBags = childBags;
	}

}
