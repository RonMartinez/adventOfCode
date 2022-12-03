package year2020.day20;

import java.util.ArrayList;
import java.util.List;

public class Row {
	
	private List<String> columns = new ArrayList<>();

	public String print() {
		StringBuilder sb = new StringBuilder();
		for(String column : columns) {
			sb.append(column);
		}
		return sb.toString();
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

}
