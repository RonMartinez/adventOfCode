package year2020.day3;

import java.util.List;

public class Forest {

	private List<ForestRow> forestRows;

	public Forest(List<ForestRow> forestRows) {
		super();
		this.forestRows = forestRows;
	}

	public List<ForestRow> getForestRows() {
		return forestRows;
	}

	public void setForestRows(List<ForestRow> forestRows) {
		this.forestRows = forestRows;
	}
}
