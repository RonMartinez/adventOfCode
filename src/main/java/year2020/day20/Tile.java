package year2020.day20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Tile {

	private long id;
	private List<Row> rows = new ArrayList<>();

	public Tile getSubTile(int x, int y, int xSize, int ySize) {
		if(y + ySize >= rows.size()
				|| x + xSize >= rows.get(0).getColumns().size()
				) {
			return null;
		}
		
		Tile tile = new Tile();
		for(int j = y; j < y+ySize; j++) {
			Row subRow = new Row();
			for(int i = x; i < x+xSize; i++) {
				subRow.getColumns().add(rows.get(j).getColumns().get(i));
			}
			tile.getRows().add(subRow);
		}
		return tile;
	}

	public void removeBorders() {
		rows.remove(rows.size()-1);
		rows.remove(0);
		
		for(Row row : rows) {
			row.getColumns().remove(0);
			row.getColumns().remove(row.getColumns().size()-1);
		}
	}

	public String print() {
		StringBuilder sb = new StringBuilder();
		for(Row row : rows) {
			sb.append(row.print());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void rotateRight() {
		List<Row> newRows = new ArrayList<>();
		for(int i = 0; i < rows.size(); i++) {
			Row newRow = new Row();
			for(int j = rows.size() - 1; j >= 0; j--) {
				Row row = rows.get(j);
				newRow.getColumns().add(row.getColumns().get(i));
			}
			newRows.add(newRow);
		}
		this.rows = newRows;
	}

	public void flip() {
		for(Row row : rows) {
			Collections.reverse(row.getColumns());
		}
	}

	public List<List<String>> getBorders() {
		return Arrays.asList(
				getTopBorder(),
				getRightBorder(),
				getBottomBorder(),
				getLeftBorder(),
				getReverseTopBorder(),
				getReverseRightBorder(),
				getReverseBottomBorder(),
				getReverseLeftBorder()
				);
	}

	public List<List<String>> getActualBorders() {
		return Arrays.asList(
				getTopBorder(),
				getRightBorder(),
				getBottomBorder(),
				getLeftBorder()
				);
	}

	public List<String> getTopBorder() {
		return rows.get(0).getColumns().stream().collect(Collectors.toList());
	}

	public List<String> getRightBorder() {
		List<String> result = new ArrayList<>();
		for(Row row : rows) {
			List<String> columns = row.getColumns();
			result.add(columns.get(columns.size() - 1));
		}
		return result;
	}

	public List<String> getBottomBorder() {
		return rows.get(rows.size() - 1).getColumns().stream().collect(Collectors.toList());
	}

	public List<String> getLeftBorder() {
		List<String> result = new ArrayList<>();
		for(Row row : rows) {
			List<String> columns = row.getColumns();
			result.add(columns.get(0));
		}
		return result;
	}

	public List<String> getReverseTopBorder() {
		List<String> result = getTopBorder();
		Collections.reverse(result);
		return result;
	}

	public List<String> getReverseRightBorder() {
		List<String> result = getRightBorder();
		Collections.reverse(result);
		return result;
	}

	public List<String> getReverseBottomBorder() {
		List<String> result = getBottomBorder();
		Collections.reverse(result);
		return result;
	}

	public List<String> getReverseLeftBorder() {
		List<String> result = getLeftBorder();
		Collections.reverse(result);
		return result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

}
