package year2022.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NodeGrid {
	
	private List<NodeRow> nodeRows;

	public List<List<Node>> getNodeColumns() {
		List<List<Node>> nodeColumns = new ArrayList<>();
		for(int i = 0; i < nodeRows.iterator().next().getNodes().size(); i++) {
			List<Node> nodes = new ArrayList<>();
			for(int j = 0; j < nodeRows.size(); j++) {
				nodes.add(nodeRows.get(j).getNodes().get(i));
			}
			nodeColumns.add(nodes);
		}
		return nodeColumns;
	}

	public List<Node> getNodes() {
		return getNodeRows().stream()
				.flatMap(tr -> tr.getNodes().stream())
				.collect(Collectors.toList());
	}
	
	public void addNodeRow(NodeRow nodeRow) {
		nodeRow.setNodeGrid(this);
		getNodeRows().add(nodeRow);
	}

	public List<NodeRow> getNodeRows() {
		if(nodeRows == null) {
			nodeRows = new ArrayList<>();
		}
		return nodeRows;
	}

	public void setNodeRows(List<NodeRow> nodeRows) {
		this.nodeRows = nodeRows;
	}

}
