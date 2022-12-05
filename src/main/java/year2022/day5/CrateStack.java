package year2022.day5;

import java.util.ArrayList;
import java.util.List;

public class CrateStack {

	private List<String> crates;

	public void addCrate(String crate) {
		getCrates().add(crate);
	}

	public List<String> getCrates() {
		if(crates == null) {
			crates = new ArrayList<>();
		}
		return crates;
	}

	public void setCrates(List<String> crates) {
		this.crates = crates;
	}

}
