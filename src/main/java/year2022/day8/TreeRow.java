package year2022.day8;

import java.util.ArrayList;
import java.util.List;

public class TreeRow {
	
	private List<Tree> trees;
	private TreeGrid treeGrid;
	
	public void addTree(Tree tree) {
		tree.setTreeRow(this);
		getTrees().add(tree);
	}

	public List<Tree> getTrees() {
		if(trees == null) {
			trees = new ArrayList<>();
		}
		return trees;
	}

	public void setTrees(List<Tree> trees) {
		this.trees = trees;
	}

	public TreeGrid getTreeGrid() {
		return treeGrid;
	}

	public void setTreeGrid(TreeGrid treeGrid) {
		this.treeGrid = treeGrid;
	}

}
