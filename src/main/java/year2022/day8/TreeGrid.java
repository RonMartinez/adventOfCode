package year2022.day8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeGrid {
	
	private List<TreeRow> treeRows;
	
	public List<Tree> getColumnTrees(int index) {
		return treeRows.stream()
				.map(tr -> tr.getTrees().get(index))
				.collect(Collectors.toList());
	}
	
	public void addTreeRow(TreeRow treeRow) {
		treeRow.setTreeGrid(this);
		getTreeRows().add(treeRow);
	}

	public List<TreeRow> getTreeRows() {
		if(treeRows == null) {
			treeRows = new ArrayList<>();
		}
		return treeRows;
	}

	public void setTreeRows(List<TreeRow> treeRows) {
		this.treeRows = treeRows;
	}

}
